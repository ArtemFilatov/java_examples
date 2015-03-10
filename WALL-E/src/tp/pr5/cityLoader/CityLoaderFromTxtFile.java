package tp.pr5.cityLoader;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.PlaceType;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;
import tp.pr5.items.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Scanner
/**
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */


public class CityLoaderFromTxtFile {
	
	private Place place;
	private City city;
	private List<Place> places;
	
	
	/**
	 * Creates the different array lists
	 * @param file - The name of the file
	 */
	
	public CityLoaderFromTxtFile()
	{
		this.city = new City();
		this.place = null;
		this.places = new ArrayList<Place>();
	}
	
	/**
	 * 
	 * @param file - The input stream where the city is stored
	 * @return The city
	 * @throws java.io.IOException - When there is some format error in the file (WrongCityFormatException) or some errors in IO operations.
	 */
	public City loadCity(java.io.InputStream file) throws java.io.IOException
	{
		Scanner scanner = new Scanner(file);
		City cityAux;
		String line;
		try
		{
			if(!scanner.hasNext())
				throw new WrongCityFormatException();
			line = scanner.nextLine();
			if(line.equalsIgnoreCase("BeginCity"))
			{
				loacPlaces(scanner);
				this.city.setCityMap(this.loadStreet(scanner));
				loadObjects(scanner);
			}
			else 
				throw new WrongCityFormatException("Error reading the map file");
			if(!scanner.hasNext())
				throw new WrongCityFormatException();
			line = scanner.nextLine();
			if (!line.equalsIgnoreCase("EndCity"))
				throw new WrongCityFormatException("Error reading the map file");
			cityAux = this.city;
			
		} 
		catch (IOException e) 
		{
			throw new WrongCityFormatException(e.getMessage());
		}

		this.place = this.places.get(0);
		return cityAux;
	}
	


	/**
	 * Returns the place where the robot will start the simulation
	 * @param
	 * @return The initial place
	 */
	public Place getInitialPlace()
	{
		return this.place;
	}
	
	//////////////////////////////// Unofficial methods ////////////////////////////////
	
	/**
	 * Load the places from the file.
	 * @param scanner - scanner variable pointing the file.
	 * @return Places list parsed from the file.
	 * @throws WrongCityFormatException 
	 */

	private void loacPlaces(Scanner scanner) throws WrongCityFormatException 
	{
		String line;
		if(scanner.hasNext())
		{
			line = scanner.nextLine();
			if(line.equalsIgnoreCase("BeginPlaces"))
			{
				if(!scanner.hasNext())
					throw new WrongCityFormatException();
				line = scanner.nextLine();
				int numPlaces = 0;
				while(!line.equalsIgnoreCase("EndPlaces"))
				{
					Place auxPlace = readPlace(line, numPlaces);
					numPlaces++;
					this.places.add(auxPlace);
					if(!scanner.hasNext())
						throw new WrongCityFormatException();
					line = scanner.nextLine();
				}
			}
		}
		else
			throw new WrongCityFormatException();
	}

	/**
	 * This method reads one Place. If wrong input throws new wrongcityformatexception.
	 * @param line
	 * @param i
	 * @return place -
	 * @throws WrongCityFormatException
	 */
	private Place readPlace(String line, int i) throws WrongCityFormatException 
	{
		boolean spaceShip = false;
		Place auxPlace = null;
		String data[] = line.split(" ");
		if(data.length != 5)
			throw new WrongCityFormatException();
		if(data[0].equalsIgnoreCase("place") && data[1].equalsIgnoreCase(String.valueOf(i)))
		{
			if(data[4].equalsIgnoreCase("noSpaceShip"))
				spaceShip = false;
			else if(data[4].equalsIgnoreCase("spaceShip"))
				spaceShip = true;
			else throw new WrongCityFormatException();
			auxPlace = new Place(data[2].replace("_", " "), spaceShip, data[3].replace("_", " "));
		}
		else if(data[0].equalsIgnoreCase("bunker") && data[1].equalsIgnoreCase(String.valueOf(i)))
		{
			if(data[4].equalsIgnoreCase("noSpaceShip"))
				spaceShip = false;
			else throw new WrongCityFormatException();
			auxPlace = new Place(data[2].replace("_", " "), spaceShip, data[3].replace("_", " "), PlaceType.BUNKER);
		}
		else if(data[0].equalsIgnoreCase("trench") && data[1].equalsIgnoreCase(String.valueOf(i)))
		{
			if(data[4].equalsIgnoreCase("noSpaceShip"))
				spaceShip = false;
			else throw new WrongCityFormatException();
			auxPlace = new Place(data[2].replace("_", " "), spaceShip, data[3].replace("_", " "), PlaceType.TRENCH);
		}
		else
			throw new WrongCityFormatException();
		return auxPlace;
	}
	
