package vista.admin;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFileActor extends JFrame {
	
	public JFileActor(FormularioActor auxiliar){
	
		getContentPane().setLayout(null);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setBounds(0, 0, 623, 397);
		getContentPane().add(fileChooser);

		FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
		fileChooser.setFileFilter(filtroImagen);
		
		//Comprobar si se ha pulsado Aceptar
		int respuesta = fileChooser.showOpenDialog(this);
		
		if (respuesta == JFileChooser.APPROVE_OPTION) {

			 try {
			      String ruta =fileChooser.getSelectedFile().toString();
			      ImageIcon img=new ImageIcon(ruta);
			      auxiliar.cambiarImagen(img);
			     } catch (Exception e1) {
			 }
			
		}else if(respuesta == JFileChooser.CANCEL_OPTION){//no hace nada
		}
		
	}


}
