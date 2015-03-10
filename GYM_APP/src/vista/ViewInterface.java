package vista;

import java.util.EventListener;
import java.util.Observer;

/**
 * Interfaz que implementaran todas las ventanas
 * incluira los metodos necesarios para trabajar con el controlador
 * @author Julia
 *
 */
public interface ViewInterface extends Observer{
	
	/**
     * Metodo que se encarga de fijar el controlador en los elementos de la vista de forma adecuada
     * @param controlador contiene el controlador encargado de la visrta.
     */
    public void fijarControlador(EventListener controlador);
    
    /**
     * Metodo que inicializa la vista. 
     */
	public void arranca();

	/**
	 * Metodo q sacara un JOptionPanel con un mensaje
	 * @param msg el mensaje a mostrar 
	 */
	void muestraVentanita(String msg);
	

}