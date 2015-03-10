package modelo.AppService;

import java.util.ArrayList;
import java.util.List;

import modelo.DAO.DAOEjercicio;
import modelo.DAO.DAORutina;
import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferEjercicio;
import modelo.transfer.TransferObject;
import modelo.transfer.TransferRutina;
import modelo.transfer.users.TransferEntrenador;

public class AppServiceRutina implements InterfaceAppService  
{
	
	private DAORutina daoRutina;
	private DAOEjercicio daoEjercicio;
	
	public AppServiceRutina()
	{
		this.daoEjercicio = new DAOEjercicio();
		this.daoRutina = new DAORutina();
	}
	
	/**
	 * Devuelve la lista de rutinas disponibles.
	 * @return List<TransferRutina> listRutinas - Rutinas disponibles.
	 */
	public List<TransferRutina> buscarRutinas()
	{
		List<TransferRutina> listRutinas = new ArrayList<TransferRutina>();
		List<TransferObject> listObjects = new ArrayList<TransferObject>();
		try {
			listObjects= this.daoRutina.buscarTodos();
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<listObjects.size(); i++){
			//Vamos recorriendo la lista de entrenadores, que estará en tipo TransferObject,
			//lo pasamos a TransferEntrenador, y lo guardamos en la lista de entrenadores de tipo TransferEntrenador.
			listRutinas.add((TransferRutina) listObjects.get(i));
		}
		
		return listRutinas;
	}

	/** 
	 * Elimina la rutina seleccionada.
	 * @param rutina - Rutina a eliminar.
	 */
	public boolean eliminarRutina(TransferRutina rutina) 
	{
		boolean success = false;
		try {
			this.daoRutina.borrar(rutina.getNombre());
			success = true;
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Busca todos aquellos ejercicios que esten en una rutina y los devuelve
	 * @param rutina  en la que buscar
	 * @return List<TransferEjercicio> lista de ejercicios de la rutina.
	 */
	public List<TransferEjercicio> buscarEjerciciosRutina(TransferRutina rutina) 
	{
		
		return null;
	}

	/**
	 * Busca todos los ejercicios que esten en la Base de Datos.
	 * @return List<TransferEjercicio> listEjercicios.
	 */
	public List<TransferEjercicio> buscarEjercicios() 
	{
		List<TransferEjercicio> listEjercicios = new ArrayList<TransferEjercicio>();
		List<TransferObject> listObjects = new ArrayList<TransferObject>();
		try {
			listObjects= this.daoEjercicio.buscarTodos();
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<listObjects.size(); i++){
			//Vamos recorriendo la lista de entrenadores, que estará en tipo TransferObject,
			//lo pasamos a TransferEntrenador, y lo guardamos en la lista de entrenadores de tipo TransferEntrenador.
			listEjercicios.add((TransferEjercicio) listObjects.get(i));
		}
		
		return listEjercicios;
	}

	public boolean insertarRutina(TransferRutina rutina) 
	{
		boolean success = false;
		try {
			this.daoRutina.inserta(rutina);
			TransferRutina rutinaAux = (TransferRutina) this.daoRutina.buscar(rutina.getNombre());
			this.daoEjercicio.insertaEjerciciosRutina(rutina.getEjercicios(), rutinaAux.getIdRutina());
			success = true;
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongDataFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

}
