package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * The Instruction for using an item. This Instruction works if the user writes OPERATE id or OPERAR id
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class OperateInstruction implements Instruction{
	
	private String id;
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private final String OperateInstructionHelp = "OPERATE|OPERAR <ID>";
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Default constructor.
	 */
	public OperateInstruction()
	{
		this.id = null;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	/**
	 * @param id - Item id
	 */
	public OperateInstruction(String id)
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
		this.engine.setItems(robotContainer);
		this.navigation = navigation;
		this.robotContainer = robotContainer;
		
	}

	/**
	 * The robot uses the requested item.
	 * @throws InstructionExecutionException - When the robot inventory does not contain an item with this name or when the item cannot be used
	 */
	public void execute() throws InstructionExecutionException 
	{
		Item item;
		item = this.robotContainer.getItem(this.id);
		if(item != null)
		{
			if(!item.use(this.engine, this.navigation))
			{
				throw new InstructionExecutionException("WALL·E says: I have problems using the object "+ this.robotContainer.getItem(this.id).getId() + LINE_SEPARATOR); //System.out.print("WALL-E says: I have problems using the object "+ this.robotContainer.getItem(this.id).getId() + LINE_SEPARATOR);
			}
			if(!item.canBeUsed())
			{
				this.robotContainer.pickItem(item.getId());
				engine.saySomething("What a pity! I have no more " + this.id + " in my inventory");
				engine.inventoryChange();
			}
		}
		else
		{
			throw new InstructionExecutionException("WALL·E says: I don't have this object.");
		}
	}
	

	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing.
	 * @return the Instruction syntax OPERATE|OPERAR <ID>
	 */
	public String getHelp() 
	{
		return this.OperateInstructionHelp;
	}

	/**
	 * Parses the String returning an instance of OperateInstruction or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction Reference to an instance of OperateInstruction
	 * @throws WrongInstructionFormatException - When the String is not OPERATE|OPERAR <ID>
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 2 && (aux[0].equalsIgnoreCase("OPERATE") || aux[0].equalsIgnoreCase("OPERAR")))
		{
			return new OperateInstruction(aux[1]);
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
	}
	

}
