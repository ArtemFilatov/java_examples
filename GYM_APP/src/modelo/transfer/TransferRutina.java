package modelo.transfer;

import java.util.ArrayList;

public class TransferRutina extends TransferObject 
{
	private ArrayList<TransferEjercicio> ejercicios;
	private String nombre;
	private int idRutina;

	/**
	 * Constructor con un parametro.
	 * @param nombre - Nombre de la rutina.
	 */
	public TransferRutina(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Constructor con dos parametros.
	 * @param nombre - Nombre de la rutina.
	 * @param ejercicios - Ejercicios de la rutina.
	 */
	public TransferRutina(String nombre, ArrayList<TransferEjercicio> ejercicios)
	{
		this.nombre = nombre;
		this.ejercicios = ejercicios;
	}
	
	public ArrayList<TransferEjercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(ArrayList<TransferEjercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}
	
	public String getNumEjercicios()
	{
		return String.valueOf(this.ejercicios.size());
	}


}
