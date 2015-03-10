package vista.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import modelo.Serie.MyListModelSerie;
import modelo.Serie.Serie;
import controlador.usuario.ControladorSeries;

@SuppressWarnings("serial")
public class BuscarSeries extends JPanel {
	private JTextField textFindSeries;

	private JButton btnBuscar;
	private JButton btnInformacion;
	private JScrollPane scrollPane;
	@SuppressWarnings("rawtypes")
	private JList list;
	private MyListModelSerie serieListString;
	private List<Serie> series;
	
	private ControladorSeries controller;
	/**
	 * Create the panel.
	 * @param controller 
	 */
	public BuscarSeries(ControladorSeries controller){
		setLayout(null);
		
		this.controller = controller;
		
		JLabel lblNewLabel = new JLabel("Buscar serie:");
		lblNewLabel.setBounds(25, 13, 93, 19);
		add(lblNewLabel);
		
		textFindSeries = new JTextField();
		textFindSeries.setBounds(128, 12, 86, 20);
		add(textFindSeries);
		textFindSeries.setColumns(10);
		
		btnInformacion = new JButton("Ver Informacion");
		btnInformacion.setBounds(86, 252, 203, 23);
		add(btnInformacion);
		btnInformacion.addActionListener(new OyenteBoton());
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(235, 11, 89, 23);
		add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 344, 199);
		add(scrollPane);
		
		serieListString = new MyListModelSerie();
		list = new JList();
		list.setModel(serieListString);
		scrollPane.setViewportView(list);
		btnBuscar.addActionListener(new OyenteBoton());
	}
	
	private void setListSeries(List<Serie> series) {
		serieListString.setSeries(series);
	}
	
	private Serie buscaSerie(String nombre) {
		Serie devolver = null;
		if(series != null){
		Iterator<Serie> iterador = series.listIterator();
		Serie serie = null;
		while(iterador.hasNext()) {
			serie = iterador.next();
			if(serie.getNombre().equalsIgnoreCase(nombre)){
				devolver = serie;
				break;
				}
		}
		}
		return devolver;
	}
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnBuscar){
				if(textFindSeries.getText().equals("")) {
					series = controller.listaSeries();
					setListSeries(series);
				} else {
					series = controller.listaSeries("%"+textFindSeries.getText()+"%");
					setListSeries(series);
				}
			}else if(e.getSource() == btnInformacion){
				Serie serie = buscaSerie((String) list.getSelectedValue());
				if(serie != null)
					new VistaSerie(serie, controller);
			} 
		}
	}
	
	
	
}
