package controlador.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import mainPackage.Utilities;
import modelo.Serie.Episodio;
import modelo.Serie.Genero;
import modelo.Serie.Serie;
import modelo.Usuario.Usuario;
import controlador.ControladorVistas;

public class ControlAdminSeries {

	private ControladorVistas ControladorGUI;
	
	public ControlAdminSeries(ControladorVistas controladorVistas) {
		this.ControladorGUI = controladorVistas;
	}

	public List<Serie> listaSeries(){
		return this.ControladorGUI.listaSeries();
	}
	
	public List<Serie> listaSeries(String text) {
		return this.ControladorGUI.listaSeries(text);
	}

	public ArrayList<Episodio> getEpisodiosSerie(Serie serie) {
		return this.ControladorGUI.getEpisodiosSerie(serie);
	}
	

	public void insertarSerie(Serie serie, ArrayList<Genero> generos) {
		ControladorGUI.insertaSerie(serie, generos);
	}


	public void actualizarSerie(Serie serie) {
		ControladorGUI.updateSerie(serie);
	}

	public void eliminarEpisodio(Episodio episodio) {
		ControladorGUI.removeEpisodio(episodio);
	}
	
	public void actualizarEpisodio(Episodio episodio) {
		ControladorGUI.updateEpisodio(episodio);
	}
	
	public void insertarEpisodio(Episodio epi) {
		ControladorGUI.insertEpisodio(epi);
	}

	public Serie getSerie(Serie s) {
		return ControladorGUI.getSerie(s);
	}

	public boolean getSeriePorNombreYFecha(String nombre, Date fEstreno) {
		return ControladorGUI.getSeriePorNombreYFecha(nombre,fEstreno);
	}

	public ArrayList<Genero> getGenerosSerie(Serie serie) {
		return ControladorGUI.getGenerosSerie(serie);
	}

	public void actualizarGenerosSerie(ArrayList<Genero> generos, Serie serie) {
		this.ControladorGUI.actualizarGenerosSerie(generos, serie);
		
	}

	
	
}
