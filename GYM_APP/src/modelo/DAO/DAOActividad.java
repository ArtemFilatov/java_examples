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
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;




public class DAOActividad extends DAO
{
	// Inserts
	private final String QUERY_INSERT_ACTIVIDAD = "INSERT INTO servicios_table (ID_USR_MONITOR, DS_SERVICIO, HORA_INICIO, HORA_FIN) VALUES (?,?,?,?)";
	private final String QUERY_INSERT_SOCIO_ACTIVIDAD = "INSERT INTO usuarios_servicios_table (ID_USR, ID_SERVICIO) VALUES (?,?)";
	// Selects
	private final String QUERY_BUSCAR_ACTIVIDADES = "SELECT H.CD_HORARIO, H.ID_SERVICIO, S.ID_USR_MONITOR, S.DS_SERVICIO, S.HORA_INICIO, S.HORA_FIN , U.CORREO " +
													"FROM servicios_table S, " + 
													"servicios_horarios_table H , " +
													"usuarios_table U " +
													"WHERE H.ID_SERVICIO = S.ID_SERVICIO AND " + 
													"U.ID_USR = S.ID_USR_MONITOR AND " + 
													"CD_HORARIO = ";
	private final String QUERY_BUSCAR_ACTIVIDADES_SOCIOS = "SELECT S.ID_SERVICIO, T.ID_USR , U.DNI " +
														   "FROM servicios_table S, " +
														   "usuarios_table U, " +
														   "usuarios_servicios_table T " +
														   "WHERE T.ID_USR = U.ID_USR AND " +
														   "S.ID_SERVICIO = ";
	
	private final String QUERY_BUSCAR_ACTIVIDADES_NO_ASIGNADAS = "SELECT S.ID_SERVICIO, S.ID_USR_MONITOR, S.DS_SERVICIO, " +
																 "S.HORA_INICIO, S.HORA_FIN, U.CORREO FROM servicios_table S, " +
																 "usuarios_table U " +
																 "WHERE S.ID_SERVICIO NOT IN ( " +
																 "SELECT S.ID_SERVICIO FROM " + 
																 "servicios_table S, " +
																 "servicios_horarios_table U " +
																 "WHERE S.ID_SERVICIO = U.ID_SERVICIO) AND " +
																 "S.ID_USR_MONITOR = U.ID_USR";
	// Delete
	private final String QUERY_BORRAR_ACTIVIDAD = "DELETE FROM servicios_table WHERE ID_SERVICIO = ";
	private final String QUERY_ULTIMA_ACTIVIDAD = "SELECT MAX(ID_SERVICIO) AS ID_SERVICIO FROM servicios_table";
	
	private int idActividad;
	
	private int ultimaId;

