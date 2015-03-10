package vista.admin;
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
import controlador.admin.ControlAdminActores;
import controlador.admin.ControlAdminPersonajes;
import controlador.admin.ControlAdminSeries;



public class VistaSeries extends JPanel {

	
	private ControlAdminSeries controlador;
	private JTextField textFindSeries;

	private VistaSeries ventana;
	
	private JButton btnBuscar;
	private JButton btnInformacion;
	private JButton btnSerie;
	private JScrollPane scrollPane;
	private JList list;
	private MyListModelSerie serieListString;
	private List<Serie> series;
	private ControlAdminActores propagar1;
	private ControlAdminPersonajes propagar2;
	/**
	 * Create the panel.
	 * @param controlAdminSeries 
	 * @param controlPersonajes 
	 * @param controlActores 
	 */
	public VistaSeries(ControlAdminSeries controlAdminSeries, ControlAdminActores controlActores, ControlAdminPersonajes controlPersonajes) {
		this.ventana = this;
		
		series = null;
		controlador = controlAdminSeries;
		propagar1 = controlActores;
		propagar2 = controlPersonajes;
		
				setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Buscar serie:");
				lblNewLabel.setBounds(42, 13, 76, 19);
				add(lblNewLabel);
				
				textFindSeries = new JTextField();
				textFindSeries.setBounds(128, 12, 86, 20);
				add(textFindSeries);
				textFindSeries.setColumns(10);
				
				btnInformacion = new JButton("Ver Informacion");
				btnInformacion.setBounds(42, 253, 130, 23);
				add(btnInformacion);
				btnInformacion.addActionListener(new OyenteBoton());
				
				
				btnBuscar = new JButton("Buscar");
				btnBuscar.setBounds(235, 11, 89, 23);
				add(btnBuscar);
				btnBuscar.addActionListener(new OyenteBoton());	
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 42, 344, 199);
				add(scrollPane);
				
				serieListString = new MyListModelSerie();
				list = new JList();
				list.setModel(serieListString);
				scrollPane.setViewportView(list);
				
				btnSerie = new JButton("Nueva serie");
				btnSerie.addActionListener(new OyenteBoton());
				btnSerie.setBounds(208, 253, 116, 23);
				add(btnSerie);
		
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
	
	public void actualiza() {
		if(textFindSeries.getText().equals("")) {
			series = controlador.listaSeries();
			setListSeries(series);
		} else {
			series = controlador.listaSeries("%"+textFindSeries.getText()+"%");
			setListSeries(series);
		}
	}
	
	
			private class OyenteBoton implements ActionListener{
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == btnBuscar){
						if(textFindSeries.getText().equals("")) {
							series = controlador.listaSeries();
							setListSeries(series);
						} else {
							series = controlador.listaSeries("%"+textFindSeries.getText()+"%");
							setListSeries(series);
						}
					
					}else if(e.getSource() == btnInformacion){
						Serie serie = buscaSerie((String) list.getSelectedValue());
						if(serie != null){
							new AdminSerie(serie, controlador, propagar1, propagar2, ventana);
							}
					} else if(e.getSource() == btnSerie){
							new FormularioSerie(controlador, ventana);
					
					} 
				}
	
		
	}
}
