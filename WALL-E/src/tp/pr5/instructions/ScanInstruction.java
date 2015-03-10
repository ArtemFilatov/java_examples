package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * The execution of this instruction shows the information of the inventory of the robot or the complete description about the item with identifier id contained in the inventory This Instruction works if the player writes SCAN or ESCANEAR (id is not mandatory)
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */
public class ScanInstruction implements Instruction {

	private String id;
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private final String ScanInstructionHelp = "SCAN | ESCANEAR [id]";
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");	
	
	/**
	 * Default constructor.
	 */
	public ScanInstruction()
	{
		this.id = null;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	/**
	 * @param id - Item id
	 */
	public ScanInstruction(String id)
	{
		this.id = id;
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
	 * Prints the description of a concrete item or the complete inventory of the robot
	 * @throws InstructionExecutionException - When the robot does not contain the item to be scanned
	 */
	public void execute() throws InstructionExecutionException 
	{
		if(this.robotContainer.numberOfItems()==0)
				throw new InstructionExecutionException("WALL·E says: My inventory is empty" + LINE_SEPARATOR); //System.out.print("WALL·E says: My inventory is empty" + LINE_SEPARATOR);
				
		else if(this.robotContainer.numberOfItems()!= 0)
		{
			if(this.id == null)
			{
				//engine.setLastInstructionMsg("WALL-E says: I am carrying the following items" + LINE_SEPARATOR
					//	+ this.robotContainer.toString() + LINE_SEPARATOR);
				//engine.setLastInstructionMsg(this.robotContainer.toString() + LINE_SEPARATOR);
				engine.inventoryScan("WALL·E says: I am carrying the following items" + LINE_SEPARATOR + this.robotContainer.toString() + LINE_SEPARATOR);

			}
			else if(this.id != null)
			{
				Item item;
				item = this.robotContainer.getItem(this.id);
				if(item != null)
				{
					//engine.setLastInstructionMsg(item.toString() + LINE_SEPARATOR);
					//engine.printRobotMessages(item.toString() + LINE_SEPARATOR);
					engine.itemScan(item.toString() + LINE_SEPARATOR);
				}
				else
					throw new  InstructionExecutionException("I have no susch object"); // No tengo el item.
			}
		}
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing
	 * @return the Instruction syntax of the instruction SCAN | ESCANEAR [id]
	 */
	public String getHelp() 
	{
		return this.ScanInstructionHelp;
	}

	/**
	 * Parses the String returning a ScanInstruction instance or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction Reference to an instance of ScanInstruction
	 * @throws WrongInstructionFormatException - When the String is not SCAN | ESCANEAR [id]
	 * 
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 2 && (aux[0].equalsIgnoreCase("SCAN") || aux[0].equalsIgnoreCase("ESCANEAR")))
			return new ScanInstruction(aux[1]);
		else if(length == 1 && (aux[0].equalsIgnoreCase("SCAN") || aux[0].equalsIgnoreCase("ESCANEAR")))
			return new ScanInstruction();
		else
			throw new WrongInstructionFormatException();
	}

}
