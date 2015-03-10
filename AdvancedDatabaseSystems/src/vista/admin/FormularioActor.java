package vista.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Serie.Actor;

import com.toedter.calendar.JDateChooser;

import controlador.admin.ControlAdminActores;

public class FormularioActor {

	private FormularioActor auxiliar;
		private ControlAdminActores controlador;
		private JFrame frmFormularioActor;

		private JButton btnCrear;
		private JButton btnCerrar;
		private VistaActores lvlSuperior;
		
		private JDateChooser dateNacimiento;
		private JLabel lblFechaDeNacimiento;
		private JLabel lblNif;
		private JTextField textNombre;
		private JTextField textNIF;
		private JLabel Foto;
		private JButton btnImagen;

		
		
		public FormularioActor( ControlAdminActores controller, VistaActores ventana) {
			this.lvlSuperior = ventana; 
			this.controlador = controller;
			 this.auxiliar = this;
			 
			 frmFormularioActor = new JFrame();
			 frmFormularioActor.getContentPane().setLayout(null);
			 frmFormularioActor.setBounds(100, 100, 422, 423);
			 frmFormularioActor.setTitle("Nuevo formulario de actor");	
			 
			 dateNacimiento = new JDateChooser();
			 dateNacimiento.setBounds(233, 284, 95, 20);
			 frmFormularioActor.getContentPane().add(dateNacimiento);
			 
			 JLabel lblSinopsis = new JLabel("Nombre");
			 lblSinopsis.setFont(new Font("Calibri", Font.PLAIN, 16));
			 lblSinopsis.setBounds(43, 22, 78, 27);
			 frmFormularioActor.getContentPane().add(lblSinopsis);
			 
			
			 
			 btnCrear = new JButton("Crear Actor");
			 btnCrear.setBounds(43, 338, 121, 29);
			 frmFormularioActor.getContentPane().add(btnCrear);
			 btnCrear.addActionListener(new OyenteBoton());
			 
			 btnCerrar = new JButton("Cerrar");
			 btnCerrar.setBounds(233, 338, 95, 29);
			 frmFormularioActor.getContentPane().add(btnCerrar);
			 btnCerrar.addActionListener(new OyenteBoton());
			 
			 lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
			 lblFechaDeNacimiento.setFont(new Font("Calibri", Font.PLAIN, 16));
			 lblFechaDeNacimiento.setBounds(43, 277, 160, 27);
			 frmFormularioActor.getContentPane().add(lblFechaDeNacimiento);
			 
			 lblNif = new JLabel("NIF");
			 lblNif.setFont(new Font("Calibri", Font.PLAIN, 16));
			 lblNif.setBounds(43, 64, 78, 27);
			 frmFormularioActor.getContentPane().add(lblNif);
			 
			 textNombre = new JTextField();
			 textNombre.setBounds(171, 22, 191, 23);
			 frmFormularioActor.getContentPane().add(textNombre);
			 textNombre.setColumns(10);
			 
			 textNIF = new JTextField();
			 textNIF.setColumns(10);
			 textNIF.setBounds(171, 56, 191, 23);
			 frmFormularioActor.getContentPane().add(textNIF);
			 
			 Foto = new JLabel(new ImageIcon("src/vista/img/defecto.png"));
			 Foto.setBounds(43, 102, 121, 120);
			 frmFormularioActor.getContentPane().add(Foto);
			 
			 btnImagen = new JButton("Añadir imagen");
			 btnImagen.setBounds(207, 163, 115, 37);
			 frmFormularioActor.getContentPane().add(btnImagen);
			 btnImagen.addActionListener(new OyenteBoton());
			 
			 frmFormularioActor.setBounds(100, 100, 422, 423);
			 frmFormularioActor.setTitle("Nuevo formulario de actor");	
			 
			 
			 
			 
			 frmFormularioActor.setVisible(true);
			 frmFormularioActor.setResizable(false);
			 
		}

		public void cambiarImagen(ImageIcon foto){
			Foto.setIcon(foto);
		}
		
		
		public void eliminarGrafico(){
			this.frmFormularioActor.removeAll();
			this.frmFormularioActor.setVisible(false);
			
		}
		
		private class OyenteBoton implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCrear){
					if (dateNacimiento.getDate() == null || textNombre.getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "Recuerde que el actor debe tener nombre y fecha de nacimiento");
					}else{
					
					ImageIcon foto = (ImageIcon) Foto.getIcon();
					Actor actor = new Actor(textNIF.getText(), textNombre.getText(), dateNacimiento.getDate(), foto);
					if(actor.nifCorrecto()){
					ArrayList<Actor> actoresbd = controlador.listaActoresNif("%"+actor.getNif()+"%");
					
					if(actoresbd.size() == 0){
						controlador.insertarActor(actor);
						lvlSuperior.actualiza();
						eliminarGrafico();
						}else{
							JOptionPane.showMessageDialog(null, "El Actor con ese nif ya existe");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Error en el formato del NIF");
					}
				}
				}else if(e.getSource() == btnCerrar){
					eliminarGrafico();
				} else if(e.getSource() == btnImagen){
					new JFileActor(auxiliar);
				}
			}
		}
	
	
}


