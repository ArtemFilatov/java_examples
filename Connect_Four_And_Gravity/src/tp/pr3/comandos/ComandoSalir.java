package tp.pr3.comandos;

import tp.pr3.Main;
import tp.pr3.control.Controlador;

/**
 * Clase con el comando para salir del juego.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class ComandoSalir implements Comando{

	/**
	 * Constructor que recibe una referencia al controlador..
	 * @param controlador
	 */
	public ComandoSalir(Controlador controlador) {
	}

	@Override
	public boolean analizar(String cadena) {
		return cadena.equalsIgnoreCase("Salir");
	}

	@Override
	public void ejecutar() {
		
	}

	@Override
	public boolean terminar() {
		return true;
	}

	@Override
	public String ayuda() {
		return "SALIR: termina la aplicación." + Main.LINE_SEPARATOR;
	}

}
