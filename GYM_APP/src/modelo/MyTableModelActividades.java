package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.transfer.TransferActividad;


@SuppressWarnings("serial")
public class MyTableModelActividades extends AbstractTableModel
{
	private ArrayList<TransferActividad> actividades;
	private final String tableHeaders[] = {"Actividad", "Hora"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelActividades()
	{
		this.actividades = new ArrayList<TransferActividad>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 * @param actividades - actividades de nuestro horario.
	 */
	public MyTableModelActividades(ArrayList<TransferActividad> actividad)
	{
		this.actividades = actividad;
	}
	
	/**
	 * Setter del atributo actividades.
	 * @param actividades - actividades de nuestro horario.
	 */
	public void setActividades(ArrayList<TransferActividad> actividades)
	{
		this.actividades = actividades;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableActividades(ArrayList<TransferActividad> actividades)
	{
		this.setActividades(actividades);
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
		return this.actividades.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.actividades.get(arg0).actividadToString();
		else
			return this.actividades.get(arg0).horaToString();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}
}
