package modelo.Usuario;

public class ValoracionEpisodio {
	private ValoracionEpisodioKey key;
	private int nota;
	
	public ValoracionEpisodio(String nick, int idSerie, int idEpisodio,
			int nota) {
		this.key = new ValoracionEpisodioKey(nick,idSerie,idEpisodio);
		this.nota = nota;
	}
	
	public ValoracionEpisodio(ValoracionEpisodioKey key, int nota) {
		this.key = key;
		this.nota = nota;
	}
	
	public ValoracionEpisodioKey getKey() {
		return key;
	}

	public String getNick() {
		return this.key.getNick();
	}

	public int getIdSerie() {
		return this.key.getIdSerie();
	}

	public int getIdEpisodio() {
		return this.key.getIdEpisodio();
	}

	public int getNota() {
		return nota;
	}
	
	
	

}
