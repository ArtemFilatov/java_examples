package tp.pr3.logica;

/**
 * Implementacion de las reglas del Complica. Se deben implementar todos los metodos del 
 * interfaz,ademas del constructor.
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * @since Practica 2
 */
public class ReglasComplica extends RJabstracts{
	

	/**
	 * Constructor de la clase, sin parametros.
	 */
	public ReglasComplica() {
		
	}
	
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(4,7);
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha _ganador = Ficha.VACIA;
		int column = ultimoMovimiento.donde;
		boolean gananBlancas = false;
		boolean gananNegras = false;
		
		for(int i = 1; i <= t.getAlto(); i++) {
			if(esLinea(t, column, i, t.getCasilla(column, i))) {
				if(t.getCasilla(column, i) == Ficha.BLANCA)
					gananBlancas = true;
					
				else if(t.getCasilla(column, i) == Ficha.NEGRA) 
					gananNegras = true;
			}
		}
		if(gananBlancas && gananBlancas != gananNegras) {
			_ganador = Ficha.BLANCA;
		} else if(gananNegras && gananNegras != gananBlancas) {
			_ganador = Ficha.NEGRA;
		} else
			_ganador = Ficha.VACIA;
			
		return _ganador;
	}

	@Override
	public boolean tablas(Ficha ultimo, Tablero t) {
		return false;
	}

	

}
