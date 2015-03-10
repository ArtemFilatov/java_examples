package tp.pr5;

import java.util.Observable;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

/**
 * This class is in charge of the robot navigation features. It contains the city where the robot looks for garbage, 
 * the current place where the robot is, and the current direction of the robot. It contains methods to handle the different 
 * robot movements and to pick and drop items at the current place.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class NavigationModule extends Observable implements NavigationObserver {

	private City aCity;
	private Place robotPlace;
	private Direction robotDirection;
	
	/**
	 * Navigation module constructor. It needs the city map and the initial place
	 * @param aCity - A city map
	 * @param initialPlace - An initial place for the robot
	 */
	public NavigationModule(City aCity, Place initialPlace)
	{
		this.aCity = aCity;
		this.robotPlace = initialPlace;
		this.robotDirection = Direction.NORTH;
	}
	
	/**
	 * Checks if the robot has arrived at a spaceship
	 * @return true if an only if the current place is the spaceship
	 */
	public boolean atSpaceship()
	{	
		return this.robotPlace.isSpaceship();
	}
	
	/**
	 * Updates the current direction of the robot according to the rotation
	 * @param rotation - left or right
	 */
	public void rotate(Rotation rotation)
	{
		if(rotation== Rotation.LEFT)
		{
			this.robotDirection = this.robotDirection.turnLeft(this.robotDirection);
		}
		else if(rotation== Rotation.RIGHT)
		{
			this.robotDirection= this.robotDirection.turnRight(this.robotDirection);
		}
		
	}
	
	/**
	 * The method tries to move the robot following the current direction. If the movement is not possible because there is no street, or there is a street which is closed, then it throws an exception. Otherwise the current place is updated according to the movement
	 * @throws InstructionExecutionException - An exception with a message about the encountered problem
	 */
	
	public void move() throws InstructionExecutionException
	{
		boolean enc = false, cerrada = true;
		int i = 0;
		City c = this.aCity;
		Street[] streets = c.cityMap;
		while (i < streets.length && !enc)
		{
			if (streets[i].isOpen() && streets[i].comeOutFrom(this.robotPlace,this.robotDirection))
			{
				enc = true;
				cerrada = false;
				this.robotPlace = streets[i].nextPlace(this.robotPlace);
			}
			else if (!streets[i].isOpen() && streets[i].comeOutFrom(this.robotPlace,this.robotDirection))
			{
				enc = true;
				cerrada = true;
			}
			else
				i++;
		}
		
		if (enc && !cerrada)
		{

		}
		else if(enc && cerrada)
		{
			throw new InstructionExecutionException("WALL�E says: Arrggg, there is a street but it is closed!"); //System.out.println("WALL�E says: Arrggg, there is a street but it is closed!");
		}
		else
			throw new InstructionExecutionException("There is not street in that direction."); //System.out.println("There is not street in that direction.");
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Item pickItemFromCurrentPlace(String id)
	{
		return this.robotPlace.pickItem(id);
	}
	
	public void dropItemAtCurrentPlace(Item it)
	{
		this.robotPlace.dropItem(it);
	}
	
	public boolean findItemAtCurrentPlace(String id)
	{
		return this.robotPlace.existItem(id);
	}
	
	public void initHeading(Direction heading)
	{
		this.robotDirection = heading;
	}
	
	/**
	 * Prints the information (description + inventory) of the current place
	 */
	public void scanCurrentPlace()
	{
		System.out.println(this.robotPlace.toString() + this.robotPlace.getInventory().toString());
	}
	
	
	 /**
     * Indicar a los observadores que se ha actualizado el modelo.
     * 
     */
    private void informarObservadores()
    {
        setChanged(); // establece que ha habido un cambio.
        notifyObservers(); // notifica a los observadores.	
    }
	
	
	
	////////////////// GETTERS /////////////////
	public Street getHeadingStreet()
	{
		Street street = this.aCity.lookForStreet(this.robotPlace,  this.robotDirection);
		return street;
	}
	public Direction getCurrentHeading()
	{
		return this.robotDirection;
	}
	
	public Place getCurrentPlace()
	{
		return this.robotPlace;
	}
	
	public City getCurrentCity()
	{
		return this.aCity;
	}
	
	///////////////// SETTERS //////////////////
	public void setRobotPlace(Place robotPlace)
	{
		this.robotPlace = robotPlace;
		this.informarObservadores();
	}
	
	public void setRobotDirection(Direction direction)
	{
		this.robotDirection = direction;
		this.informarObservadores();
	}

	////////////////////////////// NAVIGATION OBSERVER METHODS
	
	@Override
	public void headingChanged(Direction newHeading) {}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {}

}
