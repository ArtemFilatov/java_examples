package tp.pr3.logica;

/**
 * 
 * Implementaci�n de las reglas del Gravity. Se deben implementar todos los m�todos del interfaz, adem�s del constructor
 *
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */
public class ReglasGravity extends RJabstracts {
	
	private int numCols;
	private int numFilas;
	

	/**
	 * Constructor de la clase.
	 * @param numCols - N�mero de columnas del tablero.
	 * @param numFilas - N�mero de filas del tablero.
	 */
	public ReglasGravity(int numCols, int numFilas) {
		this.numCols = numCols;
		this.numFilas = numFilas;
		
	}
	
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(numCols, numFilas);
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha _ganador = Ficha.VACIA;
		if(t.isFull())
			_ganador = Ficha.VACIA;
		else {
			int column = ultimoMovimiento.donde;
			int fila = ultimoMovimiento.fila;
			if(esLinea(t, column, fila, t.getCasilla(column, fila))){
				_ganador = t.getCasilla(column, fila);
			}		
		}
		return _ganador;
	}

	@Override
	public boolean tablas(Ficha ultimo, Tablero t) {
		return t.isFull();
	}
	

}
