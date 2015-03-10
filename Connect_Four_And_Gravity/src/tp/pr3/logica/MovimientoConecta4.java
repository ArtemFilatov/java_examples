package tp.pr3.logica;

/**
 * Clase que implementa el movimiento para el juego del Conecta 4. Se deben implementar los métodos 
 * abstractos de la clase padre.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class MovimientoConecta4 extends Movimiento{
	
	/**
	 * Constructor del objeto.
	 * @param donde - Columna en la que se colocará la ficha
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoConecta4(int donde, Ficha color) {
		super(donde, color);
	}


	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido{
		// Columna valida
		if(!tab.rangoValidoX(donde))
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho() + ".");
	
		boolean puesta = false;
		Ficha ficha;
		int filaAux= tab.getAlto(); 
		// Bucle para obtener la fila correcta para insertar la nueva ficha
		while(filaAux>= 1 && !puesta){ 
			ficha = tab.getCasilla(donde, filaAux);
			// Si esta vacia, hemos dado con la fila
			if(ficha == Ficha.VACIA){ 
				// Insertamos la nueva ficha
				tab.setCasilla(donde, filaAux, color);
				puesta = true; 
			}
			// No es la fila correcta por lo que seguimos buscando
			else
				filaAux--; 
		}
		if(!puesta)
			throw new MovimientoInvalido("Columna llena.");
		
		// Actualizamos la fila
		fila = filaAux;

	}
	
	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(donde, fila, Ficha.VACIA);
	}


}
