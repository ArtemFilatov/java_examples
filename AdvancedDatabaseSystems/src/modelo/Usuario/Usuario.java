package modelo.Usuario;


import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

import modelo.Serie.Serie;


/**
 * Created by n on 12/03/14.
 */
public class Usuario {

    private String nick;
    private String password;
    private Date birth;
    private ImageIcon avatar;
    private ArrayList<Serie> misSeries;

    public Usuario(String nick, String password, Date birth, ImageIcon avatar) {
        this.nick = nick;
        this.password = password;
        this.birth = birth;
        this.avatar = avatar;
        misSeries = new ArrayList<Serie>();
    }

    public String toString() {
    	// TODO: Just for debug, no es necesario
    	return "Usuario: " + this.nick + " " + this.password + " " + this.birth + " " + this.avatar;
    }
    
    /////////////////////// GETTERS AND SETTERS
    
    public String getNick(){
    	return this.nick;
    }

 
    public Date getBirth(){
    	return birth;
    }
    
    public ImageIcon getAvatar(){
    	return avatar;
    }
    
    public String getPass(){return this.password;}
   
    public String getYears(){
    	//TODO
    	return "" + 30;
    }
    
    public ArrayList<Serie> getMisSeries() {
		return misSeries;
	}

	public void setPass(String pass){
    	this.password = pass;
    }
    
    public void setDate(Date fecha){
    	this.birth = fecha;
    }
    
    public void setAvatar(ImageIcon foto){
    	this.avatar = foto;
    }
    
	public void setMisSeries(ArrayList<Serie> misSeries) {
		this.misSeries = misSeries;
	}
    

    
    
}
