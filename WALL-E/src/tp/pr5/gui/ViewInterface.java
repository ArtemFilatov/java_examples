package tp.pr5.gui;

import java.util.EventListener;
import java.util.Observer;

import tp.pr5.RobotEngineObserver;
/**
 * Interfaz que extiende de Observer que nos servir� para establecer los controladores de las clases que la implementen.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 *
 */

public interface ViewInterface extends Observer{
	
	/**
     * M�todo que se encarga de fijar el controlador en los elementos de la vista de forma adecuada
     * @param controlador contiene el controlador encargado de la visrta.
     */
    public void fijarControlador(EventListener controlador);
    
    /**
     * Metodo que inicializa la vista. 
     */
	public void arranca();



}
