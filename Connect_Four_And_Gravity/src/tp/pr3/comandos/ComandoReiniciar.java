package tp.pr3.comandos;

import tp.pr3.Main;
import tp.pr3.control.Controlador;

/**
 * Clase con el comando para reiniciar la partida.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class ComandoReiniciar implements Comando{
	
	/**
	 * Referencia al controlador
	 */
	private Controlador controlador;

	/**
	 * Constructor que recibe una referencia al controlador..
	 * @param controlador
	 */
	public ComandoReiniciar(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public boolean analizar(String cadena) {
		return cadena.equalsIgnoreCase("Reiniciar");
	}

	@Override
	public void ejecutar() {
		controlador.getPartida().reset(controlador.getF().creaReglas());
    	System.out.println("Partida reiniciada.");
	}

	@Override
	public boolean terminar() {
		return false;
	}

	@Override
	public String ayuda() {
		return "REINICIAR: reinicia la partida." + Main.LINE_SEPARATOR;
	}

}
