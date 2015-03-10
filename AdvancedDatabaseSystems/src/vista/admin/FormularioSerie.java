package vista.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Serie.Genero;
import modelo.Serie.Serie;

import com.toedter.calendar.JDateChooser;

import controlador.admin.ControlAdminSeries;

public class FormularioSerie {

	private ControlAdminSeries controlador;
	private JFrame frmFormularioSerie;


	private JButton btnCrear;
	private JButton btnCerrar;

	private JTextArea textSinopsis;
	private JTextArea textTitular;
	
	
	private JCheckBox checkComedia;
	private JCheckBox checkDrama;
	private JCheckBox checkAccion;
	private JCheckBox checkThriller;
	private JCheckBox checkAventuras;
	private JCheckBox checkNegro;
	private JCheckBox checkTerror;
	private JCheckBox checkSuspense;
	private JCheckBox checkBelico;
	private JTextField textTitulo;
	private ArrayList<Genero> generos;
	private JDateChooser dateFin;
	private JDateChooser dateInicio;
	private VistaSeries lvlSuperior;
	
	/**
	 * Create the panel.
	 * @param ventana 
	 * @param controlAdminSeries 
	 */
	public FormularioSerie(ControlAdminSeries controlador, VistaSeries ventana) {
		 this.controlador = controlador;
		this.lvlSuperior = ventana;
		this.generos = new ArrayList<Genero>();
		 
		 frmFormularioSerie = new JFrame();
		 frmFormularioSerie.getContentPane().setLayout(null);
		 frmFormularioSerie.setBounds(100, 100, 710, 376);
		 frmFormularioSerie.setTitle("Formulario de nueva serie");		
		 
				btnCrear = new JButton("Crear");
				btnCrear.setBounds(197, 305, 130, 23);
				frmFormularioSerie.getContentPane().add(btnCrear);
				btnCrear.addActionListener(new OyenteBoton());
				
				btnCerrar = new JButton("Cerrar");
				btnCerrar.setBounds(422, 305, 119, 23);
				frmFormularioSerie.getContentPane().add(btnCerrar);
				btnCerrar.addActionListener(new OyenteBoton());
				
				JLabel lblSinopsis = new JLabel("Sinopsis");
				lblSinopsis.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblSinopsis.setBounds(10, 94, 98, 29);
				frmFormularioSerie.getContentPane().add(lblSinopsis);
				
			
				
				
				JScrollPane scrollPaneSinopsis = new JScrollPane();
				scrollPaneSinopsis.setBounds(10, 135, 367, 151);
				frmFormularioSerie.getContentPane().add(scrollPaneSinopsis);
				
				textSinopsis = new JTextArea();
				textSinopsis.setLineWrap(true);
				scrollPaneSinopsis.setViewportView(textSinopsis);
				
				textTitular = new JTextArea();
				textTitular.setBounds(400, 47, 286, 53);
				frmFormularioSerie.getContentPane().add(textTitular);
				
				
				JLabel lblGeneros = new JLabel("Generos");
				lblGeneros.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblGeneros.setBounds(400, 119, 98, 29);
				frmFormularioSerie.getContentPane().add(lblGeneros);
				
				Añadecheckbox();
				
				
				
				JLabel lblInicio = new JLabel("inicio de emision:");
				lblInicio.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblInicio.setBounds(400, 235, 141, 29);
				frmFormularioSerie.getContentPane().add(lblInicio);
				
				dateInicio = new JDateChooser();
				dateInicio.setBounds(571, 235, 104, 20);
				frmFormularioSerie.getContentPane().add(dateInicio);
				
				JLabel lblFinDeEmision = new JLabel("fin de emision:");
				lblFinDeEmision.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblFinDeEmision.setBounds(400, 266, 141, 29);
				frmFormularioSerie.getContentPane().add(lblFinDeEmision);
				
				dateFin = new JDateChooser();
				dateFin.setBounds(571, 266, 104, 20);
				frmFormularioSerie.getContentPane().add(dateFin);
				
				JLabel lblTitular = new JLabel("Titular");
				lblTitular.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblTitular.setBounds(404, 11, 84, 25);
				frmFormularioSerie.getContentPane().add(lblTitular);
				
				textTitulo = new JTextField();
				textTitulo.setBounds(22, 49, 355, 34);
				frmFormularioSerie.getContentPane().add(textTitulo);
				textTitulo.setColumns(10);
				
				JLabel lblTitulo = new JLabel("Titulo");
				lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblTitulo.setBounds(10, 11, 76, 34);
				frmFormularioSerie.getContentPane().add(lblTitulo);
				
				
				frmFormularioSerie.setVisible(true);
				frmFormularioSerie.setResizable(false);
			}
			
