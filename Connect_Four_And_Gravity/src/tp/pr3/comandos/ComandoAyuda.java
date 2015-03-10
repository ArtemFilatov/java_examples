package tp.pr3.comandos;
import tp.pr3.Main;
import tp.pr3.control.Controlador;

/**
 * Clase con el comando para mostrar la ayuda
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */

public class ComandoAyuda implements Comando{
	

	/**
	 * Constructor que recibe una referencia al controlador..
	 * @param controlador
	 */
	public ComandoAyuda(Controlador controlador) {
		
	}

	@Override
	public boolean analizar(String cadena) {
		return cadena.equalsIgnoreCase("Ayuda");
	}

	@Override
	public void ejecutar() {
		System.out.println(GestorComandos.ayudaGestorComandos());
	}

	@Override
	public boolean terminar() {
		return false;
	}

	@Override
	public String ayuda() {
		return "AYUDA: muestra esta ayuda." + Main.LINE_SEPARATOR;
	}

}
