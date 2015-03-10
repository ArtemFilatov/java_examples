package vista.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Serie.Episodio;
import modelo.Serie.Serie;

import com.toedter.calendar.JDateChooser;

import controlador.admin.ControlAdminActores;
import controlador.admin.ControlAdminPersonajes;
import controlador.admin.ControlAdminSeries;

public class AdminEpisodio {

private JFrame frmDetallesSerie;
	
	private JButton btnCerrar;
	private JButton btnModificar;
	private JButton btnBorrar;
	private JTextArea textSinopsis;
	private ControlAdminSeries controller;
	private Episodio episodio;
	private Serie serie;

	private AdminSerie lvlSuperior;
	private JDateChooser dateEstreno;
	private JLabel lblTitulo;
	private JTextField textTitulo;
	private JButton btnEditarPersonajes;
	private ControlAdminActores propagar1;
	private ControlAdminPersonajes propagar2;
	
	
	
	public AdminEpisodio(Serie serie, Episodio episodio,
			ControlAdminSeries controller, ControlAdminActores propagar1, ControlAdminPersonajes propagar2, AdminSerie ventana) {
		this.lvlSuperior = ventana;
		this.serie = serie;
		this.controller = controller;
		this.propagar1 = propagar1;
		this.propagar2 = propagar2;
		this.episodio = episodio;
		initialize();
		
	}

	
	private void initialize() {
		frmDetallesSerie = new JFrame();
		frmDetallesSerie.setTitle(serie.getNombre()+ " - Temp: "+episodio.getTemporada()+" - cap: " + episodio.getIdEpisodio());
		frmDetallesSerie.setBounds(100, 100, 420, 412);
		frmDetallesSerie.getContentPane().setLayout(null);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		lblSinopsis.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblSinopsis.setBounds(33, 67, 98, 29);
		frmDetallesSerie.getContentPane().add(lblSinopsis);
		
		JScrollPane scrollPaneSinopsis = new JScrollPane();
		scrollPaneSinopsis.setBounds(23, 97, 367, 144);
		frmDetallesSerie.getContentPane().add(scrollPaneSinopsis);
		
		textSinopsis = new JTextArea(episodio.getSinopsis());
		textSinopsis.setLineWrap(true);
		scrollPaneSinopsis.setViewportView(textSinopsis);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new OyenteBoton());
		btnBorrar.setBounds(234, 292, 138, 29);
		frmDetallesSerie.getContentPane().add(btnBorrar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new OyenteBoton());
		btnModificar.setBounds(33, 292, 151, 29);
		frmDetallesSerie.getContentPane().add(btnModificar);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new OyenteBoton());
		btnCerrar.setBounds(234, 332, 138, 29);
		frmDetallesSerie.getContentPane().add(btnCerrar);
		
		JLabel lblFechaDeEstreno = new JLabel("Fecha de estreno");
		lblFechaDeEstreno.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblFechaDeEstreno.setBounds(46, 252, 138, 29);
		frmDetallesSerie.getContentPane().add(lblFechaDeEstreno);
		
		dateEstreno = new JDateChooser(episodio.getfEstreno());
		dateEstreno.setBounds(206, 252, 104, 29);
		frmDetallesSerie.getContentPane().add(dateEstreno);
		
		lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTitulo.setBounds(45, 28, 81, 29);
		frmDetallesSerie.getContentPane().add(lblTitulo);
		
		textTitulo = new JTextField(episodio.getTitulo());
		textTitulo.setBounds(136, 28, 266, 24);
		frmDetallesSerie.getContentPane().add(textTitulo);
		textTitulo.setColumns(10);
		
		btnEditarPersonajes = new JButton("Editar personajes");
		btnEditarPersonajes.setBounds(33, 332, 151, 29);
		frmDetallesSerie.getContentPane().add(btnEditarPersonajes);
		btnEditarPersonajes.addActionListener(new OyenteBoton());
		
		 this.frmDetallesSerie.setVisible(true);
		 this.frmDetallesSerie.setResizable(false);
	}


	public void eliminarGrafico(){
		this.frmDetallesSerie.removeAll();
		this.frmDetallesSerie.setVisible(false);
		
	}
	
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnModificar){
					try{
					
					Episodio aux= new Episodio(episodio.getId(), episodio.getTemporada(), textTitulo.getText(), textSinopsis.getText(), dateEstreno.getDate());
					
					controller.actualizarEpisodio( aux);
					lvlSuperior.actualiza();
					eliminarGrafico();
					
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null,"fallo, no se entiende la temporada");
				}
			}else if(e.getSource() == btnBorrar){

				controller.eliminarEpisodio(episodio);
				lvlSuperior.actualiza();
				eliminarGrafico();
				
			}else if(e.getSource() == btnCerrar){
				eliminarGrafico();
			}else if(e.getSource() == btnEditarPersonajes){
				new VistaPersonajeActorEpisodio(propagar1, propagar2, episodio);
			}
		}
	}
}
