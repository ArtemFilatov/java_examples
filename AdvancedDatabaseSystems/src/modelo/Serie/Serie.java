package modelo.Serie;

import java.util.Date;
/**
 * Created by n on 12/03/14.
 */
public class Serie {

    private int id;
    private String nombre;
    private String titular;
    private String sinopsis;
    private Date fEstreno;
    private Date fFinal;

    /**
     * Constructor con parámetros
     * @param id
     * @param nombre
     * @param titular
     * @param sinopsis
     * @param fEstreno
     * @param fFinal
     */
    public Serie(int id, String nombre, String titular, String sinopsis,
                 Date fEstreno, Date fFinal) {
        this.id = id;
        this.nombre = nombre;
        this.titular = titular;
        this.sinopsis = sinopsis;
        this.fEstreno = fEstreno;
        this.fFinal = fFinal;
    }
    
    public Serie(String nombre,String titular, String sinopsis,
                 Date fEstreno, Date fFinal) {
        this.nombre = nombre;
        this.titular = titular;
        this.sinopsis = sinopsis;
        this.fEstreno = fEstreno;
        this.fFinal = fFinal;
    }

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTitular() {
		return titular;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public Date getfEstreno() {
		return fEstreno;
	}

	public Date getfFinal() {
		return fFinal;
	}

	/**
	 * Devuelve el periodo de duracion de una serie.
	 */
	public String getPeriodo() {
		//TODO: Hacer este metodo dinamico.
		return "2005-20014";
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public boolean equals(Object v) {
	        return this.id == ((Serie) v).getId();
	 }
    
    
    
    
    
}
