package vista.vistaadmin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.LayoutStyle.ComponentPlacement;

import vista.ViewInterface;
import modelo.transfer.users.TransferUsuario;

/**
 * Ventana que permite tanto ver como modificar los datos de los entrenadores y de los socios
 * mostrara campos de texto con sus respectivas etiquetas con los datos
 * @author Jose
 *
 */
public class VerModificarTipo extends JFrame implements ViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jTextNombre,jTextApellidos,jTextTlf,jTextDireccion;
	private JButton jButtonGuardarModificar;
	private TransferUsuario usuario;
	
	/**
	 * Constructora.
	 * @param usuarioSesion 
	 */
	public VerModificarTipo(final String tipo, TransferUsuario usuarioSesion) {
		
		this.usuario = usuarioSesion;
		
		setBounds(100, 100, 500, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		
		jTextNombre = new JTextField();
		jTextNombre.setName("jTextNombre");		
		jTextNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos");
		
		jTextApellidos = new JTextField();
		jTextApellidos.setName("jTextApellidos");
		jTextApellidos.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono");
		
		jTextTlf = new JTextField();
		jTextTlf.setName("jTextTlf");
		jTextTlf.setColumns(10);
		
		
		jButtonGuardarModificar = new JButton("Guardar Cambios");
		jButtonGuardarModificar.setName("jButtonGuardarModificar");
		
		JLabel lblDireccion = new JLabel("Direccion");
		
		jTextDireccion = new JTextField();
		jTextDireccion.setName("jTextDireccion");
		jTextDireccion.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(137)
					.addComponent(jButtonGuardarModificar)
					.addContainerGap(232, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
										.addGap(35))
									.addComponent(lblNewLabel_1))
								.addGap(96))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(93)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jTextDireccion, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextTlf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(165))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(jTextNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(jTextApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextTlf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion)
						.addComponent(jTextDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addComponent(jButtonGuardarModificar)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		
		

		
		
		if(tipo.equalsIgnoreCase("ver")){
			
			this.jTextNombre.setEditable(false);
			this.jTextApellidos.setEditable(false);
			this.jTextTlf.setEditable(false);
			this.jTextDireccion.setEditable(false);
			jButtonGuardarModificar.setVisible(false);
			verTipo();
			
		}else if(tipo.equalsIgnoreCase("modificar")){
			
			jButtonGuardarModificar.setVisible(true);
			
			modificarTipo();
			
		}
		
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Metodo para la opcion ver el el que solo se mostraran los datos
	 */
	private void verTipo(){
//		aqui se busca en la bd con el dni del parametro del metodo
//		aqui se muestran en las txtField el contenido de cada tipo.
		this.jTextNombre.setText(usuario.getNombre());
		this.jTextApellidos.setText(usuario.getApellidos());
//		this.jTextDireccion.setText(usuario.getDireccion());
//		this.textTlf.setText(String.valueOf(usuario.getTelefono()));
	}
	/**
	 * Metodo para la opcion modificar el el que solo se mostraran los datos
	 */
	private void modificarTipo(){

//		aqui se busca en la bd con el dni del parametro del metodo
		this.jTextNombre.setText(usuario.getNombre());
		this.jTextApellidos.setText(usuario.getApellidos());
//		this.jTextDireccion.setText(usuario.getDireccion());
//		this.textTlf.setText(String.valueOf(usuario.getTelefono()));

		
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
		this.jTextNombre.addKeyListener((KeyListener)controlador);
		this.jTextNombre.addActionListener((ActionListener)controlador);
		this.jTextApellidos.addKeyListener((KeyListener)controlador);
		this.jTextApellidos.addActionListener((ActionListener)controlador);
		this.jTextTlf.addKeyListener((KeyListener)controlador);
		this.jTextTlf.addActionListener((ActionListener)controlador);
		this.jTextDireccion.addKeyListener((KeyListener)controlador);
		this.jTextDireccion.addActionListener((ActionListener)controlador);
		this.jButtonGuardarModificar.addActionListener((ActionListener)controlador);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void muestraVentanita(String msg) {
		// TODO Auto-generated method stub
		
	}
}

