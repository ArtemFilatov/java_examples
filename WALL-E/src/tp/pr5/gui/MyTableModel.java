package tp.pr5.gui;

import java.util.ArrayList;

import tp.pr5.items.Item;

import javax.swing.table.AbstractTableModel;

/**
 * Clase que extiende AbstracTableModel que nos servirá como modelo para un JTable
 * @author Guillermo Martín Duque
 * @author Jesús Vázquez Pigueiras
 *
 */

public class MyTableModel extends AbstractTableModel
{

	private ArrayList<Item> items;
	private final String tableHeaders[] = {"Id", "Description"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModel()
	{
		this.items = new ArrayList<Item>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 * @param items - Robot's itemcontainer
	 */
	public MyTableModel(ArrayList<Item> items)
	{
		this.items = items;
	}
	
	/**
	 * Setter del atributo items.
	 * @param items - Robot's itemcontainer.
	 */
	public void setItems(ArrayList<Item> items)
	{
		this.items = items;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableItems(ArrayList<Item> items)
	{
		this.setItems(items);
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
	 * Devuelve el numero de items que tenemos en nuestro ArrayList.
	 */
	public int getRowCount() 
	{
		return this.items.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.items.get(arg0).getId();
		else
			return this.items.get(arg0).toString();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}
	

}
