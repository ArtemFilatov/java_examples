package modelo.transfer;


/**
 * 
 * @author Jesus Vazquez
 *
 */

public class TransferEjercicio extends TransferObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEjercicio;
	private String nombre;
	private String repeticiones;
	
	/**
	 * Constructor con un parametro.
	 * @param nombre - Nombre del ejercicio.
	 */
	public TransferEjercicio(String nombre)
	{
		this.nombre = nombre;
		this.repeticiones = "";
	}
	
	/**
	 * Constructor con dos parametros.
	 * @param nombre - Nombre del ejercicio.
	 * @param repeticiones - Repeticiones del mismo.
	 */
	public TransferEjercicio(String nombre, String repeticiones)
	{
		this.nombre=nombre;
		this.repeticiones = repeticiones;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(String repeticiones) {
		this.repeticiones = repeticiones;
	}
	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	

}
