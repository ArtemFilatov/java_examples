package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferEjercicio;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferAdministrador;
import modelo.transfer.users.TransferSocio;

public class DAOEjercicio extends DAO
{
	
	private int idEjercicio;
	private String QUERY_INSERT_EJERCICIO = "INSERT INTO ejercicios_table (DS_EJERCICIO) VALUES (?)";
	private String QUERY_INSERT_EJERCICIO_RUTINA = "INSERT INTO ejercicios_rutinas_table (CD_RUTINA, CD_EJERCICIO, REPETICIONES) VALUES (?,?,?)";
	private String QUERY_BORRAR_EJERCICIO = "DELETE FROM ejercicios_table WHERE DS_EJERCICIO = ";
	private String QUERY_BUSCAR_EJERCICIO = "SELECT * FROM ejercicios_table WHERE DS_EJERCICIO = ";
	private String QUERY_BUSCAR_EJERCICIOS = "SELECT * FROM ejercicios_table WHERE DS_EJERCICIO IS NOT NULL";
	
	private String QUERY_BUSCAR_EJERCICIOS_RUTINA = "SELECT ER.CD_EJERCICIO, E.DS_EJERCICIO, ER.REPETICIONES " + 
													"FROM   ejercicios_rutinas_table ER, " +
													"ejercicios_table E " +
													"WHERE  ER.CD_EJERCICIO = E.CD_EJERCICIO AND " +
													"ER.CD_RUTINA = ";
	
	@Override
	public void inserta(TransferObject transferObject)	throws ConnectionFailure, WrongDataFormat, AlreadyExists,DataBaseError {
		TransferEjercicio ejercicio = (TransferEjercicio)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_EJERCICIO);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setString(1, ejercicio.getNombre()); // NOMBRES 
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El ejercicio insertado ya existe (Nombre de ejercicio Duplicado)", e.getCause());
		}
		
	}

	@Override
	public void borrar(String nombreEjercicio) throws ConnectionFailure, AlreadyExists 
	{
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();

			sentencia.executeUpdate(QUERY_BORRAR_EJERCICIO + nombreEjercicio);
			
			sentencia.close(); // Cerramos el statement
			this.dbConnector.disconnectDb(); // Desconectamos
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
		
	}

	@Override
	public void modificar(TransferObject transferObject)
			throws ConnectionFailure {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransferObject buscar(String nombreEjercicio)	throws ConnectionFailure, DataBaseError 
	{
		TransferEjercicio ejercicio = null;
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_EJERCICIO + "'"+nombreEjercicio+"'");
			if(resultado.next()) 
			{
				this.idEjercicio = resultado.getInt("CD_EJERCICIO");
				ejercicio = new TransferEjercicio(resultado.getString("DS_EJERCICIO"));
				ejercicio.setIdEjercicio(this.idEjercicio);
			}
			
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		
		return ejercicio;
	}

	@Override
	public List<TransferObject> buscarTodos() throws ConnectionFailure,	DataBaseError 
	{
		List<TransferObject> listEjercicios = new ArrayList<TransferObject>();
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			TransferEjercicio ejercicio = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_EJERCICIOS);
			while (resultado.next()) 
			{
				this.idEjercicio = resultado.getInt("CD_EJERCICIO");
				ejercicio = new TransferEjercicio(resultado.getString("DS_EJERCICIO"));
				ejercicio.setIdEjercicio(this.idEjercicio);
				listEjercicios.add(ejercicio);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listEjercicios;
	}
	
	/**
	 * Busca todos los ejercicios pertenecientes a una rutina.
	 * @param codigoRutina - CD_RUTINA
	 * @return listEjercicios - Lista de ejercicios de una rutina.
	 */
	public List<TransferEjercicio> buscarTodosDeUnaRutina(int codigoRutina) throws ConnectionFailure,DataBaseError 
	{
		List<TransferEjercicio> listEjercicios = new ArrayList<TransferEjercicio>();
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			TransferEjercicio ejercicio = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_EJERCICIOS_RUTINA + codigoRutina);
			while (resultado.next()) 
			{
				this.idEjercicio = resultado.getInt("CD_EJERCICIO");
				ejercicio = new TransferEjercicio(resultado.getString("DS_EJERCICIO"), resultado.getString("REPETICIONES"));
				ejercicio.setIdEjercicio(this.idEjercicio);
				listEjercicios.add(ejercicio);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listEjercicios;
	}
	
	public void insertaEjercicioRutina(TransferEjercicio ejercicio, int codigoRutina)	throws ConnectionFailure, WrongDataFormat, AlreadyExists,DataBaseError {
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_EJERCICIO_RUTINA);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setInt(1, codigoRutina); // CD_RUTINA
			sentencia.setInt(2,  ejercicio.getIdEjercicio()); // CD_EJERCICIO.
			sentencia.setString(3, ejercicio.getRepeticiones()); // REPETICIONES
			
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El ejercicio insertado ya existe (Nombre de ejercicio Duplicado)", e.getCause());
		}
		
	}
	
	/**
	 * Inserta un arraylist de ejercicios en una rutina.
	 * @param ejercicios
	 * @param codigoRutina
	 */
	public void insertaEjerciciosRutina(ArrayList<TransferEjercicio> ejercicios, int codigoRutina)
	{
		Iterator<TransferEjercicio> it = ejercicios.iterator();
		while(it.hasNext())
		{
			TransferEjercicio aux = it.next();
			try {
				insertaEjercicioRutina(aux,codigoRutina);
			} catch (ConnectionFailure | WrongDataFormat | AlreadyExists
					| DataBaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
