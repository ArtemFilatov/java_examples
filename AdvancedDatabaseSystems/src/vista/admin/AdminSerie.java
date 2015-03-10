package vista.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Serie.Episodio;
import modelo.Serie.Genero;
import modelo.Serie.MyTableModelEpisodio;
import modelo.Serie.Serie;

import com.toedter.calendar.JDateChooser;

import controlador.admin.ControlAdminActores;
import controlador.admin.ControlAdminPersonajes;
import controlador.admin.ControlAdminSeries;

public class AdminSerie {

	private JFrame frmAdminSerie;
	private AdminSerie ventana;
	
	private JButton btnCerrar;
	private JButton btnUpdate;
	private JButton btnNuevoEpisodio;
	private JTextArea textSinopsis;
	private JTextArea textTitular;
	private ControlAdminSeries controller;
	private Serie serie;
	private JTable table;
	private MyTableModelEpisodio myTableModelEpisodio;
	private boolean released = false;
	
	private JDateChooser dateInicio;
	private JDateChooser dateFin;
	
	private JCheckBox checkComedia;
	private JCheckBox checkDrama;
	private JCheckBox checkAccion;
	private JCheckBox checkThriller;
	private JCheckBox checkAventuras;
	private JCheckBox checkNegro;
	private JCheckBox checkRomance;
	private JCheckBox checkSuspense;
	private JCheckBox checkBelico;
	private ControlAdminActores propagar1;
	private ControlAdminPersonajes propagar2;
	private VistaSeries lvlSuperior;
	private ArrayList<Genero> generos;
	
	public AdminSerie(Serie serie, ControlAdminSeries controller, ControlAdminActores propagar1, ControlAdminPersonajes propagar2, VistaSeries ventanaSuperior) {
		this.ventana = this;
		this.lvlSuperior = ventanaSuperior;
		
		this.controller = controller;
		this.propagar1 = propagar1;
		this.propagar2 = propagar2;
		initialize(serie);
	}
	
	
	private void initialize(Serie s) {
		serie = s;
		frmAdminSerie = new JFrame();
		frmAdminSerie.setTitle(serie.getNombre());
		frmAdminSerie.setBounds(100, 100, 735, 538);
		frmAdminSerie.getContentPane().setLayout(null);
		
		JLabel nombre = new JLabel(serie.getNombre());
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Calibri", Font.BOLD, 20));
		nombre.setBounds(20, 11, 639, 39);
		frmAdminSerie.getContentPane().add(nombre);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		lblSinopsis.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblSinopsis.setBounds(20, 61, 98, 29);
		frmAdminSerie.getContentPane().add(lblSinopsis);
		
		btnUpdate = new JButton("Actualizar datos");
		btnUpdate.addActionListener(new OyenteBoton());
		btnUpdate.setBounds(286, 441, 155, 39);
		frmAdminSerie.getContentPane().add(btnUpdate);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new OyenteBoton());
		btnCerrar.setBounds(533, 441, 126, 39);
		frmAdminSerie.getContentPane().add(btnCerrar);
		
		JScrollPane scrollPaneSinopsis = new JScrollPane();
		scrollPaneSinopsis.setBounds(20, 88, 367, 138);
		frmAdminSerie.getContentPane().add(scrollPaneSinopsis);
		
		textSinopsis = new JTextArea(serie.getSinopsis());
		textSinopsis.setLineWrap(true);
		scrollPaneSinopsis.setViewportView(textSinopsis);
		
