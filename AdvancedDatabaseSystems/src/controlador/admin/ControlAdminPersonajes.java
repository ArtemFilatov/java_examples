package controlador.admin;

import java.util.ArrayList;

import modelo.Serie.Actor;
import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.Personaje;
import controlador.ControladorVistas;

public class ControlAdminPersonajes {

	private ControladorVistas controlador;
	
	public ControlAdminPersonajes(ControladorVistas controladorVistas) {
		this.controlador = controladorVistas;
	}

	public ArrayList<Personaje> getPersonajes() {
		return this.controlador.listaPersonajes();
	}


	public ArrayList<Personaje> listaPersonajes(String buscar) {
		return this.controlador.listaPersonajes(buscar);
	}
	
	
	public void insertarPersonaje(Personaje personaje){
		controlador.insertaPersonaje(personaje);
	}

	public ArrayList<ActorSeriePersonaje> getActorSeriePersonajeEpisodio(
			Episodio episodio) {
		return this.controlador.getActorSeriePersonajeEpisodio(episodio);
	}

	
	
}
