package tp.pr5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * The interpreter is in charge of converting the user input in an instruction for the robot. Up to now, the valid instructions are:
 * MOVE
 * TURN { LEFT | RIGHT }
 * PICK <ITEM>
 * SCAN [ <ITEM> ]
 * OPERATE <ITEM>
 * RADAR
 * DROP <ITEM>
 * HELP
 * QUIT
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class Interpreter {

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final List<Instruction> listInstructions = loadInstructions();
	
	
	/**
	 * Generates a new instruction according to the user input
	 * @param line - A string with the user input
	 * @return The instruction read from the given line. If the instruction is not correct, then it throws an exception.
	 * @throws WrongInstructionFormatException
	 */
	public static Instruction generateInstruction(String line) throws WrongInstructionFormatException
	{
		Instruction instru = null;
		Iterator<Instruction> it = listInstructions.iterator();
		int i = 0;
		while(it.hasNext() && i <10)
		{
			try
			{
				instru = it.next().parse(line);
			}
			catch(WrongInstructionFormatException e)
			{
				
			}
			finally
			{
				i++;
			}
		}
		if(instru == null && i >= 9)
			throw new  WrongInstructionFormatException("WALL·E says: I do not understand. Please repeat");
		return instru;
	}
	
	/**
	 * This method initializes the Interpreter List Instructions.
	 * @return
	 */
	private static List<Instruction> loadInstructions() 
	{
		List<Instruction> list = new ArrayList<Instruction>();
		list.add(0,new MoveInstruction());
		list.add(1,new DropInstruction());
		list.add(2,new HelpInstruction());
		list.add(3,new OperateInstruction());
		list.add(4,new PickInstruction());
		list.add(5,new QuitInstruction());
		list.add(6,new RadarInstruction());
		list.add(7,new ScanInstruction());
		list.add(8,new TurnInstruction());
		list.add(9, new StormInstruction());
		return list;
	}

	/**
	 * It returns information about all the instructions that the robot understands
	 * @return A string with the information about all the available instructions
	 */
	
	public static String interpreterHelp()
	{
		String help = "";
		Iterator<Instruction> it = listInstructions.iterator();
		help = "The valid instructions for WALL-E are:" + LINE_SEPARATOR;
		while(it.hasNext())
		{
			help = help + "     " + it.next().getHelp() + LINE_SEPARATOR;
		}
		
		return help;
	}

}
