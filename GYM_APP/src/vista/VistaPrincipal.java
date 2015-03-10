package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import vista.vistaadmin.VistaAdmin;
import vista.vistaentrena.VistaEntrenador;
import vista.vistasocio.VistaSocio;

/**
 * Vista principal donde se mostraran las diferentes ventanas
 * dependiendo del tipo de usurio, todas tendran una barra de menu 
 * con las diferentes opciones
 * @author Jose
 *
 */
public class VistaPrincipal extends JFrame implements ViewInterface 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String WINDOW_TITLE= "GymGestor";
	private Container panelPrincipal;

	//Menu
	private  JMenuBar menuBar;
	
	//Paneles
	private LoginPanel loginPanel; // Panel de Login.
	private JLabel jLabelloginMsg;
	private VistaSocio socioPanel;
	private VistaEntrenador entrenadorPanel;
	private VistaAdmin adminpanel;

	/**
	 * Constructor de la ventana principal sin parametros.
	 */
	public VistaPrincipal() 
	{
		super(WINDOW_TITLE);
        inicializaVistaPrincipal();
	}

	/**
	 * Este metodo configura añade e inicializa los elementos de la vista.
	 */
	private void inicializaVistaPrincipal() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelPrincipal = this.getContentPane();
        
        this.loginPanel = new LoginPanel();
        this.jLabelloginMsg = new JLabel("Test"); //
        
        panelPrincipal.add(loginPanel);
        
        this.menuBar = new JMenuBar();
		this.menuBar.setVisible(false);
        this.setJMenuBar(this.menuBar);
        
		
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(798,582));
        this.pack();
        
        	
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) 
	{
		// Fijamos el controlador de todos los paneles de la vista.
		this.loginPanel.fijarControlador(controlador);
		
	}

	@Override
	public void arranca() {
		setVisible(true);
		
	}
	
	/**
	 * Elimina el componente de la vista.
	 */
	public void removeLoginPanel()
	{
		this.remove(this.jLabelloginMsg);
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
	
	public VistaSocio getSocioPanel() {
		return socioPanel;
	}

	public void setSocioPanel(VistaSocio socioPanel) {
		this.socioPanel = socioPanel;
	}

	public VistaEntrenador getEntrenadorPanel() {
		return entrenadorPanel;
	}

	public void setEntrenadorPanel(VistaEntrenador entrenadorPanel) {
		this.entrenadorPanel = entrenadorPanel;
	}

	public VistaAdmin getAdminpanel() {
		return adminpanel;
	}

	public void setAdminpanel(VistaAdmin adminpanel) {
		this.adminpanel = adminpanel;
	}
	
	 public void muestraVentanita(String msg) {
		  JOptionPane.showMessageDialog(this,msg ,"",2);
		  
		 }
}
