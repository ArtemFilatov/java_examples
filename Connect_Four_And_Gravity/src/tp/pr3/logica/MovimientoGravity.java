package tp.pr3.logica;

/**
 * Clase que implementa el movimiento para el juego del Gravity. Se deben implementar los 
 * m�todos abstractos de la clase padre.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */

public class MovimientoGravity extends Movimiento{

	
	/**
	 * Constructor del objeto.
	 * @param donde - Columna en la que se colocará la ficha
	 * @param fila  - Fila en la que se colocara la ficha
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	
	 */
	public MovimientoGravity(int donde, int fila, Ficha color) {
		super(donde, fila, color);
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		// 0. Columna valida
		if(!tab.rangoValidoX(donde) || !tab.rangoValidoY(fila))
			throw new MovimientoInvalido("Posición incorrecta.");
		
		// 0.1 Comprobamos que la posici�n est� disponible.
		if(Ficha.VACIA != tab.getCasilla(donde,  fila))
			throw new MovimientoInvalido("Casilla ocupada.");


		// 1. Definimos las distancias del tablero en todas las direcciones
		// desde la ficha a los bordes.
		int xi, xj, yi, yj;
		xi = donde-1;
		xj = tab.getAncho() - donde;
		yi = fila-1;
		yj = tab.getAlto() - fila;
		int xt, yt;
		xt = xi - xj; // izquierda - derecha
		yt = yi - yj; // arriba - abajo
		
		// 2. Calculamos hacia donde nos tenemos que mover
		int distx = 0, disty = 0;
		int dx, dy;
		if(xt < 0) { // Izquierda
			dx = -1;
			distx = xi;
		} else if(xt > 0) { // Derecha
			dx = 1;
			distx = xj;
		} else { // Se cancela por tanto no se desplaza horizontalmente
			dx = 1;
			distx=tab.getAncho()+1;
		}
		
		if(yt < 0 ) { // Arriba
			dy = 1;
			disty = yi;
		} else if(yt > 0) { // Abajo
			dy = -1;
			disty = yj;
		} else { // Se cancela por tanto no se desplaza verticalmente
			dy = 0;
			disty=tab.getAlto()+1;
		}
		
		// 3. Calculamos la posici�n final de la ficha
		
		// 3.1 Nos quedamos en el centro? 
		if(xt == 0 && yt == 0) {
			donde = tab.getAncho()/2 + 1;
			fila = tab.getAlto()/2 + 1;
		} else if(distx == disty) { // 3.2 Nos movemos en diagonal?
			avanza(tab,dx,dy*-1);
		} else { // 3.3 Que lado ejerce mas gravedad?
			if(distx < disty) { // Horizontalmente
				avanza(tab,dx,0);
			} else { // Verticalmente
				avanza(tab,0,dy*-1);
			}

		}
	
		// 5. Colocamos la casilla.
		tab.setCasilla(donde, fila, color);
	}
	
	/**
	 * Avanza hasta que ya no puede m�s modificando los valores en funci�n de la direcci�n definida.
	 * @param tab - Referencia al tablero.
	 * @param x - Columna actual
	 * @param y - Fila actual
	 * @param dx - razon de movilidad en el eje de las x
	 * @param dy - razon de movilidad en el eje de las y
	 */
	private void avanza(Tablero tab,int dx, int dy) {
		int xValida = donde, yValida = fila;
		Ficha ficha = tab.getCasilla(xValida, yValida);
		while(ficha == Ficha.VACIA && tab.rangoValidoX(donde) && tab.rangoValidoY(fila)) {
			xValida = donde;
			yValida = fila;
			donde +=  dx;
			fila +=  dy;
			ficha = tab.getCasilla(donde, fila);
		}
		donde = xValida;
		fila = yValida;
		
	}

	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(donde, fila, Ficha.VACIA);
	}

}
