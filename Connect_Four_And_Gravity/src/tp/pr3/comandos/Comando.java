package tp.pr3.comandos;

/**
 * Interface que contiene las funciones básicas para crear un comando
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public interface Comando {
	
	/** Analiza la cadena y obtiene si es un comando
	 * @param cadena: texto con el comando a ejecutar
	 * @return true: es un comando, false: no es un comando
	 */ 
	public boolean analizar(String cadena);
	
	/**
	 * Ejecuta el comando
	 */
	public void ejecutar();
	
	/**
	 * 
	 * @return true: el comando sale de la partida, false: no sale.
	 */
	public boolean terminar();
	
	/**
	 * Proporciona la ayuda del comando
	 * @return Devuelve un String con la ayuda del comando
	 */
	public String ayuda();
}
