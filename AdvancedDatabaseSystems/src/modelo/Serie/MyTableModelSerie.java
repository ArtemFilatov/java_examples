package modelo.Serie;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class MyTableModelSerie extends AbstractTableModel
{
	private ArrayList<Serie> series;
	//TODO ESTO ES PARA MIS SERIES
	private final String tableHeaders[] = {"Nombre", "Periodo"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelSerie()
	{
		this.series = new ArrayList<Serie>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 */
	public MyTableModelSerie(ArrayList<Serie> series)
	{
		this.series = series;
	}
	
	/**
	 * Setter del atributo actividades.
	 */
	public void setSeries(ArrayList<Serie> series)
	{
		this.series = series;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableSocio(ArrayList<Serie> series)
	{
		this.setSeries(series);
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
		return this.series.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.series.get(arg0).getNombre();
		else
			return this.series.get(arg0).getPeriodo();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}

}
