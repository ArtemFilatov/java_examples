package modelo.transfer.users;


/**
 * 
 * @author Jesus Vazquez, Ines Heras
 *
 */
public class TransferAdministrador extends TransferUsuario 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String direccion;
	private int telefono;
	
	
	public TransferAdministrador(String name, String apellidos, String correo, String contrasena, String dni, int cuenta, String direccion, 
			int telefono)
	{
		super(name,apellidos,correo,contrasena,dni, cuenta);
		this.direccion = direccion;
		this.telefono = telefono;

	}
	public TransferAdministrador()
	{
		

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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


}
