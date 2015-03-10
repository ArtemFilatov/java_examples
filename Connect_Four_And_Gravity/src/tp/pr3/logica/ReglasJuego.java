package tp.pr3.logica;

/**
 * Interfaz que representa las reglas de un juego concreto. La partida delega en un objeto de este 
 * interfaz para hacer avanzar la partida.
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * @since Practica 2
 */
public interface ReglasJuego {

	/**
	 * Construye el tablero que hay que utilizar para la partida, según las reglas del juego.
	 * @return Tablero a utilizar. El estado del tablero será el de inicio de la partida.
	 */
	public Tablero iniciaTablero();
	
	/**
	 * Devuelve el color del jugador que comienza la partida.
	 * @return Color del primer jugador en colocar ficha.
	 */
	public Ficha jugadorInicial();
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. Devuelve el color del ganador (si lo hay).
	 * @param ultimoMovimiento - Ultimo movimiento realizado. Las distintas implementaciones pueden utilizar este
	 *  parámetro para optimizar la búsqueda del ganador.
	 * @param t - Estado del tablero.
	 * @return color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA (eso NO significa necesariamente
	 *  que la partida haya terminado en tablas).
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento,
            Tablero t);
	
	/**
	 * Devuelve true si, con el estado del tablero dado, la partida ha terminado en tablas.
	 * @param ultimoEnPoner - Jugador que acaba de poner ficha
	 * @param t - Estado del tablero
	 * @return true si la partida ha terminado sin ganador.
	 */
	public boolean tablas(Ficha ultimoEnPoner,
            Tablero t);
	

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @param ultimoEnPoner - Último jugador en poner ficha
	 * @param t - Estado del tablero.
	 * @return Siguiente jugador que debe poner ficha.
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner,
            Tablero t);
	

	
}
