package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.transfer.TransferEjercicio;

public class MyTableModelEjercicios extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TransferEjercicio> ejercicios;
	private final String tableHeaders[] = {"Nombre", "Repeticiones"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelEjercicios()
	{
		this.ejercicios = new ArrayList<TransferEjercicio>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 * @param ejercicios
	 */
	public MyTableModelEjercicios(ArrayList<TransferEjercicio> ejercicios)
	{
		this.ejercicios = ejercicios;
	}
	
	/**
	 * Setter del atributo ejercicios
	 * @param ejercicios disponibles.
	 */
	public void setEjercicios(ArrayList<TransferEjercicio> ejercicios)
	{
		this.ejercicios = ejercicios;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableEjercicios(ArrayList<TransferEjercicio> ejercicios)
	{
		this.setEjercicios(ejercicios);
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
		return this.ejercicios.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.ejercicios.get(arg0).getNombre();
		else
			return this.ejercicios.get(arg0).getRepeticiones();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}
	
	

}

