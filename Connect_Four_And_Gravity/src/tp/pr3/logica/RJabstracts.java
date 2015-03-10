package tp.pr3.logica;

/**
 * Contiene los algortimos para verificar si hay ganador
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * @since Practica 2
 *
 */
public abstract class RJabstracts implements ReglasJuego{
	
	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}
	
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha ficha=null;
		if(ultimoEnPoner== Ficha.BLANCA)
			ficha= Ficha.NEGRA;
		else if(ultimoEnPoner == Ficha.NEGRA)
			ficha = Ficha.BLANCA;
		return ficha;
	}
	
	// Metodos nuestros
	/**
	 * Algoritmo que devuelve true si hay un ganador, false en caso contrario
	 * @param column de la ficha de la ultima jugada
	 * @param fila de la ficha de la ultima jugada
	 * @param color de la ficha de la ultima jugada
	 * @return true: hay un ganador. False: no hay ganador
	 */
	protected boolean esLinea(Tablero t, int column, int fila, Ficha color){
		return esLineaVertical(t, column, fila, color) || esLineaHorizontal(t, column, fila, color) ||
				esLineaDiagonal_1(t, column, fila, color) || esLineaDiagonal_2(t, column, fila, color);
	}
	
	/**
	* Comprueba si es 4 en raya vertical.
	* @param column - int
	* @param fila - int 
    * @param color- Ficha
	* @return True si es 4 en raya, Falso en caso contrario
	*/
	protected boolean esLineaVertical(Tablero t, int column, int fila, Ficha color) {
		int cuentAbajo = cuentaFichasEnUnaDireccion(t, column, fila, color, 0, -1);
		int cuentaArriba = cuentaFichasEnUnaDireccion(t, column, fila, color, 0, 1);
		return cuentaArriba + cuentAbajo > 4;
	}
				
	/**
	* Comprueba si es 4 en raya horizontal.
	* @param column - int
	* @param fila - int 
	* @param color- Ficha
	* @return True si es 4 en raya, Falso en caso contrario
	*/
	protected boolean esLineaHorizontal(Tablero t, int column, int fila, Ficha color) {
		int cuentIzq = cuentaFichasEnUnaDireccion(t, column, fila, color, 1, 0);
		int cuentDer = cuentaFichasEnUnaDireccion(t, column, fila, color, -1, 0);
		return (cuentIzq + cuentDer > 4);
	}
				
	/**
	* Comprueba si es 4 en raya diagonal 1 (Empezando arriba a la derecha).
	* @param column - int
	* @param fila - int 
	* @param color- Ficha
	* @return True si es 4 en raya, Falso en caso contrario
	*/
	protected boolean esLineaDiagonal_1(Tablero t, int column, int fila, Ficha color) {
		int cuentIzqUp = cuentaFichasEnUnaDireccion(t, column, fila, color, 1, 1);
		int cuentDerDown = cuentaFichasEnUnaDireccion(t, column, fila, color, -1, -1);
		return (cuentIzqUp + cuentDerDown > 4);
	}
				
	/**
	* Comprueba si es 4 en raya diagonal 2 (Empezando arriba a la izquierda).
	* @param column - int
	* @param fila - int 
	* @param color- Ficha
    * @return True si es 4 en raya, Falso en caso contrario
	*/
	protected boolean esLineaDiagonal_2(Tablero t, int column, int fila, Ficha color) {
		int cuentDerUp = cuentaFichasEnUnaDireccion(t, column, fila, color, -1, 1);
		int cuentIzqDown = cuentaFichasEnUnaDireccion(t, column, fila, color, 1, -1);
		return (cuentDerUp + cuentIzqDown > 4);
	}
				
	/**
	* Comprueba si es 4 en raya en la direccion (x,y)
	* @param column de la ficha de la ultima jugada
	* @param fila de la ficha de la ultima jugada
	* @param color de la ficha de la ultima jugada
	* @return Devuelve el numero de fichas iguales que la pasada
	* @param x Valor para desplazarse en el sentido de las X
	* @param y Valor para desplazarse en el sentido de las Y
	* @return
	*/
	protected int cuentaFichasEnUnaDireccion(Tablero t, int column, int fila, Ficha color, int x, int y) {
		boolean seguida = true;
		int contador = 1;
		// Invertimos el vector direccion por la orientacion del tablero.
		int indiceX = column+(x*-1);
		int indiceY = fila+(y*-1);
		while(contador < 4 && seguida && t.rangoValidoX(indiceX) && t.rangoValidoY(indiceY)){
			if(t.getCasilla(indiceX, indiceY)== color){
				// Invertimos el vector direccion por la orientacion del tablero.
				indiceX += x*-1;
				indiceY += y*-1;
				contador++;
			} else
				seguida = false;
		}
		return contador;
	}
	


}