package tp.pr5;

/**
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * 
 * Interface of the observers that want to be notified about the events related to the navigation module. 
 * Classes that implement this interface wiil be informed when the robot changes its heading, when it 
 * arrives at a place, when the place is modified (because the robot picked or dropped an item) or when 
 * the user requests to use the radar.
 */

public interface NavigationObserver 
{
	/**
	 * Notifies that the robot heading has changed
	 * @param newHeading - New robot heading
	 */
	public void headingChanged(Direction newHeading);
	
	/**
	 * Notifies that the navigation module has been initialized
	 * @param initialPlace - The place where the robot starts the simulation
	 * @param heading - The initial robot heading
	 */
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading);
	
	/**
	 * Notifies that the robot has arrived at a place
	 * @param heading - The robot movement direction
	 * @param place - The place where the robot arrives
	 */
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place);
	
	/**
	 * Notifies that the user requested a RADAR instruction
	 * @param placeDescription - Information with the current place
	 */
	public void placeScanned(PlaceInfo placeDescription);
	
	/**
	 * Notifies that the place where the robot stays has changed (because the robot picked or dropped an item)
	 * @param placeDescription -
	 */
	public void placeHasChanged(PlaceInfo placeDescription);	
}
