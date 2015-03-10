package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class MainView {
	
	private JFrame mainView;
	
	/**
	 * Create the application.
	 * @param user 
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		mainView = new JFrame();
		mainView.setTitle("IO BraninFunction Jesus Vazquez");
		mainView.setBounds(100, 100, 485, 507);
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainView.getContentPane().setLayout(null);
		
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 12, 459, 450);
		mainView.getContentPane().add(tabbedPane);
			
		tabbedPane.addTab("Unidimensional", new ViewUnidimensional());
		tabbedPane.addTab("Multidimensional", new ViewMultidimensional());
		
		this.mainView.setVisible(true);
		this.mainView.setResizable(false);
		
	}

	public void eliminarGrafico(){
		this.mainView.removeAll();
		this.mainView.setVisible(false);	
	}
}
