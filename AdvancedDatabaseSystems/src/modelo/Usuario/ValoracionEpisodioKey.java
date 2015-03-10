package modelo.Usuario;

public class ValoracionEpisodioKey {
	
	private String nick;
	private int idSerie;
	private int idEpisodio;
	
	public ValoracionEpisodioKey(String nick, int idSerie, int idEpisodio) {
		this.nick = nick;
		this.idSerie = idSerie;
		this.idEpisodio = idEpisodio;
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

}
