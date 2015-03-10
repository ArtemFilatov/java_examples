package tp.pr3.control;

import java.util.Scanner;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Gravity
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public class JugadorHumanoGravity implements Jugador{

	/**
	 * Referencia al scanner
	 */
	private Scanner sc;
	private FactoriaTipoJuego factoria;
	
	public JugadorHumanoGravity(Scanner in, FactoriaGravity factoriaGravity) {
		sc = in;
		factoria = factoriaGravity;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		// Leemos las entradas de consola y creamos el movimiento con esa columna y fila
		System.out.print("Introduce la columna: "); 
		int column = sc.nextInt();
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		return factoria.creaMovimiento(column,fila, color);
	}

}
