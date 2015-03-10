package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Gravity. Simplemente elige una posición aleatoria 
 * en el tablero, comprobando antes que esa casilla está vacía (se podrá poner).
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public class JugadorAleatorioGravity implements Jugador{

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int num=-1;
		int columna =-1;
		int fila =-1;
		if(tab.getMovimientoLibre().size() >= 0){
			// Obtenemos un numero aleatorio comprendido entre 0 y el maximo array posicion
			num = (int) Math.floor((Math.random() * tab.getMovimientoLibre().size()) + 0);
			// Obtenemos la columna libre
			columna = tab.getMovimientoLibre().get(num).getX();
			// Obtenemos la fila libre
			fila = tab.getMovimientoLibre().get(num).getY();
		}
		return new MovimientoGravity(columna, fila, color);
	}

}
