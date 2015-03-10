package vista;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;

/**
 * Ventana que mostrará dos campos de texto con sus respectivas 
 * etiquetas que serviran para la  autentifivavion de los usuarios 
 * en el sistema, una sera para el correo y otra para la contraseña
 * @author Jose
 *
 */
public class LoginPanel extends JPanel implements ViewInterface
{
	private JLabel jLabelUsuario;
	private JLabel jLabelContrasena;
	private JLabel jLabelLoginMsg;
	private JTextField jTextFieldUsuario;
	private JPasswordField jTextFieldContrasena;
	private JButton jButtonConectar;
	private JButton jButtonCancelar;
	
	
	/**
	 * Constructora
	 */
	public LoginPanel()
	{
		initLoginPanel();
	}
	
	
	/**
	 * Metodo que inicializa y configura los elementos del LoginPanel
	 */
	private void initLoginPanel() {
		// TODO Auto-generated method stub
		
		
		
		this.jButtonConectar = new JButton("Conectar");
		this.jButtonConectar.setName("jButtonConectar");
		
		this.jButtonCancelar = new JButton("Cancelar");
		this.jButtonCancelar.setName("jButtonCancelar");
		
		this.jTextFieldUsuario = new JTextField();
		this.jTextFieldUsuario.setName("jTextFieldUsuario");
		this.jTextFieldUsuario.setColumns(10);
		
		this.jTextFieldContrasena = new JPasswordField();
		this.jTextFieldContrasena.setName("jTextFieldContrasena");
		this.jTextFieldContrasena.setColumns(10);
		
		this.jLabelUsuario = new JLabel("Correo");
		jLabelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.jLabelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.jLabelContrasena = new JLabel("Contrase\u00F1a");
		jLabelContrasena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.jLabelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.jLabelLoginMsg = new JLabel("");
		jLabelLoginMsg.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblGymGestorLogin = new JLabel("Gym Gestor Login");
		lblGymGestorLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(224)
					.addComponent(jButtonConectar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(108)
					.addComponent(jButtonCancelar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(238, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jLabelUsuario, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(44))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jLabelContrasena, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(jTextFieldContrasena, Alignment.LEADING)
						.addComponent(jTextFieldUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
					.addGap(199))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(278, Short.MAX_VALUE)
					.addComponent(jLabelLoginMsg, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGap(257))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(286, Short.MAX_VALUE)
					.addComponent(lblGymGestorLogin, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
					.addGap(218))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(94)
					.addComponent(lblGymGestorLogin, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextFieldUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabelUsuario, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextFieldContrasena, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabelContrasena))
					.addGap(60)
					.addComponent(jLabelLoginMsg, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jButtonCancelar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(jButtonConectar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(51))
		);
		this.setLayout(gl_contentPane);
		
		
		
	}

	/**
	 * Setear el mensaje
	 * @param text texto a introducir
	 * @param c color
	 */
	public void setLoginMsg(String text, Color c)
	{
		this.jLabelLoginMsg.setText(text);
		this.setForeground(c);
		
	}



	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) 
	{
		this.jButtonConectar.addActionListener((ActionListener)controlador);
		this.jButtonCancelar.addActionListener((ActionListener)controlador);
		// jText
		this.jTextFieldUsuario.addActionListener((ActionListener)controlador);
		this.jTextFieldUsuario.addKeyListener((KeyListener)controlador);
		this.jTextFieldContrasena.addActionListener((ActionListener)controlador);
		this.jTextFieldContrasena.addKeyListener((KeyListener)controlador);
		
		
	}

	@Override
	public void arranca() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void muestraVentanita(String msg) {
		// TODO Auto-generated method stub
		
	}
}
