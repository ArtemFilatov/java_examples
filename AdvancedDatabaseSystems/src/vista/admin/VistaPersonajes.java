package vista.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.Serie.Actor;
import modelo.Serie.MyTableModelPersonaje;
import modelo.Serie.Personaje;
import controlador.admin.ControlAdminPersonajes;

public class VistaPersonajes extends JPanel {

	private ControlAdminPersonajes controlador;
	private JButton btnPersonaje;
	private JButton btnBuscar;
	private VistaPersonajes ventana;
	
	private JTextField textFindPersonajes;
	private MyTableModelPersonaje myTableModelPersonaje;
	private JScrollPane scrollPane;
	private JTable tablaPersonajes;
	private ArrayList<Personaje> personajes;
	/**
	 * Create the panel.
	 * @param controlAdminPersonajes 
	 */
	public VistaPersonajes(ControlAdminPersonajes controlAdminPersonajes) {
		this.ventana = this;
		this.controlador = controlAdminPersonajes;
		
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Buscar personaje:");
		lblNewLabel.setBounds(10, 13, 108, 19);
		add(lblNewLabel);
		
		textFindPersonajes = new JTextField();
		textFindPersonajes.setBounds(128, 12, 86, 20);
		add(textFindPersonajes);
		textFindPersonajes.setColumns(10);	
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(235, 11, 89, 23);
		add(btnBuscar);
		btnBuscar.addActionListener(new OyenteBoton());	

		btnPersonaje = new JButton("Nuevo personaje");
		btnPersonaje.addActionListener(new OyenteBoton());
		btnPersonaje.setBounds(106, 255, 142, 23);
		add(btnPersonaje);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 345, 204);
		add(scrollPane);
		
		myTableModelPersonaje = new MyTableModelPersonaje(this.getPersonajes());
		tablaPersonajes = new JTable();
		tablaPersonajes.setModel(myTableModelPersonaje);
		scrollPane.setViewportView(tablaPersonajes);
		
	}
	
	public void actualiza() {
		myTableModelPersonaje.setPersonajes(getPersonajes());
		myTableModelPersonaje.actualiza();
	}
	
	private ArrayList<Personaje> getPersonajes() {
		return this.controlador.getPersonajes();
	}
	
	private void setListPersonajes(ArrayList<Personaje> personajes) {
		myTableModelPersonaje.setPersonajes(personajes);
	}
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == btnBuscar){

				if(textFindPersonajes.getText().equals("")) {
					personajes = controlador.getPersonajes();
					
				} else {
					personajes = controlador.listaPersonajes("%"+textFindPersonajes.getText()+"%");
				}
				setListPersonajes(personajes);
				myTableModelPersonaje.actualiza();
				
			} else if(e.getSource() == btnPersonaje){
				new FormularioPersonaje(controlador, ventana);
			} 
		}


}
	
	
	
	
	
}
