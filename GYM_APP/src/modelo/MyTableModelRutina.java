package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.transfer.TransferRutina;

public class MyTableModelRutina extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TransferRutina> rutinas;
	private final String tableHeaders[] = {"Rutina", "Ejercicios"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelRutina()
	{
		this.rutinas = new ArrayList<TransferRutina>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 * @param actividades - actividades de nuestro horario.
	 */
	public MyTableModelRutina(ArrayList<TransferRutina> rutina)
	{
		this.rutinas = rutina;
	}
	
	/**
	 * Setter del atributo rutinas.
	 * @param rutinas disponibles.
	 */
	public void setRutinas(ArrayList<TransferRutina> rutinas)
	{
		this.rutinas = rutinas;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableRutina(ArrayList<TransferRutina> rutinas)
	{
		this.setRutinas(rutinas);
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
		return this.rutinas.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.rutinas.get(arg0).getNombre();
		else
			return this.rutinas.get(arg0).getNumEjercicios();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}
	
	

}
