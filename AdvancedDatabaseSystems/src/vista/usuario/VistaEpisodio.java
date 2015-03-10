package vista.usuario;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.MyTableModelActorSeriePersonaje;
import modelo.Serie.Serie;
import modelo.Usuario.ComentarioEpisodio;
import controlador.usuario.ControladorSeries;

public class VistaEpisodio {
	


	private JFrame frmDetallesSerie;
	
	private JButton btnCerrar;
	private JButton btnVotar;
	private JTextArea textSinopsis;
	private JLabel labelNotaMedia;
	private JLabel labelMiNota;
	private ControladorSeries controller;
	private Episodio episodio;
	private Serie serie;
	private MyTableModelActorSeriePersonaje myTableModelActorSeriePersonaje;
	private JSlider slider;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextArea chatBox;
	private JPanel panel;
	private JTextField commentBox;
	private JButton btnEnviarComentario;
	
	public VistaEpisodio(Serie serie, Episodio episodio, ControladorSeries controller) {
		this.serie = serie;
		this.controller = controller;
		this.episodio = episodio;
		initialize();
	}
	
	
	private void initialize() {
		frmDetallesSerie = new JFrame();
		frmDetallesSerie.setTitle(serie.getNombre());
		frmDetallesSerie.setBounds(50, 50, 915, 690);
		frmDetallesSerie.getContentPane().setLayout(null);
		
		JLabel nombre = new JLabel("Nº: "+episodio.getIdEpisodio()+" Temp: " +episodio.getTemporada()+" " +episodio.getTitulo());
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Calibri", Font.BOLD, 17));
		nombre.setBounds(274, 12, 367, 39);
		frmDetallesSerie.getContentPane().add(nombre);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		lblSinopsis.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblSinopsis.setBounds(48, 62, 98, 29);
		frmDetallesSerie.getContentPane().add(lblSinopsis);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new OyenteBoton());
		btnCerrar.setBounds(395, 622, 89, 23);
		frmDetallesSerie.getContentPane().add(btnCerrar);
		
		JScrollPane scrollPaneSinopsis = new JScrollPane();
		scrollPaneSinopsis.setBounds(38, 92, 367, 144);
		frmDetallesSerie.getContentPane().add(scrollPaneSinopsis);
		
