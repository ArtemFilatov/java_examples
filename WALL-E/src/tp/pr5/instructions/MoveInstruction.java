package tp.pr5.instructions;

import tp.pr5.City;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * Its execution moves the robot from one place to the next one in the current direction. This instruction works if the user writes MOVE or MOVER
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class MoveInstruction implements Instruction {

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");	
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private final String MoveInstructionHelp = "MOVE or MOVER";
	
	/**
	 * Default constructor
	 */
	public MoveInstruction()
	{
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	/**
	 * Set the execution context. The method receives the entire engine (engine, navigation and the robot container) even though the actual implementation of execute() may not require it.
	 * @param engine - The robot engine
	 * @param navigation - The information about the game, i.e., the places, current direction and current heading to navigate
	 * @param robotContainer - The inventory of the robot
	 */
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) 
	{
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}

	/**
	 * Moves from the current place to the next place on the current Direction. An opened street must exist between both places to be moved
	 * @throws InstructionExecutionException - When the robot cannot go to other place (there is a wall, a closed street...)
	 */
	public void execute() throws InstructionExecutionException  // Debug
	{
		boolean enc = false, cerrada = true;
		int i = 0;
		City c = this.navigation.getCurrentCity();
		Street[] streets = c.getCityMap();
		while (i < streets.length && !enc)
		{
			if (streets[i].isOpen() && streets[i].comeOutFrom(this.navigation.getCurrentPlace(),this.navigation.getCurrentHeading()))
			{
				enc = true;
				cerrada = false;

			}
			else if (!streets[i].isOpen() && streets[i].comeOutFrom(this.navigation.getCurrentPlace(),this.navigation.getCurrentHeading()))
			{
				enc = true;
				cerrada = true;
			}
			else
				i++;
		}
		
		if (enc && !cerrada)
		{
			engine.addFuel(-5);
			engine.saySomething("WALL·E says: Moving in direction " + this.navigation.getCurrentHeading());
			this.navigation.setRobotPlace(streets[i].nextPlace(this.navigation.getCurrentPlace()));
			engine.robotArrivesAtPlace();
			engine.robotUpdate();
			//engine.setLastInstructionMsg("WALL·E says: Moving in direction " + this.navigation.getCurrentHeading()  
			//		+ LINE_SEPARATOR + streets[i].nextPlace(this.navigation.getCurrentPlace()).toString() + LINE_SEPARATOR
			//		+ LINE_SEPARATOR + "      * My power is " + (this.engine.getFuel())
			//		+ LINE_SEPARATOR + "      * My reclycled material is " + this.engine.getRecycledMaterial() + LINE_SEPARATOR);
			//engine.setLastInformationMsg("Robot attributes has been updated: ("+ (this.engine.getFuel()) +"," + this.engine.getRecycledMaterial() + ")");
			 
		}
		else if(enc && cerrada)
		{
			throw new InstructionExecutionException("WALL·E says: Arrggg, there is a street but it is closed!");  //System.out.println("WALL·E says: Arrggg, there is a street but it is closed!");
		}
		else
			throw new InstructionExecutionException("There is not street in that direction.");  //System.out.println("There is not street in that direction.");
	}
	
	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing.
	 * @return the command syntax MOVE|MOVER
	 */
	public String getHelp() 
	{
		return this.MoveInstructionHelp;
	}

	/**
	 * Parses the String returning a MoveInstruction instance or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction Reference to an instance of MoveInstruction
	 * @throw WrongInstructionFormatException - When the String is not MOVE
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 1 && (aux[0].equalsIgnoreCase("MOVE") || aux[0].equalsIgnoreCase("MOVER")))
		{
			return new MoveInstruction();
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
	}

	

}
