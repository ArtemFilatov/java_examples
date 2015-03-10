package modelo.Usuario;

import java.util.Date;

public class ComentarioSerie {
	
	private int idComentario;
	private String nick;
	private int idSerie;
	private String des;
	private Date fecha;
	
	public ComentarioSerie(int idComentario, String nick, int idSerie, String des, Date fecha) {
		this.idComentario = idComentario;
		this.nick = nick;
		this.idSerie = idSerie;
		this.des = des;
		this.fecha = fecha;
	}
	
	public ComentarioSerie(String nick, int idSerie, String des, Date fecha) {
		this.nick = nick;
		this.idSerie  = idSerie;
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

	public String getDescripcion() {
		return des;
	}
	
	

}
