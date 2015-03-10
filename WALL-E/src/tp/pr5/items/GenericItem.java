package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martín Duque
 */

public class GenericItem extends Item {

	public GenericItem(String id, String des) 
	{
		super(id, des);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canBeUsed() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(RobotEngine r, NavigationModule nav) 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
