
package vista.vistaadmin;

import vista.ViewInterface;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import modelo.MyTableModelActividades;
import modelo.MyTableModelEntrenador;
import modelo.MyTableModelSocio;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferAdministrador;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;

import java.awt.Font;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;

/**
 * Clase que muestra el panel principal del administrador
 * que contendrá una barra de menu con las diferentes opciones
 * @author Jose
 *
 */
public class VistaAdmin extends JPanel implements ViewInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo ;
	private JPanel panelCambio ;
	private JScrollPane pane,scrolls,scrolle;
	private JTable jTableVistaAdmin;	
	private JButton nuevo,darBaja,jButtonModificar,nuevoAc,borraAc,modificarAc;
	private JMenuBar menuBar;
	private TransferAdministrador usuarioSesion;
	private JButton btnGestionEntrenadores,jButtonHorario,jButtonGestionSocios,btnDarAlta,btnDarAltaSocio,btnDarAltaEntrenador;

	//Componentes
	private MyTableModelSocio tableModelSocios;	
	private MyTableModelEntrenador tableModelEntrenadores;
	private MyTableModelActividades tableActividades;
	
	private TransferEntrenador entrenadorNuevo;
	private JButton jButtonUnlogin;
	private JLabel label;
	/**
	 * Constructora.
	 * @param usuarioSesion2 
	 */
	public VistaAdmin(TransferObject usuarioSesion2) {
		
		this.usuarioSesion = (TransferAdministrador)usuarioSesion2;
		
		
		menuBar = new JMenuBar();
		
		jButtonHorario = new JButton("Gestionar Horario");	
		jButtonHorario.setName("jMenuHorarioAdmin");
		menuBar.add(jButtonHorario);
		
		
		btnDarAlta = new JButton("Dar de Alta");
		btnDarAlta.setName("jMenuVistaAdminDarAlta");
	  	menuBar.add(btnDarAlta);
		
	  	jButtonGestionSocios = new JButton("Gestionar Socios");
	  	jButtonGestionSocios.setName("jMenuVistaAdminGestionSocios");

		menuBar.add(jButtonGestionSocios);
		
		btnGestionEntrenadores = new JButton("Gestionar Entrenadores");
		btnGestionEntrenadores.setName("jMenuVistaAdminGestionarEntrenadores");

		menuBar.add(btnGestionEntrenadores);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		
		this.titulo = new JLabel("Bienvenido");
		titulo.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 41));
		this.titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		jButtonUnlogin = new JButton("Cerrar Sesion");
		jButtonUnlogin.setName("jMenuVistaUnlogin");

		  menuBar.add(jButtonUnlogin);
		
		panelCambio = new JPanel();
		
		
