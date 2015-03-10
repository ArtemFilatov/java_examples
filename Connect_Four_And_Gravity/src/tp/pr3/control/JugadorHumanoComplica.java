package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

/**
 * Jugador que juega de forma humana a Complica
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public class JugadorHumanoComplica implements Jugador{
	
	/**
	 * Referencia al scanner
	 */
	private Scanner sc;
	private FactoriaTipoJuego factoria;
	
	public JugadorHumanoComplica(Scanner scanner, FactoriaTipoJuego factoriaComplica) {
		sc = scanner;
		factoria = factoriaComplica;
	}
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		// Leemos las entradas de consola y creamos el movimiento con esa columna y fila
		System.out.print("Introduce la columna: "); 
		int column = sc.nextInt();
		return factoria.creaMovimiento(column, 0, color);
	}

}
