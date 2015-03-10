package tp.pr3.comandos;
import tp.pr3.Main;
import tp.pr3.control.Controlador;

/**
 * Clase con el comando para deshacer una jugada.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class ComandoDeshacer implements Comando {
	
	/**
	 * Referencia al controlador
	 */
	private Controlador controlador;

	/**
	 * Constructor que recibe una referencia al controlador..
	 * @param controlador
	 */
	public ComandoDeshacer(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public boolean analizar(String cadena) {
		return cadena.equalsIgnoreCase("Deshacer");
	}

	@Override
	public void ejecutar() {
		if(!controlador.getPartida().undo())
    		System.err.println("Imposible deshacer.");
	}

	@Override
	public boolean terminar() {
		return false;
	}

	@Override
	public String ayuda() {
		return "DESHACER: deshace el último movimiento hecho en la partida." + Main.LINE_SEPARATOR;
	}

}
