package modelo.transfer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.transfer.users.TransferEntrenador;



/**
 * 
 * @author Ines Heras
 *
 */
public class TransferActividad extends TransferObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idActividad;
	private String nombre;
	private TransferEntrenador entrenador;
	private List <TransferObject> socios;
	private String horaInicio;
	private String horaFin;
	
	public TransferActividad(String nombre, TransferEntrenador entrena, String horaInicio, String horaFin)
	{
		this.nombre=nombre;
		this.entrenador=entrena;
		this.socios=new ArrayList<TransferObject>();
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public TransferActividad(String nombre, TransferEntrenador entrena, List<TransferObject> socios, String horaInicio, String horaFin)
	{
		this.nombre=nombre;
		this.entrenador=entrena;
		this.socios=socios;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public TransferActividad(int idActividad, String nombre, TransferEntrenador entrena, List<TransferObject> socios, String horaInicio, String horaFin)
	{
		this.idActividad = idActividad;
		this.nombre=nombre;
		this.entrenador=entrena;
		this.socios=socios;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public TransferEntrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(TransferEntrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<TransferObject> getSocios() {
		return socios;
	}

	public void setSocios(List<TransferObject> socios) {
		this.socios = socios;
	}
	
	/**
	 * Devuelve el intervalo en el que se realizara la actividad en formato hh:mm:ss(inicio) - hh:mm:ss(fin)
	 */
	public String horaToString()
	{
		return (this.horaInicio + " - " + this.horaFin);
	}
	
	public String actividadToString()
	{
		return (this.nombre + " - " + this.entrenador.getNombre());
	}

	/**
	 * Comprueba si existe concurrencia entre horarios.
	 * True si existe concurrencia.
	 */
	public boolean comprobarConcurrecia(TransferActividad actividad) 
	{
		
		try {
			DateFormat dateFormat = new SimpleDateFormat ("hh:mm:ss");
			Date date1, date2, date3, date4;
			date1 = dateFormat.parse(this.horaInicio);
			date2 = dateFormat.parse(actividad.getHoraInicio());
			date3 = dateFormat.parse(this.horaFin);
			date4 = dateFormat.parse(actividad.getHoraFin());
			
			if(date1.compareTo(date2) < 0)
			{
				if(date2.compareTo(date4) >= 0)
					return false;
			}
			else if(date2.compareTo(date1) < 0)
			{
				if(date4.compareTo(date3) <= 0)
					return false;
			}
		} catch (ParseException parseException){
			parseException.printStackTrace();
		}
		return true;
	}

}
