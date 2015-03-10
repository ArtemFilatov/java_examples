package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;
/**
 * Its execution rotates the robot This Instruction works if the robot writes TURN LEFT or RIGHT or GIRAR LEFT or RIGHT
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class TurnInstruction implements Instruction {
	  
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private Rotation turnRotation;
	private static final String TurnInstructionHelp = "TURN | GIRAR < LEFT|RIGHT >";
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  
	/**
	* Default constructor
	*/
	public TurnInstruction()
  	{
		this.turnRotation = Rotation.UNKNOWN;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
  	}
  
	/**
	 * @param turnDirection - Turn Rotation.
	 */
	public TurnInstruction(Rotation turnRotation)
	{
		this.turnRotation = turnRotation;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
  
  
	/**
	 * Set the execution context. The method receives the entire engine (engine, navigation and the robot container) even though the actual implementation of execute() may not require it.
	 * @param engine - The robot engine
	 * @param navigation - The information about the game, i.e., the places, current direction and current heading to navigate
	 * @param robotContainer - The inventory of the robot
	 */
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer)
	{
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}

	/**
	 * Turns the robot left or right
	 * @throws InstructionExecutionException - When the rotation is unknown
	 */
	public void execute() throws InstructionExecutionException
	{
		if(this.turnRotation == Rotation.UNKNOWN)
			throw new InstructionExecutionException();
		else if(this.turnRotation == Rotation.RIGHT)
			this.navigation.setRobotDirection(this.navigation.getCurrentHeading().turnRight(this.navigation.getCurrentHeading()));
		else if(this.turnRotation == Rotation.LEFT)
			this.navigation.setRobotDirection(this.navigation.getCurrentHeading().turnLeft(this.navigation.getCurrentHeading()));	
		else
			throw new InstructionExecutionException();
		this.engine.addFuel(-5);
		this.engine.headingUpdate(this.navigation.getCurrentHeading());
		this.engine.robotUpdate();
		
//		engine.setLastInstructionMsg("WALL-E is looking at direction " + this.navigation.getCurrentHeading() +
//				LINE_SEPARATOR + "      * My power is " + this.engine.getFuel() +
//				LINE_SEPARATOR + "      * My reclycled material is " + this.engine.getRecycledMaterial() + LINE_SEPARATOR);
//		engine.setLastInformationMsg("Robot attributes has been updated: ("+ this.engine.getFuel() +"," + this.engine.getRecycledMaterial() + ")");
		
	}
	
	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing.
	 * @return the command syntax TURN | GIRAR < LEFT|RIGHT >
  	 */
	public String getHelp() 
	{
		return this.TurnInstructionHelp;
	}
	
	/**
	 * Parses the String returning a TurnInstruction instance or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction Reference pointing to an instance of a Instruction subclass, if it is corresponding to the String cad
	 * @throws WrongInstructionFormatException - When the String is not TURN LEFT or RIGHT or GIRAR LEFT or RIGHT
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 2 && (aux[0].equalsIgnoreCase("TURN") || aux[0].equalsIgnoreCase("GIRAR")))
		{
			if(aux[1].equalsIgnoreCase("RIGHT"))
					return new TurnInstruction(Rotation.RIGHT);
			else if(aux[1].equalsIgnoreCase("LEFT"))
					return new TurnInstruction(Rotation.LEFT);
			else
				throw new WrongInstructionFormatException();
		}
		else if(length == 1 && (aux[0].equalsIgnoreCase("TURN") || aux[0].equalsIgnoreCase("GIRAR")))
		{
			throw new WrongInstructionFormatException();
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
		

		
	}

}
