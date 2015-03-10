package modelo.Serie;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

public class MyTableModelEpisodio extends AbstractTableModel
{
	private ArrayList<Episodio> episodios;
	private final String tableHeaders[] = {"Numero", "Temporada","Titulo","Emision"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelEpisodio()
	{
		this.episodios = new ArrayList<Episodio>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 */
	public MyTableModelEpisodio(ArrayList<Episodio> episodios)
	{
		this.episodios = episodios;
	}
	
	/**
	 * Devuelve el numero de columnas.
	 */
	public int getColumnCount() 
	{
		return 4;
	}

	/**
	 * Devuelve el numero de actividades que tenemos en nuestro ArrayList.
	 */
	public int getRowCount() 
	{
		return this.episodios.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		switch(columnIndex) {
		case 0: 
			return this.episodios.get(rowIndex).getIdEpisodio();
		case 1:
			return this.episodios.get(rowIndex).getTemporada();
		case 2:
			return this.episodios.get(rowIndex).getTitulo();
		case 3:
			return this.episodios.get(rowIndex).getfEstreno();
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
	
	public Episodio getEpisodio(int idEpisodio) {
		Iterator<Episodio> it = this.episodios.iterator();
		Episodio epi = null;
		while(it.hasNext()) {
			epi = it.next();
			if(epi.getIdEpisodio() == idEpisodio)
				break; 
		}
		return epi;
	}
	
	
	public void setEpisodios(ArrayList<Episodio> episodios)
	{
		this.episodios = episodios;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void actualiza()
	{
		this.fireTableDataChanged();
	}
	
	public void eliminaEpisodio(Episodio episodio) {
		this.episodios.remove(episodio);
	}
	public void insertaEpisodio(Episodio episodio) {
		this.episodios.add(episodio);
	}

}
