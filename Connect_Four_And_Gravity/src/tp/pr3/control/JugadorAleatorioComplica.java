package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Complica. En este caso cualquier columna es válida,
 *  pues si está llena, se hará hueco.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public class JugadorAleatorioComplica implements Jugador{

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int columna =-1;
		// Calculamos una columna aleatoria
		columna = (int) Math.floor((Math.random() * tab.getAncho()) + 1);
		return new MovimientoComplica(columna, color);
	}

}
