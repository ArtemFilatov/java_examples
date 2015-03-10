package tp.pr3.control;

import java.util.Scanner;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Conecta4
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public class JugadorHumanoConecta4 implements Jugador{

	/**
	 * Referencia al scanner
	 */
	private Scanner sc;
	private FactoriaTipoJuego factoria;
	
	public JugadorHumanoConecta4(Scanner scanner, FactoriaTipoJuego factoriaConecta4) {
		sc = scanner;
		factoria = factoriaConecta4;
	}
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		// Leemos las entradas de consola y creamos el movimiento con esa columna
		System.out.print("Introduce la columna: "); 
		int column = sc.nextInt();
		return factoria.creaMovimiento(column, 0, color);
	}

}
