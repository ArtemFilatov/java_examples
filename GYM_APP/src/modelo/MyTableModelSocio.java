package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;

public class MyTableModelSocio extends AbstractTableModel
{
	private ArrayList<TransferSocio> socios;
	private final String tableHeaders[] = {"Nombre", "Correo"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelSocio()
	{
		this.socios = new ArrayList<TransferSocio>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 * @param actividades - actividades de nuestro horario.
	 */
	public MyTableModelSocio(ArrayList<TransferSocio> socio)
	{
		this.socios = socio;
	}
	
	/**
	 * Setter del atributo actividades.
	 * @param actividades - actividades de nuestro horario.
	 */
	public void setSocio(ArrayList<TransferSocio> socios)
	{
		this.socios = socios;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableSocio(ArrayList<TransferSocio> socios)
	{
		this.setSocio(socios);
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
		return this.socios.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.socios.get(arg0).toString();
		else
			return this.socios.get(arg0).getCorreo();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}

}
