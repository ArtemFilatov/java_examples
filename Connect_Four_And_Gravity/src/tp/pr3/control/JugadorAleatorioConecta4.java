package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Conecta 4. Simplemente elige una columna aleatoria 
 * en el tablero, comprobando antes que se podrá colocar en ella (no está llena).
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public class JugadorAleatorioConecta4 implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int num=-1;
		int columna =-1;
		if(tab.getMovimientoLibre().size() >= 0){
			// Obtenemos un numero aleatorio comprendido entre 0 y el maximo array posicion
			num = (int) Math.floor((Math.random() * tab.getMovimientoLibre().size()) + 0);
			// Obtenemos la columna libre
			columna = tab.getMovimientoLibre().get(num).getX();
		}
		return new MovimientoConecta4(columna, color);
	}

}
