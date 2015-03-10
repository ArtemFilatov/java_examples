package tp.pr5.gui;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import tp.pr5.Place;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.Item;



/**
 * Panel que muestra tanto el nivel de fuel como el de material reciclado asï¿½ como los items que tiene Wall-e en su inventario
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

@SuppressWarnings("serial")
public class RobotPanel extends JPanel
{
	
	// JLabel
	private final String ROBOT_INFO_FUEL = "Fuel: ";
	private final String ROBOT_INFO_RECYCLED = "Recycled: ";
	private final String ROBOT_INFO_SHIELD = "Shield: ";
	private int robotFuel;
	private int robotRecycled;
	private double robotShield;
	
	// Table
	
	// Components
	private JLabel jLabelRobotInfo;
	private JTable jTableRobotInfo;
	private MyTableModel tableItems;
	private JScrollPane jScrollPane;
	
	
	
	/**
	 * 
	 */
	public RobotPanel()
	{
		this.robotFuel = 0;
		this.robotRecycled = 0;
		iniciarRobotPanel();
	}
	
	/**
     * Inicializa todos los componentes del panel y los consfigura adecuadamente.
     */
	private void iniciarRobotPanel()
	{
		// Panel configuration
		this.setBorder(new TitledBorder("Robot info"));
	    this.setName("robotInfoForm");
		this.setPreferredSize(new java.awt.Dimension(700, 150));
		  
		//Inicializar componentes.		  
		jLabelRobotInfo= new JLabel(ROBOT_INFO_FUEL + robotFuel + " " + ROBOT_INFO_RECYCLED + robotRecycled);
		tableItems = new MyTableModel();
		jTableRobotInfo = new JTable(tableItems);
		jScrollPane = new JScrollPane(jTableRobotInfo);
	
		//Configuraciï¿½n de los elementos
			// jLabelRobotInfo
		jLabelRobotInfo.setName("jLabelRobotInfo");
		jLabelRobotInfo.setHorizontalAlignment(SwingConstants.CENTER);
			// jTableRobotInfo
		jTableRobotInfo.setName("JTableRobotInfo");
			// jScrollPane
		jScrollPane.setName("jScrollPaneRobotPanel");
		jScrollPane.setPreferredSize(new java.awt.Dimension(700,100));
		jScrollPane.setAlignmentY(SwingConstants.TOP);		
		  
		//Aï¿½adir al panel
		this.add(jLabelRobotInfo);
		this.add(jScrollPane);
		  
	}
	
	/**
	 * MÃ©todo que actualiza tanto la tabla como los valores de fuel y material reciclado del RobotPanel 
	 */
	public void update(Observable arg0, Object arg1) 
	{
//		RobotEngineObserver modelo = (RobotEngineObserver) arg0;
		// jLabelRobotInfo
//		this.robotFuel = modelo.getFuel();
//		this.robotRecycled = modelo.getRecycledMaterial();
//		jLabelRobotInfo.setText(ROBOT_INFO_FUEL + robotFuel + " " + ROBOT_INFO_RECYCLED + robotRecycled);
//		tableItems.setTableItems(modelo.getRobotItemContainer());
	} 
	
	/**
	 * 
	 * @param controlador
	 */
   public void fijarControlador(EventListener controlador) 
   {
	   this.jTableRobotInfo.addMouseListener((MouseListener)controlador);
   }

   /**
    * Método para poner en la tabla de objetos los objetos correspondientes al lugar en el que se encuentre wall-e
    * @param inventory - ArrayList<Item>
    * @param currentPlace - Place
    */
	public void setPlaceItems(ArrayList<Item> inventory, Place currentPlace) {

		tableItems.setTableItems(inventory);
	}
	
	/**
	 * Método para actualizar los valores de fuel y material reciclado de wall-e
	 * @param fuel
	 * @param recycled
	 * @param shield 
	 */
	public void setRobotInfo(int fuel, int recycled, float shield)
	{
		this.robotFuel = fuel;
		this.robotRecycled = recycled;
		this.robotShield = shield;
		jLabelRobotInfo.setText(ROBOT_INFO_FUEL + robotFuel + " " + ROBOT_INFO_RECYCLED + robotRecycled + " " + ROBOT_INFO_SHIELD + robotShield);
	}
   
   
   
}


