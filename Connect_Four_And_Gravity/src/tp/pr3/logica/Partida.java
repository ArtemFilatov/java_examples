package tp.pr3.logica;


/**
 * Clase para representar la información de una partida. Se encarga de almacenar el estado del tablero, 
 * a quién le toca, si ya hay un ganador, etc., así como la lista de movimientos que se han ido realizando
 * para poder deshacerlos. La partida guarda al menos los 10 últimos movimientos.
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * @since Practica 1
 */
public class Partida {
	
	/**
	 * Referencia principal del tablero
	 */
	private Tablero tablero;
	/**
	 * Referencia al turno actual
	 */
	private Ficha turno;
	/**
	 * Indica si la partida ha finalizado.
	 */
	private boolean terminada;
	/**
	 * Referencia al ganador de la partida (Blancas o Negras)
	 */
	private Ficha ganador;
	/**
	 * Pila con los ultimos 10 movimientos
	 */
	private Stack<Movimiento> stack;
	
	private ReglasJuego reglas;
	
	/**
	 * Construye una partida nueva.
	 */
	public Partida(ReglasJuego reglasJuego) {
		reglas = reglasJuego;
		reset(reglasJuego);
	}
	
	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse.
	 */
	public void reset(ReglasJuego reglasJuego) {
		reglas = reglasJuego;
		tablero = reglasJuego.iniciaTablero();
		turno = reglasJuego.jugadorInicial();
		ganador = null;
		terminada = false;
		stack = new Stack<Movimiento>(10);
	}
	
	/**
	 * Ejecuta el movimiento indicado
	 * @param mov mov - Movimiento a ejecutar. Se asume que el movimiento es compatible con las
	 *  reglas de la partida que se est� jugando (si se est� jugando a Conecta 4, el tipo de 
	 *  movimiento es Conecta 4, etc.). En caso contrario, el comportamiento 
	 *  es indeterminado.
	 * @throws MovimientoInvalido - si no se puede efectuar el movimiento. Es un error
	 *  intentar colocar una ficha del jugador que no tiene el turno, cuando la partida
	 *  est� terminada, columna llena, etc.
	 */
	public void ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido{

		// Vamos a ejecutar el movimiento.
		// Jugador tiene el turno, la partida no esta terminada y se puede ejecutar el movimiento
		if(mov.getJugador()!=turno || terminada)
			throw new MovimientoInvalido();
		
		mov.ejecutaMovimiento(tablero);
		stack.push(mov); 
		turno = reglas.siguienteTurno(turno, tablero); 
		
		// Comprobamos si la partida ha acabado (cuando hay ganador o acaba en tablas)
		Ficha fichaGanadora = reglas.hayGanador(mov, tablero);
		if(fichaGanadora != Ficha.VACIA || reglas.tablas(reglas.hayGanador(mov, tablero), tablero)){
			ganador = fichaGanadora;
			terminada=true;
		}
	}
	
	/**
	 * Deshace el último movimiento ejecutado.
	 * @return true si se pudo deshacer.
	 */
	public boolean undo() {
		boolean undo= false;
		// Tiene que existir un movimiento para poder deshacer
		if(!stack.isEmpty()){ 
			Movimiento mov = (Movimiento) stack.top();
			mov.undo(tablero);
			// Eliminamos del array la jugada
			stack.pop(); 
			undo=true; 
			turno = reglas.siguienteTurno(turno, tablero); 
		}
		return undo;
	}
	
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	
	public Ficha getTurno(){
		Ficha ficha=null;
		if(!isTerminada())
			ficha = turno;
		else 
			ficha = Ficha.VACIA;
		return ficha;
	}
		
	/**
	 * Devuelve el color del ganador. S�lo v�lido si la partida ya ha terminado (isTerminada() == true).
	 * @return Color del ganador. Si la partida termin� en tablas, Ficha.VACIA. Si la partida no ha 
	 * terminado a�n, el resultado es indeterminado.
	 */
	public Ficha getGanador(){
		if(isTerminada()){
			return ganador;
		}
		else 
			return Ficha.VACIA;		
	}
	
	/**
	 * Método para saber si la partida ha concluido ya o no.
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada(){
		return terminada;
	}
	
	public void setTerminada(boolean vo){
		this.terminada=vo;
	}
	
	/**
	 * M�todo de acceso al tablero. Dependiendo de c�mo se haga la implementaci�n del resto de clases 
	 * (principalmente de la clase Controlador), es posible que no se utilice para nada en la pr�ctica.  
	 * Sin embargo, es necesario implementarlo para poder ejecutar los test de unidad.
	 * @return Estado del tablero actual.
	 */
	public Tablero getTablero() {
		return tablero;
	}


	/**
	 * Devuelve las reglas del juego
	 */
	public ReglasJuego getJuego() {
		return reglas;
	}
	
}
