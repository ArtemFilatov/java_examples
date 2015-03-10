package controlador.admin;

import java.util.ArrayList;

import modelo.Serie.Actor;
import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.Personaje;
import controlador.ControladorVistas;

public class ControlAdminActores {

	private ControladorVistas controlador;
	
	public ControlAdminActores(ControladorVistas controladorVistas) {
		this.controlador = controladorVistas;
	}

	public ArrayList<Actor> getActores() {
		return this.controlador.listaActores();
	}

	public ArrayList<Actor> listaActores(String string) {
		return this.controlador.listaActores(string);
	}
	
	public ArrayList<Actor> listaActoresNif(String string) {
		return this.controlador.listaActoresNif(string);
	}

	public void nuevaRelacion(Actor act, Personaje per, Episodio episodio) {
		ActorSeriePersonaje x = new ActorSeriePersonaje(act.getNif(),per.getNombre(), episodio.getIdSerie(), episodio.getIdEpisodio());
		
		this.controlador.insertActorSeriePersonaje(x);

	}

	public void eliminaRelacion(ActorSeriePersonaje relacion){
		controlador.eliminaRelacion(relacion);
	}
	

	public void insertarActor(Actor actor){
		controlador.insertaActor(actor);
	}

	

}
