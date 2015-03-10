package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * This instruction tries to pick an Item from the current place and puts it the robot inventory. This instruction works if the user writes PICK id or COGER id
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */
public class PickInstruction implements Instruction{
	
	
	private String id;
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private final String PickInstructionHelp = "PICK|COGER <id>";
	
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");	
	
	/**
	 * Default constructor
	 */
	public PickInstruction()
	{
		this.id = null;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	/**
	 * @param id - Item id
	 */
	public PickInstruction(String id)
	{
		this.id = id;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}

	/**
	 * Configures the context
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
	 * The robot adds an item to its inventory from the current place, if it exists
	 * @throws instructionExecutionException - When the place does not contain an item with this name or when there is another item with the same id in the robot inventory InstructionExecutionException - if there exist any execution error.
	 */
	public void execute() throws InstructionExecutionException 
	{
		Item itemPlace, itemRobot;
		itemPlace = this.navigation.getCurrentPlace().pickItem(this.id);
		if(itemPlace != null)
		{
			itemRobot = this.robotContainer.getItem(this.id);
			if(itemRobot == null)
			{
				this.robotContainer.addItem(itemPlace);
				engine.saySomething("WALL·E says: I am happy! Now I have " + itemPlace.getId());
				engine.inventoryChange();
				engine.placeChange();
			}	
			else // ya existe
				throw new InstructionExecutionException("WALL·E says: I am stupid! I had already the object "+ itemPlace.getId() + LINE_SEPARATOR); // System.out.print("WALL·E says: I am stupid! I had already the object "+ itemPlace.getId() + LINE_SEPARATOR);
		}
		else
			throw new InstructionExecutionException("WALL·E says: Oops, this places has...."); // 	System.out.println("WALL·E says: Oops, this places has....");
	}
	

	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing.
	 * @return the command syntax PICK|COGER <id>
	 */
	public String getHelp() 
	{
		return this.PickInstructionHelp;
	}

	/**
	 * Parses the String returning an instance of PickInstruction or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction Reference to an instance of PickInstruction
	 * @throws WrongInstructionFormatException - hen the String is not PICK|COGER <id>
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 2 && (aux[0].equalsIgnoreCase("PICK") || aux[0].equalsIgnoreCase("COGER")))
		{
			return new PickInstruction(aux[1]);
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
	}

}
