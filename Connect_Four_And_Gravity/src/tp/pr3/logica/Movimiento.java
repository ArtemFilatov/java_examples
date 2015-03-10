package tp.pr3.logica;


/**
 * Clase que representa el movimiento de un jugador. Tiene un método para ejecutar el movimiento
 *  sobre la partida, y otro para deshacerlo. Es una clase abstracta; habrá una clase no abstracta 
 *  por cada tipo de juego soportado.
 *  
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public abstract class Movimiento {
	
	protected int donde;
	protected int fila;
	protected Ficha color;
	
	/**
	 * Constructor
	 * @param donde: columna
	 * @param color: color ficha
	 */
	public Movimiento(int donde, Ficha color) {
		this.donde = donde;
		this.color = color;
	}
	
	/**
	 * Constructor con la fila
	 * @param donde: columna
	 * @param fila: fila
	 * @param color: color ficha
	 */
	public Movimiento(int donde, int fila, Ficha color) {
		this.donde = donde;
		this.fila = fila;
		this.color = color;
	}
	
	
	/**
	 * Devuelve el color del jugador al que pertenece el movimiento. (Puede hacerse abstracto)
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */
	public Ficha getJugador(){
		return color;
	}
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. Se puede dar por 
	 * cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento. 
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab - Tablero sobre el que ejecutar el movimiento
	 * @throws MovimientoInvalido 
	 */
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. Se puede dar por cierto que el movimiento 
	 * se ejecutó sobre ese tablero; en caso contrario, el comportamiento es indeterminado. Por lo tanto, 
	 * es de suponer que el método siempre funcionará correctamente.
	 * @param tab - Tablero de donde deshacer el movimiento.
	 */
	public abstract void undo(Tablero tab);


}
