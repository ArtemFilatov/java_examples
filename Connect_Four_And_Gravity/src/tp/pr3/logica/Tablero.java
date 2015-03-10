package tp.pr3.logica;

import java.util.ArrayList;

/**
 * Almacena la informacion de un tablero rectangular. El tamaño se fija en el momento de la construccion,
 *  y contiene metodos para acceder a la informacion de cada celda y para colocar fichas.
 *  
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * @since Practica 2
 */
public class Tablero {
	
	/**
	 * Ancho
	 */
	private int x;
	/**
	 * Alto
	 */
	private int y;
	/**
	 * Tablero
	 */
	private Ficha[][] tablero;
	
	/**
	 * Salto de linea 
	 */
	private final String LINE_SEPARATOR = System.getProperty("line.separator").toString();
	
	/**
	 * Construye un tablero vacio.
	 * @param tx - Tamaño en la coordenada x (o numero de columnas).
			  ty - Tamaño en la coordenada y y (o numero de filas).
	 */
	public Tablero(int tx, int ty){
		reset(tx,ty);
	}
	
	
	/**
	 * Metodo para obtener el alto del tablero.
	 * @return Numero de filas del tablero.
	 */
	public int getAlto(){
		return y;
	}
	
	/**
	 * Metodo para obtener el ancho del tablero.
	 * @return Numero de columnas del tablero
	 */
	public int getAncho(){
		return x;
	}
	
	/**
	 * Metodo para acceder a la informacion de una casilla o celda concreta.
	 * @param x - Numero de columna (1..ancho)
	 * @param y - Numero de fila (1..alto)
	 * @return Informacion de la casilla. Si la casilla no es valida, devuelve Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y){
		if(!rangoValidoX(x) || !rangoValidoY(y))
			return Ficha.VACIA;
		return tablero[x-1][y-1];
	}
	
	/**
	 * Permite especificar el nuevo contenido de una casilla. Tambien puede utilizarse para quitar una ficha
	 * @param x - Numero de columna (1..ancho)
	 * @param y - Numero de fila (1..alto)
	 * @param color - Color de la casilla. Si se indica Ficha.VACIA, la celda quedara sin ficha.
	 */
	public void setCasilla(int x, int y, Ficha color){
		if(rangoValidoX(x) && rangoValidoY(y))
			tablero[x-1][y-1] = color;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int fila = 1; fila <= y; fila++){
			sb.append("|");
			for(int columna = 1; columna <= x; columna++){
				Ficha ficha = getCasilla(columna, fila);
				sb.append(Ficha.valorTablero(ficha));
			}
			sb.append("|" + LINE_SEPARATOR);
		}
		sb.append("+");
		for(int i=0; i<getAncho(); i++)
			sb.append("-");
		sb.append("+" + LINE_SEPARATOR);
		sb.append(" ");
		for(int i=1; i<getAncho()+1; i++)
			sb.append(i);
		sb.append(LINE_SEPARATOR);
		return sb.toString();
	}
	
	/**
	 * Devuelve si el tablero esta lleno
	 * @return true si el tablero esta lleno, false en caso contrario
	 */
	public boolean isFull(){
		boolean isFull=true;
		for(int i = 0; i < x; i++) 
			for(int j = 0; j < y; j++)
				if(tablero[i][j] == Ficha.VACIA)
					isFull=false;
		return isFull;
	}
	
	/**
	 * Devuelve true si el valor de x esta comprendido entre 1 y getAncho.
	 * @param x - Int
	 */
	public boolean rangoValidoX(int x) {
		return x >= 1 && x <= getAncho();
	}
	
	/**
	 * Devuelve true si el valor de Y esta comprendido entre 1 y getAlto()
	 * @param y - Int
	 * @return
	 */
	public boolean rangoValidoY(int y)
	{
		return y >= 1 && y <= getAlto();
	}
	
	
	public ArrayList<Posicion> getMovimientoLibre(){
		ArrayList<Posicion> libre = new ArrayList<Posicion>(); 
		
		for(int i = 0; i < x; i++) 
			for(int j = 0; j < y; j++)
				if(tablero[i][j] == Ficha.VACIA)
					libre.add(new Posicion(i+1, j+1));
		return libre;
		
	}
	
	/**
	 * Resetea el tablero
	 * @param tx Columna
	 * @param ty Fila
	 */
	private void reset(int tx, int ty){
		x = tx;
		y = ty;
		if(!rangoValidoX(x) || !rangoValidoY(y)){
			x = 1;
			y = 1;
		}
		// Inicializamos el tablero
		tablero = new Ficha[x][y];
		for(int i = 0; i < x; i++) 
			for(int j = 0; j < y; j++)
				tablero[i][j] = Ficha.VACIA;
		
	}
}
