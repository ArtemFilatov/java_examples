package tp.pr3.logica;

/**
 * Clase encargada de representar la pila.
 * 
 * @author Guillermo Mart�n Duque
 * @author Jesus Vazquez Pigueiras
 */

public class Stack<E> {
	
	private E pila[];
	private int contador;
	/**
	 * Constructora que crea la _pila con la capacidad m�xima indicada.
	 * @param max_size - es la capacidad m�xima de la pila. Se asume que siempre ser� un n�mero mayor que 0.
	 */
	@SuppressWarnings("unchecked")
	public Stack(int max_size){
		pila= (E[]) new Object[max_size];
		contador=0;
	}
	
	/**
	 * Indica si la pila est� vacia.
	 * @return true si la pila est� vacia.
	 */
	public boolean isEmpty(){
	   return contador==0;
	}
	
	/**
	 * A_pila un valor
	 * @param value - es el valor a a_pilar
	 * @return true si se ha podidio realizar el apilado o false si no se ha realizado porque la pila estaba llena.
	 */
	public boolean push(E value){
		if(isFull())
			desplazar();		
		pila[contador]=value;
		contador++;
	   return true;
	}
	
	/**
	 * Desa_pila el valor de la cima. 
	 * Los clientes de esta clase deber�n comprobar con el m�todo isEmpty() que la pila no est� vacia antes de invocar este m�todo. 
	 * Si se intenta eliminar un valor en la pila vac�a, el comportamiento es indeterminado.
	 */
	public void pop(){
		if(!isEmpty())
			contador--;		
	}
	
	/**
	 * Devuelve el valor de la cima. 
	 * Los clientes de esta clase deber�n comprobar con el m�todo isEmpty() que la pila no est� vacia antes de invocar este m�todo. 
	 * En caso de que la _pila est� vacia se devolver� 0 por defecto.
	 * @return el valor de la cima de la _pila o 0 si est� vac�a.
	 */
	public E top(){
		E cima= null;
		if(!isEmpty())
			cima=(E) pila[contador-1];
		
		return cima;
	}
	
	/**
	 * Indica si la pila esta llena.
	 * @return True si la pila esta llena, false en caso contrario.
	 */
	public boolean isFull(){
		return contador >= pila.length;
	}
	
	/**
	 * Desplaza el array una unidad eliminando la ultima posicion de la pila
	 */
	private void desplazar(){
		for(int i=0; i<contador-1; i++){
			pila[i] = pila[i+1];
		}
		contador--;
		
	}
}
