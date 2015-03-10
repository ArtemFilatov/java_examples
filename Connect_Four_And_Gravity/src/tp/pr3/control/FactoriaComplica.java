package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasJuego;

/**
 * Implementaci�n de la factor�a para el juego del Complica. Los m�todos devuelven los objetos capaces de jugar 
 * a ese juego.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 *
 */

public class FactoriaComplica implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		Jugador jugador = new JugadorHumanoComplica(in, this);
		return jugador;
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

}
