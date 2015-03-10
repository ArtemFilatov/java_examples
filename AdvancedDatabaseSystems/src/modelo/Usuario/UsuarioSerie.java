package modelo.Usuario;

public class UsuarioSerie {
	private String nick;
	private int idSerie;
	
	public UsuarioSerie(String nick, int idSerie) {
		this.nick = nick;
		this.idSerie = idSerie;
	}

	public String getNick() {
		return nick;
	}

	public int getIdSerie() {
		return idSerie;
	}


}
