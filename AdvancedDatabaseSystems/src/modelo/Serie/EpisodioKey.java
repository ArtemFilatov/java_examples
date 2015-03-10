package modelo.Serie;

public class EpisodioKey {

	private int idSerie;
	private int idEpisodio;
	
	public EpisodioKey(int idSerie, int idEpisodio) {
		this.idSerie = idSerie;
		this.idEpisodio = idEpisodio;
	}
	
	public int getIdSerie() {
		return idSerie;
	}

	public int getIdEpisodio() {
		return idEpisodio;
	}
	

}
