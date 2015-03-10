package tp.pr5.gui;

import java.awt.Dimension;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;

/**
 * Panel de Navegaci�n que muestra la localizaci�n del Robot as� como la direcci�n en la que mira.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 *
 */

public class NavigationPanel extends JPanel implements ViewInterface
{
	private String pathImg = "src/tp/pr5/gui/images/"; // "bin/top/pr5/gui/images"
	
	private CityMapPanel cityPanel;
	
	private JLabel walleImgLabel;
	private ImageIcon walleImg;
	
	
	/**
	 * Constructor NavitagionPanel sin parametros que inicializar� el jpanel a partir del metodo iniciarNavigationPanel
	 * @params Null
	 */
	public NavigationPanel()
	{
		iniciarNavigationPanel();
	}
	
	/**
     * Inicializa todos los componentes del panel y los consfigura adecuadamente.
     */
	private void iniciarNavigationPanel()
	{
		// Panel configuration
		this.setPreferredSize(new java.awt.Dimension(1010, 380));
		 
		//Inicializar componentes.
		cityPanel = new CityMapPanel();
        walleImg = new ImageIcon(pathImg+"walleNorth.png");
        walleImgLabel = new JLabel(walleImg);
		
		//Configuraci�n de los elementos
        walleImgLabel.setPreferredSize(new Dimension(120,200));
		
		  
		//A�adir al panel
        this.add(walleImgLabel, JPanel.LEFT_ALIGNMENT);
        this.add(cityPanel, JPanel.LEFT_ALIGNMENT);
	}

	/**
	 * M�todo que se encarga de actualizar la direcci�n a la que mira la imagen de Wall-e y el estado de las celdas.
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
//		RobotEngineObserver model = (RobotEngineObserver) o;
		// Update Walle Img
//		String direction = model.getRobotHeading().toString().toLowerCase();
//		char[] c = direction.toCharArray();
//		c[0] = Character.toUpperCase(c[0]);
//		direction = new String(c);
//		walleImg = new ImageIcon(pathImg + "walle" + direction + ".png");
//		walleImgLabel.setIcon(walleImg);
		// Update CityMap
//		this.cityPanel.update(o, arg);
		
		
	}
	
	/**
	 * Metodo para fijar el controlador del subpanel CityMap.
	 */
	@Override
	public void fijarControlador(EventListener controlador) 
	{
		this.cityPanel.fijarControlador(controlador);
	}
	
	
	/**
	 * Metodo que devuelve el subpanel CityMapPanel 
	 * @return citymappanel
	 */
	public CityMapPanel getCityMapPanel()
	{
		return this.cityPanel;
	}

	@Override
	public void arranca() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Realizamos lso cambios necesarios en la vista para notificar que el place ha cambiado.
	 * @param currentHeading - Direction
	 * @param placeInfo - Place
	 */
	public void robotArrivesAtPlace(Direction currentHeading, Place placeInfo){
		this.cityPanel.setPlaceCell(placeInfo, currentHeading, true);
	}

	public void updateImg(Direction currentHeading)
	{
		String direction = currentHeading.toString().toLowerCase();
		char[] c = direction.toCharArray();
		c[0] = Character.toUpperCase(c[0]);
		direction = new String(c);
		walleImg = new ImageIcon(pathImg + "walle" + direction + ".png");
		walleImgLabel.setIcon(walleImg);
	}
}
