package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * The superclass of every type of item. It contains the common information for all the items and it defines the interface that the items must match
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martín Duque
 */
public abstract class Item implements Comparable<Item>{
	
	private String id;
	private String description;
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Builds an item from a given identifier and description
	 * @param id - Item identifier
	 * @param description - Item description
	 */
	public Item(String id, String des)
	{
		this.id = id;
		this.description = des;
	}
	
	/**
	 * Checks if the item can be used. Subclasses must override this method
	 * @return true if the item can be used
	 */
	public abstract boolean canBeUsed();
	
	/**
	 * Try to use the item with a robot in a given place. It returns whether the action was completed. Subclasses must override this method
	 * @param r - The robot that uses the item
	 * @param nav - the navigation module to look for the street
	 * @return
	 */
	public abstract boolean use(RobotEngine r, NavigationModule nav);
	
	
	/**
	 * Return the item identifier
	 * @return The item identifier
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * Generates a String with the Item description
	 */
	public String toString()
	{
		return this.description;
	}
	
	@Override
    public int compareTo(Item otherItem) 
    {
        return  this.id.compareTo(otherItem.id);
    } 
    
	@Override
	public boolean equals(Object otherItem)
	{
		 return this.compareTo((Item) otherItem) == 0;
	}
    

	
}
