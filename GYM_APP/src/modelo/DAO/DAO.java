package modelo.DAO;


import java.util.List;

import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferObject;



/**
 * 
 * @author Jes�s V�zquez
 *
 */
public abstract class DAO 
{
	
	protected DbConnector dbConnector;
	
	/**
	 * Constructor sin parametros.
	 */
	public DAO()
	{
		this.dbConnector = new DbConnector();
	}
	/**
	 * Inserta informacion en la base de datos.
	 * @param transferObject
	 */
	public abstract void inserta(TransferObject transferObject) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError;
	
	/**
	 * Borra informacion de la base de datos dado un identificador.
	 * @param transferObject
	 */
	public abstract void borrar(String identificador) throws ConnectionFailure, AlreadyExists; 
	
	/**
	 * Modifica informacion existente en la base de datos a partir de un objeto que contendra la informacion a actualizar.
	 * @param transferObject
	 */
	public abstract void modificar(TransferObject transferObject)  throws ConnectionFailure ;
	
	/**
	 * Busca informacion en la base de datos dado un identificador.
	 * @param identificador
	 */
	public abstract TransferObject buscar(String identificador) throws ConnectionFailure, DataBaseError;
	
	/**
	 * Busca informacion en la base de datos de todos los elementos disponibles del tipo deseado.
	 * @return ArrayList de todos los elementos de un tipo.
	 * @throws ConnectionFailure
	 * @throws DataBaseError
	 */
	public abstract List<TransferObject> buscarTodos() throws ConnectionFailure, DataBaseError;
	
	

}
