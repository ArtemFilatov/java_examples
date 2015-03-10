package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.transfer.users.TransferEntrenador;

public class MyTableModelEntrenador extends AbstractTableModel
{
	private ArrayList<TransferEntrenador> entrenadores;
	private final String tableHeaders[] = {"Nombre", "Correo"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelEntrenador()
	{
		this.entrenadores = new ArrayList<TransferEntrenador>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 * @param actividades - actividades de nuestro horario.
	 */
	public MyTableModelEntrenador(ArrayList<TransferEntrenador> entrenador)
	{
		this.entrenadores = entrenador;
	}
	
	/**
	 * Setter del atributo actividades.
	 * @param actividades - actividades de nuestro horario.
	 */
	public void setEntrenadores(ArrayList<TransferEntrenador> entrenadores)
	{
		this.entrenadores = entrenadores;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableEntrenadores(ArrayList<TransferEntrenador> entrenadores)
	{
		this.setEntrenadores(entrenadores);
		this.fireTableDataChanged();
	}
	
	/**
	 * Devuelve el numero de columnas.
	 */
	public int getColumnCount() 
	{
		return 2;
	}

	/**
	 * Devuelve el numero de actividades que tenemos en nuestro ArrayList.
	 */
	public int getRowCount() 
	{
		return this.entrenadores.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.entrenadores.get(arg0).toString();
		else
			return this.entrenadores.get(arg0).getCorreo();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}

}
