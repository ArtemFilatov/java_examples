package modelo.Serie;

import java.util.Date;

/**
 * Created by n on 12/03/14.
 */
public class Episodio {

	private EpisodioKey id;
    private int temporada;
    private String titulo;
    private String sinopsis;
    private Date fEstreno;

    public Episodio(EpisodioKey id, int temporada, String titulo,
                    String sinopsis, Date fEstreno) {
        this.id = id;
        this.temporada = temporada;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.fEstreno = fEstreno;
    }
    
    public EpisodioKey getId() {
		return id;
	}

	public int getIdSerie() {
		return this.id.getIdSerie();
	}

	public int getIdEpisodio() {
		return this.id.getIdEpisodio();
	}

	public int getTemporada() {
		return temporada;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public Date getfEstreno() {
		return fEstreno;
	}
	
	@Override
	public boolean equals(Object v) {
	        return (this.getIdEpisodio() == ((Episodio) v).getIdEpisodio()) && 
	        		(this.getIdSerie() == ((Episodio) v).getIdSerie());
	 }


}