//		imagen del gym
		
		
		
		JLabel imagenGym = new JLabel("");
		
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("imagen.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(608, Short.MAX_VALUE)
					.addComponent(imagenGym, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelCambio, GroupLayout.PREFERRED_SIZE, 735, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 558, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(imagenGym, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelCambio, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
	
		
		//this.model = new modeloAdminGS();
		this.tableModelSocios = new MyTableModelSocio();
		this.tableActividades = new MyTableModelActividades();
		this.tableModelEntrenadores = new MyTableModelEntrenador();
		this.jTableVistaAdmin= new JTable(this.tableModelSocios);
		this.jTableVistaAdmin.setName("jTableVistaAdmin");
		
		this.jTableVistaAdmin.setPreferredScrollableViewportSize(new Dimension(200, 100));
		
		this.jTableVistaAdmin.setAutoCreateRowSorter(true); // para ordenar los elementos
		
		this.pane = new JScrollPane(this.jTableVistaAdmin);
		
		this.pane.setVisible(false);
	
		GroupLayout gl_panelCambio = new GroupLayout(panelCambio);
		gl_panelCambio.setHorizontalGroup(
			gl_panelCambio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCambio.createSequentialGroup()
					.addGap(36)
					.addComponent(pane, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(145, Short.MAX_VALUE))
		);
		gl_panelCambio.setVerticalGroup(
			gl_panelCambio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCambio.createSequentialGroup()
					.addGap(72)
					.addComponent(pane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(317, Short.MAX_VALUE))
		);
		
		nuevo = new JButton("Ver");
		nuevo.setBounds(600, 100, 100, 50);
		nuevo.setName("btnVer");

		
		jButtonModificar = new JButton("Modificar");
		jButtonModificar.setBounds(600, 225, 100, 50);
		jButtonModificar.setName("jButtonModificar");
		
		
		darBaja = new JButton("Dar de baja");
		darBaja.setBounds(575, 350, 150, 50);
		darBaja.setName("jBotonDarBaja");
		
		//Botones para el horario
		nuevoAc = new JButton("Nueva Actividad");
		nuevoAc.setBounds(575, 100, 150, 50);
		nuevoAc.setName("btnNewAc");

		
		modificarAc = new JButton("Modificar Actividad");
		modificarAc.setBounds(575, 225, 150, 50);
		modificarAc.setName("jModificarAc");
		
		
		borraAc = new JButton("Borrar Actividad");
		borraAc.setBounds(575, 350, 150, 50);
		borraAc.setName("jBorraAc");
				
		//botonesDarAlta
		btnDarAltaSocio=new JButton("Socio");
		btnDarAltaSocio.setBounds(500, 150, 100, 50);
		btnDarAltaSocio.setName("jButtonDarAltaSocio");
		
		btnDarAltaEntrenador = new JButton("Entrenador");
		btnDarAltaEntrenador.setBounds(300, 150, 100, 50);
		btnDarAltaEntrenador.setName("jButtonDarAltaEntrenador");
		
		
		this.panelCambio.add(nuevo);
		this.panelCambio.add(jButtonModificar);
		this.panelCambio.add(darBaja);
		this.panelCambio.add(btnDarAltaSocio);
		this.panelCambio.add(btnDarAltaEntrenador);
		this.panelCambio.add(nuevoAc);
		this.panelCambio.add(modificarAc);
		this.panelCambio.add(borraAc);
		panelCambio.setLayout(gl_panelCambio);
		
		nuevoAc.setVisible(false);
		modificarAc.setVisible(false);
		borraAc.setVisible(false);
		btnDarAltaEntrenador.setVisible(false);
		btnDarAltaSocio.setVisible(false);
		nuevo.setVisible(false);
		darBaja.setVisible(false);
		jButtonModificar.setVisible(false);
		
		contentPane.setLayout(gl_contentPane);
		
		setVisible(true);
	}
	
	/**
	 * Metodo que se encargara de la opcion gestion de entrenadores
	 * @param entrenadores la lista de entrenadores
	 */
	public void gestionEntrenador(ArrayList<TransferEntrenador> entrenadores){
		titulo.setText("Gestión De Los Entrenadores");
		this.tableModelEntrenadores.setEntrenadores(entrenadores);
		this.jTableVistaAdmin.setModel(this.tableModelEntrenadores);
		this.borraAc.setVisible(false);
		this.nuevoAc.setVisible(false);
		this.modificarAc.setVisible(false);
		this.pane.setVisible(true);
		this.pane.repaint();
		
		btnDarAltaSocio.setVisible(false);
		btnGestionEntrenadores.setVisible(false);
		
		nuevo.setVisible(true);
		darBaja.setVisible(true);
		jButtonModificar.setVisible(true);
		this.pane.setVisible(true);
		this.panelCambio.setVisible(true);
		
	}

	/**
	 * Metodo que se encargara de la opcion gestion de entrenadores
	 * @param socios la lista de socios
	 */
	public void gestionSocio(ArrayList<TransferSocio> socios)
	{
		this.titulo.setText("Gestión De Los Socios");
		this.tableModelSocios.setTableSocio(socios);
		this.jTableVistaAdmin.setModel(this.tableModelSocios);
		this.borraAc.setVisible(false);
		this.nuevoAc.setVisible(false);
		this.modificarAc.setVisible(false);
		this.pane.setVisible(true);
		this.pane.repaint();
		
		btnDarAltaSocio.setVisible(false);
		btnGestionEntrenadores.setVisible(false);
		
		nuevo.setVisible(true);
		darBaja.setVisible(true);
		jButtonModificar.setVisible(true);
	}
	
	/**
	 * Metodo que se encargara de la opcion dar de alta
	 */
	public void gestionAlta(){
		nuevo.setVisible(false);
		darBaja.setVisible(false);
		jButtonModificar.setVisible(false);
		this.borraAc.setVisible(false);
		this.nuevoAc.setVisible(false);
		this.modificarAc.setVisible(false);
		this.pane.setVisible(false);
		this.titulo.setText("Selecione Tipo De Usuario");
		btnDarAltaSocio.setVisible(true);
		btnDarAltaEntrenador.setVisible(true);

		
		this.panelCambio.setVisible(true);
		
	}
	

	
	/**
	 * Metodo que se encargara de la opcion gestion de horario
	 * @param horario
	 */
	public void gestionHorario(TransferHorario horario) 
	{	
		
		this.tableActividades.setTableActividades(horario.getActividades());
		this.jTableVistaAdmin.setModel(this.tableActividades);

		titulo.setText("Lista de Actividades");
		this.btnDarAltaSocio.setVisible(false);
		this.borraAc.setVisible(true);
		this.nuevoAc.setVisible(true);
		this.modificarAc.setVisible(true);
		nuevo.setVisible(false);
		jButtonModificar.setVisible(false);
		
		this.pane.setVisible(true);
		
		
		this.pane.repaint();
		
		panelCambio.setVisible(true);
		
		
	}
	
	public JMenuBar getJMenu(){
		
		return this.menuBar;
		
	}
	public TransferEntrenador getEntrenadorNuevo(){
		return this.entrenadorNuevo;
	}

	public Container getContentPane() {
		// TODO Auto-generated method stub
		return this.contentPane;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) {
		
		jButtonGestionSocios.addActionListener((ActionListener)controlador);
		jButtonModificar.addActionListener((ActionListener)controlador);
		this.jTableVistaAdmin.addMouseListener((MouseListener) controlador);
		
		btnGestionEntrenadores.addActionListener((ActionListener)controlador);
		nuevo.addActionListener((ActionListener)controlador);
		btnDarAlta.addActionListener((ActionListener)controlador);
		darBaja.addActionListener((ActionListener)controlador);
		btnDarAltaEntrenador.addActionListener((ActionListener)controlador);
		jButtonHorario.addActionListener((ActionListener)controlador);
		btnDarAltaSocio.addActionListener((ActionListener)controlador);
		borraAc.addActionListener((ActionListener)controlador);
		nuevoAc.addActionListener((ActionListener)controlador);
		jButtonUnlogin.addActionListener((ActionListener)controlador);
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

