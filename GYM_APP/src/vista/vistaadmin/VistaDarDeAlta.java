package vista.vistaadmin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JButton;


import vista.ViewInterface;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Clase que crea la ventana para dar de alta a un nuevo usurio 
 * la clase mostrara dos botones uno para los socios y 
 * otro para los usuarios y dependiendo del boton saldran unas opciones u otras
 * @author Julia,Jose
 *
 */
public class VistaDarDeAlta extends JFrame  implements ViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private final JComboBox comboBox;
	private GroupLayout gl_contentPane;
	private JLabel cuota;
	private JTextField textNombre,textCuota,textDni, textCorreo,textContraseña, textApellidos,textCuenta;
	JButton btnGuardar;
	int codigo;

	/**
	 * Constructora.
	 * @param usuarioSesion 
	 */
	public VistaDarDeAlta(int cd) {
		this.codigo = cd;
		setBounds(100, 100, 488, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setName("JtextNameDA");
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setName("jButonGuardarDarAlta");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setName("JtextDniDA");
		
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setName("JtextCorreoDA");
		
		textContraseña = new JTextField();
		textContraseña.setColumns(10);
		textContraseña.setName("JtextContrasenaDA");
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setName("JtextApellidosDA");
		
		cuota = new JLabel("Cuota:          1.- Mensual 2.- Trimestral 3.- Semestral 4.- Anual ");
		cuota.setFont(new Font("Dialog", Font.PLAIN, 18));
		cuota.setBounds(40, 475, 500, 50);
		
		textCuota= new JTextField();
		textCuota.setColumns(10);
		textCuota.setBounds(600, 475, 50, 50);
		textCuota.setName("JtextCuotaDA");
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCorreo, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar)
						.addComponent(textNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addComponent(textApellidos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addComponent(textCorreo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addComponent(textContraseña, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addComponent(textDni, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textApellidos, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(77)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCorreo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCorreo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textContraseña, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textDni, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(btnGuardar)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		if(this.codigo==1){
			setBounds(50, 10, 700, 700);
			 
			JLabel cuenta = new JLabel("Cuenta:");
			cuenta.setFont(new Font("Dialog", Font.PLAIN, 18));
			cuenta.setBounds(40, 550, 75, 50);
			getContentPane().add(cuenta);
			
			textCuenta = new JTextField();
			textCuenta.setColumns(10);
			textCuenta.setBounds(160, 550, 500, 50);
			textCuenta.setName("JtextCuentaDA");
			getContentPane().add(textCuenta);
			
			getContentPane().add(cuota);
			
			getContentPane().add(textCuota);
			
			cuota.setVisible(true);
			textCuota.setVisible(true);
			repaint();
		}else if(this.codigo==2){
			setBounds(50, 10, 700, 700);
			
			cuota.setVisible(false);
			textCuota.setVisible(false);
			
			JLabel cuenta = new JLabel("Cuenta:");
			cuenta.setFont(new Font("Dialog", Font.PLAIN, 18));
			cuenta.setBounds(40, 500, 75, 50);
			getContentPane().add(cuenta);
			
			textCuenta = new JTextField();
			textCuenta.setColumns(10);
			textCuenta.setBounds(160, 500, 500, 50);
			textCuenta.setName("JtextCuentaDA");
			getContentPane().add(textCuenta);
			repaint();
			
		}
	}
	
	
	public void arranca(){
		
		this.setVisible(true);
		
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) {
		btnGuardar.addActionListener((ActionListener) controlador);
		textApellidos.addKeyListener((KeyListener) controlador);
		textContraseña.addKeyListener((KeyListener) controlador);
		textCorreo.addKeyListener((KeyListener) controlador);
		textCuenta.addKeyListener((KeyListener) controlador);
		textCuota.addKeyListener((KeyListener) controlador);
		textDni.addKeyListener((KeyListener) controlador);
		textNombre.addKeyListener((KeyListener) controlador);
		
	}


	@Override
	public void muestraVentanita(String msg) {
		// TODO Auto-generated method stub
		
	}
}

