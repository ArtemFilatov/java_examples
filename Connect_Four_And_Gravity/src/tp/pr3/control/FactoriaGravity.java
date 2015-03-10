package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;

/**
 * Implementaci�n de la factor�a para el juego del Gravity. Los m�todos devuelven los objetos capaces de jugar a ese juego.
 *  Dado que el tama�o del tablero de Gravity no est� fijo, sino que se puede cambiar en cada partida, 
 *  la factor�a puede configurarse con el tama�o del tablero que se quiere utilizar.
 *  
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 *
 */
public class FactoriaGravity implements FactoriaTipoJuego {
	
	private int columnas=10;
	private int filas=10;
	
	public FactoriaGravity(int columnas, int filas){
		this.columnas = columnas;
		this.filas = filas;
	}
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(columnas, filas);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(in, this);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

}
