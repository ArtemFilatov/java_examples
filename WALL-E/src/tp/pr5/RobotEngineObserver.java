package tp.pr5;

import java.util.ArrayList;

import tp.pr5.items.Item;

/**
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * 
 * <p>Esta interfaz presenta los m�todos de consulta del modelo que necesita la vista para poder presentar los resultados.
 * De esta forma en la aplicaci�n nunca tendr� una variable del tipo 
 * <code>RobotEngine</code> sino del tipo <code>ObservableRobotEngine</code>.
 * 
 * <p>Aqu� nunca se deben meter m�todos que modifiquen el modelo.
 */


public interface RobotEngineObserver {

	/**
	 * The robot engine informs that the communication is over.
	 */
	public void communicationCompleted();
	
	/**
	 * The robot engine informs that the help has been requested
	 * @param help A string with information help
	 */
	public void communicationHelp (String help);
	
	/**
	 * The robot engine informs that the robot has shut down 
	 * (because it has arrived at the spaceship or it has run out of fuel)
	 * @param atShip true if the robot shuts down because it has arrived at the spaceship or false if it has run out of fuel
	 */
	public void engineOff (boolean atShip);
	
	/**
	 * The robot engine informs that it has raised an error
	 * @param msg Error message
	 */
	public void raiseError (String msg);
	
	/**
	 * The robot engine informs that the robot wants to say something
	 * @param message The robot message
	 */
	public void robotSays (String message);
	
	/**
	 * The robot engine informs that the fuel and/or the amount of recycled material has changed
	 * @param fuel Current amount of fuel
	 * @param recycledMaterial Current amount of recycled material
	 */
	public void robotUpdate(int fuel, int recycledMaterial);
}
