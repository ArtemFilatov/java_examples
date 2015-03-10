package modelo.Serie;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModelActorSeriePersonaje extends AbstractTableModel
{
	private ArrayList<ActorSeriePersonaje> actorSeriePersonaje;
	private final String tableHeaders[] = {"Actor", "Personaje"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelActorSeriePersonaje()
	{
		this.actorSeriePersonaje = new ArrayList<ActorSeriePersonaje>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 */
	public MyTableModelActorSeriePersonaje(ArrayList<ActorSeriePersonaje> actorSeriePersonaje)
	{
		this.actorSeriePersonaje = actorSeriePersonaje;
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
		return this.actorSeriePersonaje.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		switch(columnIndex) {
		case 0: 
			return this.actorSeriePersonaje.get(rowIndex).getActor().getNombre();
		case 1:
			return this.actorSeriePersonaje.get(rowIndex).getNombrePersonaje();
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
	
	public void setActorSeriePersonaje(ArrayList<ActorSeriePersonaje> actorSeriePersonaje)
	{
		this.actorSeriePersonaje = actorSeriePersonaje;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void actualiza()
	{
		this.fireTableDataChanged();
	}

	public ActorSeriePersonaje getUnionElegida(int selectedRow) {
		ActorSeriePersonaje union = null;
		if (selectedRow < 0) {
			
		} else {
			union =  this.actorSeriePersonaje.get(selectedRow);
		}
		return union;
	}

}
