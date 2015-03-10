package modelo.Serie;

public class ActorSeriePersonaje {
	
	private String nif;
	private String nombrePersonaje;
	private int idSerie;
	private int idEpisodio;
	private Actor actor;
	private Personaje personaje;
	
	public ActorSeriePersonaje (String nif, String nombrePersonaje, int idSerie, int idEpisodio) {
		this.nif = nif;
		this.nombrePersonaje = nombrePersonaje;
		this.idSerie = idSerie;
		this.idEpisodio = idEpisodio;
	}

	public String getNif() {
		return nif;
	}

	public String getNombrePersonaje() {
		return nombrePersonaje;
	}

	public int getIdSerie() {
		return idSerie;
	}

	public int getIdEpisodio() {
		return idEpisodio;
	}

	public Actor getActor() {
		return actor;
	}
	
	public String getNombreActor() {
		return actor.getNombre();
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
}
