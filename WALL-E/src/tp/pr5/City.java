package tp.pr5;

/**
 * This class represents the city where the robot is wandering. It contains information about the streets and the places in the city
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */
public class City {

	protected Street[] cityMap;
	
	/**
	 * Default constructor. Needed for testing purposes
	 */
	public City()
	{
		this.cityMap = null;
	}
	
	/**
	 * Creates a city using an array of streets
	 * @param cityMap
	 */
	public City(Street[] cityMap)
	{
		this.cityMap = cityMap;
	}
	
	/**
	 * Looks for the street that starts from the given place in the given direction.
	 * @param - The place where to look for the street
	 * @param - The direction to look for the street
	 * @return
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading)
	{
		Street street = null;
		if(this.cityMap != null)
		{
			int i = 0;
			boolean enc = false;
			while(!enc && i < cityMap.length)
			{
				street = cityMap[i];
				if (street.comeOutFrom(currentPlace, currentHeading))
					enc = true;
				else
					i++;
			}
			if(enc)
				return street;
			else
				return null;
		}
		else
			return null;
	}
	
	/**
	 * Adds an street to the city
	 * @param street
	 */
	public void addStreet(Street street)
	{
		this.cityMap[this.cityMap.length] = street;
	}
	
	////////////////////////////////////////// Unofficial Methods /////////////////////////////////////////////////////
	/**
	 * CityMap Getter.
	 * @return CityMap
	 */
	public Street[] getCityMap()
	{
		return this.cityMap;
	}
	
	/**
	 * CityMap Setter
	 * @param cityMap
	 */
	public void setCityMap(Street[] cityMap)
	{
		this.cityMap = cityMap;
	}
	
}
