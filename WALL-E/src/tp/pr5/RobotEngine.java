package tp.pr5;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GuiController;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * This class represents the robot engine. It controls robot movements by processing the instructions introduced with the keyboard. The engine stops when the robot arrives at the space ship, runs out of fuel or receives a quit instruction.
 * The robot engine is also responsible for updating the fuel level and the recycled material according to the actions that the robot performs in the city.
 * The robot engine also contains an inventory where the robot stores the items that it collects from the city
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class RobotEngine extends Observable implements RobotEngineObserver
{
	private City city;
	private int fuel;
	private int recycledMaterial;
	private float shield;
	private ItemContainer items;
	private NavigationModule nav;
	private GuiController guiController;
	private ConsoleController consoleController;
	
	
	private Scanner cad = new Scanner(System.in);
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	
	/**
	 * Creates the robot engine in an initial place, facing an initial direction and with a city map. Initially the robot has not any items or recycled material but it has an initial amount of fuel (50).
	 * @param citymap - The city where the robot wanders
	 * @param initialPlace - The place where the robot starts
	 * @param dir - The initial direction where the robot is facing.
	 */
	public RobotEngine(City citymap, Place initialPlace, Direction dir)
	{
		this.city = citymap;
		this.fuel = 100;
		this.recycledMaterial = 0;
		this.shield = 100;
		this.items = new ItemContainer();
		this.nav = new NavigationModule(this.city, initialPlace);
		this.nav.setRobotDirection(dir);
	}
	
	/**
	 * It executes an instruction. The instruction must be configured with the context before executing it. It controls the end of the simulation. If the execution of the instruction throws an exception, then the corresponding message is printed
	 * @param c - The instruction to be executed
	 */
	public void communicateRobot(Instruction c)
	{
		try
		{
			c.configureContext(this, this.nav, this.items);
			c.execute();
			informarObservadores();  
		}
		catch(InstructionExecutionException e)
		{
			if(this.consoleController != null)
				this.consoleController.raiseError(e.getMessage());
			if(this.guiController != null)
				this.guiController.requestError(e.getMessage());
			informarObservadores();
		}
	}
		
	/**
	 * Requests the end of the simulation
	 */
	public void requestQuit()
	{	
		System.exit(0);
	}

	
	/**
	 * Increases the amount of recycled material of the robot
	 * @param weight - Amount of recycled material
	 */
	public void addRecycledMaterial(int weight) 
	{
		this.recycledMaterial += weight;
	}
	
	/**
	 * Adds an amount of fuel to the robot (it can be negative)
	 * @param fuel - Amount of fuel added to the robot
	 */
	public void addFuel(int fuel) 
	{
		this.fuel += fuel;
		if(this.fuel <= 0)
			this.fuel = 0;
	}
	
	/**
	 * Adds an amount of shield to the robot ( it can be negative)
	 * @param damageCaused - Amount of shield added to the robot.
	 */
	public void addShield(float damageCaused)
	{
		this.shield += damageCaused;
		if(this.shield <= 0)
			this.shield = 0;
	}
	
	/**
	 * Prints the information about all possible instructions
	 */
	public void requestHelp()
	{
		if(this.consoleController != null)
			this.consoleController.requestHelp(Interpreter.interpreterHelp());
	}
	
	public void requestStart() {
		if(this.guiController != null){
			this.guiController.startGui();
			this.guiController.robotArrivesAtPlace(this.nav.getCurrentHeading(), this.nav.getCurrentPlace());
			this.guiController.robotUpdate(this.fuel,  this.recycledMaterial, this.shield);
		}
		if(this.consoleController != null){
			this.consoleController.robotArrivesAtPlace(this.nav.getCurrentHeading(), this.nav.getCurrentPlace());
			this.consoleController.headingUpdate(this.nav.getCurrentHeading());
			this.consoleController.robotUpdate(this.fuel, this.recycledMaterial, this.shield);
			this.consoleController.startConsole();
			
		}
	}
	
	@Override
	public void raiseError(String msg) {}

	@Override
	public void communicationHelp(String help) {}

	@Override
	public void engineOff(boolean atShip) {}

	@Override
	public void communicationCompleted() {}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {}

	@Override
	public void robotSays(String message) {}
	
	

	//////////////////////////////////////////////////////////// Unofficial Methods ////////////////////////////////////////////////////////////
	
	/**
	 * Robot's itemcontainer getter
	 * @return The robot's itemcontainer
	 */
	public ItemContainer getItems() 
	{
		return this.items;
	}

	/**
	 * Robot's fuel getter
	 * @return The robot's fuel level
	 */
	public int getFuel() 
	{
		return this.fuel;
	}

	/**
	 * Robot's recycled material getter
	 * @return The robot's recycled material level
	 */
	public int getRecycledMaterial() 
	{
		return this.recycledMaterial;
	}
	
	/**
	 * Robot's shield getter.
	 * @return The robot's shield level.
	 */
	public float getShield()
	{
		return this.shield;
	}
	
	/**
	 * 
	 * @param cadena
	 * @return
	 */
	public String aMinusculas(String cadena)
	{
		return cadena.toLowerCase();
	}

	/**
	 * Metodo para fijar los controladores del modelo.
	 * @param guiController - GuiController object
	 * @param consoleController - ConsoleController object
	 * @return 
	 */
	public void setController(GuiController guiController, ConsoleController consoleController)
	{
		this.guiController = guiController;
		this.consoleController = consoleController;
	}
	
	/**
	 * Robot's itemcontainer setter
	 * @param robotContainer
	 */
	public void setItems(ItemContainer robotContainer) 
	{
		this.items = robotContainer;
		
	}
	
	/**
	 * @return robot's current place description.
	 */
	public String getPlaceDescription()
	{
		return this.nav.getCurrentPlace().toString();
	}
	
	/**
	 * @return robot's item container
	 */
	public ArrayList<Item> getRobotItemContainer()
	{
		return this.items.getInventory();
	}
	
    /**
     * Indicar a los observadores que se ha actualizado el modelo.
     * 
     */
    private void informarObservadores()
    {
        setChanged(); // establece que ha habido un cambio.
        notifyObservers(); // notifica a los observadores.	
    }
    
    /**
     * @return robot's heading direction
     */
    public Direction getRobotHeading()
    {
    	return this.nav.getCurrentHeading();
    }
    
    /**
     * @return robot's current place
     */
    public Place getRobotPlace()
    {
    	return this.nav.getCurrentPlace();
    }

    
    
    
    /**
     * Metodo para avisar al controlador de que se ha producido un scan del inventario.
     * @param inventoryDescription
     */
	public void inventoryScan(String inventoryDescription){
		if(this.consoleController != null)
			this.consoleController.inventoryScan(inventoryDescription);
	}

	/**
	 * Metodo para avisar al controlador de que se ha producido un scan de un item.
	 * @param string
	 */
	public void itemScan(String itemDescription){
		if(this.consoleController != null)
			this.consoleController.inventoryScan(itemDescription);
	}

	/** 
	 * Metodo que notifica a las vistas que wall-e ha dicho algo.
	 * @param s - String s: El mensaje de Wall-e
	 */
	public void saySomething(String s){
		if(this.consoleController != null)
			this.consoleController.robotSays(s);
		if(this.guiController != null)
			this.guiController.robotSays(s);
		
	}

	/**
	 * Notifica que el inventorio ha cambiado.
	 */
	public void inventoryChange(){
		if(this.guiController != null)
			this.guiController.inventoryChange(this.items.getInventory(), this.nav.getCurrentPlace());
	}

	/**
	 * Notifica a las vistas que el robot se ha movido y ha llegado a otro place.
	 */
	public void robotArrivesAtPlace(){
		if(this.consoleController != null)
			this.consoleController.robotArrivesAtPlace(this.nav.getCurrentHeading(), this.nav.getCurrentPlace());
		
		 if(this.guiController != null)
		 	this.guiController.robotArrivesAtPlace(this.nav.getCurrentHeading(), this.nav.getCurrentPlace());
	}

	/**
	 * Notifica a las vistas que el fuel y el material reciclado han cambiado.
	 */
	public void robotUpdate(){
		if(this.consoleController != null)
			this.consoleController.robotUpdate(this.fuel, this.recycledMaterial, this.shield);
		
		 if(this.guiController != null)
		 	this.guiController.robotUpdate(this.fuel, this.recycledMaterial, this.shield);
		 if(this.shield == 0)
				shieldDestroyed();
	}

	/**
	 * Notificamos a la vista que la comunicaci�n con wall-e ha finalizado debido a que se ha interpretado una instrucci�n Quit.
	 * @param null
	 */
	public void comProblems() {
		if(this.consoleController != null)
			this.consoleController.comProblems();
	}

	/**
	 * Notificamos a la vista que un place ha sido escaneado y mandamos el mensaje de wall-e
	 * @param placeDescription - String
	 */
	public void placeScanned(String placeDescription) {
		if(this.consoleController != null)
			this.consoleController.placeScanned(placeDescription);
		
	}

	/**
	 * Notificamos a las vistas que wall-e ha girado y ahora mira en otra direcci�n.
	 * @param currentHeading - Direction
	 */
	public void headingUpdate(Direction currentHeading) {	
		if(this.consoleController != null)
			this.consoleController.headingUpdate(currentHeading);
		
		 if(this.guiController != null)
		 	this.guiController.headingUpdate(currentHeading);
	}
	
	/**
	 * Se ha producido un cambio en el place actual al coger un nuevo objeto.
	 */
	public void placeChange(){
		if(this.guiController != null)
			this.guiController.updatePlaceInventory(this.nav.getCurrentPlace());
	}

	public void shieldDestroyed() {
		if(this.consoleController != null)
			this.consoleController.shieldDestroyed();
		
		 if(this.guiController != null)
		 	this.guiController.shieldDestroyed();
		 
		 this.requestQuit();
		
	}

	


    
}	
