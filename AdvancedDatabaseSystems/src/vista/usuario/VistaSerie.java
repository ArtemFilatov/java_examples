package vista.usuario;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Serie.Episodio;
import modelo.Serie.MyTableModelEpisodio;
import modelo.Serie.Serie;
import modelo.Usuario.ComentarioSerie;
import controlador.usuario.ControladorSeries;


public class VistaSerie {

	private JFrame frmDetallesSerie;
	
	private JButton btnCerrar;
	private JButton btnSeguir;
	private JButton btnVotar;
	private JButton btnEnviarComentario;
	private JTextArea textSinopsis;
	private JTextArea textTitular;
	private JTextArea chatBox;
	private JLabel labelNotaMedia;
	private JLabel labelMiNota;
	private JLabel lblGenero;
	private ControladorSeries controller;
	private Serie serie;
	private JTable table;
	private ArrayList<Episodio> episodios;
	private MyTableModelEpisodio myTableModelEpisodio;
	private boolean released = false;
	private JSlider slider;
	private JTextField commentBox;
	
	public VistaSerie(Serie serie, ControladorSeries controller) {
		this.controller = controller;
		initialize(serie);
	}
	
	
	private void initialize(Serie s) {
		serie = s;
		frmDetallesSerie = new JFrame();
		frmDetallesSerie.setTitle(serie.getNombre());
		frmDetallesSerie.setBounds(50, 50, 915, 667);
		frmDetallesSerie.getContentPane().setLayout(null);
		
		JLabel nombre = new JLabel(serie.getNombre());
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Calibri", Font.BOLD, 20));
		nombre.setBounds(267, 0, 367, 39);
		frmDetallesSerie.getContentPane().add(nombre);
		
		String generos = this.controller.getGenerosSerie(serie);
		lblGenero = new JLabel(generos);// + serie.getGeneros());
		lblGenero.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblGenero.setBounds(38, 118, 367, 29);
		frmDetallesSerie.getContentPane().add(lblGenero);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		lblSinopsis.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblSinopsis.setBounds(38, 154, 98, 29);
		frmDetallesSerie.getContentPane().add(lblSinopsis);
		
		btnSeguir = new JButton("Seguir");
		btnSeguir.addActionListener(new OyenteBoton());
		btnSeguir.setBounds(692, 601, 89, 23);
		frmDetallesSerie.getContentPane().add(btnSeguir);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new OyenteBoton());
		btnCerrar.setBounds(812, 601, 89, 23);
		frmDetallesSerie.getContentPane().add(btnCerrar);
		
		JScrollPane scrollPaneSinopsis = new JScrollPane();
		scrollPaneSinopsis.setBounds(38, 181, 367, 98);
		frmDetallesSerie.getContentPane().add(scrollPaneSinopsis);
		
		textSinopsis = new JTextArea(serie.getSinopsis());
		textSinopsis.setEditable(false);
		textSinopsis.setLineWrap(true);
		scrollPaneSinopsis.setViewportView(textSinopsis);
		
