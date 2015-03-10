package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * The garbage is a type of item that generates recycled material after using it. The garbage can be used only once. After using it, it must be removed from the robot inventory
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martín Duque
 */
public class Garbage extends Item{

	private int recycledMaterial;
	
	/**
	 * Garbage constructor
	 * @param id - Item id
	 * @param description - Item description
	 * @param recycledMaterial - The amount of recycled material that the item generates
	 */
	public Garbage(String id, String des, int recycledMaterial)
	{
		super(id,des);
		this.recycledMaterial = recycledMaterial;
	}
	
	/**
	 * Garbage can be used only once
	 * @return true if the item has not been used yet
	 */
	public boolean canBeUsed() 
	{
		return(this.recycledMaterial>0);
	}

	/**
	 * The garbage generates recycled material for the robot that uses it
	 * @param r - the robot engine
	 * @param nav - The navigation module
	 * @return true if the garbage was transformed in recycled material
	 */
	public boolean use(RobotEngine r, NavigationModule nav) 
	{
		boolean use = false;
		if(canBeUsed())
		{
			r.addRecycledMaterial(this.recycledMaterial);
			this.recycledMaterial = 0;
			r.getItems().getInventory().remove(this);
			if(r.getItems().pickItem(this.getId())!=null)
			{
				//r.mostrarEstadisticas();
				System.out.print("WALLÂ·E says: What a pity! I have no more "+this.getId()+" in my inventory" + LINE_SEPARATOR);
			}
			use = true;
		}
		return use;
	}
	
	/**
	 * Generates a String with the Item description
	 */
	public String toString()
	{
		return super.toString()+ LINE_SEPARATOR + this.recycledMaterial;
	}

}
