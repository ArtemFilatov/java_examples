package modelo.transfer.users;

import java.util.List;

import modelo.DAO.DAOEntrenador;
import modelo.DAO.DAOSocio;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.enums.Cuota;
import modelo.enums.Sueldo;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;




/**
 * 
 * @author Jesus Vazquez
 *
 */

	
public class TransferEntrenador extends TransferUsuario
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Sueldo sueldo;
	private String direccion;
	private int telefono;
	private TransferHorario horarioEntreno;
	
	public TransferEntrenador(String name, String apellidos, String correo, String contrasena, String dni, int cuenta,Sueldo sueldo, String direccion, 
			int telefono)
	{
		super(name,apellidos,correo,contrasena,dni, cuenta);
		this.direccion = direccion;
		this.telefono = telefono;
		this.horarioEntreno = null;

	}
	
	public TransferEntrenador(String name, String apellidos, String correo, String contrasena, String dni, int cuenta, Sueldo sueldo, String direccion, 
			int telefono,TransferHorario horarioEntreno)
	{
		super(name,apellidos,correo,contrasena,dni, cuenta);
		this.direccion = direccion;
		this.telefono = telefono;
		this.horarioEntreno = horarioEntreno;

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

	public TransferHorario getHorarioEntreno() {
		return horarioEntreno;
	}

	public void setHorarioEntreno(TransferHorario horarioEntreno) {
		this.horarioEntreno = horarioEntreno;
	}
	
	public Sueldo getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		switch(sueldo)
		{
			case 1: 
				this.sueldo = Sueldo.SUELDOBASE;
				break;
			default:
				this.sueldo= Sueldo.SUELDOBASE;
				break;
		}
	}


	public boolean equals(TransferEntrenador e)
	{
		return (this.correo.equalsIgnoreCase(e.getCorreo()));
	}

	
}
