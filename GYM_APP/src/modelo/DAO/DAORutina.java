package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferEjercicio;
import modelo.transfer.TransferObject;
import modelo.transfer.TransferRutina;

public class DAORutina extends DAO{

	private String QUERY_INSERT_RUTINA = "INSERT INTO rutinas_table (DS_RUTINA) VALUES (?)";
	private String QUERY_BORRAR_RUTINA = "DELETE FROM rutinas_table WHERE DS_RUTINA = ";
	private String QUERY_BUSCAR_RUTINA = "SELECT * FROM rutinas_table WHERE DS_RUTINA = ";
	private String QUERY_BUSCAR_RUTINAS = "SELECT * FROM rutinas_table WHERE DS_RUTINA IS NOT NULL";
	
	private int idRutina;
	
	@Override
	public void inserta(TransferObject transferObject)	throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError 
	{
		TransferRutina rutina = (TransferRutina)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_RUTINA);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setString(1, rutina.getNombre()); // NOMBRES
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("La rutina que estas creando ya existe.(Nombre de rutina duplicado)", e.getCause());
		}
	}

	@Override
	public void borrar(String nombreRutina) throws ConnectionFailure,	AlreadyExists 
	{
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();

			sentencia.executeUpdate(QUERY_BORRAR_RUTINA + "'" + nombreRutina + "'");
			
			sentencia.close(); // Cerramos el statement
			this.dbConnector.disconnectDb(); // Desconectamos
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(TransferObject transferObject)
			throws ConnectionFailure {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransferObject buscar(String nombreRutina) throws ConnectionFailure, DataBaseError 
	{
		TransferRutina rutina = null;
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
			resultado = sentencia.executeQuery(QUERY_BUSCAR_RUTINA + "'"+nombreRutina+"'");
			if(resultado.next()) 
			{
				this.idRutina = resultado.getInt("CD_RUTINA");
				rutina = new TransferRutina(resultado.getString("DS_RUTINA"));
				rutina.setIdRutina(this.idRutina);
				DAOEjercicio daoEjercicio = new DAOEjercicio();
				
				rutina.setEjercicios((ArrayList<TransferEjercicio>) daoEjercicio.buscarTodosDeUnaRutina(this.idRutina));
			}
			
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		
		return rutina;
	}

	@Override
	public List<TransferObject> buscarTodos() throws ConnectionFailure,	DataBaseError {
		List<TransferObject> listRutinas = new ArrayList<TransferObject>();
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
			DAOEjercicio daoEjercicio = new DAOEjercicio();
			TransferRutina rutina = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_RUTINAS);
			while (resultado.next()) 
			{
				this.idRutina = resultado.getInt("CD_RUTINA");
				rutina = new TransferRutina(resultado.getString("DS_RUTINA"));
				rutina.setIdRutina(this.idRutina);
				rutina.setEjercicios((ArrayList<TransferEjercicio>) daoEjercicio.buscarTodosDeUnaRutina(this.idRutina));
				listRutinas.add(rutina);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listRutinas;
	}

}
