package vista.vistaadmin;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import vista.ViewInterface;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

import modelo.MyTableModelEntrenador;
import modelo.transfer.TransferActividad;
import modelo.transfer.users.TransferEntrenador;

/**
 * Clase que crea la ventana para crear y añadir una nueva actividad
 * mostrara los campos a anañir con sus respectivas etiquetas
 * @author jose,Julia
 *
 */
public class VistaNuevaActividad extends JFrame  implements ViewInterface{

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		//private final JComboBox comboBox;
		private GroupLayout gl_contentPane;
		private JLabel cuota;
		private JTextField textNombre;
		JButton btnGuardar;
		int codigo;
		private JTextField textHInico;
		private JTextField textHFin;
		private JTable jTableVistaNuevaActividad;
		private MyTableModelEntrenador tableModelEntrenadores;
		private JScrollPane scrollPane;

		/**
		 * Constructora
		 */
		public VistaNuevaActividad(){
			
			setBounds(100, 100, 488, 436);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			

			btnGuardar = new JButton("Guardar");
			btnGuardar.setName("jButonGuardarActividad");

			btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			//DNI
			JLabel lblEntrenador = new JLabel("Entrenador:");
			lblEntrenador.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			//Correo
			JLabel lblHInicio = new JLabel("Hora de Inicio:");
			lblHInicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			//Contraseña
			JLabel lblContrasea = new JLabel("Hora de Fin:");
			lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textHInico = new JTextField();
			textHInico.setColumns(10);
			textHInico.setName("JtextInicioAC");
			
			textHFin = new JTextField();
			textHFin.setColumns(10);
			textHFin.setName("JtextFinAC");
			
			
			
			textNombre = new JTextField();
			textNombre.setColumns(10);
			textNombre.setName("JtextNameAC");
			
			
			tableModelEntrenadores = new MyTableModelEntrenador();
			jTableVistaNuevaActividad = new JTable(tableModelEntrenadores);
			jTableVistaNuevaActividad.setName("jTableVistaNuevaActividad");
			scrollPane = new JScrollPane(jTableVistaNuevaActividad);
			
			
			
			
			gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnGuardar)
								.addGap(182))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(49)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
												.addGap(18)))
										.addGap(18))
									.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblEntrenador, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblHInicio, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textHInico, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
									.addComponent(textNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
									.addComponent(textHFin, 239, 239, 239))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
								.addContainerGap())))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(40)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNombre))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textHInico, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHInicio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textHFin, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(lblEntrenador, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(btnGuardar))
			);
			contentPane.setLayout(gl_contentPane);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) {
		btnGuardar.addActionListener((ActionListener) controlador);
		textNombre.addKeyListener((KeyListener) controlador);
		
		textHInico.addKeyListener((KeyListener) controlador);
		textHFin.addKeyListener((KeyListener) controlador);
		jTableVistaNuevaActividad.addMouseListener((MouseListener)controlador);
	}

	@Override
	public void arranca() {
		this.setVisible(true);
		
	}

	public void fijarEntrenadores(ArrayList<TransferEntrenador> listEntrenadores)
	{
		if(listEntrenadores == null)
		{
			ArrayList<TransferEntrenador> aux = new ArrayList<TransferEntrenador>();
			this.tableModelEntrenadores.setTableEntrenadores(aux);
		}
		else
			this.tableModelEntrenadores.setTableEntrenadores(listEntrenadores);
		this.jTableVistaNuevaActividad.setModel(tableModelEntrenadores);
		this.scrollPane.repaint();
		this.contentPane.repaint();		
		
	}
	@Override
	public void muestraVentanita(String msg) {
		// TODO Auto-generated method stub
		
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
