package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

/**
 * Interfaz que representa un jugador; cuando el controlador quiere saber qué movimiento 
 * ejecutar a continuación, le pregunta al jugador que tiene el turno. NO es un concepto de 
 * la partida (que va recibiendo las órdenes de qué movimientos ejecutar), sino del que 
 * controla el flujo de ejecución de la aplicación. De ahí que esté en el paquete control y 
 * no en el paquete logica.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public interface Jugador {

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador. Las distintas implementaciones pueden hacer cosas distintas
	 *  si se les pide un movimiento sobre un tablero en el que no se puede colocar ficha del color indicado, 
	 *  como devolver null, un movimiento incorrecto o incluso no terminar.
	 * @param tab - Estado del tablero donde poner.
	 * @param color - Color de la ficha que hay que colocar. Las distintas implementaciones no tienen por qué 
	 * hacer uso de este parámetro, si los objetos han sido configurados previamente en el momento de la construcción. 
	 * Se añade para facilitar la implementación de algunas clases derivadas.
	 * 
	 * @return Movimiento que se desea ejecutar. Dependiendo de la implementación, el movimiento puede ser correcto o no.
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color);
	
}
