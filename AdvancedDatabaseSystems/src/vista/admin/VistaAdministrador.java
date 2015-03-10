package vista.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controlador.admin.ControlAdminActores;
import controlador.admin.ControlAdminPersonajes;
import controlador.admin.ControlAdminSeries;

public class VistaAdministrador{

private JPanel contentPane;
private JFrame frmAdmin;

public VistaAdministrador(ControlAdminActores controlActores, ControlAdminPersonajes controlPersonajes, ControlAdminSeries controlSeries) {
	
	initialize(controlActores, controlPersonajes, controlSeries);
}


	/**
	 * Create the frame.
	 */
	public void initialize(ControlAdminActores controlActores, ControlAdminPersonajes controlPersonajes, ControlAdminSeries controlSeries) {
		
		frmAdmin = new JFrame();
		frmAdmin.setTitle("Panel de administracion");
		frmAdmin.setBounds(100, 100, 372, 340);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmAdmin.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 369, 315);
			contentPane.add(tabbedPane);
			
			tabbedPane.addTab("Series", new VistaSeries(controlSeries, controlActores, controlPersonajes));
			tabbedPane.addTab("Actores", new VistaActores(controlActores));
			tabbedPane.addTab("Personajes", new VistaPersonajes(controlPersonajes));
			
			this.frmAdmin.setVisible(true);
			this.frmAdmin.setResizable(false);
			
	}

}