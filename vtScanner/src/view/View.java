package view;

import java.awt.EventQueue;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Application View
 * @author Estado
 */

public class View extends JFrame implements ViewInterface {
	
	// Atributos.
	private JFileChooser jFileInput;
	private JButton jButtonFind;
	private JButton jButtonUpload;
	private JButton jButtonScan;
	private JPanel jPanelInput;
	private JPanel jPanelOutput;
	private JPanel jPanelScanId;
	private JTextField jTextFilePath;
	private JTextField jTextFileHash;
	private JScrollPane scrollPane;
	private JTextArea jTextOutput;
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * Constructor sin parametros.
	 */
    public View() 
    {
	        initComponents(); 
	}

    /**
     * Inicializa y configura todos los componentes de la vista.
     */
	private void initComponents() 
	{
		setTitle("VirusTotal Scanner v0.1");
		setResizable(false);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Initialize componets.
		jFileInput = new JFileChooser();
		jFileInput.setName("jFileInput");
        jPanelInput = new  JPanel();
        jTextFilePath = new  JTextField();
        jTextFilePath.setName("jTextFilePath");
        jButtonFind = new  JButton();
        jButtonFind.setName("jButtonFind");
        jButtonUpload = new  JButton();
        jButtonUpload.setName("jButtonUpload");
        jButtonScan= new JButton();
        jButtonScan.setName("jButtonScan");
        jPanelOutput = new  JPanel();
        jPanelScanId = new JPanel();
        jTextFileHash = new JTextField();
        jTextFileHash.setName("jTextFileHash");
        
        jPanelInput.setBorder( BorderFactory.createTitledBorder("Input (File)"));
        jPanelInput.setLayout(null);
        jPanelInput.add(jTextFilePath);
        jTextFilePath.setBounds(24, 32, 350, 19);

        jButtonFind.setText("Search");
        jPanelInput.add(jButtonFind);
        jButtonFind.setBounds(384, 30, 79, 25);

        jButtonUpload.setText("Upload");
        jPanelInput.add(jButtonUpload);
        jButtonUpload.setBounds(481, 30, 79, 25);

        getContentPane().add(jPanelInput);
        jPanelInput.setBounds(20, 30, 570, 70);

        jPanelOutput.setBorder( BorderFactory.createTitledBorder("Output"));
        jPanelOutput.setLayout(null);

        getContentPane().add(jPanelOutput);
        jPanelOutput.setBounds(20, 192, 570, 308);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 21, 550, 270);
        jPanelOutput.add(scrollPane);
        
        jTextOutput = new JTextArea();
        scrollPane.setViewportView(jTextOutput);
        

        jPanelScanId.setBorder(BorderFactory.createTitledBorder("Scan id (SHA256)"));
        jPanelScanId.setLayout(null);
        jPanelScanId.add(jTextFileHash);
        jTextFileHash.setBounds(24, 32, 350, 19);
        jPanelScanId.setBounds(20, 111, 570, 70);
        getContentPane().add(jPanelScanId);
        
        jButtonScan.setText("Scan");
        jButtonScan.setBounds(384, 31, 82, 25);
        jPanelScanId.add(jButtonScan);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-618)/2, (screenSize.height-542)/2, 618, 542);

    }
   
   /**
    * Hace la vista visible.
    */
   public void arranca()
   {
	   EventQueue.invokeLater(new Runnable(){
       	public void run() {
       		setVisible(true);
       	}
       });		
   }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fijarControlador(EventListener controlador) 
	{
		this.jButtonFind.addActionListener((ActionListener)controlador);
		this.jButtonUpload.addActionListener((ActionListener)controlador);
		this.jButtonScan.addActionListener((ActionListener)controlador);
		this.jTextFileHash.addKeyListener((KeyListener)controlador);
		this.jTextFilePath.addKeyListener((KeyListener)controlador);
	}
	
	public JFileChooser getJFileInput()
	{
		return this.jFileInput;
	}
	
	/**
	 * Setea el texto del JTextArea - Output.
	 * @param text - String a setear.
	 */
	public void setTextOutput(String text)
	{
		String aux = this.jTextOutput.getText();
		aux += text + LINE_SEPARATOR;
		this.jTextOutput.setText(aux);
	}
	
	/**
	 * Setea el path del fichero seleccionado en el JFileChooser.
	 * @param path - String path
	 */
	public void setTextFilePath(String path) 
	{
		this.jTextFilePath.setText(path);
	}
}
