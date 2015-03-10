package modelo.transfer.users;

import modelo.enums.Cuota;
import modelo.transfer.TransferHorario;





/**
 * 
 * @author Jesus Vazquez
 *
 */
public class TransferSocio extends TransferUsuario
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cuota cuota;
	private String direccion;
	private int telefono;
	private TransferHorario horarioEntreno;
	
	/**
	 * Constructor vacio
	 */
	public TransferSocio(){
		super();
	}
	/**
	 * Constructor con los parametros iniciales, sin el entrenamiento ni el horario
	 * @param name
	 * @param apellidos
	 * @param correo
	 * @param contrasena
	 * @param dni
	 * @param cuenta
	 * @param cuota
	 * @param direccion
	 * @param telefono
	 */
	public TransferSocio(String name, String apellidos, String correo, String contrasena, String dni, int cuenta, Cuota cuota, String direccion, 
			int telefono){
		super(name,apellidos,correo,contrasena,dni, cuenta);
		this.cuota = cuota;
		this.direccion = direccion;
		this.telefono = telefono;
		
	}
	/**
	 * Constructor con todos los parametros
	 * @param name
	 * @param apellidos
	 * @param correo
	 * @param contrasena
	 * @param dni
	 * @param cuenta
	 * @param cuota
	 * @param direccion
	 * @param telefono
	 * @param horarioEntreno
	 * @param entrenamiento
	 */
	public TransferSocio(String name, String apellidos, String correo, String contrasena, String dni, int cuenta, Cuota cuota, String direccion, 
			int telefono,TransferHorario horarioEntreno)
	{
		super(name,apellidos,correo,contrasena,dni, cuenta);
		this.cuenta = cuenta;
		this.cuota = cuota;
		this.direccion = direccion;
		this.telefono = telefono;
		this.horarioEntreno = horarioEntreno;
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

	public Cuota getCuota() {
		return cuota;
	}

	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
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

	public TransferHorario getHorarioEntreno() {
		return horarioEntreno;
	}

	public void setHorarioEntreno(TransferHorario horarioEntreno) {
		this.horarioEntreno = horarioEntreno;
	}

	public void setCuota(int cuota) 
	{
		switch(cuota)
		{
			case 1: 
				this.cuota = Cuota.MENSUAL;
				break;
			case 2:
				this.cuota = Cuota.TRIMESTRAL;
				break;
			case 3:
				this.cuota = Cuota.SEMESTRAL;
				break;
			case 4:
				this.cuota = Cuota.ANUAL;
				break;
			default:
				this.cuota = Cuota.MENSUAL;
				break;
		}
		
	}
	
	
}
