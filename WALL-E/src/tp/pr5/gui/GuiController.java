package tp.pr5.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.StormType;
import tp.pr5.instructions.*;
import tp.pr5.items.Item;

/**
 * Esta clase representa el controlador que se encargar� de actualizar la vista realizando los cambios correspondientes en el modelo.
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 *
 */

public class GuiController implements ActionListener, // para los JButton , los jTextField y los JCheckBox
								 						KeyListener,// para los jTextField cuando se salga que cambie la aplicaci�n
							 							MouseListener	// para los JTable
{
	
	private RobotEngine modelo;
	private Rotation currentRotation; // Rotation seleccionada en el combobox
	private PlaceInfo currentModelPlace; // Ultimo place almacenado en el modelo.
	private String itemIdJTable; // id escrito en el jtextfield
	private String itemIdJTextField; // id seleccionado en la tabla
	private ArrayList <PlaceInfo> visitedPlaces; // Array de places visitados por wall-e
	private int fuelLevel; // Robot's fuel level.
	private List<MainWindow> mainWindow; // Referencia a la vista.
	
	/**
     * Crea un controlador sin establecer la vista
	 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Observable.html"> Observable </a>,
     *      <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Observer.html"> Observer </a>
     */
	public GuiController()
	{
		this.modelo = null;
		this.currentRotation = Rotation.LEFT;
		this.itemIdJTextField = "";
		this.itemIdJTable = "";
		this.visitedPlaces = new ArrayList <PlaceInfo>();
		this.visitedPlaces.add(modelo.getRobotPlace()); // Lo inicializamos con Sol.
		this.currentModelPlace = modelo.getRobotPlace();
		this.fuelLevel = 0;
		this.mainWindow = new ArrayList<MainWindow>();
	}
	
	/**
	 * Crea el controlador estableciendo el parametro r para poder inicializar el modelo.
	 * @param r - RobotEngine
	 */
	public GuiController(RobotEngine r)
	{
		setModelo(r);
		this.currentRotation = Rotation.LEFT;
		this.itemIdJTextField = "";
		this.itemIdJTable = "";
		this.visitedPlaces = new ArrayList <PlaceInfo>();
		this.visitedPlaces.add(modelo.getRobotPlace()); // Lo inicializamos con Sol.
		this.currentModelPlace = modelo.getRobotPlace();
		this.fuelLevel = modelo.getFuel();
		this.mainWindow = new ArrayList<MainWindow>(); // Referencia a la vista GUI
	}
	
	 /**
     * Establece el modelo que esta controlando esta clase. 
     * @param elModelo el modelo a controlar
     */
    public void setModelo(RobotEngine elModelo)
    {
	        this.modelo = elModelo;
	}
    
    /**
     * Establece una referencia a la vista que el controlador tendrá que actualizar.
     * @param mainWindow la vista que tendremos quea ctualizar.
     */
    public void addGuiView(MainWindow mainWindow)
    {
    	this.mainWindow.add(mainWindow);
    }
    
    /**
	  * Método que se encarga dependiendo del evento de modificar o no el modelo.
	  * La vista se actualizará a partir de los observadores.
	  * @param fuente el que ha realizado la solicitud de modificaci�n del modelo.
	  */
    public void cambiarModelo(Component fuente)
    {
    	if(this.fuelLevel <= 0)
    		requestGameOver();
    	if(fuente.getName().equals("jButtonMove"))
        {
    		modelo.communicateRobot(new MoveInstruction());
    		if(this.currentModelPlace != modelo.getRobotPlace())
    		{
    			//this.mainWindow.getNavigationPanel().getCityMapPanel().setPlaceCell(modelo.getRobotPlace(),modelo.getRobotHeading(), !checkVisitedPlace(modelo.getRobotPlace()));
    			this.currentModelPlace = modelo.getRobotPlace();
    			if(this.currentModelPlace.isSpaceship())
    				requestWin();
    		}
    		this.fuelLevel = modelo.getFuel();
        }
        else if(fuente.getName().equals("jButtonTurn"))
        {
        	modelo.communicateRobot(new TurnInstruction(currentRotation));
        	this.fuelLevel = modelo.getFuel();
        }
        else if(fuente.getName().equals("jButtonQuit"))
        {
        	int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Quit", JOptionPane.YES_NO_OPTION);
   			if (JOptionPane.OK_OPTION == confirm)
   				modelo.communicateRobot(new QuitInstruction());
        }
        else if(fuente.getName().equals("jButtonPick"))
        {
		     modelo.communicateRobot(new PickInstruction(itemIdJTextField));
        }
        else if(fuente.getName().equals("jButtonDrop"))
        {
	         modelo.communicateRobot(new DropInstruction(itemIdJTable));
        }
        else if(fuente.getName().equals("jButtonOperate"))
        {
	         modelo.communicateRobot(new OperateInstruction(itemIdJTable));
        }
        else if(fuente.getName().equals("jComboBoxDirections"))
    	{
        	@SuppressWarnings("rawtypes")
			JComboBox combo = (JComboBox)fuente;
        	setCurrentRotation((Rotation) combo.getSelectedItem());
    	}
    	else if(fuente.getName().equals("jTextItem"))
    	{
    		JTextField campo=(JTextField)fuente;
    	    itemIdJTextField= campo.getText();
    	}
    	else if(fuente.getName().equals("jFileMenuQuit"))
		{
        	int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Quit", JOptionPane.YES_NO_OPTION);
   			if (JOptionPane.OK_OPTION == confirm)
   				modelo.communicateRobot(new QuitInstruction());
		}
    	else if(fuente.getName().equals("jStormAcidRain"))
    	{
    		modelo.communicateRobot(new StormInstruction(StormType.ACIDRAIN));
    	}
    	else if(fuente.getName().equals("jStormSandStorm"))
    	{
    		modelo.communicateRobot(new StormInstruction(StormType.SANDSTORM));
    	}
    	else if(fuente.getName().equals("jStormTornado"))
    	{
    		modelo.communicateRobot(new StormInstruction(StormType.TORNADO));
    	}
    	else if(fuente.getName().equals("JTableRobotInfo"))
    	{
    		JTable table = (JTable)fuente;
    		int row = table.getSelectedRow();
    		if(row != -1)
    			this.itemIdJTable = (String) table.getValueAt(row, 0); // Column 0: Item's id.
    	}
    	else
    	{
    		int i = 0;
    		boolean encontrado = false;
    		while(!encontrado && i < this.visitedPlaces.size())
    		{
    			if(fuente.getName().equals("jButton"+this.visitedPlaces.get(i).getName()))
    			{
    				Iterator<MainWindow> it = this.mainWindow.iterator();
    				while(it.hasNext())
    					it.next().getLogPanel().setLogText(this.visitedPlaces.get(i).toString());
    				encontrado = true;
    			}
    			i++;
    		}
    	}
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
     * Metodo para que en caso de que se haya producido un error salte un JDialog notificandonos del mismo.
     * @param message - String
     */
    public void requestError(String message) {
    	JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
    
    /**
     * Método que establece la rotación seleccionada en el combo box.
     * @param rotation
     */
	private void setCurrentRotation(Rotation rotation) 
    {
   		this.currentRotation = rotation;
	}

	/**
     * Método para tratar los eventos de forma genérica.
     * Se encarga tanto de solicitar la modificaci�n al modelo como de informar a la vista
     * @param e el evento a tratar
     */
    private void tratarEventoGenerico(EventObject event){
        Component fuente = (Component) event.getSource(); // el que gener� el evento
		//System.err.println(fuente.getName());
        cambiarModelo(fuente);
	}
	
    
   /**
     * Se tratan los eventos del tipo <code>ActionEvent</code> informando cuando es necesario a la vista y al modelo.
     */
	public void actionPerformed(ActionEvent a) 
	{
       tratarEventoGenerico(a);
	}


	@Override
	public void keyPressed(KeyEvent a){}
	
	/**
	 * Método sobreescrito que nos permite capturar el evento que produce soltar una tecla
	 */
	@Override
	public void keyReleased(KeyEvent a) 
	{
		tratarEventoGenerico(a);
	}

	@Override
	public void keyTyped(KeyEvent a){}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		tratarEventoGenerico(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0){}

	@Override
	public void mouseExited(MouseEvent arg0){}

	@Override
	public void mousePressed(MouseEvent arg0){}

	@Override
	public void mouseReleased(MouseEvent arg0){}

	/**
	 * Notifica que wall-e tiene algo que decir.
	 * @param s
	 */
	public void robotSays(String s) 
	{
		Iterator<MainWindow> it = this.mainWindow.iterator();
		while(it.hasNext())
			it.next().robotSays(s);
	}

	/**
	 * Metodo que notifica a la vista que los niveles de fuel y material reciclado de wall-e han cambiado.
	 * @param fuel - Int fuel
	 * @param recycledMaterial - Int recycledMaterial.
	 * @param shield 
	 */
	public void robotUpdate(int fuel, int recycledMaterial, float shield) 
	{
		Iterator<MainWindow> it = this.mainWindow.iterator();
		while(it.hasNext())
			it.next().robotUpdate(fuel, recycledMaterial, shield);
		
	}
	
	/**
	 * Metodo que notifica a la vista que wall-e ha llegado a un nuevo sitio.
	 * @param currentHeading - Direction
	 * @param placeInfo - Place
	 */
	public void robotArrivesAtPlace(Direction currentHeading, Place placeInfo) {
		Iterator<MainWindow> it = this.mainWindow.iterator();
		while(it.hasNext())
			it.next().robotArrivesAtPlace(currentHeading, placeInfo);
		
	}

	/**
	 * Notificamos a la vista que el lugar a cambiado y hay que volver a escanear los objetos.
	 */
	public void inventoryChange(ArrayList<Item> inventory, Place currentPlace){
		Iterator<MainWindow> it = this.mainWindow.iterator();
		while(it.hasNext())
			it.next().inventoryChange(inventory, currentPlace);
	}

	/**
	 * Notificamos a la vista que wall-e esta mirando en una nueva direccion.
	 * @param currentHeading - Direction
	 */
	public void headingUpdate(Direction currentHeading) {
		Iterator<MainWindow> it = this.mainWindow.iterator();
		while(it.hasNext())
			it.next().headingUpdate(currentHeading);
	}
	
	/**
	 * Iniciamos las vistas.
	 */
	public void startGui() {
		Iterator<MainWindow> it = this.mainWindow.iterator();
		while(it.hasNext())
			it.next().arranca();
	}

	/**
	 * Indicamos a la vista que el invnetario del place ha cambiado y hay que actualizar el log.
	 * @param currentPlace - Place
	 */
	public void updatePlaceInventory(Place currentPlace) {
		Iterator<MainWindow> it = this.mainWindow.iterator();
		while(it.hasNext())
			it.next().updatePlaceLog(currentPlace);
		
	}

	public void shieldDestroyed() {
    	JOptionPane.showMessageDialog(null, "My shield has been destroyed. Shutting down...");
		
	}
}
