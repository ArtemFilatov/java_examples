package tp.pr3.comandos;

import tp.pr3.Main;
import tp.pr3.control.Controlador;
import tp.pr3.control.Jugador;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;

/**
 * Clase con el comando para poner una ficha
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */

public class ComandoPoner implements Comando{
	
	private Controlador controlador;

	/**
	 * Constructor que recibe una referencia al controlador..
	 * @param controlador
	 */
	public ComandoPoner(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public boolean analizar(String cadena) {
		return cadena.equalsIgnoreCase("Poner");
	}

	@Override
	public void ejecutar() {
		Jugador jugador = controlador.getJugadorTurno();
		Partida p = controlador.getPartida();
		Movimiento mov = jugador.getMovimiento(p.getTablero(), p.getTurno());
		try {
			p.ejecutaMovimiento(mov);
		}catch(MovimientoInvalido e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public boolean terminar() {
		return false;
	}

	@Override
	public String ayuda() {
		return "PONER: utilízalo para poner la siguiente ficha." + Main.LINE_SEPARATOR;
	}

}
