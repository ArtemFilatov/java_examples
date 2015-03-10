package vista.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import modelo.Serie.Episodio;
import modelo.Serie.MyTableModelEpisodio;
import modelo.Serie.Serie;
import controlador.usuario.ControladorSeries;

public class SeriesSiguiendo extends JPanel {
	private TableModel tableModel;

	private JButton btnInformacion;
	private JButton btnVisto;
	private JComboBox comboBox;
	
	private JScrollPane sp;
	private JTable table;
	private ArrayList<Serie> series;
	private ArrayList<Episodio> episodios;
	private String[] nombreSeries;
	private MyTableModelEpisodio myTableModelEpisodio;
	//private MyTableModelSerie myTableModelSerie;
	
	
	private ControladorSeries controller;
	/**
	 * Create the panel.
	 * @param controller 
	 */
	@SuppressWarnings("unchecked")
	public SeriesSiguiendo(ControladorSeries controller) {
		setLayout(null);
		
		this.controller = controller;
		
		JLabel lblSerie = new JLabel("Serie:");
		lblSerie.setBounds(45, 11, 49, 21);
		add(lblSerie);

		nombreSeries = getNombresSeries();
		comboBox = new JComboBox(nombreSeries);
		comboBox.setName("comboBox");
		comboBox.setBounds(142, 11, 187, 20);
		add(comboBox);
		comboBox.addActionListener(new OyenteBoton());
		comboBox.addFocusListener(new Focus());
		
		JLabel lblNewLabel = new JLabel("Episodios no vistos:");
		lblNewLabel.setBounds(31, 43, 146, 21);
		add(lblNewLabel);

		
		btnInformacion = new JButton("Informacion");
		btnInformacion.setBounds(20, 252, 122, 23);
		add(btnInformacion);
		btnInformacion.addActionListener(new OyenteBoton());
		
		
		btnVisto = new JButton("Marcar como visto");
		btnVisto.setBounds(162, 251, 167, 24);
		add(btnVisto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 344, 174);
		add(scrollPane);
		
		table = new JTable();
		myTableModelEpisodio = new MyTableModelEpisodio();
		table.setModel(myTableModelEpisodio);
		scrollPane.setViewportView(table);
		btnVisto.addActionListener(new OyenteBoton());
		
	}

	private String[] getNombresSeries() {
		this.series = controller.getUserSeries(); 
		Iterator<Serie> it = series.iterator();
		String[] nombres = new String[series.size()];
		int i = 0;
		while(it.hasNext())
		{
			nombres[i] = it.next().getNombre();
			i++;
		}
		return nombres;
	}
	
	private Serie buscaSerie(String nombre) {
		Iterator<Serie> iterador = series.iterator();
		Serie serie = null;
		while(iterador.hasNext()) {
			serie = iterador.next();
			if(serie.getNombre().equalsIgnoreCase(nombre))
				break;
		}
		return serie;
	}
	
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnInformacion){
				if(table.getSelectedRow() > -1) {
					int idEpisodio = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 0);
					Episodio episodio = myTableModelEpisodio.getEpisodio(idEpisodio);
					Serie serie = buscaSerie(comboBox.getSelectedItem().toString());
					new VistaEpisodio(serie, episodio, controller);
				}
				else
					JOptionPane.showMessageDialog(null,"Selecciona un capitulo");
			}else if(e.getSource() == btnVisto){
				if(table.getSelectedRow() > -1) {
					int idEpisodio = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 0);
					Episodio episodio = myTableModelEpisodio.getEpisodio(idEpisodio);
					controller.setEpisodioVisto(episodio);
					myTableModelEpisodio.eliminaEpisodio(episodio);
					myTableModelEpisodio.actualiza();
					JOptionPane.showMessageDialog(null,"Episodio marcado como visto");
				}
				else
					JOptionPane.showMessageDialog(null,"Selecciona un capitulo");
			}else if(e.getSource()== comboBox) {
				if(comboBox.getSelectedIndex() >= 0) {
					Serie serie = buscaSerie(comboBox.getSelectedItem().toString());
					episodios = controller.buscaEpisodiosNoVistos(serie);
					myTableModelEpisodio.setEpisodios(episodios);
					myTableModelEpisodio.actualiza();
				}
			}
		}
	}
	private class Focus implements FocusListener {

		@SuppressWarnings("unchecked")
		@Override
		public void focusGained(FocusEvent e) {
			series = controller.getUserSeries();
			nombreSeries = getNombresSeries();
			comboBox.removeAllItems();
			for(String str : nombreSeries) {
			   comboBox.addItem(str);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {}
		
	}
}
