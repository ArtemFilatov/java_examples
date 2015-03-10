package tp.pr5.console;

import java.util.EventListener;
import java.util.Observable;

import tp.pr5.Direction;
import tp.pr5.Interpreter;
import tp.pr5.NavigationObserver;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.gui.ViewInterface;
import tp.pr5.items.InventoryObserver;

/**
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class ConsoleView implements ViewInterface, RobotEngineObserver {
	
	/**
	 * Constructor sin parametros.
	 */
	public ConsoleView(){}
	
	/**
	 * Mostrara por consola el prompt de wall-e.
	 */
	public void printPrompt()
	{
		System.out.print("WALL·E> ");
	}
	
	/**
	 * M�todo a traves del cual wall-e dice algo
	 * @param s
	 */
	public void robotSays(String s) 
	{
		System.out.println(s);		
	}

	/**
	 * Notifica que han cambiado el fuel y el material reciclado.
	 * @param fuel - Int fuel
	 * @param recycledMaterial - Int recycled material.
	 * @param shield 
	 */
	public void robotUpdate(int fuel, int recycledMaterial, float shield){
		this.robotSays("      * My power is " + fuel 
					   + Interpreter.LINE_SEPARATOR + "      * My recycled material is " + recycledMaterial
					   + Interpreter.LINE_SEPARATOR + "      * My shield is " + shield);
	}

	/**
	 * Escribimos la descripci�n del lugar al que hemos llegado.
	 * @param currentHeading - Direction
	 * @param placeInfo - Place
	 */
	public void robotArrivesAtPlace(Direction currentHeading, Place placeInfo) {
		this.robotSays(placeInfo.toString());
	}
	
	/**
	 * La comunicaci�n ha finalizado posiblemente por que se ha interpretado una instruccion Quit.
	 * @param null
	 */
	public void comProblems(){
		this.robotSays("WALL·E says: I have communication problems. Bye Bye");
	}

	/**
	 * Escribimos el mensaje de ayuda de wall-e
	 * @param helpMessage
	 */
	public void requestHelp(String helpMessage) {
		this.robotSays(helpMessage);
	}

	/**
	 * Escribimos el mensaje del nuevo sitio escaneado por Wall-e
	 * @param placeDescription - String
	 */
	public void placeScanned(String placeDescription) {
		this.robotSays(placeDescription);
	}

	/**
	 * Escribimos el mensaje de la nueva direcci�n en la que mira wall-e
	 * @param currentHeading - Direction
	 */
	public void headingUpdate(Direction currentHeading) {
		this.robotSays("WALL·E is looking at direction " + currentHeading);
	}

	/**
	 * Se ha producido una excepci�n al ejecutar una instrucci�n y a trav�s de este m�todo escribimos el error.
	 * @param message - String
	 */
	public void raiseError(String message) {
		this.robotSays(message);
	}

	/**
	 * Un evento ha a�adido o eliminado un elemento del inventorio de Wall-e y tenemos que volver a escanearlo.
	 * @param inventoryDescription
	 */
	public void inventoryScan(String inventoryDescription) {
		this.robotSays(inventoryDescription);
	}

	
	@Override
	public void arranca() {}
	
	@Override
	public void update(Observable arg0, Object arg1) {}

	@Override
	public void fijarControlador(EventListener controlador) {}
	
	@Override
	public void communicationCompleted() {}

	@Override
	public void communicationHelp(String help) {}

	@Override
	public void engineOff(boolean atShip) {}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {}

}
