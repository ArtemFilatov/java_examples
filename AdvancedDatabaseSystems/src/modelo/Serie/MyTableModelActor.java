package modelo.Serie;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModelActor extends AbstractTableModel
{
	private ArrayList<Actor> actores;
	//TODO ESTO ES PARA MIS SERIES
	private final String tableHeaders[] = {"Nif", "Nombre","Dob"}; // la foto no la muestra, sino se va de espacio
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelActor()
	{
		this.actores = new ArrayList<Actor>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 */
	public MyTableModelActor(ArrayList<Actor> actor)
	{
		this.actores = actor;
	}
	
	/**
	 * Setter del atributo actividades.
	 */
	public void setActores(ArrayList<Actor> actores)
	{
		this.actores = actores;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableSocio(ArrayList<Actor> actores)
	{
		this.setActores(actores);
		this.fireTableDataChanged();
	}
	
	/**
	 * Devuelve el numero de columnas.
	 */
	public int getColumnCount() 
	{
		return 3;
	}

	/**
	 * Devuelve el numero de actividades que tenemos en nuestro ArrayList.
	 */
	public int getRowCount() 
	{
		if(this.actores == null){
			return 0;
		}else{
			return this.actores.size();
		}
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		switch(columnIndex) {
		case 0: 
			return this.actores.get(rowIndex).getNif();
		case 1:
			return this.actores.get(rowIndex).getNombre();
		case 2:
			return this.actores.get(rowIndex).getDob().toString();
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

	public Actor getActorElegido(int seleccion) {
		Actor actor = null;
		if (seleccion < 0) {
			
		} else {
			actor =  this.actores.get(seleccion);
		}
		return actor;
	}

	public void actualiza() {
			this.fireTableDataChanged();
	}

	

}
