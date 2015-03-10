package modelo.transfer.users;

import modelo.transfer.TransferObject;

/**
 * 
 * @author Jesus Vazquez, Ines Heras
 *
 */
public class TransferUsuario extends TransferObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String apellidos;
	protected String correo;
	protected String contrasena;
	protected String dni;
	protected int cuenta;
	


	/**
	 * 
	 * @param name
	 * @param pass
	 * @param dni
	 * @param cuenta
	 */
	
	public TransferUsuario(){
		
	}

	public TransferUsuario(String name, String apellidos,String correo, String pass, String dni, int cuenta){
		this.nombre = name;
		this.apellidos=apellidos;
		this.contrasena = pass;
		this.dni = dni;
		this.correo = correo;
		this.cuenta=cuenta;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getCuenta() {
		return cuenta;
	}
	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	
	public String toString()
	{
	    return this.nombre + " " + this.apellidos;
	}


	
}
