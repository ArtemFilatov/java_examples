package modelo.Usuario;

public class ValoracionSerie {
	
	private ValoracionSerieKey key;
	private int nota;
	
	public ValoracionSerie(String nick, int idSerie, int nota) {
		this.key = new ValoracionSerieKey(nick,idSerie);
		this.nota = nota;
	}
	
	public ValoracionSerieKey getKey() {
		return this.key;
	}

	public String getNick() {
		return this.key.getNick();
	}

	public int getIdSerie() {
		return this.key.getIdSerie();
	}

	public int getNota() {
		return nota;
	}
	
	
	

}
