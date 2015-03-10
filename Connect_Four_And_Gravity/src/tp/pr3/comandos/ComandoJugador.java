package tp.pr3.comandos;

import tp.pr3.Main;
import tp.pr3.control.Controlador;

/**
 * Clase con el comando para cambiar el tipo de jugador (humano o aleatorio)
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */

public class ComandoJugador implements Comando {

	/**
	 * Referencia al controlador
	 */
	private Controlador controlador;
	
	/**
	 * Color de la ficha
	 */
	private String colorJugador;
	
	/**
	 * Contiene como es el jugador (humano o aleatorio)
	 */
	private String tipoJugador;

	/**
	 * Constructor que recibe una referencia al controlador..
	 * @param controlador
	 */
	public ComandoJugador(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public boolean analizar(String cadena) {
		String[] cadenas = cadena.split(" ");
		boolean result = false;
		if (cadenas[0].equalsIgnoreCase("Jugador") && 
				(cadenas[1].equalsIgnoreCase("Blancas") || cadenas[1].equalsIgnoreCase("Negras")) && 
				 (cadenas[2].equalsIgnoreCase("Humano") || cadenas[2].equalsIgnoreCase("Aleatorio"))) {
			this.colorJugador = cadenas[1];
			this.tipoJugador = cadenas[2];
			result = true;
		}
		return result;
	}

	@Override
	public void ejecutar() {
		controlador.cambiaJugador(colorJugador, tipoJugador);
	}

	@Override
	public boolean terminar() {
		return false;
	}

	@Override
	public String ayuda() {
		return "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador." + Main.LINE_SEPARATOR;
	}

}