	@Override
	public void inserta(TransferObject transferObject) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError 
	{
		TransferActividad actividad = (TransferActividad)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_ACTIVIDAD);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			DAOEntrenador daoEntrenador = new DAOEntrenador();
			TransferEntrenador entrenadorAux = (TransferEntrenador) daoEntrenador.buscar(actividad.getEntrenador().getCorreo());
			sentencia.setInt(1, daoEntrenador.getUsrId()); // ID_MONITOR_ACTIVIDAD
			sentencia.setString(2, actividad.getNombre()); // Nombre de la actividad
			sentencia.setString(3, actividad.getHoraInicio());
			sentencia.setString(4,  actividad.getHoraFin());
			// Falta añadir horario
			
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El usuario insertado ya existe (DNI o Correo Duplicado)", e.getCause());
		}	
		
	}

	@Override
	public void borrar(String idActividad) throws ConnectionFailure, AlreadyExists 
	{
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();
			
			sentencia.executeUpdate(QUERY_BORRAR_ACTIVIDAD + idActividad);
			
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
	public TransferObject buscar(String identificador)
			throws ConnectionFailure, DataBaseError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransferObject> buscarTodos() throws ConnectionFailure,	DataBaseError 
	{
		return null;
	}
	
	/**
	 * Busca todas aquellas actividades que se encuentren en un horario.
	 * @param cdHorario
	 * @return Devuelve todas las actividades de un horario.
	 * @throws ConnectionFailure
	 * @throws DataBaseError
	 */
	public List<TransferActividad> buscarTodos(int cdHorario) throws ConnectionFailure, DataBaseError
	{
		List<TransferActividad> listActividades = new ArrayList<TransferActividad>();
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexiï¿½n establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			TransferActividad actividad = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_ACTIVIDADES + cdHorario);
			while (resultado.next()) 
			{
				this.idActividad = resultado.getInt("ID_SERVICIO"); // ID Servicio
				DAOEntrenador daoEntrenador = new DAOEntrenador();
				TransferEntrenador entrenador = (TransferEntrenador) daoEntrenador.buscar(resultado.getString("CORREO")); // Entrenador
				actividad = new TransferActividad(this.idActividad,
						resultado.getString("DS_SERVICIO"), // Nombre de la actividad
						entrenador,
						null, // Lo seteamos despues
						resultado.getString("HORA_INICIO"), // Hora inicio de la actividad
						resultado.getString("HORA_FIN")); // Hora final de la acitividad
				List<TransferObject> listSocios = new ArrayList<TransferObject>();
				ResultSet r2;
				Statement sentencia2;
				sentencia2 = this.dbConnector.getConnection().createStatement();
				r2 = sentencia2.executeQuery(QUERY_BUSCAR_ACTIVIDADES_SOCIOS + actividad.getIdActividad());
				while(r2.next())
				{
					
					DAOSocio daoSocio = new DAOSocio();
					TransferSocio socio = (TransferSocio) daoSocio.buscar(r2.getString("DNI"));
					listSocios.add(socio);
				}
				actividad.setSocios(listSocios); // Lista de usuarios que estan en esa actividad
				// Falta entrenamiento y horario.
				listActividades.add(actividad);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listActividades;
	}
	
	/**
	 * Busca todas las actividades que no tengan un horario asignado.
	 * @return Devuelve todas las actividades que no hayan sido asignadas a un horario.
	 */
	public List<TransferActividad> buscarTodasSinHorario() throws ConnectionFailure, DataBaseError
	{
		List<TransferActividad> listActividades = new ArrayList<TransferActividad>();
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexiï¿½n establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			TransferActividad actividad = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_ACTIVIDADES_NO_ASIGNADAS);
			while (resultado.next()) 
			{
				this.idActividad = resultado.getInt("ID_SERVICIO"); // ID Servicio
				DAOEntrenador daoEntrenador = new DAOEntrenador();
				TransferEntrenador entrenador = (TransferEntrenador) daoEntrenador.buscar(resultado.getString("CORREO")); // Entrenador
				actividad = new TransferActividad(this.idActividad,
						resultado.getString("DS_SERVICIO"), // Nombre de la actividad
						entrenador,
						null, // Lo seteamos despues
						resultado.getString("HORA_INICIO"), // Hora inicio de la actividad
						resultado.getString("HORA_FIN")); // Hora final de la acitividad
				List<TransferObject> listSocios = new ArrayList<TransferObject>();
				ResultSet r2;
				Statement sentencia2;
				sentencia2 = this.dbConnector.getConnection().createStatement();
				r2 = sentencia2.executeQuery(QUERY_BUSCAR_ACTIVIDADES_SOCIOS + actividad.getIdActividad());
				while(r2.next())
				{
					
					DAOSocio daoSocio = new DAOSocio();
					TransferSocio socio = (TransferSocio) daoSocio.buscar(r2.getString("DNI"));
					listSocios.add(socio);
				}
				actividad.setSocios(listSocios); // Lista de usuarios que estan en esa actividad
				// Falta entrenamiento y horario.
				listActividades.add(actividad);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listActividades;
		
	}
	
	
	/**
	 * Inserta un socio en una actividad dado el codigo de la actividad.
	 * @param transferObject - El socio.
	 * @param cdActividad - Int Codigo de actividad.
	 */
	public void insertaSocioActividad(TransferObject transferObject, int cdActividad) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError
	{
		TransferSocio socio = (TransferSocio)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_SOCIO_ACTIVIDAD);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			DAOSocio daoSocio = new DAOSocio();
			socio = (TransferSocio) daoSocio.buscar(socio.getCorreo());
			sentencia.setInt(1, daoSocio.getUsrId()); // ID_USR
			sentencia.setInt(2, cdActividad); // ID_ACTIVIDAD
			
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El usuario insertado ya esta en la actividad)", e.getCause());
		}	
		
	}
	
	/**
	 * Inserta un arrayList de socios en una actividad dado su codigo de actividad.
	 * @param transferObject - ArrayList<TransferObject> ArrayList de socios.
	 * @param cdActividad - Int Codigo de actividad.
	 */
	public void insertaSociosActividad(ArrayList<TransferObject> transferObject, int cdActividad) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError
	{
		Iterator<TransferObject> it = transferObject.iterator();
		while(it.hasNext())
		{
			TransferObject aux = it.next();
			insertaSocioActividad(aux,cdActividad);
		}
		
	}
	
	/**
	 * Busca la ultima actividad insertada.
	 * @return Devuelve la ultima actividad insertada.
	 */
	public void buscarUltimaActividad() throws ConnectionFailure, DataBaseError
	{
		TransferActividad actividad = null;
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexiï¿½n establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			resultado = sentencia.executeQuery(QUERY_ULTIMA_ACTIVIDAD);
			if(resultado.next()) 
			{
				this.ultimaId = resultado.getInt("ID_SERVICIO"); // ID Servicio
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
	}
	
	public int getUltimaId() {
		return ultimaId;
	}

	public void setUltimaId(int ultimaId) {
		this.ultimaId = ultimaId;
	}
	
}


