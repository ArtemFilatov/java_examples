package tp.pr3.logica;

/**
 * Excepción generada cuando se intenta ejecutar un movimiento incorrecto.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */

@SuppressWarnings("serial")
public class MovimientoInvalido extends Exception {
	
	/**
	 * Constructor sin parámetros.
	 */
	public MovimientoInvalido(){
		
	}
	
	/**
	 * Constructor con un parámetro para el mensaje
	 * @param msg: mensaje
	 */
	public MovimientoInvalido(String msg){
		super(msg);
	}
	
	
	/**
	 * Constructor con un parámetro para el mensaje y otro para la causa.
	 * @param msg: mensaje
	 * @param arg: causa
	 */
	public MovimientoInvalido(String msg, Throwable arg){
		super(msg, arg);
	}
	
	
	/**
	 * Constructor con un parámetro para la causa inicial que provocó la excepción.
	 * @param arg: causa inicial que provocó la excepción
	 */
	public MovimientoInvalido(java.lang.Throwable arg){
			super(arg);
	}
	

}
