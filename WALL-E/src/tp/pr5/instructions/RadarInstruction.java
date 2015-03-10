package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * This Instruction shows the description of the current place and the items in it. This Instruction works if the user writes RADAR
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */
public class RadarInstruction implements Instruction {

	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private final String RadarInstructionHelp = "RADAR";
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");	
	
	/**
	 * Default constructor
	 */
	public RadarInstruction()
	{
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	/**
	 * Configure the context
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
	 * Shows the current place description
	 * @throws InstructionExecutionException - if there exist any execution error.
	 */
	public void execute() throws InstructionExecutionException 
	{
		if(this.navigation.getCurrentPlace().getInventory().numberOfItems() == 0)
		{
			throw new InstructionExecutionException("WALL·E says: The place is empty" + LINE_SEPARATOR); 
		}
		else
		{
			engine.placeScanned(this.navigation.getCurrentPlace().getDescription() + LINE_SEPARATOR + this.navigation.getCurrentPlace().getInventory().toString());
			//engine.setLastInstructionMsg(this.navigation.getCurrentPlace().toString() + LINE_SEPARATOR);
			//engine.printRobotMessages(this.navigation.getCurrentPlace().toString() + LINE_SEPARATOR);
		}
		
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing.
	 * @return the Instruction syntax RADAR
	 */
	public String getHelp() 
	{
		return this.RadarInstructionHelp;
	}

	/**
	 * Parses the String returning an instance of RadarInstruction or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction Reference to an instance of RadarInstruction
	 * @throws WrongInstructionFormatException - When the String is not RADAR
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 1 && (aux[0].equalsIgnoreCase("RADAR") ))
		{
			return new RadarInstruction();
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
	}

}
