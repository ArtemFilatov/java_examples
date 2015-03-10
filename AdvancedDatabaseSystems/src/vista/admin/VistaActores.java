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
import modelo.Serie.MyTableModelActor;
import controlador.admin.ControlAdminActores;

public class VistaActores extends JPanel {

	private VistaActores ventana;
	private ControlAdminActores controlador;
	private JButton btnActor;
	private JButton btnBuscar;
	
	private JTextField textFindActores;
	private MyTableModelActor myTableModelActor;
	private JTable tablaActores;
	private ArrayList<Actor> actores;
	
	/**
	 * Create the panel.
	 * @param controlAdminActores 
	 */
	public VistaActores(ControlAdminActores controlAdminActores) {
		this.controlador = controlAdminActores;
		this.ventana = this;
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Buscar actor:");
		lblNewLabel.setBounds(42, 13, 76, 19);
		add(lblNewLabel);
		
		textFindActores = new JTextField();
		textFindActores.setBounds(128, 12, 86, 20);
		add(textFindActores);
		textFindActores.setColumns(10);	
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(235, 11, 89, 23);
		add(btnBuscar);
		btnBuscar.addActionListener(new OyenteBoton());	
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 338, 196);
		add(scrollPane);
		
		
		//tabla actor
		myTableModelActor = new MyTableModelActor(this.getActores());
		tablaActores = new JTable();
		tablaActores.setModel(myTableModelActor);
		scrollPane.setViewportView(tablaActores);
		
		btnActor = new JButton("Nuevo actor");
		btnActor.addActionListener(new OyenteBoton());
		btnActor.setBounds(107, 255, 116, 23);
		add(btnActor);
		

	}

	public void actualiza() {
		myTableModelActor.setActores(getActores());
		myTableModelActor.actualiza();
	}
	
	private ArrayList<Actor> getActores() {
		return this.controlador.getActores();
	}
	
	private void setListActores(ArrayList<Actor> actores) {
		myTableModelActor.setActores(actores);
		
	}
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnBuscar){

				if(textFindActores.getText().equals("")) {
					actores = controlador.getActores();	
				} else {
					actores = controlador.listaActores("%"+textFindActores.getText()+"%");
				}
				setListActores(actores);
				myTableModelActor.actualiza();
			
			} else if(e.getSource() == btnActor){
				new FormularioActor(controlador, ventana);
				
			} 
		}


}
}
