package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * Its execution request the robot to finish the simulation This Instruction works if the user writes QUIT or SALIR
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */
public class QuitInstruction implements Instruction {

	
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private final String QuitInstructionHelp = "QUIT|SALIR";
	
	
	/**
	 * Default constructor
	 */
	public QuitInstruction()
	{
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
	 * Request the robot to stop the simulation
	 * @throws InstructionExecutionException - if there exist any execution error.
	 */
	public void execute() throws InstructionExecutionException 
	{
		this.engine.comProblems();
		this.engine.requestQuit();
	}
	
	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing.
	 * @return the Instruction syntax QUIT|SALIR
	 */
	public String getHelp() 
	{
		return this.QuitInstructionHelp;
	}

	/**
	 * Parsers the String returning an instance of QuitInstruction or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction reference to an instance of QuitInstruction
	 * @throws WrongInstructionFormatException - When the String is not QUIT | SALIR
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 1 && (aux[0].equalsIgnoreCase("QUIT") || aux[0].equalsIgnoreCase("SALIR")))
		{
			return new QuitInstruction();
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
	}
	

}
