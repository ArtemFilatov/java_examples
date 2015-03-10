package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;
/**
 * Shows the game help with all the instructions that the robot can execute. This instruction works if the user writes HELP or AYUDA
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class HelpInstruction implements Instruction{

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");	
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private static final String HelpInstructionHelp = "HELP or AYUDA";
	
	
	/**
	 * Default Constructor
	 */
	public HelpInstruction()
	{
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	
	/**
	 * Configuration of the context for this instruction
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
	 * Prints the help string of every instruction. It delegates to the Interpreter class.
	 * @param 
	 * @throws InstructionExecutionException - if there exist any execution error.
	 */
	public void execute() throws InstructionExecutionException 
	{
		this.engine.requestHelp();
	}

	/**
	 * Help syntax
	 * @return the instruction syntax HELP
	 */
	public String getHelp() 
	{
		return this.HelpInstructionHelp;
		
	}

	/**
	 * Configuration of the context for this instruction
	 * @param cad - Text String to parse
	 * @return Instruction Reference to an instance of HelpInstruction
	 * @throw WrongInstructionFormatException - When the String is not HELP
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 1 && (aux[0].equalsIgnoreCase("HELP") || aux[0].equalsIgnoreCase("AYUDA")))
		{
			return new HelpInstruction();
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
	}
	
}