		textTitular = new JTextArea(serie.getTitular());
		textTitular.setBounds(406, 88, 305, 53);
		frmAdminSerie.getContentPane().add(textTitular);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 265, 367, 156);
		frmAdminSerie.getContentPane().add(scrollPane_1);
		
		myTableModelEpisodio = new MyTableModelEpisodio(this.getEpisodiosSerie());
		table = new JTable();
		table.setModel(myTableModelEpisodio);
		table.addMouseListener(new OyenteRaton());
		table.getSelectionModel().addListSelectionListener(new SelectionListener());
		scrollPane_1.setViewportView(table);
		
		JLabel lblEpisodios = new JLabel("Episodios");
		lblEpisodios.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEpisodios.setBounds(20, 225, 98, 29);
		frmAdminSerie.getContentPane().add(lblEpisodios);
		
		JLabel lblGeneros = new JLabel("Generos");
		lblGeneros.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblGeneros.setBounds(415, 164, 98, 29);
		frmAdminSerie.getContentPane().add(lblGeneros);
		
		Añadecheckbox();
		
		
		
		JLabel lblInicio = new JLabel("inicio de emision:");
		lblInicio.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblInicio.setBounds(415, 304, 141, 29);
		frmAdminSerie.getContentPane().add(lblInicio);
		
		dateInicio = new JDateChooser(serie.getfEstreno());
		dateInicio.setBounds(586, 304, 104, 20);
		frmAdminSerie.getContentPane().add(dateInicio);
		
		JLabel lblFinDeEmision = new JLabel("fin de emision:");
		lblFinDeEmision.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblFinDeEmision.setBounds(415, 357, 141, 29);
		frmAdminSerie.getContentPane().add(lblFinDeEmision);
		
		dateFin = new JDateChooser(serie.getfFinal());
		dateFin.setBounds(586, 357, 104, 20);
		frmAdminSerie.getContentPane().add(dateFin);
		
		btnNuevoEpisodio = new JButton("Nuevo episodio");
		btnNuevoEpisodio.setBounds(50, 445, 154, 35);
		btnNuevoEpisodio.addActionListener(new OyenteBoton());
		frmAdminSerie.getContentPane().add(btnNuevoEpisodio);
		
		this.frmAdminSerie.setVisible(true);
		this.frmAdminSerie.setResizable(false);
	}
	
	private void Añadecheckbox() {
		
		generos = this.controller.getGenerosSerie(serie);
		checkComedia = new JCheckBox("Comedia");
		checkComedia.setBounds(416, 200, 97, 23);
		frmAdminSerie.getContentPane().add(checkComedia);
		checkComedia.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.COMEDIA)) checkComedia.setSelected(true);
		
		checkDrama = new JCheckBox("Drama");
		checkDrama.setBounds(416, 227, 97, 23);
		frmAdminSerie.getContentPane().add(checkDrama);
		checkDrama.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.DRAMA)) checkDrama.setSelected(true);
		
		checkAccion = new JCheckBox("Accion");
		checkAccion.setBounds(515, 200, 97, 23);
		frmAdminSerie.getContentPane().add(checkAccion);
		checkAccion.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.ACCION)) checkAccion.setSelected(true);
		
		checkThriller = new JCheckBox("Thriller");
		checkThriller.setBounds(515, 224, 97, 23);
		frmAdminSerie.getContentPane().add(checkThriller);
		checkThriller.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.THRILLER)) checkThriller.setSelected(true);
		
		checkAventuras = new JCheckBox("Aventuras");
		checkAventuras.setBounds(415, 250, 97, 23);
		frmAdminSerie.getContentPane().add(checkAventuras);
		checkAventuras.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.AVENTURAS)) checkAventuras.setSelected(true);
		
		checkNegro = new JCheckBox("Negro");
		checkNegro.setBounds(515, 250, 97, 23);
		frmAdminSerie.getContentPane().add(checkNegro);
		checkNegro.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.NEGRO)) checkNegro.setSelected(true);
		
		checkRomance = new JCheckBox("Romance");
		checkRomance.setBounds(614, 200, 97, 23);
		frmAdminSerie.getContentPane().add(checkRomance);
		checkRomance.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.ROMANCE)) checkRomance.setSelected(true);
		
		checkSuspense = new JCheckBox("Suspense");
		checkSuspense.setBounds(614, 224, 97, 23);
		frmAdminSerie.getContentPane().add(checkSuspense);
		checkSuspense.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.SUSPENSE)) checkSuspense.setSelected(true);
		
		checkBelico = new JCheckBox("Belico");
		checkBelico.setBounds(614, 250, 97, 23);
		frmAdminSerie.getContentPane().add(checkBelico);
		checkBelico.addItemListener(new OyenteCheckBox());
		if(generos.contains(Genero.BELICO)) checkBelico.setSelected(true);
	}


	private ArrayList<Episodio> getEpisodiosSerie() {
		return this.controller.getEpisodiosSerie(this.serie);
	}
	
	public void eliminarGrafico(){
		this.frmAdminSerie.removeAll();
		this.frmAdminSerie.setVisible(false);
		
	}
	
	public void actualiza(){
		this.serie = controller.getSerie(serie);
		this.textSinopsis.setText(serie.getSinopsis());
		this.textTitular.setText(serie.getTitular());
		this.dateFin.setDate(serie.getfFinal());
		this.dateInicio.setDate(serie.getfEstreno());
		myTableModelEpisodio.setEpisodios(this.getEpisodiosSerie());
		myTableModelEpisodio.actualiza();
		lvlSuperior.actualiza();
	}
	
	public void añadirGenero(String strGenero){
		Genero genero = Genero.createFromString(strGenero);
		if(!generos.contains(genero))
			generos.add(genero);
	}
	public void eliminarGenero(String strGenero){
		Genero genero = Genero.createFromString(strGenero);
		generos.remove(genero);
	}
	
	private class OyenteBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnUpdate){
				Serie aux = new Serie(serie.getId(),serie.getNombre(),textTitular.getText(), textSinopsis.getText(),
						dateInicio.getDate(), dateFin.getDate());// como no hay setter se crea nueva

				controller.actualizarSerie(aux);
				controller.actualizarGenerosSerie(generos, aux);
				lvlSuperior.actualiza();
				eliminarGrafico();
					
			}else if(e.getSource() == btnCerrar){
				eliminarGrafico();
			} else if(e.getSource() == btnNuevoEpisodio){
				new FormularioEpisodio(serie, controller, myTableModelEpisodio, ventana);
				
			} 
		}
	}
	
	private class SelectionListener implements ListSelectionListener {
		  
		  public void valueChanged(ListSelectionEvent e) {
			  if(released) {
				  int idEpisodio = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 0);
				  Episodio episodio = myTableModelEpisodio.getEpisodio(idEpisodio);
				  new AdminEpisodio(serie, episodio, controller, propagar1, propagar2, ventana);
			  }
		  }
		  public void valueChanged1(ListSelectionEvent e) {
			// TODO Auto-generated method stub
		  }
	}
	
	private class OyenteRaton implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {released = true;}

		@Override
		public void mouseReleased(MouseEvent e) {released = false;}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
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
