package view;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pk1.Biseccion;
import pk1.Branin;
import pk1.NewtonUnidimensional;

public class ViewMultidimensional extends JPanel {
	private JTextField epsilonField;
	private JTextField maxIterationsField;
	private JTextField xField;
	private JTextField aField;
	private JTextField bField;
	private JCheckBoxMenuItem newtonCheckBox;
	private JCheckBoxMenuItem biseccionCheckBox;
	private JButton minimizar;
	private JButton dibujar;
	private JTextArea textArea;
	
	private double epsilon = 0.0001;
	private int maxIterations = 200;
	private double x = 0;
	private double a = -5;
	private double b = 5;
	private Branin f;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 * @param controller 
	 */
	public ViewMultidimensional(){
		this.f = new Branin();
		setLayout(null);
		
		minimizar = new JButton("Minimizar");
		minimizar.setBounds(12, 201, 110, 25);
		add(minimizar);
		minimizar.addActionListener(new OyenteBoton());
		
		dibujar = new JButton("Dibujar");
		dibujar.addActionListener(new OyenteBoton());
		dibujar.setBounds(338, 201, 103, 25);
		add(dibujar);
		
		JLabel lblNewLabel = new JLabel("EPSILON");
		lblNewLabel.setBounds(12, 90, 70, 15);
		add(lblNewLabel);
		
		JLabel lblMaxIterations = new JLabel("MAX ITERATIONS");
		lblMaxIterations.setBounds(10, 143, 143, 15);
		add(lblMaxIterations);
		
		JLabel lblNewLabel_1 = new JLabel(" (1-2*x2 + 0.05*sen(4*pi*x2)-x1)² + (x2 -0.5*sen(2*pi*x1))²");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 37, 429, 41);
		add(lblNewLabel_1);
		
		JLabel lblFuncion = new JLabel("FUNCION");
		lblFuncion.setBounds(10, 27, 70, 15);
		add(lblFuncion);
		
		epsilonField = new JTextField();
		epsilonField.setBounds(10, 106, 132, 25);
		add(epsilonField);
		epsilonField.setColumns(10);
		epsilonField.setText(String.valueOf(this.epsilon));
		epsilonField.setEnabled(false);
		
		maxIterationsField = new JTextField();
		maxIterationsField.setColumns(10);
		maxIterationsField.setBounds(10, 164, 132, 25);
		add(maxIterationsField);
		maxIterationsField.setText(String.valueOf(this.maxIterations));
		
		biseccionCheckBox = new JCheckBoxMenuItem("Biseccion");
		biseccionCheckBox.setBounds(171, 124, 114, 19);
		add(biseccionCheckBox);
		biseccionCheckBox.setSelected(true);
		biseccionCheckBox.addItemListener(new OyenteCheckBox());
		
		newtonCheckBox = new JCheckBoxMenuItem("Newton");
		newtonCheckBox.setBounds(171, 90, 114, 19);
		add(newtonCheckBox);
		newtonCheckBox.addItemListener(new OyenteCheckBox());
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(303, 90, 18, 15);
		add(lblX);
		
		JLabel lblA = new JLabel("A");
		lblA.setBounds(303, 123, 18, 15);
		add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setBounds(303, 159, 18, 15);
		add(lblB);
		
		xField = new JTextField();
		xField.setBounds(327, 88, 114, 19);
		add(xField);
		xField.setColumns(10);
		xField.setEnabled(false);
		xField.setText(String.valueOf(this.x));
		
		aField = new JTextField();
		aField.setColumns(10);
		aField.setBounds(327, 121, 114, 19);
		add(aField);
		aField.setText(String.valueOf(this.a));
		
		bField = new JTextField();
		bField.setColumns(10);
		bField.setBounds(327, 157, 114, 19);
		bField.setText(String.valueOf(this.b));
		add(bField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 238, 429, 167);
		add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
	}
	
	private class OyenteBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == minimizar) {
				if(biseccionCheckBox.isSelected()) {
					a = Double.parseDouble(aField.getText());
					b = Double.parseDouble(bField.getText());
					maxIterations = Integer.parseInt(maxIterationsField.getText());
					String text = Biseccion.minimiza(f, a, b, maxIterations);
					textArea.setText(text);
				} else {
					x = Double.parseDouble(xField.getText());
					maxIterations = Integer.parseInt(maxIterationsField.getText());
					String text = "EL METODO NO FUNCIONA ADECUADAMENTE \n \n";
					text += NewtonUnidimensional.minimiza(f, x,maxIterations);
					textArea.setText(text);
				}
			}
			else if(e.getSource() == dibujar)
				Branin.dibujaMultidimensional(f);
		}
	}
	
	private class OyenteCheckBox implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == biseccionCheckBox) {
				if(biseccionCheckBox.isSelected()) {
					newtonCheckBox.setSelected(false);
					aField.setEnabled(true);
					bField.setEnabled(true);
					xField.setEnabled(false);
				} else {
					newtonCheckBox.setSelected(true);
					aField.setEnabled(false);
					bField.setEnabled(false);
					xField.setEnabled(true);
				}
			} else if (e.getSource() == newtonCheckBox) {
				if(newtonCheckBox.isSelected()) {
					biseccionCheckBox.setSelected(false);
					xField.setEnabled(true);
					aField.setEnabled(false);
					bField.setEnabled(false);
				} else {
					biseccionCheckBox.setSelected(true);
					aField.setEnabled(true);
					bField.setEnabled(true);
					xField.setEnabled(false);
				}
			}
				
			
		}
	}
}
