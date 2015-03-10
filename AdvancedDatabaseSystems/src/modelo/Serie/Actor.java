package modelo.Serie;


import java.util.Date;

import javax.swing.ImageIcon;



/**
 * Created by n on 12/03/14.
 */
public class Actor {

	private String nif;
    private String nombre;
    private Date dob;
    private ImageIcon foto;

    public Actor(String nif, String nombre, Date dob, ImageIcon foto) {
        this.nif = nif;
        this.nombre = nombre;
        this.dob = dob;
        this.foto = foto;
    }
    
    public String getNif() {
		return nif;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getDob() {
		return dob;
	}

	public ImageIcon getFoto() {
		return foto;
	}
/**
 * true si bien
 * @return
 */
	public boolean nifCorrecto() {
		boolean correcto = true;
		int i = 0;
		char s;
		String parser;
		if (nif.length() != 9){/// se comprueba que son 9 letras
			correcto = false;
		}else{
				while(i < 8 && correcto){  // se comprueba que los 8 primeros son numeros
					try{
						s = nif.charAt(i);
						parser = s+ "";
						Integer.parseInt(parser);
					}catch(NumberFormatException e){
						correcto = false;
					}
					i++;
				}
				
				try{						// se comprueba que el ultimo no es un numero forzando la excepcion
					s = nif.charAt(8);
					parser = s+ "";
					Integer.parseInt(parser);
					correcto = false;
				}catch(NumberFormatException e){
					
				}
				
				
		}
		return correcto;
	}
}


