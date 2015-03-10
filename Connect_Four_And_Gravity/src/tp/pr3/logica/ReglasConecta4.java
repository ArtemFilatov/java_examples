package tp.pr3.logica;

/**
 * Implementacion de las reglas del Conecta 4. Se deben implementar todos los metodos del 
 * interfaz, ademas del constructor.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class ReglasConecta4 extends RJabstracts{
	
	/**
	 * Constructor de la clase, sin parámetros.
	 */
	public ReglasConecta4() {
	
	}
	
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(7, 6);
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha _ganador = Ficha.VACIA;
		if(t.isFull())
			_ganador = Ficha.VACIA;
		else {
			int column = ultimoMovimiento.donde;
			int fila= ultimoMovimiento.fila;
			if(esLinea(t, column, fila, t.getCasilla(column, fila))){
				_ganador = t.getCasilla(column, fila);
			}		
		}
		return _ganador;
	}

	@Override
	public boolean tablas(Ficha ultimo, Tablero t) {
		boolean tablas=true;
		for(int i=1; i< t.getAncho()+1; i++)
			if(t.getCasilla(i, 1) == Ficha.VACIA)
				tablas=false;
		
		return tablas;	
	}

}
