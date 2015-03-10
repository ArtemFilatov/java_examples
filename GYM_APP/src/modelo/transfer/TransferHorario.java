package modelo.transfer;

import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;



/**
 * 
 * @author Ines Heras
 *
 */
public class TransferHorario extends TransferObject 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
	private ArrayList<TransferActividad> actividades;
	private int cdHorario;
	
	public int getCdHorario() {
		return cdHorario;
	}

	public void setCdHorario(int cdHorario) {
		this.cdHorario = cdHorario;
	}

	public TransferHorario(String fecha)
	{
		this.fecha = fecha;
	}
	
	public TransferHorario(String fecha, ArrayList<TransferActividad> actividades)
	{
		this.fecha = fecha;
		this.actividades = actividades;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public ArrayList<TransferActividad> getActividades() {
		return actividades;
	}

	public void setActividades(ArrayList<TransferActividad> actividades) {
		this.actividades = actividades;
	}
	
}
