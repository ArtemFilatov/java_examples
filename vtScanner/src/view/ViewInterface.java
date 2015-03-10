package view;

import java.util.EventListener;
import java.util.Observer;

public interface ViewInterface extends Observer
{
	/**
     * Método que se encarga de fijar el controlador en los elementos de la vista de forma adecuada
     * @param controlador contiene el controlador encargado de la visrta.
     */
    public void fijarControlador(EventListener controlador);

}