		textTitular = new JTextArea(serie.getTitular());
		textTitular.setEditable(false);
		textTitular.setBounds(38, 54, 367, 53);
		frmDetallesSerie.getContentPane().add(textTitular);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 317, 367, 156);
		frmDetallesSerie.getContentPane().add(scrollPane_1);
		
		myTableModelEpisodio = new MyTableModelEpisodio(this.getEpisodiosSerie());
		table = new JTable();
		table.setModel(myTableModelEpisodio);
		table.addMouseListener(new OyenteRaton());
		table.getSelectionModel().addListSelectionListener(new SelectionListener());
		scrollPane_1.setViewportView(table);
		
		JLabel lblEpisodios = new JLabel("Episodios");
		lblEpisodios.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEpisodios.setBounds(38, 282, 98, 29);
		frmDetallesSerie.getContentPane().add(lblEpisodios);
		
		slider = new JSlider(0, 10);
		slider.setValue(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setName("jSliderVoto");
		slider.setMajorTickSpacing(1);
		slider.setBounds(38, 505, 367, 63);
		frmDetallesSerie.getContentPane().add(slider);
		
		JLabel label = new JLabel("Calificación Media: ");
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(38, 478, 158, 29);
		frmDetallesSerie.getContentPane().add(label);
		
		labelNotaMedia = new JLabel(this.controller.encuentraNotaSerie(serie));
		labelNotaMedia.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelNotaMedia.setBounds(189, 478, 38, 29);
		frmDetallesSerie.getContentPane().add(labelNotaMedia);
		
		
		JLabel label_2 = new JLabel("Mi Calificación:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_2.setBounds(239, 478, 121, 29);
		frmDetallesSerie.getContentPane().add(label_2);
		
		int miNota = this.controller.encuentraVotoSerie(serie);
		if(miNota < 0)
			labelMiNota = new JLabel();
		else
			labelMiNota = new JLabel(String.valueOf(miNota));
		labelMiNota.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelMiNota.setBounds(367, 478, 38, 29);
		frmDetallesSerie.getContentPane().add(labelMiNota);
		
		btnVotar = new JButton("Votar");
		btnVotar.setBounds(48, 580, 89, 23);
		btnVotar.addActionListener(new OyenteBoton());
		frmDetallesSerie.getContentPane().add(btnVotar);
		
		chatBox = new JTextArea();
		chatBox.setText("");
		chatBox.setLineWrap(true);
		chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		chatBox.setEditable(false);
		chatBox.setBounds(457, 54, 417, 466);
		frmDetallesSerie.getContentPane().add(chatBox);
		cargaComentarios();
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(454, 532, 420, 29);
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
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.weighty = 1.0;
		gbc_button.weightx = 1.0;
		gbc_button.insets = new Insets(0, 10, 0, 0);
		gbc_button.fill = GridBagConstraints.NONE;
		gbc_button.anchor = GridBagConstraints.LINE_END;
		gbc_button.gridx = 1;
		gbc_button.gridy = 0;
		panel.add(btnEnviarComentario, gbc_button);
		btnEnviarComentario.addActionListener(new OyenteBoton());
		
		 this.frmDetallesSerie.setVisible(true);
		 this.frmDetallesSerie.setResizable(false);
		 
	}
	
	
	private ArrayList<Episodio> getEpisodiosSerie() {
		return this.controller.getEpisodiosSerie(this.serie);
	}


	public void eliminarGrafico(){
		this.frmDetallesSerie.removeAll();
		this.frmDetallesSerie.setVisible(false);
		
	}
	
	public void cargaComentarios() {
		this.chatBox.setText("");
		ArrayList<ComentarioSerie> comentarios = this.controller.getComentariosSerie(serie);
		Iterator<ComentarioSerie> it = comentarios.iterator();
		ComentarioSerie aux;
		while(it.hasNext()) {
			aux = it.next();
			this.chatBox.append("< " + aux.getNick() + " > (" +
					aux.getFecha().toString() + "): " +
					aux.getDescripcion() +
					"\n");
		}
		
	}
	
	
	private class OyenteBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSeguir){
				if (controller.sigueSerie(serie))
					JOptionPane.showMessageDialog(null,"Ahora sigues la serie " + serie.getNombre());
				else {
					JDialog.setDefaultLookAndFeelDecorated(true);
				    int response = JOptionPane.showConfirmDialog(null, "Ya sigues esta serie, ¿deseas dejar de seguirla?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.YES_OPTION) {
				      controller.dejarSeguirSerie(serie);
				    }
				}
			}else if(e.getSource() == btnCerrar){
				eliminarGrafico();
			}else if(e.getSource() == btnVotar){
				if(controller.encuentraVotoSerie(serie) > -1) {
					JDialog.setDefaultLookAndFeelDecorated(true);
				    int response = JOptionPane.showConfirmDialog(null, "Ya has votado esta serie, ¿deseas cambiar tu valoracion por un " + slider.getValue() + " ?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.YES_OPTION) {
				      controller.actualizaVotoSerie(serie, slider.getValue());
				      labelMiNota.setText(String.valueOf(slider.getValue()));
				      labelNotaMedia.setText(controller.encuentraNotaSerie(serie));
				    }
				} else {
					if(controller.votarSerie(serie, slider.getValue()))
					{
						JOptionPane.showMessageDialog(null, "Se ha ingresado su voto satisfactoriamente.");
						labelMiNota.setText(String.valueOf(slider.getValue()));
						labelNotaMedia.setText(controller.encuentraNotaSerie(serie));
					}else
						JOptionPane.showMessageDialog(null, "Ocurrio algo inesperado. Su voto no se ha guardado");
				}
			} else if (e.getSource() == btnEnviarComentario) {
				if(!commentBox.getText().equals(""))
					controller.insertaComentarioSerie(serie,commentBox.getText());
				cargaComentarios();
				commentBox.setText("");
			}
		}
	}
		
	private class SelectionListener implements ListSelectionListener {
		  
		  public void valueChanged(ListSelectionEvent e) {
			  if(released) {
				  int idEpisodio = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 0);
				  Episodio episodio = myTableModelEpisodio.getEpisodio(idEpisodio);
				  new VistaEpisodio(serie, episodio, controller);
			  }
		  }
		  public void valueChanged1(ListSelectionEvent e) {}
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
}