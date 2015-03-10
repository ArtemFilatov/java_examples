package controlador.usuario;

import java.util.Date;

import javax.swing.ImageIcon;

import mainPackage.Utilities;
import modelo.Usuario.Usuario;
import controlador.ControladorVistas;

public class ControladorDatosPersonales {
	
private ControladorVistas ControladorGUI;

	public ControladorDatosPersonales(ControladorVistas a){
		this.ControladorGUI = a;
	}
	
	
	/**
	 * cambia la ventana grafica a la ventana principal del usuario, sus series y demas
	 */
	public void cambiarGrafico (){
		ControladorGUI.cambiarAUsuario();
	}
	
	/**
	 * actualiza los datos del usuario que han cambiado
	 * @param nombre
	 * @param oldPass
	 * @param newPass
	 * @param birth
	 * @param foto
	 * @return
	 */
	public boolean actualiza(String oldPass, String newPass, Date birth, ImageIcon foto){
		Usuario user = this.ControladorGUI.getUsuario();
		
		if(user.getPass().equals(Utilities.stringToSha1(oldPass))){
			if(!newPass.equals("")){
				user.setPass(Utilities.stringToSha1(newPass));
			}
			if(birth != null){
				user.setDate(birth);
			}
			if(foto != null){
				user.setAvatar(foto);
			}
			this.ControladorGUI.actualizaUsuario(user);
			return true;
		}else{
			return false;
		}		
	}


	public boolean actualiza(String text, char[] password, char[] password2,
			Date date, Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	
}