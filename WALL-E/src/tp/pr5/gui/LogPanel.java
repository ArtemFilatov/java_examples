package tp.pr5.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.RobotEngineObserver;

/**
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class LogPanel extends JPanel implements ViewInterface
{
	
	//private JTextArea jTextAreaLog;
	private static JTextArea jTextAreaLog;
	private JScrollPane jScrollPane;
	
	
	/**
	 * <p> Inicializa el panel sin controlador. Posteriomente es necesario estaqblecer el controlador
	 * llamando a <code>fijarControlador</code>
	 */
	public LogPanel()
	{
		iniciarLogPanel();
	}
	
	/**
     * Inicializa todos los componentes del panel y los consfigura adecuadamente.
     */
	public void iniciarLogPanel()
	{
		// Panel configuration
		this.setBorder(new TitledBorder("Log"));
	    this.setName("logForm");
		this.setPreferredSize(new java.awt.Dimension(1010, 150));
		 
		//Inicializar componentes.		  
		jTextAreaLog = new JTextArea();
		jScrollPane  = new JScrollPane(jTextAreaLog);
		//Configuraci�n de los elementos
		jTextAreaLog.setBackground(Color.WHITE);
		jTextAreaLog.setVisible(true);
		  
		//A�adir al panel
		this.add(jScrollPane);
	    LayoutManager thisLayout = new GridLayout(1,1);
	    this.setLayout(thisLayout);
	    
	}
	
	/**
	 * Metodo que sirve como Setter del texto del LogPanel.
	 * @param text
	 */
	public void setLogText(String text)
	{
		jTextAreaLog.setText(text);
	}

	/**
	 * Metodo que actualiza el texto del LogPanel.
	 */
	public void update(Observable o, Object arg){}

	@Override
	public void fijarControlador(EventListener controlador){}

	@Override
	public void arranca() {}

}