	private void Añadecheckbox() {
		checkComedia = new JCheckBox("Comedia");
		checkComedia.setBounds(401, 155, 97, 23);
		frmFormularioSerie.getContentPane().add(checkComedia);
		checkComedia.addItemListener(new OyenteCheckBox());
		
		checkDrama = new JCheckBox("Drama");
		checkDrama.setBounds(401, 182, 97, 23);
		frmFormularioSerie.getContentPane().add(checkDrama);
		checkDrama.addItemListener(new OyenteCheckBox());
		
		checkAccion = new JCheckBox("Accion");
		checkAccion.setBounds(500, 155, 97, 23);
		frmFormularioSerie.getContentPane().add(checkAccion);
		checkAccion.addItemListener(new OyenteCheckBox());
		
		checkThriller = new JCheckBox("Thriller");
		checkThriller.setBounds(500, 179, 97, 23);
		frmFormularioSerie.getContentPane().add(checkThriller);
		checkThriller.addItemListener(new OyenteCheckBox());
		
		checkAventuras = new JCheckBox("Aventuras");
		checkAventuras.setBounds(400, 205, 97, 23);
		frmFormularioSerie.getContentPane().add(checkAventuras);
		checkAventuras.addItemListener(new OyenteCheckBox());
		
		checkNegro = new JCheckBox("Negro");
		checkNegro.setBounds(500, 205, 97, 23);
		frmFormularioSerie.getContentPane().add(checkNegro);
		checkNegro.addItemListener(new OyenteCheckBox());
		
		checkTerror = new JCheckBox("Romance");
		checkTerror.setBounds(599, 155, 97, 23);
		frmFormularioSerie.getContentPane().add(checkTerror);
		checkTerror.addItemListener(new OyenteCheckBox());
		
		checkSuspense = new JCheckBox("Suspense");
		checkSuspense.setBounds(599, 179, 97, 23);
		frmFormularioSerie.getContentPane().add(checkSuspense);
		checkSuspense.addItemListener(new OyenteCheckBox());
		
		checkBelico = new JCheckBox("Belico");
		checkBelico.setBounds(599, 205, 97, 23);
		frmFormularioSerie.getContentPane().add(checkBelico);
		checkBelico.addItemListener(new OyenteCheckBox());
	}
	
	
	public void eliminarGrafico(){
		this.frmFormularioSerie.removeAll();
		this.frmFormularioSerie.setVisible(false);
		
	}
	
	public void añadirGenero(String strGenero){
		Genero genero = Genero.createFromString(strGenero);
		generos.add(genero);
	}
	public void eliminarGenero(String strGenero){
		Genero genero = Genero.createFromString(strGenero);
		generos.remove(genero);
	}
	
	
	private class OyenteBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCrear){
				
			if(dateInicio.getDate() == null || textTitulo.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null,"Recuerde que la serie debe tener un nombre y una fecha de inicio de emision");
			}else{
				Serie s = new Serie (textTitulo.getText(), textTitular.getText(), textSinopsis.getText(),
						dateInicio.getDate(), dateFin.getDate());
				if(controlador.getSeriePorNombreYFecha(s.getNombre(), s.getfEstreno())) {
					JOptionPane.showMessageDialog(null,"Ya existe una serie con ese nombre y esa fecha de estreno");
				}else {
					controlador.insertarSerie(s, generos); // movidos aqui para que solo actualice y cierre si 
												// lo ha conseguido, sino que pueda cambiar datos
					lvlSuperior.actualiza();
					eliminarGrafico();
				}
				
			}	
				
			}else if(e.getSource() == btnCerrar){
				eliminarGrafico();
			} 
		}
	}
	
	private class OyenteCheckBox implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox test = (JCheckBox) e.getSource();
			if(test.isSelected()) añadirGenero(test.getText());
			else eliminarGenero(test.getText());
		}
		
	}
}
