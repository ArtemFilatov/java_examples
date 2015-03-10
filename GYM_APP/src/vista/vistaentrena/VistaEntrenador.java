package vista.vistaentrena;



import vista.ViewInterface;

import java.awt.Component;
import java.awt.Font;

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

import modelo.MyTableModelFactura;
import modelo.MyTableModelRutina;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferFactura;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.TransferRutina;


/**
 * Clase que muestra el panel principal del entrenador
 * que contendrá una barra de menu con las diferentes opciones
 * @author Julia
 *
 */
public class VistaEntrenador extends JPanel implements ViewInterface {

	/**
	 * 
	 */
	private JPanel contentPane,panelCambio;
	private JLabel titulo ;
	private JScrollPane pane;
	private DefaultTableModel model;
	private JMenu mnConsultas, verHorarios;
	private JMenuItem verTablas,verFacturas,miHorario,gymHorario;
	private JButton jButtonVerRutina,jButtonCrearRutina, jButtonEliminarRutina, jButtonVerFacturasEntrenador;
	private JButton jButtonGestionarRutinas;
	private String tipo;
	private JMenuBar menu;
	
	// Components.
	private JTable jTableVistaEntrenador;	
	private MyTableModelActividades tableActividadesEntrenador;
	private MyTableModelRutina tableRutinasEntrenador;
	private MyTableModelFactura tableFacturasEntrenador;
	private JButton jButtonUnlogin;
	private JLabel label;


