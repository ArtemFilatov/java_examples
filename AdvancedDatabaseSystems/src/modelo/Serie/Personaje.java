package modelo.Serie;

/**
 * Created by n on 12/03/14.
 */
public class Personaje {
	private int idPersonaje;
    private String nombre;
    private String des;

    public Personaje(int idPersonaje, String nombre, String des) {
    	this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.des = des;
    }
    
	public Personaje(String nombre, String des) {
        this.nombre = nombre;
        this.des = des;
    }
    
    public int getIdPersonaje() {
		return idPersonaje;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDes() {
		return des;
	}
    
    
}
