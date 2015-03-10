package tp.pr5.instructions;

import javax.swing.JOptionPane;

import tp.pr5.NavigationModule;
import tp.pr5.Place;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * This instruction drops an Item from the current place and puts it the robot inventory. This instruction works if the user writes DROP or SOLTAR
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class DropInstruction implements Instruction {
	
	private String id;
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private final String DropInstructionHelp = "DROP <id> OR SOLTAR <id>";
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");	
	
	/**
	 * Default Constructor
	 */
	
	public DropInstruction()
	{
		this.id = null;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	/**
	 * 
	 * @param id - Item id
	 */
	public DropInstruction(String id)
	{
		this.id = id;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	
	/**
	 * Fixes the current context, i.e., the robot engine and the navigation module
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
	 * The robot drops an Item from its inventory in the current place, if the item exists
	 * @param
	 * @return 
	 * @throws InstructionExecutionException - When the robot inventory does not contain an item with this name or when there is another item with the same id in the current place
	 */
	public void execute() throws InstructionExecutionException 
	{
		String id = this.id;
		Item item = this.robotContainer.pickItem(id);
		Place place = this.navigation.getCurrentPlace();
		if(item != null)
		{
			if(place.existItem(id))
				throw new InstructionExecutionException("The place already contains this item");
			engine.saySomething("WALL·E says: Great! I have dropped " + id);
			engine.inventoryChange();
			place.addItem(item);
			engine.placeChange();
		}
		else
			throw new InstructionExecutionException("You do not have any " + id +".");
	}
	
	/**
	 * Instruction help
	 * @return the instruction syntax DROP <id>
	 */
	public String getHelp() 
	{
		return this.DropInstructionHelp;
	}

	/**
	 * Parses the String returning an instance of DropInstruction or throwing a WrongInstructionFormatException()
	 * @param cad - text String to parse
	 * @return Instruction Reference to an instance of DropInstruction
	 * @throws WrongInstructionFormatException - When the String is not DROP <id>
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException
	{
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 2 && (aux[0].equalsIgnoreCase("DROP") || aux[0].equalsIgnoreCase("SOLTAR")))
		{
			return new DropInstruction(aux[1]);
		}
		else
		{
			throw new WrongInstructionFormatException();
		}

		
	}


}