	/**
	 * Controlador.
	 * @param usuarioSesion 
	 */
	public VistaEntrenador(TransferObject usuarioSesion) {

		setBounds(25, 25, 1000, 700);
		
		menu = new JMenuBar();
		
		mnConsultas = new JMenu("Horario");
		menu.add(mnConsultas);
		miHorario = new JMenuItem("Mi Horario");
		miHorario.setName("jFileMenuEntrenadorMiHorario");
		mnConsultas.add(miHorario);
		gymHorario = new JMenuItem("Ver Actividades de Hoy");
		gymHorario.setName("jFileMenuEntrenadorHorarioSistema");
		mnConsultas.add(gymHorario);
		mnConsultas.setSelected(false);
		
		jButtonGestionarRutinas = new JButton("Gestionar Rutinas");
		jButtonGestionarRutinas.setName("jButtonGestionarRutinas");
		menu.add(jButtonGestionarRutinas);
		
		jButtonVerFacturasEntrenador = new JButton("Ver Facturas");
		jButtonVerFacturasEntrenador.setName("jButtonVerFacturasEntrenador");
		menu.add(jButtonVerFacturasEntrenador);
		
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
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("imagen.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 599, GroupLayout.PREFERRED_SIZE)
					.addGap(91))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelCambio, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(panelCambio, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
		);
	
		/* JTABLE GENERADA POR EL WINDOW BUILDER 
		String data[][] = new String[0][2];
		String col[] = { "", "" };
		
		this.model = new DefaultTableModel(data, col);

		this.jTableActividades= new JTable(this.model);
		
		this.jTableActividades.setPreferredScrollableViewportSize(new Dimension(200, 100));
		
		this.jTableActividades.setAutoCreateRowSorter(true); // para ordenar los elementos */
		
		/* JTable por nosotros */
		this.tableRutinasEntrenador = new MyTableModelRutina();
		this.tableActividadesEntrenador = new MyTableModelActividades();
		this.tableFacturasEntrenador = new MyTableModelFactura();
		this.jTableVistaEntrenador = new JTable(this.tableActividadesEntrenador);
		this.jTableVistaEntrenador.setName("jTableVistaEntrenador");
		this.pane = new JScrollPane(this.jTableVistaEntrenador);
		
		this.pane.setVisible(false);
		
		jButtonVerRutina = new JButton("Ver");
		jButtonVerRutina.setName("jButtonVerRutina");
		
		jButtonCrearRutina = new JButton("Crear");
		jButtonCrearRutina.setName("jButtonCrearRutina");
		
		
		jButtonEliminarRutina = new JButton("Eliminar");
		jButtonEliminarRutina.setName("jButtonEliminarRutina");
	
		GroupLayout gl_panelCambio = new GroupLayout(panelCambio);
		gl_panelCambio.setHorizontalGroup(
			gl_panelCambio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCambio.createSequentialGroup()
					.addGap(82)
					.addComponent(pane, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addGroup(gl_panelCambio.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jButtonEliminarRutina, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButtonVerRutina, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButtonCrearRutina, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_panelCambio.setVerticalGroup(
			gl_panelCambio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCambio.createSequentialGroup()
					.addGap(74)
					.addGroup(gl_panelCambio.createParallelGroup(Alignment.LEADING)
						.addComponent(pane, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCambio.createSequentialGroup()
							.addComponent(jButtonCrearRutina, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jButtonVerRutina, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jButtonEliminarRutina, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(228, Short.MAX_VALUE))
		);
		
		
		
		
		
		panelCambio.setLayout(gl_panelCambio);
		
		
		contentPane.setLayout(gl_contentPane);
		
		panelCambio.setVisible(false);
		setVisible(true);
	}
	
	/**
	 * Metodo que muestra la lista de actividades de un horario
	 * @param horario 
	 */
	public void verListaActividades(TransferHorario horario) 
	{
		this.tableActividadesEntrenador.setTableActividades(horario.getActividades());
		this.jTableVistaEntrenador.setModel(this.tableActividadesEntrenador);
		tipo = "actividad";
		titulo.setText("La Lista de Actividades");
		this.pane.setVisible(true);
		
		this.pane.repaint();
		
		panelCambio.setVisible(true);
		
		this.jButtonVerRutina.setVisible(false);
		this.jButtonCrearRutina.setVisible(false);
	}

	/**
	 * Metodo que muestra el horario del socio con las 
	 * actividades a las que esta apuntado
	 * @param horario
	 */
	public void verMiHorario(TransferHorario horario) 
	{
		if(horario == null)
		{
			ArrayList<TransferActividad> aux = new ArrayList<TransferActividad>();
			this.tableActividadesEntrenador.setTableActividades(aux);
		}
		else
			this.tableActividadesEntrenador.setTableActividades(horario.getActividades());
		this.jTableVistaEntrenador.setModel(this.tableActividadesEntrenador);
		
		tipo = "actividad";
		titulo.setText("Mi Horario de Trabajo");
		this.pane.setVisible(true);
		
		this.pane.repaint();

		this.jButtonVerRutina.setVisible(false);
		this.jButtonCrearRutina.setVisible(false);
		this.jButtonEliminarRutina.setVisible(false);
		
		panelCambio.setVisible(true);
		
	}

	/**
	 * metodo que se encarga de la opcion de gestion de rutinas
	 * @param rutinas
	 */
	public void gestionRutinas(ArrayList<TransferRutina> rutinas) 
	{
		if(rutinas == null)
		{
			ArrayList<TransferRutina> aux = new ArrayList<TransferRutina>();
			this.tableRutinasEntrenador.setTableRutina(aux);
		}
		if(rutinas.size() >= 0)
			this.tableRutinasEntrenador.setTableRutina(rutinas);
		this.jTableVistaEntrenador.setModel(this.tableRutinasEntrenador);

		tipo = "producto";
		titulo.setText("Gestion De Rutinas");
		
		this.jButtonVerRutina.setVisible(true);
		this.jButtonCrearRutina.setVisible(true);
		this.jButtonEliminarRutina.setVisible(true);
		this.pane.setVisible(true);
		
		this.pane.repaint();
		panelCambio.setVisible(true);
		
	}

	/**
	 * Gestion de los entrenadores
	 */
	public void verFacturas(ArrayList<TransferFactura> facturas) 
	{
		titulo.setText("Gestion De Facturas");
		if(facturas == null)
		{
			ArrayList<TransferFactura> aux = new ArrayList<TransferFactura>();
			this.tableFacturasEntrenador.setTableFacturas(aux);
		}
		else
			this.tableFacturasEntrenador.setTableFacturas(facturas);
		
		this.jTableVistaEntrenador.setModel(this.tableFacturasEntrenador);
				
		this.jButtonVerRutina.setVisible(false);
		this.jButtonCrearRutina.setVisible(false);
		this.jButtonEliminarRutina.setVisible(false);
	
		this.pane.setVisible(true);
		this.pane.repaint();
		panelCambio.setVisible(true);
	}
	
	public JMenuBar getJMenu(){
		
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
		this.jTableVistaEntrenador.addMouseListener((MouseListener)controlador);
		this.jButtonGestionarRutinas.addActionListener((ActionListener)controlador);
		this.jButtonEliminarRutina.addActionListener((ActionListener)controlador);
		this.jButtonCrearRutina.addActionListener((ActionListener)controlador);
		this.jButtonVerRutina.addActionListener((ActionListener)controlador);
		this.jButtonVerFacturasEntrenador.addActionListener((ActionListener)controlador);
		this.jButtonUnlogin.addActionListener((ActionListener)controlador);
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
