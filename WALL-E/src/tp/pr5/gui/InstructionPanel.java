package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import java.util.EventListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.Rotation;

/**
 * 
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

@SuppressWarnings("serial")
public class InstructionPanel extends JPanel 
{
	
	
	private final String INSTRUCTION_MOVE= "MOVE";
	private final String INSTRUCTION_OPERATE="OPERATE";
	private final String INSTRUCTION_QUIT= "QUIT";
	private final String INSTRUCTION_PICK= "PICK";
	private final String INSTRUCTION_DROP= "DROP";
	private final String INSTRUCTION_TURN= "TURN"; 
	private final Rotation[] JCOMBOBOX_DIRECTION_OPTIONS= {Rotation.LEFT , Rotation.RIGHT};
	
	private JButton jButtonMove, jButtonOperate, jButtonQuit, jButtonPick, jButtonDrop, jButtonTurn; // todos los botones.
	private JComboBox jComboBoxDirections; // left, right, unknown...
	private JTextField jTextItem; // newspapers...
		
	
	/**
	 * Constructor sin parametros
	 */
	public InstructionPanel()
	{
		inicializarInstructions();
	}
	
	/**
	 * Método que inicializa, configura y añade los componentes a Instruction Panel
	 */
	public void inicializarInstructions()
	{
	     this.setBorder(new TitledBorder("Instructions"));
	     this.setName("Form Text");
	     this.setPreferredSize(new java.awt.Dimension(300, 150));
	     
	   //Inicializacion
		jButtonMove= new JButton(INSTRUCTION_MOVE);	
		jButtonTurn = new JButton(INSTRUCTION_TURN);
		jButtonDrop= new JButton(INSTRUCTION_DROP);
		jButtonQuit= new JButton(INSTRUCTION_QUIT);
		jButtonPick= new JButton(INSTRUCTION_PICK);
		jTextItem= new JTextField();
		jButtonOperate= new JButton(INSTRUCTION_OPERATE);
		jComboBoxDirections = new JComboBox(JCOMBOBOX_DIRECTION_OPTIONS);
			
			
		//Asignacion
		jButtonMove.setName("jButtonMove");
		jButtonTurn.setName("jButtonTurn");
		jButtonDrop.setName("jButtonDrop");
		jButtonPick.setName("jButtonPick");
		jButtonQuit.setName("jButtonQuit");
		jButtonOperate.setName("jButtonOperate");
		jComboBoxDirections.setName("jComboBoxDirections");
		jTextItem.setName("jTextItem");
			

			
		// Añadimos al JPanel
	    this.add(jButtonMove);
	    this.add(jButtonQuit);
	    this.add(jButtonTurn);
	    this.add(jComboBoxDirections);
	    this.add(jButtonPick);
	    this.add(jTextItem);
	    this.add(jButtonDrop);
	    this.add(jButtonOperate);
		    
		    
	     LayoutManager thisLayout = new GridLayout(4,2);
	     this.setLayout(thisLayout);
	     
	     
	     
	}
	
	/**
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public void update(Observable arg0, Object arg1){} 
	
	/**
	 * Fijamos el controlador de todos los componentes del jpanel
	 * @param controlador
	 */
	public void fijarControlador(EventListener controlador) 
	{
		jButtonMove.addActionListener((ActionListener)controlador);
		jButtonQuit.addActionListener((ActionListener)controlador);
		jButtonTurn.addActionListener((ActionListener)controlador);
		jButtonPick.addActionListener((ActionListener)controlador);
		jButtonDrop.addActionListener((ActionListener)controlador);
		jButtonOperate.addActionListener((ActionListener)controlador);
		// Combo
		jComboBoxDirections.addActionListener((ActionListener)controlador);
		// jText
		jTextItem.addActionListener((ActionListener)controlador);
		jTextItem.addKeyListener((KeyListener)controlador);
	}
	 

}
