package modelo.Serie;

public class SerieGenero {
	
	private int idSerie;
	private Genero genero;
	
	public SerieGenero(int idSerie, Genero genero) {
		this.idSerie = idSerie;
		this.genero = genero;
	}

	public int getIdSerie() {
		return idSerie;
	}

	public Genero getGenero() {
		return genero;
	}
	
	

}
