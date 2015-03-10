package vista.vistaentrena;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import vista.ViewInterface;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.JTable;

import modelo.MyTableModelEjercicios;
import modelo.transfer.TransferEjercicio;

import java.awt.Color;
import javax.swing.JScrollPane;
/**
 * Clase que crea la ventana para la creacion de una nueva rutina
 * inclye un campo para el nombre una lista de ejercicos, un campo 
 * para las repeticones, un boton para añadir ejercicios y otro para 
 * guardar la rutina
 * @author Julia
 *
 */
public class VistaCrearRutina extends JFrame implements ViewInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jTextFieldNombreRutina,jTextFieldRepeticiones;
	private JTable table;
	private JTable jTableEjercicios;
	private JButton jButtonAddEjercicio, jButtonGuardarRutina;
	private MyTableModelEjercicios tableModelEjercicios;
	private MyTableModelEjercicios tableModelEjerciciosEscogidos;
	private JLabel jLabelEjerciciosEscogidos, jLabelEjercicios ;
	private JTable jTableEjerciciosEscogidos;
	private JScrollPane scrollPane_1;

	
	/**
	 * Controlador.
	 */
	public VistaCrearRutina() {
		setTitle("Crear Rutina");
		
		
		setBounds(100, 100, 469, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel jLabelNombre = new JLabel("Nombre Rutina");
		
		jTextFieldNombreRutina = new JTextField();
		jTextFieldNombreRutina.setName("jTextFieldNombreRutina");
		jTextFieldNombreRutina.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Numero Repeticiones");
		
		jTextFieldRepeticiones = new JTextField();
		jTextFieldRepeticiones.setName("jTextFieldRepeticiones");
		jTextFieldRepeticiones.setColumns(10);

		
		
		jButtonAddEjercicio = new JButton("Añadir Ejercicio");
		jButtonAddEjercicio.setName("jButtonAddEjercicio");
		
		table = new JTable();
		
		tableModelEjercicios = new MyTableModelEjercicios();
		tableModelEjerciciosEscogidos = new MyTableModelEjercicios();
		
		jLabelEjercicios = new JLabel("Ejercicios");
		
		jLabelEjerciciosEscogidos = new JLabel("Ejercicios Escogidos");
		
		jButtonGuardarRutina = new JButton("Guardar Cambios");
		jButtonGuardarRutina.setName("jButtonGuardarRutina");
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jButtonGuardarRutina, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(jLabelEjercicios, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(jLabelNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(131)
										.addComponent(jTextFieldNombreRutina, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
										.addGap(165))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblNewLabel_2)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(jButtonAddEjercicio, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
													.addGap(30)
													.addComponent(jTextFieldRepeticiones, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
												.addComponent(jLabelEjerciciosEscogidos, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
										.addGap(23)
										.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(jLabelNombre)
						.addComponent(jTextFieldNombreRutina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabelEjercicios)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextFieldRepeticiones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(jButtonAddEjercicio, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(jLabelEjerciciosEscogidos)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(jButtonGuardarRutina, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		jTableEjerciciosEscogidos = new JTable(this.tableModelEjerciciosEscogidos);
		scrollPane_1.setViewportView(jTableEjerciciosEscogidos);
		jTableEjerciciosEscogidos.setName("jTableEjerciciosEscogidos");
		jTableEjerciciosEscogidos.setBackground(Color.WHITE);
		jTableEjercicios = new JTable(this.tableModelEjercicios);
		jTableEjercicios.setName("jTableEjercicios");
		scrollPane.setViewportView(jTableEjercicios);
		jTableEjercicios.setBackground(Color.WHITE);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Set para la tabla de ejercios 
	 * @param listEjercicios lista de ejercios 
	 */
	public void setTableEjercicios(ArrayList<TransferEjercicio> listEjercicios)
	{
		if(listEjercicios == null)
		{
			ArrayList<TransferEjercicio> aux = new ArrayList<TransferEjercicio>();
			this.tableModelEjercicios.setTableEjercicios(aux);
		}
		else
			this.tableModelEjercicios.setTableEjercicios(listEjercicios);
		this.jTableEjercicios.setModel(this.tableModelEjercicios);
		
		this.contentPane.repaint();
	}
	
	/**
	 * Set para la tabala de ejercicios que se van añadiendo
	 * @param listEjercicios lista de ejercicos 
	 */
	public void setTableEjerciciosEscogidos(ArrayList<TransferEjercicio> listEjercicios)
	{
		if(listEjercicios == null)
		{
			ArrayList<TransferEjercicio> aux = new ArrayList<TransferEjercicio>();
			this.tableModelEjerciciosEscogidos.setTableEjercicios(aux);
		}
		else
			this.tableModelEjerciciosEscogidos.setTableEjercicios(listEjercicios);
		this.jTableEjerciciosEscogidos.setModel(this.tableModelEjerciciosEscogidos);
		
		this.contentPane.repaint();
	}
	
	public void arranca(){
		
		this.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) 
	{
		this.jButtonGuardarRutina.addActionListener((ActionListener)controlador);
		this.jButtonAddEjercicio.addActionListener((ActionListener)controlador);
		this.jTableEjercicios.addMouseListener((MouseListener)controlador);
		this.jTableEjerciciosEscogidos.addMouseListener((MouseListener)controlador);
		this.jTextFieldNombreRutina.addActionListener((ActionListener)controlador);
		this.jTextFieldNombreRutina.addKeyListener((KeyListener)controlador);
		this.jTextFieldRepeticiones.addActionListener((ActionListener)controlador);
		this.jTextFieldRepeticiones.addKeyListener((KeyListener)controlador);
		
	}

	@Override
	public void muestraVentanita(String msg) {
		// TODO Auto-generated method stub
		
	}
}
