package modelo.transfer.Tienda;

import modelo.transfer.TransferObject;

/**
 * 
 * @author Ines Heras
 *
 */
public class TransferArticulo extends TransferObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransferProveedor dueno;
	private String nombre;
	private int precio;
	private String disponibles;

	/**
	 * 
	 * @param proveedor
	 * @param nombre
	 * @param precio
	 * @param disponibles
	 */
	public TransferArticulo(TransferProveedor proveedor, String nombre, int precio, String disponibles){
		this.nombre=nombre;
		this.dueno=proveedor;
		this.precio=precio;
		this.setDisponibles(disponibles);
	}

	public TransferProveedor getDueno() {
		return dueno;
	}

	public void setDueno(TransferProveedor dueno) {
		this.dueno = dueno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getDisponibles() {
		return disponibles;
	}

	public void setDisponibles(String dispo){
		try{
		     Integer.parseInt(dispo);//Comprobamos que la cadena es un numero
		     this.disponibles=dispo;
		}catch(NumberFormatException e){
		    this.disponibles="";
		}  
		
	}
}
