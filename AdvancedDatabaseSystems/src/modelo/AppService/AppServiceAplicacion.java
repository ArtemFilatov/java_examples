package modelo.AppService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import modelo.DBMapper.ActorMapper;
import modelo.DBMapper.ActorSeriePersonajeMapper;
import modelo.DBMapper.ComentarioEpisodioMapper;
import modelo.DBMapper.ComentarioSerieMapper;
import modelo.DBMapper.EpisodioMapper;
import modelo.DBMapper.GeneroMapper;
import modelo.DBMapper.Operator;
import modelo.DBMapper.PersonajeMapper;
import modelo.DBMapper.QueryCondition;
import modelo.DBMapper.SerieGeneroMapper;
import modelo.DBMapper.SerieMapper;
import modelo.DBMapper.UsuarioEpisodioMapper;
import modelo.DBMapper.UsuarioMapper;
import modelo.DBMapper.UsuarioSerieMapper;
import modelo.DBMapper.ValoracionEpisodioMapper;
import modelo.DBMapper.ValoracionSerieMapper;
import modelo.Serie.Actor;
import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.EpisodioKey;
import modelo.Serie.Genero;
import modelo.Serie.Personaje;
import modelo.Serie.Serie;
import modelo.Serie.SerieGenero;
import modelo.Usuario.ComentarioEpisodio;
import modelo.Usuario.ComentarioSerie;
import modelo.Usuario.Usuario;
import modelo.Usuario.UsuarioEpisodio;
import modelo.Usuario.UsuarioSerie;
import modelo.Usuario.ValoracionEpisodio;
import modelo.Usuario.ValoracionEpisodioKey;
import modelo.Usuario.ValoracionSerie;
import modelo.Usuario.ValoracionSerieKey;

/**
 * Patron de arquitectura AppService para el acceso a la capa de base de datos. Clase principal.
 */
public class AppServiceAplicacion {
	/* Referencia a todos los Mapper */
	ActorMapper actorMapper;
	ActorSeriePersonajeMapper actorSeriePersonajeMapper;
	ComentarioEpisodioMapper comentarioEpisodioMapper;
	ComentarioSerieMapper comentarioSerieMapper;
	EpisodioMapper episodioMapper;
	GeneroMapper generoMapper;
	PersonajeMapper personajeMapper;
	SerieMapper serieMapper;
	SerieGeneroMapper serieGeneroMapper;
	UsuarioMapper usuarioMapper;
	UsuarioEpisodioMapper usuarioEpisodioMapper;
	UsuarioSerieMapper usuarioSerieMapper;
	ValoracionEpisodioMapper valoracionEpisodioMapper;
	ValoracionSerieMapper valoracionSerieMapper;
	
	public AppServiceAplicacion(DataSource ds) {
		this.actorMapper = new ActorMapper(ds);
		this.actorSeriePersonajeMapper = new ActorSeriePersonajeMapper(ds);
		this.comentarioEpisodioMapper = new ComentarioEpisodioMapper(ds);
		this.comentarioSerieMapper = new ComentarioSerieMapper(ds);
		this.episodioMapper = new EpisodioMapper(ds);
		this.generoMapper = new GeneroMapper(ds);
		this.personajeMapper = new PersonajeMapper(ds);
		this.serieMapper = new SerieMapper(ds);
		this.serieGeneroMapper = new SerieGeneroMapper(ds);
		this.usuarioMapper = new UsuarioMapper(ds);
		this.usuarioEpisodioMapper = new UsuarioEpisodioMapper(ds);
		this.usuarioSerieMapper = new UsuarioSerieMapper(ds);
		this.valoracionEpisodioMapper = new ValoracionEpisodioMapper(ds);
		this.valoracionSerieMapper = new ValoracionSerieMapper(ds);
	}

	//////////////////// METHODS FOR SERIES
	/**
	 * Devuelve todas las series que se encuentren en la base de datos.
	 * @return String[]- Array que contiene los nombres de todas las series de la base de datos.
	 */
	public List<Serie> getAllSeries() {
		List<Serie> l = new ArrayList<Serie>();
		l = this.serieMapper.findAll();
		return l;
	}
	
	/**
	 * Devuelve true si encuentra una serie que coincida con la tupla NOMBRE,FECHA_ESTRENO (UNICA EN LA BD) 
	 * @param nombre
	 * @param fEstreno
	 * @return
	 */
	public boolean getSeriePorNombreYFecha(String nombre, Date fEstreno) {
		QueryCondition c1 = new QueryCondition("NOMBRE", Operator.EQUAL, nombre);
		QueryCondition c2 = new QueryCondition("FECHA_ESTRENO", Operator.EQUAL, fEstreno);
		QueryCondition[] conditions = {c1,c2};
		List<Serie> l = this.serieMapper.findByConditions(conditions);
		Iterator<Serie> it = l.listIterator();
		Serie s = null;
		if(it.hasNext())
			s = it.next(); 
		if(s != null) return true;
		return false;
	}
	