	/**
	 * Load the streets from the file
	 * @param scanner - scanner variable pointing the file.
	 * @param places - City places.
	 * @return Streets list parsed from the file.
	 */
	private Street[] loadStreet(Scanner scanner) throws WrongCityFormatException
	{

		List<Street> auxStreets = new ArrayList<Street>();
		Street street = null;
		String line;
		if(!scanner.hasNext())
			throw new WrongCityFormatException();
		line = scanner.nextLine();
		if(line.equalsIgnoreCase("BeginStreets"))
		{
			if(!scanner.hasNext())
				throw new WrongCityFormatException();
			line = scanner.nextLine();
			int numStreets = 0;
			while(!line.equalsIgnoreCase("EndStreets"))
			{
				Street auxStreet = readStreet(line, numStreets);
				auxStreets.add(auxStreet);
				numStreets++;
				if(!scanner.hasNext())
					throw new WrongCityFormatException();
				line = scanner.nextLine();
			}
					
		}
		else 
			throw new WrongCityFormatException();
		Street[] streets = new Street[auxStreets.size()];
		auxStreets.toArray(streets);
		return streets;
	}

	/**
	 * This method reads one Street. If wrong input throws new wrongcityformatexception
	 * @param line
	 * @param numStreets
	 * @return
	 * @throws WrongCityFormatException
	 */
	private Street readStreet(String line, int numStreets) throws WrongCityFormatException 
	{
		String[] data = line.split(" ");
		Street street = null;
		if(data.length != 8 && data.length != 9)
			throw new WrongCityFormatException();
		else
		{
			if(data[0].equalsIgnoreCase("street") 
					&& data[2].equalsIgnoreCase("place")
					&& data[5].equalsIgnoreCase("place")
					&& data[1].equalsIgnoreCase(String.valueOf(numStreets))
					&& Integer.parseInt(data[3])<=places.size()
					&& Integer.parseInt(data[6])<= places.size())
			{
				String code = null;
				boolean open;
				if(data[7].equalsIgnoreCase("open"))
				{
					open = true;
					if(data.length == 9)
						throw new WrongCityFormatException();
				}
				else if(data[7].equalsIgnoreCase("closed"))
				{
					open = false;
					if(data.length == 8)
						throw new WrongCityFormatException();
					else
						code = data[8];
				}
				else
					throw new WrongCityFormatException();
									
				Direction direction = Direction.UNKNOWN;
				direction = direction.parseDirection(data[4]);
				if(direction == Direction.UNKNOWN)
					throw new WrongCityFormatException();
				if(open)
					street = new Street(places.get(Integer.parseInt(data[3])),direction, places.get(Integer.parseInt(data[6])));
				else
					street = new Street(places.get(Integer.parseInt(data[3])), direction, places.get(Integer.parseInt(data[6])), open, code);
			}
			else
				throw new WrongCityFormatException();
		}
		return street;
	}

	/**
	 * Load the objects from the file
	 * @param scanner - scanner variable pointing the file
	 * @param places - City places
	 * @throws java.io.IOException
	 */
	private void loadObjects(Scanner scanner)	throws WrongCityFormatException 
	{
		String line;
		if(!scanner.hasNext())
			throw new WrongCityFormatException();
		line= scanner.nextLine();
		if (line.equalsIgnoreCase("BeginItems")) 
		{
			if(!scanner.hasNext())
				throw new WrongCityFormatException();
			line = scanner.nextLine();
			int numObjects = 0;
		    while (!line.equalsIgnoreCase("EndItems")) 
		    {
				readObject(line,numObjects);
				numObjects++;
				if(!scanner.hasNext())
					throw new WrongCityFormatException();
				line = scanner.nextLine();
			}
		}
	}

	/**
	 * This method reads one Objects. If wrong input throws new wrongcityformatexception.
	 * @param line
	 * @param numObjects
	 * @throws WrongCityFormatException
	 */
	private void readObject(String line, int numObjects) throws WrongCityFormatException 
	{
		Item it;
		int nPlace;
		String[] data = line.split(" ");
		if (data.length != 8 && data.length != 7)
			throw new WrongCityFormatException();
		else 
		{
			if (data[1].equalsIgnoreCase(String.valueOf(numObjects))) 
			{

				switch (data[0]) 
				{
					case "fuel": 
					{
						if (data.length != 8
								|| !data[6].equalsIgnoreCase("place") || 
								Integer.parseInt(data[7])>=places.size())
							throw new WrongCityFormatException();
						else 
						{
							it = new Fuel(data[2], data[3].replace("_",
									" "), Integer.parseInt(data[4]),
									Integer.parseInt(data[4]));
							nPlace = Integer.parseInt(data[7]);
						}
					}
					break;
					case "codecard": 
					{
						if (data.length != 7
									|| !data[5].equalsIgnoreCase("place") ||
									Integer.parseInt(data[6])>=places.size())
							throw new WrongCityFormatException();
						else 
						{
							it = new CodeCard(data[2].replace("_", " "),
									data[3].replace("_", " "),
									data[4].replace("_", " "));
							nPlace = Integer.parseInt(data[6]);
						}
					}
					break;
					case "garbage": 
					{
						if (data.length != 7
								|| !data[5].equalsIgnoreCase("place")
								||Integer.parseInt(data[6])>=places.size())
							throw new WrongCityFormatException();
						else 
						{
							it = new Garbage(data[2].replace("_", " "),
									data[3].replace("_", " "),
									Integer.parseInt(data[4]));
							nPlace = Integer.parseInt(data[6]);
						}
					}
					break;
					default:
						throw new WrongCityFormatException();
				}
				if (!places.get(nPlace).addItem(it))
					throw new WrongCityFormatException("You cannot add items to this place.");
			} 
			else
				throw new WrongCityFormatException();
		}
		
	}

}
