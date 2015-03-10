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
import modelo.Serie.EpisodioKey;
import modelo.Serie.MyTableModelEpisodio;
import modelo.Serie.Serie;

import com.toedter.calendar.JDateChooser;

import controlador.admin.ControlAdminSeries;

public class FormularioEpisodio {

	private Serie serie;
	private ControlAdminSeries controlador;
	private JFrame frmFormularioEpisodio;
	private AdminSerie lvlSuperior;
	
	private JButton btnCrear;
	private JButton btnCerrar;

	private JTextArea textSinopsis;
	private JTextField textField;
	
	private JDateChooser dateEstreno;
	private JLabel lblFechaDeEstreno;
	private JLabel lblTemporada;
	private JLabel lblTitulo;
	private JTextField textNumero;
	private JLabel lblNDeCapitulo;
	private JTextField textNCapitulo;
	private MyTableModelEpisodio myTableModelEpisodio;
	
	public FormularioEpisodio(Serie serie, ControlAdminSeries controller, MyTableModelEpisodio myTableModelEpisodio, AdminSerie ventana) {
		this.lvlSuperior = ventana; 
		this.controlador = controller;
		 this.serie = serie;
		 this.myTableModelEpisodio = myTableModelEpisodio;
		 
		 frmFormularioEpisodio = new JFrame();
		 frmFormularioEpisodio.getContentPane().setLayout(null);
		 frmFormularioEpisodio.setBounds(100, 100, 422, 423);
		 
		 dateEstreno = new JDateChooser();
		 dateEstreno.setBounds(233, 284, 95, 20);
		 frmFormularioEpisodio.getContentPane().add(dateEstreno);
		 
		 JLabel lblSinopsis = new JLabel("Sinopsis");
		 lblSinopsis.setFont(new Font("Calibri", Font.PLAIN, 16));
		 lblSinopsis.setBounds(22, 91, 78, 27);
		 frmFormularioEpisodio.getContentPane().add(lblSinopsis);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(22, 129, 367, 144);
		 frmFormularioEpisodio.getContentPane().add(scrollPane);
		 
		  textSinopsis = new JTextArea((String) null);
		  textSinopsis.setLineWrap(true);
		 scrollPane.setViewportView(textSinopsis);
		 
		 textField = new JTextField();
		 textField.setBounds(132, 11, 257, 27);
		 frmFormularioEpisodio.getContentPane().add(textField);
		 textField.setColumns(10);
		 
		 btnCrear = new JButton("Crear Episodio");
		 btnCrear.setBounds(43, 338, 121, 29);
		 frmFormularioEpisodio.getContentPane().add(btnCrear);
		 btnCrear.addActionListener(new OyenteBoton());
		 
		 btnCerrar = new JButton("Cerrar");
		 btnCerrar.setBounds(233, 338, 95, 29);
		 frmFormularioEpisodio.getContentPane().add(btnCerrar);
		 
		 lblFechaDeEstreno = new JLabel("Fecha de estreno");
		 lblFechaDeEstreno.setFont(new Font("Calibri", Font.PLAIN, 16));
		 lblFechaDeEstreno.setBounds(43, 284, 121, 27);
		 frmFormularioEpisodio.getContentPane().add(lblFechaDeEstreno);
		 
		 lblTemporada = new JLabel("Temporada");
		 lblTemporada.setFont(new Font("Calibri", Font.PLAIN, 16));
		 lblTemporada.setBounds(22, 53, 100, 27);
		 frmFormularioEpisodio.getContentPane().add(lblTemporada);
		 
		 lblTitulo = new JLabel("Titulo");
		 lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 16));
		 lblTitulo.setBounds(22, 11, 100, 27);
		 frmFormularioEpisodio.getContentPane().add(lblTitulo);
		 
		 textNumero = new JTextField();
		 textNumero.setBounds(132, 49, 43, 27);
		 frmFormularioEpisodio.getContentPane().add(textNumero);
		 textNumero.setColumns(10);
		 
		 lblNDeCapitulo = new JLabel("Nº de capitulo");
		 lblNDeCapitulo.setFont(new Font("Calibri", Font.PLAIN, 16));
		 lblNDeCapitulo.setBounds(194, 49, 100, 27);
		 frmFormularioEpisodio.getContentPane().add(lblNDeCapitulo);
		 
		 textNCapitulo = new JTextField();
		 textNCapitulo.setColumns(10);
		 textNCapitulo.setBounds(317, 49, 43, 27);
		 frmFormularioEpisodio.getContentPane().add(textNCapitulo);
		 btnCerrar.addActionListener(new OyenteBoton());
		 
		
		 frmFormularioEpisodio.setTitle("Nuevo capitulo de "+ serie.getNombre());	
		 
		 
		 
		 
		 frmFormularioEpisodio.setVisible(true);
		 frmFormularioEpisodio.setResizable(false);
		 
	}

	
	public void eliminarGrafico(){
		this.frmFormularioEpisodio.removeAll();
		this.frmFormularioEpisodio.setVisible(false);
		
	}
	
	private class OyenteBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCrear){
				try{
					int temporada = Integer.parseInt(textNumero.getText());
					int capitulo = Integer.parseInt(textNCapitulo.getText());
					EpisodioKey key = new EpisodioKey(serie.getId(), capitulo);
					Episodio epi = new Episodio(key, temporada,textField.getText(), textSinopsis.getText(), dateEstreno.getDate());
					//TODO insertar
					controlador.insertarEpisodio( epi);
					//myTableModelEpisodio.insertaEpisodio(epi);
					//myTableModelEpisodio.actualiza();
					lvlSuperior.actualiza();
					eliminarGrafico();
					
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null,"No se comprende la temporada escrita");
				}
				
			}else if(e.getSource() == btnCerrar){
				eliminarGrafico();
			} 
		}
	}
}
