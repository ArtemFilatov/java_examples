package tp.pr3.logica;

/**
 * Excepci�n generada cuando se intenta ejecutar un movimiento incorrecto.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 3
 */

@SuppressWarnings("serial")
public class MovimientoInvalido extends Exception {
	
	/**
	 * Constructor sin par�metros.
	 */
	public MovimientoInvalido(){
		
	}
	
	/**
	 * Constructor con un par�metro para el mensaje
	 * @param msg: mensaje
	 */
	public MovimientoInvalido(String msg){
		super(msg);
	}
	
	
	/**
	 * Constructor con un par�metro para el mensaje y otro para la causa.
	 * @param msg: mensaje
	 * @param arg: causa
	 */
	public MovimientoInvalido(String msg, Throwable arg){
		super(msg, arg);
	}
	
	
	/**
	 * Constructor con un par�metro para la causa inicial que provoc� la excepci�n.
	 * @param arg: causa inicial que provoc� la excepci�n
	 */
	public MovimientoInvalido(java.lang.Throwable arg){
			super(arg);
	}
	

}
