package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * An item that represents fuel. This item can be used at least once and it provides power energy to the robot. When the item is used the configured number of times, then it must be removed from the robot inventory
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martín Duque
 */
public class Fuel extends Item {
	
	private int power;
	private int times;

	/**
	 * Fuel constructor
	 * @param id - Item id
	 * @param description - Item description
	 * @param power - The amount of power energy that this item provides the robot
	 * @param times - Number of times the robot can use the item
	 */
	public Fuel(String id, String des, int power, int times) 
	{
		super(id, des);
		this.power = power;
		this.times = times;
	}
	
	/**
	 * Fuel can be used as many times as it was configured
	 * @return true it the item still can be used
	 */
	public boolean canBeUsed() 
	{
		return this.times > 0;
	}
	
	/**
	 * Using the fuel provides energy to the robot (if it can be used)
	 * @param r - robot that is going to use the fuel
	 * @param nav - the navigation module where the fuel is going to be used
	 * @param true if the fuel has been used
	 */
	public boolean use(RobotEngine r, NavigationModule nav) 
	{
		
		boolean use = false;
		if(this.canBeUsed())
		{
			r.addFuel(this.power);
			//r.mostrarEstadisticas();
			this.times--;
			use = true;
			if(this.times == 0)
			{
				r.getItems().getInventory().remove(this);
				if(r.getItems().pickItem(this.getId())!=null)
					System.out.print("WALLÂ·E says: What a pity! I have no more " + r.aMinusculas(this.getId()) + " in my inventory" + LINE_SEPARATOR);
				else
					System.out.print("Item not found");
			}
		}
		
		
		
		return use;
	}
	
	/**
	 * Generates a String with the Item description
	 */
	public String toString() 
	{
		return super.toString()+"// power = " + this.power + ", times = " + this.times;
	}
	

}
