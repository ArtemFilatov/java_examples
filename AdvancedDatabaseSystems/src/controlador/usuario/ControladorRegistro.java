package controlador.usuario;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

import mainPackage.Utilities;
import modelo.Serie.Serie;
import modelo.Usuario.Usuario;
import controlador.ControladorVistas;

public class ControladorRegistro {

	private Usuario user;
	private ControladorVistas ControladorVistas;
	
	public ControladorRegistro(ControladorVistas a){
		this.ControladorVistas = a;
	}
	

	public boolean acceso (String usuario, String pass){

		// TODO: CHECK "usuario" por posible SQL injections!!!! 
		this.user = this.ControladorVistas.encuentraUsuario(usuario);
		if(this.user != null && user.getPass().equalsIgnoreCase(Utilities.stringToSha1(pass))){//comparar contraseña de la bd (hash sha1) con la que nos dan (la hasheamos)
			this.ControladorVistas.setUsuario(this.user);
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean registro (String usuario, String pass, Date birth){
		Usuario aux = null;
		aux = this.ControladorVistas.encuentraUsuario(usuario);
		if(aux == null){
			this.user = new Usuario(usuario, Utilities.stringToSha1(pass), birth, new ImageIcon("src/vista/img/defecto.png"));
			
			this.ControladorVistas.insertaUsuario(this.user);
			this.ControladorVistas.setUsuario(this.user);
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * modifica los datos del usuario que entra en la aplicacion e inicia la ventana principal
	 */
	public void cambiarGrafico (){
		ControladorVistas.setUsuario(user);
		ControladorVistas.cambiarAUsuario();
	}
	
	
}
