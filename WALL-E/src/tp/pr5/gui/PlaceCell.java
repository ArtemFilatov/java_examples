package tp.pr5.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JButton;

import tp.pr5.Place;
import tp.pr5.PlaceInfo;

/**
 * Clase que extiende de JButton e implementa a View Interface. 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 *
 */

public class PlaceCell extends JButton implements ViewInterface
{
	private PlaceInfo place;
	
	
	/**
	 * 
	 */
	public PlaceCell()
	{
		super();
		this.place = null;
		this.setName("");
		
	}
	
	/**
	 * Setter de place y del nombre del jbutton
	 * @param placeInfo
	 */
	public void setPlace(PlaceInfo placeInfo)
	{
		this.place = placeInfo;
		this.setName("jButton" + this.place.getName());
		this.setText(this.place.getName());
	}
	
	/**
	 * Setter del background del boton para saber que estamos en el.
	 */
	public void setActivePlaceCell()
	{
		this.setBackground(Color.GREEN);
	}
	
	/**
	 * Setter del background del boton para saber que ya no estamos en el
	 */
	public void setUnactivePlaceCell()
	{
		this.setBackground(Color.GRAY);
	}

	/**
	 * 
	 */
	public void update(Observable arg0, Object arg1) 
	{
		
	}

	/**
	 * Fijamos el controlador de cada boton.
	 */
	public void fijarControlador(EventListener controlador) 
	{
		this.addActionListener((ActionListener)controlador);
	}

	@Override
	public void arranca() {
		// TODO Auto-generated method stub
		
	}

}