	/**
	 * Devuelve un conjunto de series cuyo nombre contenga la cadena recibida por el parametro
	 * @param text - String a comparar con el nombre de todas las series
	 * @return String[] - Array de nombres de series.
	 */
	public List<Serie> getSeriesByName(String text) {
		List<Serie> l = new ArrayList<Serie>();
		QueryCondition condition = new QueryCondition("NOMBRE", Operator.LIKE, text);
		QueryCondition[] conditions = {condition};
		l = this.serieMapper.findByConditions(conditions);
		return l;
	}

	/**
	 * Inserta una serie en la base de datos y despues sus generos.
	 * @param serie
	 * @param generos
	 */
	public void insertaSerie(Serie serie, ArrayList<Genero> generos) {
		this.serieMapper.insert(serie);
		QueryCondition c1 = new QueryCondition("NOMBRE", Operator.EQUAL, serie.getNombre());
	    String dateString = new SimpleDateFormat("yyyy-MM-dd").format(serie.getfEstreno());
		QueryCondition c2 = new QueryCondition("FECHA_ESTRENO", Operator.EQUAL, dateString);
		QueryCondition[] conditions = {c1,c2}; // TUPLA UNICA
		List<Serie> l = this.serieMapper.findByConditions(conditions);  // Necesitamos la ID
		Iterator<Serie> itL = l.listIterator();
		Serie s = null;
		if(itL.hasNext())
			s = itL.next(); 
		if(s != null){
			this.actualizaGenerosSerie(generos, s);
		}
	}
	
	/**
	 * Actualiza una serie
	 * @param serie
	 */
	public void updateSerie(Serie serie) {
		this.serieMapper.update(serie);
		
	}
	
	public Serie getSerie(Serie s) {
		
		QueryCondition condition = new QueryCondition("NOMBRE", Operator.LIKE, s.getNombre());
		QueryCondition[] conditions = {condition};
		Serie s1 = this.serieMapper.findByConditions(conditions).get(0);
		return s1;
	}
	
	/////////////////// METHODS FOR GENEROS
	
	/**
	 * Obtiene los generos de una serie.
	 * @param s - Serie s
	 * @return ArrayList<Genero> 
	 */
	public ArrayList<Genero> getGenerosSerie(Serie s) {
		ArrayList<Genero> generos = new ArrayList<Genero>();
		QueryCondition condition = new QueryCondition("ID_SERIE", Operator.EQUAL, s.getId());
		QueryCondition[] conditions = {condition};
		List<SerieGenero> list = this.serieGeneroMapper.findByConditions(conditions);
		Iterator<SerieGenero> it = list.listIterator();
		while(it.hasNext()) {
			generos.add(it.next().getGenero());
		}		
		return generos;
	}
	
	/**
	 * Elimina todos los generos pertenecientes a una serie de la base de datos.
	 * @param s
	 */
	public void eliminaGenerosSerie(Serie s) {
		this.serieGeneroMapper.eliminaGenerosSerie(s);
	}
	
	public void actualizaGenerosSerie(ArrayList<Genero> generos, Serie s) {
		this.eliminaGenerosSerie(s);
		SerieGenero aux;
		Iterator<Genero> it = generos.iterator();
		while(it.hasNext()) {
			aux = new SerieGenero(s.getId(), it.next());
			this.serieGeneroMapper.insert(aux);
		}
		
	}
	
	/////////////////// METHODS FOR USERS
	
	/**
	 * Devuelve un usuario de la base de datos.
	 * @param usuario - String usuario (Contiene el identificador del usuario en la BD)
	 * @return Usuario - usuario. El usuario encontrado.
	 */
	public Usuario encuentraUsuario(String usuario) {
		return this.usuarioMapper.findById(usuario);
	}
	
	/**
	 * Inserta un usuario en la base de datos. 
	 * @param usuario
	 */
	public void insertaUsuario(Usuario usuario) {
		this.usuarioMapper.insert(usuario);
	}
	
	/**
	 * Actualiza un usuario en la base de datos.
	 * @param user
	 */
	public void actualizaUsuario(Usuario user) {
		this.usuarioMapper.update(user);
	}
	
	public ArrayList<Serie> getUserSeries(Usuario user) {
		ArrayList<Serie> series = new ArrayList<Serie>();
		QueryCondition condicion = new QueryCondition("NICK", Operator.EQUAL, user.getNick());
		QueryCondition[] conditions = new QueryCondition[]{condicion};
		List<UsuarioSerie> ids = this.usuarioSerieMapper.findByConditions(conditions);
		Iterator<UsuarioSerie> it = ids.listIterator();
		while(it.hasNext()) {
			series.add(this.serieMapper.findById(it.next().getIdSerie()));
		}
		return series;
	}

