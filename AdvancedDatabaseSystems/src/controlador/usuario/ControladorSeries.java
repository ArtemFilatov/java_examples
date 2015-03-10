package controlador.usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.Genero;
import modelo.Serie.Serie;
import modelo.Usuario.ComentarioEpisodio;
import modelo.Usuario.ComentarioSerie;
import vista.usuario.BuscarSeries;
import controlador.ControladorVistas;

public class ControladorSeries {

	private ControladorVistas ControladorGUI;
	private BuscarSeries buscarSeriesVista;
	
	public ControladorSeries(ControladorVistas a){
		this.ControladorGUI = a;
	}
	
	public void actualizaDatos(){// dependiendo del modo de hacerlo, se pasarian unos datos u otros
		
	}
	
	public List<Serie> listaSeries(){
		return this.ControladorGUI.listaSeries();
	}
	
	public List<Serie> listaSeries(String text) {
		return this.ControladorGUI.listaSeries(text);
	}
	
	
	public void cambiarGrafico (){
		ControladorGUI.cambiarAInicio();
	}
	
	public void cambiarGraficoDatos (){
		ControladorGUI.cambiarADatosPersonales();
	}

	public ArrayList<Serie> getUserSeries() {
		return this.ControladorGUI.getUserSeries();
	}

	public ArrayList<Episodio> buscaEpisodiosNoVistos(Serie serie) {
		return this.ControladorGUI.buscaEpisodiosNoVistos(serie);
	}

	
	public boolean sigueSerie(Serie serie) {
		return this.ControladorGUI.sigueSerie(serie);
		
	}

	public ArrayList<Episodio> getEpisodiosSerie(Serie serie) {
		return this.ControladorGUI.getEpisodiosSerie(serie);
	}

	public void setEpisodioVisto(Episodio episodio) {
		this.ControladorGUI.setEpisodioVisto(episodio);
		
	}

	public boolean votarEpisodio(Episodio episodio, int value) {
		return this.ControladorGUI.votarEpisodio(episodio,value);
	}

	public boolean votarSerie(Serie serie, int value) {
		return this.ControladorGUI.votarSerie(serie,value);
	}

	public int encuentraVotoSerie(Serie serie) {
		return this.ControladorGUI.encuentraVotoSerie(serie);
	}

	public int encuentraVotoEpisodio(Episodio episodio) {
		return this.ControladorGUI.encuentraVotoEpisodio(episodio);
	}

	public String encuentraNotaSerie(Serie serie) {
		return this.ControladorGUI.encuentraNotaSerie(serie);
	}

	public String encuentraNotaEpisodio(Episodio episodio) {
		return this.ControladorGUI.encuentraNotaEpisodio(episodio);
	}

	public ArrayList<ActorSeriePersonaje> getActorSeriePersonajeEpisodio(
			Episodio episodio) {
		return this.ControladorGUI.getActorSeriePersonajeEpisodio(episodio);
	}

	public ArrayList<ComentarioEpisodio> getComentariosEpisodio(
			Episodio episodio) {
		return this.ControladorGUI.getComentariosEpisodio(episodio);
	}


	public void insertaComentarioEpisodio(Episodio episodio, String text) {
		this.ControladorGUI.insertaComentarioEpisodio(episodio,text);
		
	}

	public ArrayList<ComentarioSerie> getComentariosSerie(Serie serie) {
		return this.ControladorGUI.getComentariosSerie(serie);
	}

	public void insertaComentarioSerie(Serie serie, String text) {
		this.ControladorGUI.insertaComentarioSerie(serie,text);
	}

	public void actualizaVotoEpisodio(Episodio episodio, int value) {
		this.ControladorGUI.actualizaVotoEpisodio(episodio,value);
		
	}

	public void actualizaVotoSerie(Serie serie, int value) {
		this.ControladorGUI.actualizaVotoSerie(serie, value);
		
	}

	public void dejarSeguirSerie(Serie serie) {
		this.ControladorGUI.dejarSeguirSerie(serie);
		
	}

	public String getGenerosSerie(Serie serie) {
		ArrayList<Genero> generos = this.ControladorGUI.getGenerosSerie(serie);
		String ret = "Genero:"; 
		Iterator<Genero> it = generos.iterator();
		while(it.hasNext()) {
			ret += " "+ it.next().toString();
		}
		return ret;
	}

	




	
	
}
