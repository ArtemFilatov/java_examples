package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;

/**
 * A CodeCard can open or close the door placed in the streets. The card contains a code that must match the street code in order to perform the action.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martín Duque
 */
public class CodeCard extends Item {
	
	private String code;
	
	/**
	 * Code card constructor
	 * @param id - Code card name
	 * @param description - Code card description
	 * @param code - Secret code stored in the code card
	 */
	public CodeCard(String id, String des, String code)
	{
		super(id, des);
		this.code = code;
	}
	
	/**
	 * A code card always can be used. Since the robot has the code card it never loses it
	 * @return true because it always can be used
	 */
	public boolean canBeUsed()
	{
		return true;
	}
	
	/**
	 * The method to use a code card. If the robot is in a place which contains a street in the direction he is looking at, then the street is opened or closed if the street code and the card code match.
	 * @param r - the robot engine employed to use the card.
	 * @param nav - the navigation module to look for the street
	 * @return true If the code card can complete the action of opening or closing a street. Otherwise it returns false.
	 */
	public boolean use(RobotEngine r, NavigationModule nav)
	{
		boolean use = false;
		if(this.canBeUsed())
		{
			Street street = nav.getHeadingStreet();
			if(street != null)
			{
				if(street.isOpen())
					use = street.close(this);
				else
					use = street.open(this);
			}
		}
		return use;
	}
	
	/**
	 * Gets the code stored in the code card
	 * @return A String that represents the code
	 */
	public String getCode()
	{
		return this.code;
	}


}