	public ArrayList<Episodio> buscaEpisodiosNoVistos(Serie serie, Usuario user) {
		ArrayList<Integer> ids = this.usuarioMapper.misEpisodiosPendientes(serie,user);
		ArrayList<Episodio> episodios = new ArrayList<Episodio>();
		int idSerie = serie.getId();
		EpisodioKey aux;
		Iterator<Integer> it = ids.iterator();
		while(it.hasNext()) {
			aux = new EpisodioKey(idSerie,it.next());
			episodios.add(this.episodioMapper.findById(aux));
		}
		return episodios;
	}

	public void sigueSerie(Usuario user, Serie serie) {
		UsuarioSerie us = new UsuarioSerie(user.getNick(), serie.getId());
		this.usuarioSerieMapper.insert(us);
	}
	
	public void dejarDeSeguirSerie(Usuario user, Serie serie) {
		UsuarioSerie us = new UsuarioSerie(user.getNick(), serie.getId());
		this.usuarioSerieMapper.delete(us);
	}

	/////////////////// METHODS FOR CHAPTERS
	
	public ArrayList<Episodio> getEpisodiosSerie(Serie serie) {
		QueryCondition condicion = new QueryCondition("ID_SERIE", Operator.EQUAL, serie.getId());
		QueryCondition[] conditions = new QueryCondition[] {condicion};
		ArrayList<Episodio> episodios = new ArrayList<Episodio>();
		List<Episodio> l = this.episodioMapper.findByConditions(conditions);
		Iterator<Episodio> it = l.listIterator();
		while(it.hasNext()) {
			episodios.add(it.next());
		}
		return episodios; 
	}

	public void setEpisodioVisto(Episodio episodio, Usuario user) {
		UsuarioEpisodio ue = new UsuarioEpisodio(user.getNick(), 
				episodio.getIdSerie(), 
				episodio.getIdEpisodio());
		this.usuarioEpisodioMapper.insert(ue);
		
	}
	
	public void removeEpisodio(Episodio episodio) {
		this.episodioMapper.delete(episodio);
	}
	
	public void updateEpisodio(Episodio episodio) {
		this.episodioMapper.update(episodio);
	}
	
	public void insertEpisodio(Episodio epi) {
		this.episodioMapper.insert(epi);
	}
	
	///////////////////////////// METHODS FOR VOTES
	
	public int encuentraVotoSerie(Serie serie, Usuario user) {
		ValoracionSerieKey valKey = new ValoracionSerieKey(user.getNick(),serie.getId());
		ValoracionSerie val = this.valoracionSerieMapper.findById(valKey);
		if(val != null)
			return val.getNota();
		return -1;
	}

	public boolean votarSerie(Serie serie, int nota, Usuario user) {
		ValoracionSerie val = new ValoracionSerie(user.getNick(),serie.getId(),nota);
		this.valoracionSerieMapper.insert(val); // TODO: Cambiar insert a boolean para ver si el retorno es correcto
		return true;
	}
	
	public String encuentraMediaSerie(Serie serie) {
		return String.valueOf(this.valoracionSerieMapper.findAverage(serie));
	}

	public int encuentraVotoEpisodio(Episodio episodio, Usuario user) {
		ValoracionEpisodioKey valKey = new ValoracionEpisodioKey(user.getNick(),episodio.getIdSerie(), episodio.getIdEpisodio());
		ValoracionEpisodio val = this.valoracionEpisodioMapper.findById(valKey);
		if(val != null)
			return val.getNota();
		return -1;
	}
	
	public boolean votarEpisodio(Episodio episodio, int value, Usuario user) {
		ValoracionEpisodio val = new ValoracionEpisodio(user.getNick(),  episodio.getIdSerie(), episodio.getIdEpisodio(), value);
		this.valoracionEpisodioMapper.insert(val);  // TODO: Cambiar insert a boolean para ver si el retorno es correcto
		return true;
	}
	
	public String encuentraMediaEpisodio(Episodio episodio) {
		return String.valueOf(this.valoracionEpisodioMapper.findAverage(episodio));
	}

	public ArrayList<ActorSeriePersonaje> getActorSeriePersonajeEpisodio(
			Episodio episodio) {
		ArrayList<ActorSeriePersonaje> arrayList = this.actorSeriePersonajeMapper.findForEpisode(episodio);
		for(int i = 0; i < arrayList.size(); i++) {
			arrayList.get(i).setActor(this.actorMapper.findById(arrayList.get(i).getNif()));
			arrayList.get(i).setPersonaje(this.personajeMapper.findById(arrayList.get(i).getNombrePersonaje()));
		}
		return arrayList;
	}

