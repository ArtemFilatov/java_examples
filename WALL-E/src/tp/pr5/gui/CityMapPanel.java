package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.Place;

/**
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class CityMapPanel extends JPanel implements ViewInterface
{
	
	private final int MAX_HEIGHT_BUTTONS = 11;
	private final int MAX_WIDTH_BUTTONS = 11;
	private boolean initMap;

	private PlaceCell[][] placeCell;
	private int row;
	private int col;
	
	
	/**
	 * Constructor sin parametros que inicia el JPanel
	 */
	public CityMapPanel()
	{
		this.row = 5;
		this.col = 5;
		this.initMap = true;
		iniciarCityMapPanel();
	}
	
	/**
	 * M�todo que inicializa, configure y a�ade los componentes de CityMapPanel
	 */
	public void iniciarCityMapPanel()
	{
		// Panel configuration
		this.setBorder(new TitledBorder("City Map"));
	    this.setName("cityMapForm");
		this.setPreferredSize(new java.awt.Dimension(880, 370));
		 
		//Inicializar componentes.	
		    placeCell = new PlaceCell[11][11];
		for(int i = 0; i < MAX_HEIGHT_BUTTONS; i++)
		{
			for(int j = 0; j < MAX_WIDTH_BUTTONS; j++)
			{
				placeCell[i][j] = new PlaceCell();
			}
		}
		  
		//A�adir al panel
		for(int i = 0; i < MAX_HEIGHT_BUTTONS; i++)
		{
			for(int j = 0; j < MAX_WIDTH_BUTTONS; j++)
			{
				this.add(placeCell[i][j]);
			}
		}
		
		LayoutManager thisLayout = new GridLayout(11,11);
		this.setLayout(thisLayout);
	}
	
	/**
	 * Metodo que se encarga de setear tanto el place como la posicion de la nueva celda en nuestro array de celdas
	 * @param placeInfo
	 * @param direction
	 * @param newPlace
	 */
	public void setPlaceCell(Place placeInfo, Direction direction, Boolean newPlace)
	{
		if(initMap)
		{
			placeCell[row][col].setPlace(placeInfo);
			placeCell[row][col].setActivePlaceCell();
			initMap = false;
		}
		else{
			if((row >= 0 && row <= 11) && (col >= 0 && col <= 11))
			{
				placeCell[row][col].setUnactivePlaceCell(); // Desactivamos la celda actual
				switch(direction)
				{
					case NORTH:
						row -= 1;
						break;
					case WEST:
						col -= 1;
						break;
					case SOUTH:
						row += 1;
						break;
					case EAST:
						col += 1;
						break;
					default:
						break;
						
				}
				if(newPlace)
				{
					placeCell[row][col].setPlace(placeInfo);
					
				}
				placeCell[row][col].setActivePlaceCell();
			}
		}
	}
	

	/**
	 * Metodo que se encarga de actualizar las celdas
	 * M�todo que se encarga de actualizar las celdas.
	 */
	@Override
	public void update(Observable o, Object arg){}

	/**
	 * Fijamos el controlador de las celdas del cityMap
	 */
	@Override
	public void fijarControlador(EventListener controlador) 
	{
		for(int i = 0; i < MAX_HEIGHT_BUTTONS; i++)
		{
			for(int j = 0; j < MAX_WIDTH_BUTTONS; j++)
			{
				placeCell[i][j].fijarControlador(controlador);
			}
		}
	}

	@Override
	public void arranca() {
		// TODO Auto-generated method stub
		
	}
	
	

}
