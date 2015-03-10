package modelo.Serie;

import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

public class MyListModelSerie extends AbstractListModel {
	
	String[] series = new String[] {};
	
	@Override
	public int getSize() {
		return series.length;
	}

	@Override
	public Object getElementAt(int index) {
		return series[index];
	}
	
	public void setSeries(List<Serie> list) {
		String[] aux = new String[list.size()];
		Iterator<Serie> iterador = list.listIterator();
		int i = 0;
		while(iterador.hasNext()) {
			aux[i] = iterador.next().getNombre();
			i++;
		}
		this.series = aux;
		this.fireContentsChanged(this, 0, this.series.length);
	}

}
