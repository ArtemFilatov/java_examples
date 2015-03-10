package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.ReglasJuego;

/**
 * Interfaz que aglutina los métodos de construcción de los distintos objetos involucrados 
 * en un juego concreto. Habrá una implementación de este interfaz por cada tipo de juego 
 * soportado.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */

public interface FactoriaTipoJuego {

	/**
	 * Construye las reglas del juego concreto.
	 * 
	 * @return El objeto que implementa las reglas del juego al que representamos.
	 */
	public ReglasJuego creaReglas();
	
	
	/**
	 * Construye un movimiento para el juego concreto. Es posible que la implementación no utilice todos los parámetros.
	 * @param col - Columna donde se quiere colocar.
	 * @param fila - Fila donde se quiere colocar. En juegos como Conecta 4 o Complica este parámetro no se utilizará.
	 * @param color - Color de la ficha que se pondrá.
	 * @return Objeto de tipo Movimiento capaz de ejecutar el movimiento para el juego concreto.
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	/**
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por consola el siguiente movimiento a realizar.
	 * @param in - Scanner de la entrada que utilizará el objeto para preguntar al usuario.
	 * @return Objeto jugador que utilizar para preguntar al usuario el siguiente movimiento.
	 */
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in);
	
	/**
	 * Construye el objeto Jugador capaz de jugar al juego concreto de forma aleatoria.
	 * @return Objeto jugador que juega de forma aleatoria.
	 */
	public Jugador creaJugadorAleatorio();
	
}
