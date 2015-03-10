package tp.pr5;
/**
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * PlaceInfo defines a non-modifiable interface over a Place.
 * It is employed by the classes that need to access the information 
 * contained in the place but that cannot modify the place itself.s
 */

public interface PlaceInfo {
	
	/**
	 * Return the place name
	 * @return The place name
	 */
	public java.lang.String getName();
	
	/**
	 * Return the place description
	 * @return The place description
	 */
	public java.lang.String getDescription();

	/**
	 * Is this place the space ship?
	 * @return true if the place represents a space ship
	 */
	public boolean isSpaceship();

	/**
	 * Which kind of place is this one?
	 * @return PlaceType - Place, Bunker or Trench
	 */
	public PlaceType getPlaceType();
}