		textSinopsis = new JTextArea(episodio.getSinopsis());
		textSinopsis.setEditable(false);
		textSinopsis.setLineWrap(true);
		scrollPaneSinopsis.setViewportView(textSinopsis);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 420, 367, 174);
		frmDetallesSerie.getContentPane().add(scrollPane_1);
		
		myTableModelActorSeriePersonaje = new MyTableModelActorSeriePersonaje(this.getActorSeriePersonajeEpisodio());
		table = new JTable();
		table.setModel(myTableModelActorSeriePersonaje);
		//table.addMouseListener(new OyenteRaton());
		//table.getSelectionModel().addListSelectionListener(new SelectionListener());
		scrollPane_1.setViewportView(table);
		
		JLabel lblEpisodios = new JLabel("Actores");
		lblEpisodios.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEpisodios.setBounds(48, 388, 98, 29);
		frmDetallesSerie.getContentPane().add(lblEpisodios);
		
		JLabel lblVot = new JLabel("Calificación Media: ");
		lblVot.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblVot.setBounds(38, 248, 158, 29);
		frmDetallesSerie.getContentPane().add(lblVot);
		
		labelNotaMedia = new JLabel(this.controller.encuentraNotaEpisodio(episodio));
		labelNotaMedia.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelNotaMedia.setBounds(184, 248, 38, 29);
		frmDetallesSerie.getContentPane().add(labelNotaMedia);
		
		JLabel label = new JLabel("Mi Calificación:");
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(236, 248, 121, 29);
		frmDetallesSerie.getContentPane().add(label);
		
		int miNota = this.controller.encuentraVotoEpisodio(episodio);
		if(miNota < 0)
			labelMiNota = new JLabel();
		else
			labelMiNota = new JLabel(String.valueOf(miNota));
		labelMiNota.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelMiNota.setBounds(369, 248, 38, 29);
		frmDetallesSerie.getContentPane().add(labelMiNota);

		slider = new JSlider(0,10);
		slider.setName("jSliderVoto");
		slider.setValue(5);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMaximum(10);
		slider.setBounds(38, 278, 367, 63);
		frmDetallesSerie.getContentPane().add(slider);
		
		btnVotar = new JButton("Votar");
		btnVotar.addActionListener(new OyenteBoton());
		btnVotar.setBounds(38, 353, 89, 23);
		frmDetallesSerie.getContentPane().add(btnVotar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(453, 92, 420, 469);
		frmDetallesSerie.getContentPane().add(scrollPane);
		
		chatBox = new JTextArea();
		chatBox.setLineWrap(true);
		chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		chatBox.setEditable(false);
		scrollPane.setViewportView(chatBox);
		cargaComentarios();
		
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(453, 565, 420, 29);
		frmDetallesSerie.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		commentBox = new JTextField(30);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weighty = 1.0;
		gbc_textField.weightx = 512.0;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.anchor = GridBagConstraints.LINE_START;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel.add(commentBox, gbc_textField);
		
		btnEnviarComentario = new JButton("Enviar Comentario");
		btnEnviarComentario.addActionListener(new OyenteBoton());
		GridBagConstraints gbc_btnEnviarComentario = new GridBagConstraints();
		gbc_btnEnviarComentario.weighty = 1.0;
		gbc_btnEnviarComentario.weightx = 1.0;
		gbc_btnEnviarComentario.insets = new Insets(0, 10, 0, 0);
		gbc_btnEnviarComentario.fill = GridBagConstraints.NONE;
		gbc_btnEnviarComentario.anchor = GridBagConstraints.LINE_END;
		gbc_btnEnviarComentario.gridx = 1;
		gbc_btnEnviarComentario.gridy = 0;
		panel.add(btnEnviarComentario, gbc_btnEnviarComentario);
		
		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblComentarios.setBounds(463, 62, 121, 29);
		frmDetallesSerie.getContentPane().add(lblComentarios);
		
		
		 this.frmDetallesSerie.setVisible(true);
		 this.frmDetallesSerie.setResizable(false);
	}


	public void cargaComentarios() {
		this.chatBox.setText("");
		ArrayList<ComentarioEpisodio> comentarios = this.controller.getComentariosEpisodio(episodio);
		Iterator<ComentarioEpisodio> it = comentarios.iterator();
		ComentarioEpisodio aux;
		while(it.hasNext()) {
			aux = it.next();
			this.chatBox.append("< " + aux.getNick() + " > (" +
					aux.getFecha().toString() + "): " +
					aux.getDescripcion() +
					"\n");
		}
		
	}


	private ArrayList<ActorSeriePersonaje> getActorSeriePersonajeEpisodio() {
		return this.controller.getActorSeriePersonajeEpisodio(episodio);
	}


	public void eliminarGrafico(){
		this.frmDetallesSerie.removeAll();
		this.frmDetallesSerie.setVisible(false);
		
	}
	
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCerrar){
				eliminarGrafico();
			}else if(e.getSource()== btnVotar) {
				if(controller.encuentraVotoEpisodio(episodio) > -1) {
				    JDialog.setDefaultLookAndFeelDecorated(true);
				    int response = JOptionPane.showConfirmDialog(null, "Ya has votado este episodio, ¿deseas cambiar tu valoracion por un " + slider.getValue() + " ?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.YES_OPTION) {
				      controller.actualizaVotoEpisodio(episodio, slider.getValue());
				      labelMiNota.setText(String.valueOf(slider.getValue()));
				      labelNotaMedia.setText(controller.encuentraNotaEpisodio(episodio));
				    }
				} else {
					if(controller.votarEpisodio(episodio, slider.getValue()))
					{
						JOptionPane.showMessageDialog(null, "Se ha ingresado su voto satisfactoriamente.");
						labelMiNota.setText(String.valueOf(slider.getValue()));
						labelNotaMedia.setText(controller.encuentraNotaEpisodio(episodio));
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Ocurrio algo inesperado. Su voto no se ha guardado");
					}
				}
			}else if(e.getSource() == btnEnviarComentario) {
				if(!commentBox.getText().equals(""))
					controller.insertaComentarioEpisodio(episodio,commentBox.getText());
				cargaComentarios();
				commentBox.setText("");
			}
		}
		
	}
}