	public void actualizaVotoEpisodio(Episodio episodio, int value, Usuario user) {
		ValoracionEpisodio val = new ValoracionEpisodio(user.getNick(), episodio.getIdSerie(), episodio.getIdEpisodio(), value);
		this.valoracionEpisodioMapper.update(val);
	}

	public void actualizaVotoSerie(Serie serie, int value, Usuario user) {
		ValoracionSerie val = new ValoracionSerie(user.getNick(),serie.getId(), value);
		this.valoracionSerieMapper.update(val);
		
	}
	
	/////////////////////////////// METHODS FOR COMMENTS 
	
	public ArrayList<ComentarioEpisodio> getComentariosEpisodio(
			Episodio episodio, Usuario user) {
		return this.comentarioEpisodioMapper.findComentariosOrdenados(episodio, user);
	}
	
	public void insertaComentarioEpisodio(ComentarioEpisodio comentario) {
		this.comentarioEpisodioMapper.insert(comentario);
	}

	public ArrayList<ComentarioSerie> getComentariosSerie(Serie serie,
			Usuario user) {
		return this.comentarioSerieMapper.findComentariosOrdenados(serie, user);
	}

	public void insertaComentarioSerie(ComentarioSerie comentario) {
		this.comentarioSerieMapper.insert(comentario);
		
	}

	
	////////////////METHODS FOR CHARACTERS

	public void insertPersonaje(Personaje personaje) {
		this.personajeMapper.insert(personaje);
	}


	
	
	public ArrayList<Personaje> getListaPersonajes() {
		List<Personaje> aux = new ArrayList<Personaje>();
		aux = this.personajeMapper.findAll();
		ArrayList<Personaje> l = new ArrayList<Personaje>(); 
		
		Iterator<Personaje> it = aux.listIterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	}
	
	
	public ArrayList<Personaje> getListaPersonajes(String buscar) {
		ArrayList<Personaje> l = new ArrayList<Personaje>();
		QueryCondition condition = new QueryCondition("NOMBRE_PERSONAJE", Operator.LIKE, buscar);
		QueryCondition[] conditions = {condition};	
		List<Personaje> aux = this.personajeMapper.findByConditions(conditions);
		Iterator<Personaje> it = aux.listIterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	}
	
	////////////////METHODS FOR ACTORS
	public void insertaActor(Actor actor) {
		this.actorMapper.insert(actor);
		
	}
	
	public ArrayList<Actor> getListaActores() {
		List<Actor> aux = new ArrayList<Actor>();
		aux = this.actorMapper.findAll();
		ArrayList<Actor> l = new ArrayList<Actor>(); 
		
		Iterator<Actor> it = aux.listIterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	
	}
		
	public ArrayList<Actor> getListaActores(String buscar) {
		ArrayList<Actor> l = new ArrayList<Actor>();
		QueryCondition condition = new QueryCondition("NOMBRE", Operator.LIKE, buscar);
		QueryCondition[] conditions = {condition};
		List<Actor> aux = this.actorMapper.findByConditions(conditions);
		Iterator<Actor> it = aux.listIterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	}
	
	
	public ArrayList<Actor> getListaActoresPorNif(String buscar) {
		ArrayList<Actor> l = new ArrayList<Actor>();
		QueryCondition condition = new QueryCondition("NIF", Operator.LIKE, buscar);
		QueryCondition[] conditions = {condition};
		List<Actor> aux = this.actorMapper.findByConditions(conditions);
		Iterator<Actor> it = aux.listIterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	}
	
	
	/////////////////////////////// METHODS FOR ACTOR SERIE CHARACTER
	public void insertActorSeriePersonaje(ActorSeriePersonaje actSerPer) {
		this.actorSeriePersonajeMapper.insert(actSerPer);
	}
	
	public ArrayList<ActorSeriePersonaje> getListaActSeriePers(Episodio episodio) {
			
		ArrayList<ActorSeriePersonaje> l = new ArrayList<ActorSeriePersonaje>();
		QueryCondition condition = new QueryCondition("ID_EPISODIO", Operator.LIKE, episodio.getIdEpisodio());
		QueryCondition[] conditions = {condition};
		List<ActorSeriePersonaje> aux = this.actorSeriePersonajeMapper.findByConditions(conditions);
		Iterator<ActorSeriePersonaje> it = aux.listIterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
		
		
	}

	public void eliminaRelacion(ActorSeriePersonaje relacion) {
		this.actorSeriePersonajeMapper.delete(relacion);
		
	}

	

	
	

}
