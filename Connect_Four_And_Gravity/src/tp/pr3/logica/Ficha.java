package tp.pr3.logica;

/**
 * Repesenta la informacion del color de una ficha. El enumerado es utilizado para guardar 
 * la informacion de cada posicion de los tableros, por lo que contiene tambien un simbolo 
 * para indicar la ausencia de ficha en esa posicion. Tambien se utiliza para el color de 
 * un jugador. IMPORTANTE: en la documentacion aparecen una serie de metodos (valueOf, values) 
 * que NO HAY QUE IMPLEMENTAR, sino que son añadidos automaticamente por el compilador. 
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 * @since Practica 1
 */
public enum Ficha{
	BLANCA("blancas", "O"), NEGRA("negras", "X"), VACIA("vacias", " ");
	
	private final String name;
	private final String value;
	
	private Ficha(String s, String v) {
		name = s;
		value = v;
	}
	
	public String toString() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	/**
	 * Devuelve la cadena que representa el tipo de ficha para mostrar en el tablero
	 * @return - String
	 */
	public static String valorTablero(Ficha ficha) {
		return ficha.getValue();
	}
}
