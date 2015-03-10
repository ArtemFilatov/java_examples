package tp.pr5.console;

import java.util.Scanner;

import javax.swing.JOptionPane;

import tp.pr5.Direction;
import tp.pr5.Interpreter;
import tp.pr5.Place;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class ConsoleController
{
	
	private RobotEngine modelo; // Referencia al modelo.
	private ConsoleView consoleView; // Referencia a la vista.
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	
	private Scanner cad = new Scanner(System.in);
	
	/**
	 * Constructor sin parámetros.
	 */
	public ConsoleController()
	{
		this.modelo = null;
		this.consoleView = new ConsoleView();
	}
	
	/**
	 * Constructor con parametros.
	 * @param modelo - Referencia al modelo
	 */
	public ConsoleController(RobotEngine modelo)
	{
		this.modelo = modelo;
		this.consoleView = new ConsoleView();
	}
	
	/**
	 * Metodo que inicializa el bucle del juego. El controlador se encargará de recibir la instrucción, parsearla
	 * y solicitar al robotengine que ejecute el comando solicitado por el usuario.
	 */
	public void startConsole()
	{
		Instruction instru;
		while(!(this.modelo.getRobotPlace().isSpaceship()))
		{
			if(this.modelo.getFuel() > 0)
			{
				this.consoleView.printPrompt(); // Print's wall-e's prompt
				String orden = this.cad.nextLine();
				try
				{
					instru = Interpreter.generateInstruction(orden);
					this.modelo.communicateRobot(instru);
				}
				catch(WrongInstructionFormatException e)
				{
					this.consoleView.robotSays(e.getMessage());
				}
				if(this.modelo.getShield() == 0)
				{
					this.modelo.shieldDestroyed();
				}
			}
			else
			{
				this.consoleView.robotSays("WALL·E says: I run out of fuel. I cannot move. Shutting down..." + LINE_SEPARATOR );
				//System.out.print("WALL�E says: I run out of fuel. I cannot move. Shutting down..." + LINE_SEPARATOR );
				this.modelo.requestQuit();
			}
		}

		if(this.modelo.getRobotPlace().isSpaceship())
			this.consoleView.robotSays("WALL·E says: I am at my spaceship. Bye bye");
	}
	
	
   /**
    * Metodo para que en caso de quedarte sin fuel salte el JDialog de Game Over.
    */
    private void requestGameOver() 
    {
    	JOptionPane.showMessageDialog(null, "Game Over");
    	modelo.requestQuit();
	}
    
    /**
    * Metodo para que en caso llegar a la nave nodriza avise de que has ganado.
    */    
    private void requestWin()
    {
    	JOptionPane.showMessageDialog(null, "Congratulations You Win");
    	modelo.requestQuit();    	
    }
	
	/**
	 * 
	 * @param inventoryDescription
	 */
	public void inventoryScan(String inventoryDescription) {
		this.consoleView.inventoryScan(inventoryDescription);
		
	}

	/**
	 * Notificamos a la vista por consola que wall-e tiene algo que decir.
	 * @param s
	 */
	public void robotSays(String s) {
		this.consoleView.robotSays(s);
		
	}

	/**
	 * Notifica a la consola que el fuel o el material reciclado han cambiado.
	 * @param fuel - Int fuel
	 * @param recycledMaterial - Int recycledMaterial.
	 * @param shield 
	 */
	public void robotUpdate(int fuel, int recycledMaterial, float shield){
		this.consoleView.robotUpdate(fuel,recycledMaterial, shield);
	}

	/**
	 * Notifica a la vista por consola que wall-e ha llegado un place y por tanto tiene que escribir un mensaje.
	 * @param currentHeading
	 * @param currentPlace
	 */
	public void robotArrivesAtPlace(Direction currentHeading, Place currentPlace){
		this.consoleView.robotArrivesAtPlace(currentHeading, currentPlace);
	}

	public void comProblems() {
		this.consoleView.comProblems();
	}

	/**
	 * Pedimos a la vista que muestre el mensaje de ayuda de walle.
	 * @param helpMessage - String
	 */
	public void requestHelp(String helpMessage){
		this.consoleView.requestHelp(helpMessage);
	}

	/**
	 * Pedimos a la vista que muestre el mensaje del place scaneado por wall-e
	 * @param placeDescription
	 */
	public void placeScanned(String placeDescription) {
		this.consoleView.placeScanned(placeDescription);
	}

	/**
	 * Pedimos a la vista que muestre el mensaje notificando que wall-e ha cambiado su direccion.
	 * @param currentHeading - Direction
	 */
	public void headingUpdate(Direction currentHeading) {
		this.consoleView.headingUpdate(currentHeading);
		
	}

	/**
	 * Una instrucci�n ha producido un error y necesitamos comunicarle a la consola el error.
	 * @param message
	 */
	public void raiseError(String message) {
		this.consoleView.raiseError(message);
	}

	public void shieldDestroyed() {
		this.consoleView.robotSays("WALL�E says: My shield has been destroyed. Shutting down...");
	}
	
	

}
