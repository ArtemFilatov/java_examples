package vista.vistasocio;

import vista.ViewInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import modelo.MyTableModelActividades;
import modelo.MyTableModelEntrenador;
import modelo.MyTableModelFactura;
import modelo.MyTableModelRutina;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferFactura;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.TransferRutina;
import modelo.transfer.users.TransferEntrenador;

/**
 Clase que muestra el panel principal del socio
 * que contendrá una barra de menu con las diferentes opciones
 * @author Julia
 *
 */
public class VistaSocio extends JPanel implements ViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelCambio;
	private JLabel titulo ;
	private JScrollPane pane;
	private DefaultTableModel model;
	private JMenu mnConsultas, verHorarios;
	private JMenuItem verRutinas,miHorario,gymHorario;
	private JButton jButtonApuntarseActividad,jButtonEntrenamientoPersonal, jButtonVerEntrenadores, jButtonVerRutinaSocio, jButtonFacturasSocios;
	private JMenuBar menu;
	
	// Components.
	private JTable jTableVistaSocio;	
	private MyTableModelActividades tableActividadesSocio;
	private MyTableModelEntrenador tableEntrenadoresSocio;
	private MyTableModelRutina tableRutinasSocio;
	private MyTableModelFactura tableFacturasSocio;
	private JButton jButtonUnlogin;
	private JLabel lblNewLabel;
	

	/**
	 * Constructor.
	 * @param usuarioSesion 
	 */
	public VistaSocio(TransferObject usuarioSesion) 
	{
		
		menu = new JMenuBar();
		
		mnConsultas = new JMenu("Consultas");
		menu.add(mnConsultas);
		
		verRutinas = new JMenuItem("Ver Rutinas");
		verRutinas.setName("jFileMenuSocioVerRutinas");
		mnConsultas.add(verRutinas);
		
		verHorarios = new JMenu("Ver Horarios");
		mnConsultas.add(verHorarios);
		
		miHorario = new JMenuItem("Mi Horario");
		miHorario.setName("jFileMenuSocioMiHorario");
		verHorarios.add(miHorario);
		
		gymHorario = new JMenuItem("Ver Actividades de Hoy");
		gymHorario.setName("jFileMenuSocioHorarioSistema");
		verHorarios.add(gymHorario);
		
		jButtonFacturasSocios = new JButton("Gestionar Facturas");
		jButtonFacturasSocios.setName("jButtonFacturasSocios");
		menu.add(jButtonFacturasSocios);
		
		this.jButtonVerEntrenadores = new JButton("Entrenamiento Personal");
		this.jButtonVerEntrenadores.setName("jButtonVerEntrenadores");
	

		
		menu.add(jButtonVerEntrenadores);
		jButtonUnlogin = new JButton("Cerrar Sesion");
		jButtonUnlogin.setName("jMenuVistaUnlogin");

		  menu.add(jButtonUnlogin);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
//		setContentPane(contentPane);
		
		this.titulo = new JLabel("Bienvenido");
		titulo.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 41));
		this.titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelCambio = new JPanel();
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("imagen.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panelCambio, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panelCambio, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
					.addGap(151))
		);
	
		/* JTABLE GENERADA POR EL WINDOW BUILDER 
		String data[][] = new String[0][2];
		String col[] = { "", "" };
		
		this.model = new DefaultTableModel(data, col);

		this.jTableActividades= new JTable(this.model);
		
		this.jTableActividades.setPreferredScrollableViewportSize(new Dimension(200, 100));
		
		this.jTableActividades.setAutoCreateRowSorter(true); // para ordenar los elementos */
		
		/* JTable por nosotros */
		this.tableEntrenadoresSocio = new MyTableModelEntrenador();
		this.tableActividadesSocio = new MyTableModelActividades();
		this.tableRutinasSocio = new MyTableModelRutina(); 
		this.tableFacturasSocio = new MyTableModelFactura();
		this.jTableVistaSocio = new JTable(this.tableActividadesSocio);
		this.jTableVistaSocio.setName("jTableVistaSocio");
		this.pane = new JScrollPane(this.jTableVistaSocio);
		
		this.pane.setVisible(false);
		
		jButtonApuntarseActividad = new JButton("Apuntarse");
		jButtonApuntarseActividad.setName("jButtonApuntarseVistaSocio");
		
		jButtonEntrenamientoPersonal = new JButton("Solicitar Entrenamiento");
		jButtonEntrenamientoPersonal.setName("jButtonEntrenamientoPersonal");
		
		jButtonVerRutinaSocio = new JButton("Ver Rutina");
		jButtonVerRutinaSocio.setName("jButtonVerRutinaSocio");
		
		
		
	
		GroupLayout gl_panelCambio = new GroupLayout(panelCambio);
		gl_panelCambio.setHorizontalGroup(
			gl_panelCambio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCambio.createSequentialGroup()
					.addGap(77)
					.addComponent(pane, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addGroup(gl_panelCambio.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jButtonVerRutinaSocio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButtonApuntarseActividad, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButtonEntrenamientoPersonal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(73))
		);
		gl_panelCambio.setVerticalGroup(
			gl_panelCambio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCambio.createSequentialGroup()
					.addGroup(gl_panelCambio.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCambio.createSequentialGroup()
							.addGap(97)
							.addComponent(jButtonVerRutinaSocio, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jButtonEntrenamientoPersonal, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jButtonApuntarseActividad, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCambio.createSequentialGroup()
							.addGap(82)
							.addComponent(pane, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(269, Short.MAX_VALUE))
		);
		
		
		
		
		
		panelCambio.setLayout(gl_panelCambio);
		
		
		contentPane.setLayout(gl_contentPane);
		
		panelCambio.setVisible(false);
		setVisible(true);
	}
	
	/**
	 * Metodo que muestra las actividades de un horrio
	 * @param horario
	 */
	public void verListaActividades(TransferHorario horario) 
	{
		if(horario == null)
		{
			ArrayList<TransferActividad> aux = new ArrayList<TransferActividad>();
			this.tableActividadesSocio.setTableActividades(aux);
		}
		else
			this.tableActividadesSocio.setTableActividades(horario.getActividades());
		this.jTableVistaSocio.setModel(this.tableActividadesSocio);
		titulo.setText("Lista de Actividades");
		this.pane.setVisible(true);
		
		this.pane.repaint();
		
		panelCambio.setVisible(true);
		
		this.jButtonApuntarseActividad.setVisible(true);
		this.jButtonEntrenamientoPersonal.setVisible(false);
		this.jButtonVerRutinaSocio.setVisible(false);
		
	}

	/**
	 * Metodo que muestra el horario personal del socio
	 * @param horario
	 */
	public void verMiHorario(TransferHorario horario) 
	{
		if(horario == null)
		{
			ArrayList<TransferActividad> aux = new ArrayList<TransferActividad>();
			this.tableActividadesSocio.setTableActividades(aux);
		}
		else
			this.tableActividadesSocio.setTableActividades(horario.getActividades());
		this.jTableVistaSocio.setModel(this.tableActividadesSocio);
		
		titulo.setText("Mi Horario de Actividades");
		this.pane.setVisible(true);
		
		this.pane.repaint();
		
		panelCambio.setVisible(true);
		this.jButtonVerRutinaSocio.setVisible(false);
		this.jButtonApuntarseActividad.setVisible(false);
		this.jButtonEntrenamientoPersonal.setVisible(false);
	
	}

	/**
	 * Gestion de los entrenadores
	 */
	public void verFacturas(ArrayList<TransferFactura> facturas) 
	{
		if(facturas == null)
		{
			ArrayList<TransferFactura> aux = new ArrayList<TransferFactura>();
			this.tableFacturasSocio.setTableFacturas(aux);
		}
		else
			this.tableFacturasSocio.setTableFacturas(facturas);
		
		this.jTableVistaSocio.setModel(this.tableFacturasSocio);
				
		this.jButtonVerRutinaSocio.setVisible(false);
		this.jButtonApuntarseActividad.setVisible(false);
		this.jButtonEntrenamientoPersonal.setVisible(false);
		
		this.pane.setVisible(true);
		this.pane.repaint();
		panelCambio.setVisible(true);
	}

	/**
	 * Metodo que permite elegir un entrenador personal
	 * @param listEntrenadores
	 */
	public void gestionListaEntrenador(ArrayList<TransferEntrenador> listEntrenadores) 
	{
		
		titulo.setText("Lista De Entrenadores");
		
		this.tableEntrenadoresSocio.setTableEntrenadores(listEntrenadores);
		this.jTableVistaSocio.setModel(this.tableEntrenadoresSocio);
		
		this.pane.setVisible(true);
		
		this.jButtonVerRutinaSocio.setVisible(false);
		this.jButtonApuntarseActividad.setVisible(false);
		this.jButtonEntrenamientoPersonal.setVisible(true);
		
		this.pane.repaint();
		
		panelCambio.setVisible(true);
		
	}
	/**
	 * Metodo que permite ver las rutinas
	 * @param listRutinas
	 */
	public void verRutinas(ArrayList<TransferRutina> listRutinas)
	{
		titulo.setText("Gestion De Facturas");
		if(listRutinas == null)
		{
			ArrayList<TransferRutina> aux = new ArrayList<TransferRutina>();
			this.tableRutinasSocio.setTableRutina(aux);
		}
		else
			this.tableRutinasSocio.setTableRutina(listRutinas);
		this.jTableVistaSocio.setModel(this.tableRutinasSocio);
		titulo.setText("Lista de Rutinas");
		this.pane.setVisible(true);
		
		this.pane.repaint();
		
		panelCambio.setVisible(true);
		
		this.jButtonVerRutinaSocio.setVisible(true);
		this.jButtonApuntarseActividad.setVisible(false);
		this.jButtonEntrenamientoPersonal.setVisible(false);
		
	}

	public JMenuBar getJMenu()
	{
		return this.menu;
	}
	
	public JPanel getContentPane(){
		
		return this.contentPane;

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) 
	{
		this.miHorario.addActionListener((ActionListener)controlador);
		this.gymHorario.addActionListener((ActionListener)controlador);
		this.verRutinas.addActionListener((ActionListener)controlador);
		this.jTableVistaSocio.addMouseListener((MouseListener)controlador);
		this.jButtonApuntarseActividad.addActionListener((ActionListener)controlador);
		this.jButtonVerEntrenadores.addActionListener((ActionListener)controlador);
		this.jButtonEntrenamientoPersonal.addActionListener((ActionListener)controlador);
		this.jButtonVerRutinaSocio.addActionListener((ActionListener)controlador);
		this.jButtonFacturasSocios.addActionListener((ActionListener)controlador); 
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
