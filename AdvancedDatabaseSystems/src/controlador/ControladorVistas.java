package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.AppService.AppServiceAplicacion;
import modelo.Serie.Actor;
import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.Genero;
import modelo.Serie.Personaje;
import modelo.Serie.Serie;
import modelo.Usuario.ComentarioEpisodio;
import modelo.Usuario.ComentarioSerie;
import modelo.Usuario.Usuario;
import vista.admin.VistaAdministrador;
import vista.usuario.DatosPersonales;
import vista.usuario.Inicio;
import vista.usuario.VistaUsuario;
import controlador.admin.ControlAdminActores;
import controlador.admin.ControlAdminPersonajes;
import controlador.admin.ControlAdminSeries;
import controlador.usuario.ControladorDatosPersonales;
import controlador.usuario.ControladorRegistro;
import controlador.usuario.ControladorSeries;

public class ControladorVistas {
	
	private AppServiceAplicacion modelo;
	private Usuario user;
	private ControladorRegistro registro;
	private ControladorSeries series;
	private ControladorDatosPersonales datos;
	private ControlAdminActores ControlActores;
	private ControlAdminPersonajes ControlPersonajes;
	private ControlAdminSeries ControlSeries;
	

	/**
	 * arranca el controlador total de la aplicacion, creando los demas subcontroladores
	 * y arrancando la ventana pos defecto, que es el registro
	 */
	public ControladorVistas(AppServiceAplicacion modelo, boolean isUser){
		this.modelo = modelo;
		
		
		
		if(isUser){ // lanza la aplicacion para el usuario
				preparaControladoresUsuario();
				try {
					new Inicio(registro);	
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else{// lanza la aplicacion para el admin
			preparaControladoresAdmin();
			try {
				new VistaAdministrador(ControlActores, ControlPersonajes, ControlSeries);	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	private void preparaControladoresAdmin() {
		this.ControlActores = new ControlAdminActores (this);
		this.ControlPersonajes = new ControlAdminPersonajes (this);
		this.ControlSeries = new ControlAdminSeries (this);
		
	}

	private void preparaControladoresUsuario() {
		this.registro = new ControladorRegistro(this);
		this.series = new ControladorSeries(this);
		this.datos = new ControladorDatosPersonales(this);
		
	}

	/**
	 * cambio creado al deslogearse
	 */
	public void cambiarAInicio(){
		try {
			new Inicio(registro);
			this.user = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * version al acceder a la aplicacion,
	 * el usuario se crea con los datos sacados o de base de datos o de registro
	 * @param user
	 */
	public void cambiarAUsuario(){ //
		try {
			new VistaUsuario(series, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * cambia el grafico de la aplicacion, abriendo la ventana de modificacion de datos
	 */
	public void cambiarADatosPersonales() {
		try {
			new DatosPersonales(datos, user);	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * devuelve los datos del usuario cuando son necesarios
	 * @return
	 */
	public Usuario getUsuario (){
		return user;
	}
	
	
	/**
	 * cambia los datos del usuario que esta en la aplicacion,
	 * sirve para actualizar, y para registrarse o acceder
	 * @param user
	 */
	public void setUsuario(Usuario user) {
		this.user = user;
	}
	

	
	public List<Serie> listaSeries() {
		return this.modelo.getAllSeries();
	}
	
	public List<Serie> listaSeries(String text) {
		return this.modelo.getSeriesByName(text);
	}

	public Usuario encuentraUsuario(String usuario) {
		Usuario aux = this.modelo.encuentraUsuario(usuario);
		if(aux != null){
		ArrayList<Serie> series = this.modelo.getUserSeries(aux);
		aux.setMisSeries(series);
		}
		return aux;
	}

	public void insertaUsuario(Usuario usuario) {
		this.modelo.insertaUsuario(usuario);
	}

	public ArrayList<Serie> getUserSeries() {
		this.user.setMisSeries(this.modelo.getUserSeries(this.user));
		return this.user.getMisSeries();
	}

	public ArrayList<Episodio> buscaEpisodiosNoVistos(Serie serie) {
		return this.modelo.buscaEpisodiosNoVistos(serie, this.user);
	}

	public boolean sigueSerie(Serie serie) {
		if(this.user.getMisSeries().contains(serie))
			return false;
		this.modelo.sigueSerie(user,serie);
		this.user.setMisSeries(this.modelo.getUserSeries(this.user)); // Mantenemos actualizadas misSeries
		return true;
		
	}

	public ArrayList<Episodio> getEpisodiosSerie(Serie serie) {
		return this.modelo.getEpisodiosSerie(serie);
	}

	public void setEpisodioVisto(Episodio episodio) {
		this.modelo.setEpisodioVisto(episodio, this.user);
		
	}

	public boolean votarEpisodio(Episodio episodio, int value) {
		return this.modelo.votarEpisodio(episodio,value,user);
	}

	public void actualizaUsuario(Usuario user) {
		this.modelo.actualizaUsuario(user);
		this.user = user;
		
	}

	public boolean votarSerie(Serie serie, int value) {
		return this.modelo.votarSerie(serie,value,user);

	}

	public int encuentraVotoSerie(Serie serie) {
		return this.modelo.encuentraVotoSerie(serie, user);
	}

	public int encuentraVotoEpisodio(Episodio episodio) {
		return this.modelo.encuentraVotoEpisodio(episodio,user);
	}

	public String encuentraNotaSerie(Serie serie) {
		return this.modelo.encuentraMediaSerie(serie);
		
	}

	public String encuentraNotaEpisodio(Episodio episodio) {
		return this.modelo.encuentraMediaEpisodio(episodio);
	}

	public ArrayList<ActorSeriePersonaje> getActorSeriePersonajeEpisodio(
			Episodio episodio) {
		return this.modelo.getActorSeriePersonajeEpisodio(episodio);
	}

	public ArrayList<ComentarioEpisodio> getComentariosEpisodio(
			Episodio episodio) {
		return this.modelo.getComentariosEpisodio(episodio,user);
	}

	public void insertaComentarioEpisodio(Episodio episodio, String text) {
		ComentarioEpisodio comentario = new ComentarioEpisodio(user.getNick(), 
				episodio.getIdSerie(), 
				episodio.getIdEpisodio(),
				text,
				new Date());
		this.modelo.insertaComentarioEpisodio(comentario);
	}

	public ArrayList<ComentarioSerie> getComentariosSerie(Serie serie) {
		return this.modelo.getComentariosSerie(serie,user);
	}

	public void insertaComentarioSerie(Serie serie, String text) {
		ComentarioSerie comentario = new ComentarioSerie(user.getNick(), serie.getId(), text, new Date());
		this.modelo.insertaComentarioSerie(comentario);
	}

	public void actualizaVotoEpisodio(Episodio episodio, int value) {
		this.modelo.actualizaVotoEpisodio(episodio,value,user);		
	}

	public void actualizaVotoSerie(Serie serie, int value) {
		this.modelo.actualizaVotoSerie(serie,value,user);
	}

	public void dejarSeguirSerie(Serie serie) {
		this.modelo.dejarDeSeguirSerie(user, serie);
		
	}

	public void insertaSerie(Serie serie, ArrayList<Genero> generos) {
		this.modelo.insertaSerie(serie, generos);
	
	}

	public void updateSerie(Serie serie) {
		this.modelo.updateSerie(serie);
		
	}

	public void removeEpisodio(Episodio episodio) {
		this.modelo.removeEpisodio(episodio);
		
	}

	public void updateEpisodio(Episodio episodio) {
		this.modelo.updateEpisodio(episodio);
	}

	public void insertEpisodio(Episodio epi) {
		this.modelo.insertEpisodio(epi);
	}

	public void insertaPersonaje(Personaje personaje) {
		this.modelo.insertPersonaje(personaje);
		
	}

	public void insertaActor(Actor actor) {
		this.modelo.insertaActor(actor);
		
	}

	public void insertActorSeriePersonaje(ActorSeriePersonaje actSerPer) {
		this.modelo.insertActorSeriePersonaje(actSerPer);
		
	}

	public ArrayList<Personaje> listaPersonajes() {
		return this.modelo.getListaPersonajes();
	}
	public ArrayList<Personaje> listaPersonajes(String buscar) {
		return this.modelo.getListaPersonajes(buscar);
	}

	public ArrayList<Actor> listaActores() {
		return this.modelo.getListaActores();
	}

	public ArrayList<Actor> listaActores(String buscar) {
		return this.modelo.getListaActores(buscar);
	}

	public ArrayList<ActorSeriePersonaje> listaActSeriePers(Episodio episodio) {
		return this.modelo.getListaActSeriePers(episodio);
		
	}

	public void eliminaRelacion(ActorSeriePersonaje relacion) {
		this.modelo.eliminaRelacion(relacion);
		
	}

	public Serie getSerie(Serie s) {
		return this.modelo.getSerie(s);
		
	}

	public boolean getSeriePorNombreYFecha(String nombre, Date fEstreno) {
		return this.modelo.getSeriePorNombreYFecha(nombre,fEstreno);
	}

	public ArrayList<Genero> getGenerosSerie(Serie serie) {
		return this.modelo.getGenerosSerie(serie);
	}

	public void actualizarGenerosSerie(ArrayList<Genero> generos, Serie serie) {
		this.modelo.actualizaGenerosSerie(generos, serie);
	}

	public ArrayList<Actor> listaActoresNif(String string) {
		return this.modelo.getListaActoresPorNif(string);
	}

	
	
}
