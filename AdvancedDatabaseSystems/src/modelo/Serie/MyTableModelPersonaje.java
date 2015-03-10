package modelo.Serie;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModelPersonaje extends AbstractTableModel
{
	private ArrayList<Personaje> personajes;
	//TODO ESTO ES PARA MIS SERIES
	private final String tableHeaders[] = { "nombre", "descripcion"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelPersonaje()
	{
		this.personajes = new ArrayList<Personaje>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 */
	public MyTableModelPersonaje(ArrayList<Personaje> personajes)
	{
		this.personajes = personajes;
	}
	
	/**
	 * Setter del atributo actividades.
	 */
	public void setPersonajes(ArrayList<Personaje> personajes)
	{
		this.personajes = personajes;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableSocio(ArrayList<Personaje> personajes)
	{
		this.setPersonajes(personajes);
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
		if(this.personajes == null){
			return 0;
		}else{
		return this.personajes.size();
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{

		switch(columnIndex) {
		case 0:
			return this.personajes.get(rowIndex).getNombre();
		case 1:
			return this.personajes.get(rowIndex).getDes();
		default:
			throw new IndexOutOfBoundsException();
		}
	
	
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}

	public Personaje getPersonajeElegido(int selectedRow) {
		Personaje per = null;
		if (selectedRow < 0) {
			
		} else {
			per =  this.personajes.get(selectedRow);
		}
		return per;
	}
	
	public void actualiza() {
		this.fireTableDataChanged();
	}
}
