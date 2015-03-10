package tp.pr5;

import tp.pr5.items.CodeCard;

/**
 * A street links two places A and B in one direction. If it is defined as Street(A,NORTH,B) 
 * it means that Place B is at NORTH of Place A. Streets are two-way streets, i.e. if B is at NORTH of A then A is at SOUTH of B.
 * Some streets are closed and a code (contained in a code card) is needed to open them
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class Street {
	private Place source; // A
	private Direction direction;
	private Place target; // B
	private boolean isOpen;
	private String code;
	
	/**
	 * Creates an open street and it have not any code to open or close it
	 * @param source - Source place
	 * @param direction - Represents how is placed the target place with respect to the source place.
	 * @param target - Target place
	 */
	public Street(Place source, Direction direction, Place target)
	{
		this.source = source;
		this.direction = direction;
		this.target = target;
		this.isOpen = true;
		this.code = null;
	}
	
	/**
	 * Creates a street that has a code to open and close it
	 * @param source - Source place
	 * @param direction - Represents how is placed the target place with respect to the source place.
	 * @param target - Target place
	 * @param isisOpen - Determines is the street is opened or closed
	 * @param code - The code that opens and closes the street
	 */
	public Street(Place source, Direction direction, Place target, boolean isOpen, String code)
	{
		this.source = source;
		this.direction = direction;
		this.target = target;
		this.isOpen = isOpen;
		this.code = code;
	}
	
	/**
	 * Tries to close a street using a code card. Codes must match in order to complete this action
	 * @param card - A code card to close the street
	 * @return true if the action has been completed
	 */
	public boolean close(CodeCard card)
	{
		if(this.code.equalsIgnoreCase(card.getCode()))
			this.isOpen = false;
		return !isOpen();
	}
	
	public boolean isOpen()
	{
		return this.isOpen;
	}
	
	/**
	 * Tries to open a street using a code card. Codes must match in order to complete this action
	 * @param card - A code card to open the street
	 * @return true if the action has been completed
	 */
	public boolean open(CodeCard card)
	{
		if(!this.isOpen() && this.code.equals(card.getCode())) 
			this.isOpen = true;
		return isOpen();
	}
	
	/**
	 * Checks if the street comes out from a place in a given direction. Remember that streets are two-way
	 * @param place - The place to check
	 * @param whichDirection - Direction used.
	 * @return Returns true if the street comes out from the input Place.
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection)
	{
		boolean valid = true;
		if (this.source == place)
			valid = this.direction == whichDirection;
		else if (this.target == place)
			valid = this.direction == this.direction.opposite(whichDirection);
		else
			valid = false;
		
		return valid;
	}
	
	/**
	 * Returns the place of the other side from the place whereAmI. This method does not consider whether the street is open or closed.
	 * @param whereAmI - The place where I am.
	 * @return It returns the Place at the other side of the street (even if the street is closed). Returns null if whereAmI does not belong to the street.
	 */
	public Place nextPlace(Place whereAmI)
	{
		Place p = null;
		if (this.source == whereAmI)
			p = this.target;
		if (this.target == whereAmI)
			p = this.source;
		
		return p;
	}
	
	
}
