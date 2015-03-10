package vista.usuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modelo.Usuario.Usuario;

import com.toedter.calendar.JDateChooser;

import controlador.usuario.ControladorDatosPersonales;

public class DatosPersonales {

	private DatosPersonales auxiliar;
	private JFrame frmDatosUsuario;
	private JTextField passwordOld;
	private JTextField passwordNew;
	
	private JLabel Imagen;
	private JLabel Nombre;
	private JDateChooser dateChooser;
	
	private JButton Actualizar;
	private JButton Descartar;
	private JButton btnImagen;
	
	private ControladorDatosPersonales controller;
	
	private Usuario user;
	
	/**
	 * Create the application.
	 * @param user 
	 */
	public DatosPersonales(ControladorDatosPersonales datos, Usuario user) {
		this.auxiliar = this;
		this.user = user;
		this.controller = datos;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatosUsuario = new JFrame();
		frmDatosUsuario.setTitle("Datos usuario: "+user.getNick());
		frmDatosUsuario.setBounds(100, 100, 353, 414);
		frmDatosUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatosUsuario.getContentPane().setLayout(null);
		
		Actualizar = new JButton("Actualizar");
		Actualizar.setBounds(21, 327, 133, 23);
		frmDatosUsuario.getContentPane().add(Actualizar);
		Actualizar.addActionListener(new OyenteBoton());
		
		Descartar = new JButton("Descartar");
		Descartar.setBounds(178, 327, 111, 23);
		frmDatosUsuario.getContentPane().add(Descartar);
		Descartar.addActionListener(new OyenteBoton());
		
		JLabel lblNombre = new JLabel(user.getNick());
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(89, 11, 155, 39);
		frmDatosUsuario.getContentPane().add(lblNombre);
		
		passwordOld = new JTextField();
		passwordOld.setBounds(200, 62, 111, 23);
		frmDatosUsuario.getContentPane().add(passwordOld);
		
		passwordNew = new JTextField();
		passwordNew.setBounds(200, 92, 111, 23);
		frmDatosUsuario.getContentPane().add(passwordNew);
		
		JLabel lblContraseaAntigua = new JLabel("Contrase\u00F1a antigua");
		lblContraseaAntigua.setBounds(21, 60, 161, 23);
		frmDatosUsuario.getContentPane().add(lblContraseaAntigua);
		
		JLabel lblContraseaNueva = new JLabel("Contrase\u00F1a nueva");
		lblContraseaNueva.setBounds(21, 92, 133, 23);
		frmDatosUsuario.getContentPane().add(lblContraseaNueva);
		
		Imagen = new JLabel(user.getAvatar());
		Imagen.setBounds(36, 181, 118, 120);
		frmDatosUsuario.getContentPane().add(Imagen);
		
		btnImagen = new JButton("Cambiar Imagen");
		btnImagen.setFont(new Font("Dialog", Font.BOLD, 12));
		btnImagen.addActionListener(new OyenteBoton());
		btnImagen.setBounds(157, 225, 154, 29);
		frmDatosUsuario.getContentPane().add(btnImagen);
		
		Nombre = new JLabel("");
		Nombre.setBounds(176, 14, 46, 14);
		frmDatosUsuario.getContentPane().add(Nombre);
		
		dateChooser = new JDateChooser(user.getBirth());
		dateChooser.setBounds(200, 149, 111, 23);
		frmDatosUsuario.getContentPane().add(dateChooser);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(21, 149, 161, 20);
		frmDatosUsuario.getContentPane().add(lblFechaDeNacimiento);
		
		this.frmDatosUsuario.setVisible(true);
		this.frmDatosUsuario.setResizable(false);
		
	}
	
	
	public void cambiarImagen(ImageIcon foto){
		Imagen.setIcon(foto);
	}
	
	
	public void eliminarGrafico(){
		this.frmDatosUsuario.removeAll();
		this.frmDatosUsuario.setVisible(false);	
	}
	
	
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Actualizar){
				if((passwordOld.getText().equalsIgnoreCase(""))){ 
					JOptionPane.showMessageDialog(null,"Rellene el campo Contraseña antigua"); 
				}else if (controller.actualiza(passwordOld.getText(), passwordNew.getText(), dateChooser.getDate(),(ImageIcon) Imagen.getIcon())){
					eliminarGrafico();
					controller.cambiarGrafico();
				}else{
					JOptionPane.showMessageDialog(null,"Error en los datos, recuerde que es necesario su contraseña para cambiar datos"); 
				}
			
			}else if (e.getSource() == Descartar){
				eliminarGrafico();
				controller.cambiarGrafico();
			
			}else if(e.getSource() == btnImagen) {//TODO aprender gestion de archivos
				new JFile(auxiliar);
				
			}
		}
		
		
		
	}
}
