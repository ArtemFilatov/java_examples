package tp.pr3.logica;

/**
 * Clase que implementa el movimiento para el juego del Complica. Se deben implementar los métodos 
 * abstractos de la clase padre.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class MovimientoComplica extends Movimiento{
	
	/**
	 * Guarda el color de la ficha que se quito si se desplazo el tablero
	 */
	private Ficha fichaDesplazada;
	
	/**
	 * Constructor del objeto.
	 * @param donde - Columna en la que se colocará la ficha
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoComplica(int donde, Ficha color) {
		super(donde, color);
		this.fichaDesplazada = Ficha.VACIA;
	}


	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		// Columna valida
		if(!tab.rangoValidoX(donde))
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho() + ".");
		
		boolean puesta = false;
		Ficha ficha;
		int filaAux= tab.getAlto(); 
		// Bucle para obtener la fila correcta para insertar la nueva ficha
		while(!puesta){ 
			ficha = tab.getCasilla(donde, filaAux);
			// Si esta vacia, hemos dado con la fila
			if(filaAux == 0){
				this.fichaDesplazada= tab.getCasilla(donde, tab.getAlto());
				//System.out.println("Fichadespla " + fichaDesplazada);
				for(int i=tab.getAlto(); i > 1; i--){
					tab.setCasilla(donde, i, tab.getCasilla(donde, i-1));
				}
				filaAux+=1;
				puesta = true;
			}
			if(ficha== Ficha.VACIA){ 				
				// Insertamos la nueva ficha
				tab.setCasilla(donde, filaAux, color); 
				puesta = true; 
			}
			// No es la fila correcta por lo que seguimos buscando
			else
				filaAux--; 
			}
		
		// Actualizamos la fila
		fila = filaAux;
			
	}
	
	@Override
	public void undo(Tablero tab) {
		if(fichaDesplazada != Ficha.VACIA){
			
			// Reorganizamos todas las filas de la columna. Volviendo a su estado orginal
			for(int i=1; i < tab.getAlto(); i++){
				tab.setCasilla(donde, i, tab.getCasilla(donde, i+1));
			}
			tab.setCasilla(donde, tab.getAlto(), fichaDesplazada);
		}
		else
			// Metemos la ultima
			tab.setCasilla(donde, fila, Ficha.VACIA);
	
	}
	

	
}
