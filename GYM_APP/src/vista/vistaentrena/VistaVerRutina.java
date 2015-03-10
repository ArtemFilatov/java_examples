package vista.vistaentrena;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import modelo.MyTableModelEjercicios;
import modelo.transfer.TransferEjercicio;
import vista.ViewInterface;

/**
 * Clase que muestra la ventana para ver los datos de una rutina, el nombre de la rutina y la lista de ejercicios.
 * @author Jesus
 *
 */
public class VistaVerRutina extends JFrame implements ViewInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jTextFieldNombreRutina;
	private JTable table;
	private JTable jTableEjercicios;
	private MyTableModelEjercicios tableModelEjercicios;
	private MyTableModelEjercicios tableModelEjerciciosEscogidos;
	private JLabel jLabelEjercicios ;

	
	/**
	 * constructora.
	 */
	public VistaVerRutina() {
		setTitle("Ver Rutina");
		
		
		setBounds(100, 100, 460, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel jLabelNombre = new JLabel("Nombre Rutina");
		
		jTextFieldNombreRutina = new JTextField();
		jTextFieldNombreRutina.setEditable(false);
		jTextFieldNombreRutina.setName("jTextFieldNombreRutina");
		jTextFieldNombreRutina.setColumns(10);
		
		table = new JTable();
		
		tableModelEjercicios = new MyTableModelEjercicios();
		tableModelEjerciciosEscogidos = new MyTableModelEjercicios();
		
		jLabelEjercicios = new JLabel("Ejercicios");
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jLabelEjercicios, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(jLabelNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(78)
									.addComponent(jTextFieldNombreRutina, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
									.addGap(53))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
							.addGap(57)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabelNombre)
						.addComponent(jTextFieldNombreRutina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(jLabelEjercicios)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(199, Short.MAX_VALUE))
		);
		jTableEjercicios = new JTable(this.tableModelEjercicios);
		jTableEjercicios.setName("jTableEjercicios");
		scrollPane.setViewportView(jTableEjercicios);
		jTableEjercicios.setBackground(Color.WHITE);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Set de la tabla de ejercicios
	 * @param listEjercicios
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
	 * set del nombre de la rutina
	 * @param nombreRutina
	 */
	public void setNombreRutina(String nombreRutina)
	{
		this.jTextFieldNombreRutina.setText(nombreRutina);
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
		this.jTableEjercicios.addMouseListener((MouseListener)controlador);
	}

	@Override
	public void muestraVentanita(String msg) {
		// TODO Auto-generated method stub
		
	}
}
