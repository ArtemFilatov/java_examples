package vista.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Serie.Personaje;

import controlador.admin.ControlAdminPersonajes;

public class FormularioPersonaje {

	private VistaPersonajes lvlSuperior;
	private JFrame frmFormularioPersonaje;
	private ControlAdminPersonajes controlador;
	private JTextField textNombre;
	private JButton btnCrearPersonaje;
	private JButton btnCerrar;
	private JTextArea textDescripcion;
	
	public FormularioPersonaje(ControlAdminPersonajes controlador, VistaPersonajes ventana) {
		this.lvlSuperior = ventana;
		this.controlador = controlador;
		 
		frmFormularioPersonaje = new JFrame();
		frmFormularioPersonaje.getContentPane().setLayout(null);
		frmFormularioPersonaje.setBounds(100, 100, 422, 335);
		frmFormularioPersonaje.setTitle("Formulario de personaje");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 95, 367, 144);
		frmFormularioPersonaje.getContentPane().add(scrollPane);
		
		textDescripcion = new JTextArea((String) null);
		textDescripcion.setLineWrap(true);
		scrollPane.setViewportView(textDescripcion);
		
		btnCrearPersonaje = new JButton("Crear Personaje");
		btnCrearPersonaje.setBounds(69, 266, 121, 29);
		frmFormularioPersonaje.getContentPane().add(btnCrearPersonaje);
		btnCrearPersonaje.addActionListener(new OyenteBoton());
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(243, 266, 95, 29);
		frmFormularioPersonaje.getContentPane().add(btnCerrar);
		btnCerrar.addActionListener(new OyenteBoton());
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDescripcion.setBounds(47, 64, 95, 20);
		frmFormularioPersonaje.getContentPane().add(lblDescripcion);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNombre.setBounds(33, 22, 95, 20);
		frmFormularioPersonaje.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(114, 22, 224, 20);
		frmFormularioPersonaje.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		
		frmFormularioPersonaje.setVisible(true);
		frmFormularioPersonaje.setResizable(false);
		
	}

	
	
	public void eliminarGrafico(){
		this.frmFormularioPersonaje.removeAll();
		this.frmFormularioPersonaje.setVisible(false);
		
	}
	
	
	
	private class OyenteBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCrearPersonaje){
				Personaje p = new Personaje(textNombre.getText(), textDescripcion.getText());
				controlador.insertarPersonaje(p);
				lvlSuperior.actualiza();
				eliminarGrafico();
			}else if(e.getSource() == btnCerrar){
				eliminarGrafico();
			} 
		}
	}
}
