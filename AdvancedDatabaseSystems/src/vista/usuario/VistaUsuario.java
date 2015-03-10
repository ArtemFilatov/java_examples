package vista.usuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import modelo.Usuario.Usuario;
import controlador.usuario.ControladorSeries;

public class VistaUsuario {

	private JFrame frmVentanaDeUsuario;
	
	private ControladorSeries controller;
	
	private JButton btnAvatar;
	private Usuario user;
	/**
	 * Create the application.
	 * @param user 
	 */
	public VistaUsuario(ControladorSeries control, Usuario user) {
		this.controller = control;
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmVentanaDeUsuario = new JFrame();
		frmVentanaDeUsuario.setTitle("Ventana de usuario");
		frmVentanaDeUsuario.setBounds(100, 100, 402, 507);
		frmVentanaDeUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaDeUsuario.getContentPane().setLayout(null);
		
		JPanel PanelUsuario = new JPanel();
				PanelUsuario.setBounds(10, 11, 364, 130);
				frmVentanaDeUsuario.getContentPane().add(PanelUsuario);
				PanelUsuario.setLayout(null);
				
				btnAvatar = new JButton("");
				btnAvatar.setBounds(25, 0, 120, 120);
				PanelUsuario.add(btnAvatar);
				btnAvatar.addActionListener(new OyenteBoton());
				btnAvatar.setIcon(user.getAvatar());
				
				JLabel lblNombreUsuario = new JLabel(user.getNick());
				lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
				lblNombreUsuario.setBounds(183, 21, 150, 39);
				PanelUsuario.add(lblNombreUsuario);
				
				
				
				JLabel lblEdad = new JLabel(calcularEdad());
				lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
				lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEdad.setBounds(218, 85, 84, 23);
				PanelUsuario.add(lblEdad);
		
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(10, 152, 364, 310);
			frmVentanaDeUsuario.getContentPane().add(tabbedPane);
			
			tabbedPane.addTab("Buscar series", new BuscarSeries(controller));
			tabbedPane.addTab("Mis series", new SeriesSiguiendo(controller));
		
		this.frmVentanaDeUsuario.setVisible(true);
		this.frmVentanaDeUsuario.setResizable(false);
		
	}
	

	private String calcularEdad() {
		long MILLSECS_DAY = 24 * 60 * 60 * 1000;// milisegundos por año
		
		Date hoy = new Date();
		long años = ((hoy.getTime() - user.getBirth().getTime())/MILLSECS_DAY)/365;
		return años + "";
	}

	public void eliminarGrafico(){
		this.frmVentanaDeUsuario.removeAll();
		this.frmVentanaDeUsuario.setVisible(false);	
	}
		
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnAvatar){
				eliminarGrafico();
				controller.cambiarGraficoDatos();		
			}	
		}
	}
}
