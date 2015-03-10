package tp.pr3.control;
import java.util.Scanner;

import tp.pr3.comandos.GestorComandos;
import tp.pr3.logica.Partida;


/**
 * Clase que controla la ejecucion de la partida, pidiendo al usuario que quiere ir haciendo, 
 * hasta que la partida termina.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class Controlador {

	/**
	 * Partida reference
	 */
	private Partida partida;
	
	/**
	 * Referencia al punto de datos del usuario
	 */
	private Scanner in;
	
	/**
	 * Referencia al gestor de comandos.
	 */
	private GestorComandos gc;
	
	/**
	 * Referencia a la factoria para crear objetos.
	 */
	private FactoriaTipoJuego f;
	
	/**
	 * Referencia a los jugadores.
	 */
	private Jugador jugadorBlancas;
	private Jugador jugadorNegras;

	
	/**
	 * Constructor de la clase.
	 * @param p - Partida a la que se jugará.
	 * @param in - Scanner que hay que utilizar para pedirle la información al usuario.
	 */
	public Controlador(FactoriaTipoJuego f, Partida p, Scanner in) {
		partida = p;
		this.in = in;
		this.f = f;
		jugadorBlancas = f.creaJugadorHumanoConsola(in);
		jugadorNegras = f.creaJugadorHumanoConsola(in);
		gc = new GestorComandos(this);	
	}
	
	/**
	 * Ejecuta la partida hasta que ésta termina.
	 */
	public void run() {
		
		String instruccion="";
		System.out.println(partida.getTablero().toString());
		
		// Bucle principal del programa
		while(!partida.isTerminada() && !gc.terminar()) {
			
			System.out.println("Juegan "+ partida.getTurno());
			System.out.print("Qué quieres hacer? ");

			instruccion= in.nextLine().trim();
			
			while(instruccion.equalsIgnoreCase(""))
				instruccion= in.nextLine().trim();

			// Tratamos de ejecutar el comando.
			if(!gc.ejecutar(instruccion))
	        	System.err.println("No te entiendo.");
			
			// Hemos terminado?
			if(!gc.terminar())
				System.out.println(partida.getTablero().toString());
			
		}
		// Si hemos terminado
		if(!gc.terminar()){
				// La partida ha acabado en tablas
				if(partida.getJuego().tablas(partida.getGanador(), partida.getTablero()))
					System.out.println("Partida terminada en tablas.");
				// La partida ha acabado con un ganador
				else if(partida.getGanador() != null)
					System.out.println("Ganan las " + partida.getGanador().toString().toLowerCase());
		}
	}

	/**
	 * Obtener jugador en funcion del turno actual.
	 * @return
	 */
	public Jugador getJugadorTurno() {
		Jugador jugador = null;
		switch(partida.getTurno()) {
		case BLANCA:
			jugador = jugadorBlancas;
			break;
		case NEGRA:
			jugador = jugadorNegras;
			break;
		default:
			break;
		}
		return jugador;
	}
	
	/**
	 * Cambia el jugador que corresponda en funcion de los dos parametros recibidos.
	 * @param colorJugador - Color del jugador (Ficha BLANCA o NEGRA)
	 * @param tipoJugador - Tipo del jugador (Humano o Aleatorio)
	 */
	public void cambiaJugador(String colorJugador, String tipoJugador) {
		if(colorJugador.equalsIgnoreCase("Blancas"))
		{
			if(tipoJugador.equalsIgnoreCase("Humano"))
				jugadorBlancas = f.creaJugadorHumanoConsola(in);
			else
				jugadorBlancas = f.creaJugadorAleatorio();
		} else {
			if(tipoJugador.equalsIgnoreCase("Humano"))
				jugadorNegras = f.creaJugadorHumanoConsola(in);
			else
				jugadorNegras = f.creaJugadorAleatorio();
		}
	}
	
	// GETTERS AND SETTERS
	
	public Partida getPartida() {
		return partida;
	}

	public FactoriaTipoJuego getF() {
		return f;
	}

	/**
	 * Cambia la factoria y los jugadores
	 * @param f
	 */
	public void setFactoria(FactoriaTipoJuego f) {
		this.f = f;
		// Cambiamos tambi�n los jugadores
		jugadorBlancas = f.creaJugadorHumanoConsola(in);
		jugadorNegras = f.creaJugadorHumanoConsola(in);
	}	

}
