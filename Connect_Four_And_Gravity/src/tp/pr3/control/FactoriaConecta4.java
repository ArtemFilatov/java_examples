package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.ReglasConecta4;
import tp.pr3.logica.ReglasJuego;

/**
 * Implementación de la factoría para el juego del Conecta 4. Los métodos devuelven los objetos capaces 
 * de jugar a ese juego.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 *
 */

public class FactoriaConecta4 implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoConecta4(in, this);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

}
