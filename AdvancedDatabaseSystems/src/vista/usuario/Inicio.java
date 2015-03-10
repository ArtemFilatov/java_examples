package vista.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controlador.usuario.ControladorRegistro;

public class Inicio {

	private JFrame frmRegistroEInicio;
	private JTextField textNombre;
	private JTextField textUsuarioAcceso;

	private JButton btnRegistrarse;
	private JButton btnAcceder;

	private JDateChooser dateChooser;

	private ControladorRegistro controller;
	private JTextField passwordField;
	private JTextField textPasswordAcceder;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Inicio window = new Inicio();
	 * window.frmRegistroEInicio.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the application.
	 */
	public Inicio(ControladorRegistro controler) {
		this.controller = controler;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistroEInicio = new JFrame();
		frmRegistroEInicio.setTitle("Registro e inicio");
		frmRegistroEInicio.setBounds(100, 100, 475, 254);
		frmRegistroEInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistroEInicio.getContentPane().setLayout(null);

		JPanel panel_izquierda = new JPanel();
		panel_izquierda.setBounds(10, 11, 218, 198);
		frmRegistroEInicio.getContentPane().add(panel_izquierda);
		panel_izquierda.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(15, 25, 79, 17);
		panel_izquierda.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(104, 22, 104, 20);
		panel_izquierda.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(15, 70, 79, 14);
		panel_izquierda.add(lblContrasea);

		JLabel lblNacimiento = new JLabel("Nacimiento");
		lblNacimiento.setBounds(15, 111, 79, 14);
		panel_izquierda.add(lblNacimiento);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new OyenteBoton());
		btnRegistrarse.setBounds(30, 153, 146, 34);
		panel_izquierda.add(btnRegistrarse);

		passwordField = new JTextField();
		passwordField.setBounds(104, 67, 104, 23);
		panel_izquierda.add(passwordField);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(104, 111, 104, 20);
		panel_izquierda.add(dateChooser);

		JPanel panel_derecha = new JPanel();
		panel_derecha.setBounds(238, 11, 211, 198);
		frmRegistroEInicio.getContentPane().add(panel_derecha);
		panel_derecha.setLayout(null);

		JLabel lblNombreAcceso = new JLabel("Nombre");
		lblNombreAcceso.setBounds(22, 47, 56, 14);
		panel_derecha.add(lblNombreAcceso);

		textUsuarioAcceso = new JTextField();
		textUsuarioAcceso.setBounds(102, 44, 86, 20);
		panel_derecha.add(textUsuarioAcceso);
		textUsuarioAcceso.setColumns(10);

		JLabel lblContraseñaAcceso = new JLabel("Contrase\u00F1a");
		lblContraseñaAcceso.setBounds(22, 103, 68, 14);
		panel_derecha.add(lblContraseñaAcceso);

		btnAcceder = new JButton("acceder");
		btnAcceder.addActionListener(new OyenteBoton());
		btnAcceder.setBounds(39, 153, 137, 34);
		panel_derecha.add(btnAcceder);

		textPasswordAcceder = new JTextField();
		textPasswordAcceder.setBounds(102, 100, 86, 20);
		panel_derecha.add(textPasswordAcceder);

		this.frmRegistroEInicio.setVisible(true);
		this.frmRegistroEInicio.setResizable(false);
		
	}

	public void eliminarGrafico() {
		this.frmRegistroEInicio.removeAll();
		this.frmRegistroEInicio.setVisible(false);
	}

	private class OyenteBoton implements ActionListener {

		@Override
		/**
		 * metodo que implementa la accion derivada de la pulsacion del boton
		 * @param e - evento a tratar
		 */
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnAcceder) {

				String usuario = textUsuarioAcceso.getText();
				String pass = textPasswordAcceder.getText();
				
				if (usuario.equalsIgnoreCase("") || pass.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null,"Fallo, algun campo esta incompleto");
				} else if (controller.acceso(usuario, pass)) {
					eliminarGrafico();

					controller.cambiarGrafico();
				} else {
					JOptionPane.showMessageDialog(null,"Fallo al acceder, el usuario y la contraseña no coinciden");

				}

			} else if (e.getSource() == btnRegistrarse) {
				String usuario = textNombre.getText();
				String pass = passwordField.getText();
				Date fecha = dateChooser.getDate();
				if (usuario.equalsIgnoreCase("") || pass.equalsIgnoreCase("")|| fecha == null) {
					JOptionPane.showMessageDialog(null,"Fallo, algun campo esta incompleto");
				
				} else if (controller.registro(usuario, pass, fecha)) {
					eliminarGrafico();
					controller.cambiarGrafico();
				} else {
					JOptionPane.showMessageDialog(null,"Fallo, el usuario ya existe o hay algun problema en el registro");
				}

			}

		}
	}
}
