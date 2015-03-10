package modelo.Usuario;

import java.util.Date;

/**
 * Created by n on 12/03/14.
 */
public class ComentarioEpisodio {
	private int idComentario;
	private String nick;
	private int idSerie;
	private int idEpisodio;
	private String des;
	private Date fecha;
	
	public ComentarioEpisodio(int idComentario, String nick, int idSerie, 
			int idEpisodio, String des, Date fecha) {
		this.idComentario = idComentario;
		this.nick = nick;
		this.idSerie = idSerie;
		this.idEpisodio = idEpisodio;
		this.des = des;
		this.fecha = fecha;
	}
	
	public ComentarioEpisodio(String nick, int idSerie, 
			int idEpisodio, String des, Date fecha) {
		this.nick = nick;
		this.idSerie = idSerie;
		this.idEpisodio = idEpisodio;
		this.des = des;
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getIdComentario() {
		return idComentario;
	}

	public String getNick() {
		return nick;
	}

	public int getIdSerie() {
		return idSerie;
	}
	
	public int getIdEpisodio() {
		return idEpisodio;
	}

	public String getDescripcion() {
		return des;
	}
	
	
}
