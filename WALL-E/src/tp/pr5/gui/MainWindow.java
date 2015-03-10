package tp.pr5.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.Item;

/**
 * Ventana principal que contiene todos los componentes.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ViewInterface, RobotEngineObserver {

	
	private static String WINDOW_TITLE= "WALL-E The garbage collector";
	private Container panelPrincipal;
	
	// Paneles
	private InstructionPanel instructionPanel;
	private RobotPanel robotPanel;
	private NavigationPanel navPanel;
	private LogPanel logPanel;
	private JPanel tagPanel; // Panel para la etiqueta inferior.
	
	// Componentes.
	private JSplitPane jSplitPane;
	private JMenuBar jMenuBar;
	private JMenu jFileMenu;
	private JMenuItem jFileMenuQuit;
	private JMenu jStormMenu;
	private JMenuItem jStormAcidRain;
	private JMenuItem jStormSandStorm;
	private JMenuItem jStormTornado;
	private JLabel jLabelRobotMsg; // Etiqueta para los mensajes del robot.

	/**
	 * Constructor de la ventana principal sin parametros.
	 */
	public MainWindow() 
	{
		super(WINDOW_TITLE);
        inicializaVistaPrincipal();
	}

	/**
	 * Constructor de la ventana principal con el controlador como parametro.
	 * @param controlador
	 */
	public MainWindow(GuiController controller) 
	{
		super(WINDOW_TITLE);
        inicializaVistaPrincipal();
        fijarControlador(controller);
  
	}

	/**
	 * Funcion que inicializa, configura y a�ade a la vista los componentes.
	 */
	public void inicializaVistaPrincipal () 
	{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelPrincipal = this.getContentPane();
        
        // Inicializacion de componentes.
        instructionPanel= new InstructionPanel();
        robotPanel= new RobotPanel();
        navPanel = new NavigationPanel();
        logPanel = new LogPanel();
        tagPanel = new JPanel();
        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jMenuBar = new JMenuBar();
        jFileMenu = new JMenu("File");
        jFileMenuQuit = new JMenuItem("Quit");
        jStormMenu = new JMenu("Storm");
        jStormAcidRain = new JMenuItem("Acid Rain");
        jStormSandStorm = new JMenuItem("Sand Storm");
        jStormTornado = new JMenuItem("Tornado");
        jLabelRobotMsg = new JLabel("");
        
		 // Configuracion de componentes.
		
        // SplitPane para las dos primeras vistas.
		jSplitPane.add(instructionPanel);
		jSplitPane.add(robotPanel);
		jSplitPane.setName("jSplitPaneMainWindow");
		
		
			//menuBar adding components.
		jMenuBar.setName("jMenuBar");
		jMenuBar.add(jFileMenu);
		
		// jFileMenu adding menuitems
		jFileMenu.add(jFileMenuQuit);
		jFileMenu.setName("jFileMenu");
		//jFileMenuQuit conf
		jFileMenuQuit.setName("jFileMenuQuit");
		// Storm
		jMenuBar.add(jStormMenu);
		jStormMenu.setName("jStormMenu");
		jStormMenu.add(jStormAcidRain);
		jStormAcidRain.setName("jStormAcidRain");
		jStormMenu.add(jStormSandStorm);
		jStormSandStorm.setName("jStormSandStorm");
		jStormMenu.add(jStormTornado);
		jStormTornado.setName("jStormTornado");
		
		
		// tagPanel
		tagPanel.setPreferredSize(new Dimension(1010,30));
		tagPanel.add(jLabelRobotMsg);
		// jLabelRobotMsg
		jLabelRobotMsg.setHorizontalAlignment(SwingConstants.CENTER);
		// Anadimos la imagen al jlabel de la imagen.
		this.setJMenuBar(jMenuBar);
        panelPrincipal.add(jSplitPane);
        panelPrincipal.add(navPanel);
        panelPrincipal.add(logPanel);
        panelPrincipal.add(tagPanel);
        
        // est�tica y emmpaquetar la aplicaci�n para ajustar el tama�o de la ventana
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(1035,790));
        this.pack();
    }
   
    /**
     * Metodo que hace visible la vista 
     */
	public void arranca()
	{
		setVisible(true);
	}
	
	/**
     * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
     * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener y FocusListener.
     */
    public void fijarControlador(EventListener controlador) 
    {
    	// Panels
    	instructionPanel.fijarControlador(controlador);
    	robotPanel.fijarControlador(controlador);
    	navPanel.fijarControlador(controlador);
    	logPanel.fijarControlador(controlador);
    	
    	// Other Components
    	jFileMenuQuit.addActionListener((ActionListener)controlador);
    	jStormAcidRain.addActionListener((ActionListener)controlador);
    	jStormSandStorm.addActionListener((ActionListener)controlador);
    	jStormTornado.addActionListener((ActionListener)controlador);
    }
	
	/**
	 * Metodo para actualizar los componentes de la vista.
	 */
	@Override
	public void update(Observable o, Object arg){}
	
	/**
	 * Notifica a la vista que wall-e tiene algo que decir.
	 * @param s
	 */
	public void robotSays(String s) {
		this.jLabelRobotMsg.setText(s);
	}

	/**
	 * Notifica a la vista que los valores de fuel y material reciclado de wall-e han cambiado.
	 * @param fuel - Int fuel
	 * @param recycledMaterial - Int recycledMaterial
	 * @param shield 
	 */
	public void robotUpdate(int fuel, int recycledMaterial, float shield){
		this.jLabelRobotMsg.setText("Robot attributes has been updated: ("+ fuel +"," + recycledMaterial + "," + shield + ")");
		this.robotPanel.setRobotInfo(fuel, recycledMaterial,shield);
	}

	/**
	 * Realizamos lso cambios necesarios en la vista para notificar que el place ha cambiado.
	 * @param currentHeading - Direction
	 * @param placeInfo - Place
	 */
	public void robotArrivesAtPlace(Direction currentHeading, Place placeInfo){
		this.navPanel.robotArrivesAtPlace(currentHeading, placeInfo);
		this.updatePlaceLog(placeInfo);
	}
	
	/**
	 * Notificamos en el log la situaci�n del place actual.
	 * @param placeInfo - Place
	 */
	public void updatePlaceLog(Place placeInfo)
	{
		this.logPanel.setLogText(placeInfo.toString());
	}
	
	/**
	 * Realizamos los cambios necesarios en la vista para mostrar los objetos del lugar en el que se encuentra wall-e
	 * @param inventory - ArrayList<Item>
	 * @param currentPlace - Place
	 */
	public void inventoryChange(ArrayList<Item> inventory, Place currentPlace) {
		this.robotPanel.setPlaceItems(inventory,currentPlace);
	}
	
	/**
	 * Notificamos a la vista que la direcci�n en la que mira Wall-e ha cambiado y que por tanto tieneq ue girar la imagen.
	 * @param currentHeading - Direction
	 */
	public void headingUpdate(Direction currentHeading) {
		this.navPanel.updateImg(currentHeading);
	}

	@Override
	public void communicationCompleted() {}

	@Override
	public void communicationHelp(String help) {}

	@Override
	public void engineOff(boolean atShip) {}

	@Override
	public void raiseError(String msg) {}
	
	
	///////////////////////////////////////////////////// Getters /////////////////////////////////////////////////////
	
	public InstructionPanel getInstructionPanel()
	{
		return this.instructionPanel;
	}
	
	public RobotPanel getRobotPanel()
	{
		return this.robotPanel;
	}
	
	public LogPanel getLogPanel()
	{
		return this.logPanel;
	}
	
	public NavigationPanel getNavigationPanel()
	{
		return this.navPanel;
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {}



}

	
	

