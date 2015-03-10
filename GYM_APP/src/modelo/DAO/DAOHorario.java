package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;




public class DAOHorario extends DAO {
	
	private final String QUERY_INSERT_HORARIO = "INSERT INTO horarios_table (ID_USR, DIA) VALUES (?,?)";
	private final String QUERY_BORRAR_HORARIO = "DELETE FROM horarios_table WHERE CD_HORARIO =";
	private final String QUERY_BUSCAR_HORARIO_OFICIAL = "SELECT * FROM horarios_table WHERE ID_USR = 0 AND DIA = ";
	public int getCdHorario() {
		return cdHorario;
	}

	public void setCdHorario(int cdHorario) {
		this.cdHorario = cdHorario;
	}

	private final String QUERY_BUSCAR_HORARIO_USUARIO = "SELECT * FROM horarios_table WHERE ID_USR = "; // falta el dia
	private final String QUERY_INSERTAR_ACTIVIDADES_HORARIO = "INSERT INTO servicios_horarios_table (ID_SERVICIO, CD_HORARIO) VALUES (?,?)";

	private int cdHorario; 
	
	/**
	 * Inserta un horario del sistema.
	 */
	public void inserta(TransferObject transferObject) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError 
	{
		TransferHorario horario = (TransferHorario)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_HORARIO);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setInt(1, 0); // ID_USR = 0 (Propio Gym)
			sentencia.setString(2, horario.getFecha()); // Dia
			
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El sistema ya tiene un horario para esa fecha", e.getCause());
		}	
	}

	@Override
	public void borrar(String idHorario) throws ConnectionFailure
	{
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();
			sentencia.executeUpdate(QUERY_BORRAR_HORARIO + idHorario);
			
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
	public TransferObject buscar(String fecha) throws ConnectionFailure, DataBaseError 
	{
		return null;
	}
	
	@Override
	public List<TransferObject> buscarTodos() throws ConnectionFailure,	DataBaseError {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Busca el horario del gimnasio del dia indicado.
	 * @param String fecha
	 * @return Horario oficial del gimnasio correspondiente a la fecha indicada.
	 */
	public TransferObject buscarHorarioOficial(String fecha) throws ConnectionFailure, DataBaseError 
	{
		TransferHorario horario = null;
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_HORARIO_OFICIAL + "'" + fecha + "'");
			if(resultado.next()) 
			{
				this.cdHorario = resultado.getInt("CD_HORARIO");
				horario = new TransferHorario(resultado.getString("DIA"), // Fecha del horario
						null); // Lo seteamos despues.
				
				// Buscamos las actividades del horario.
				DAOActividad daoActividad = new DAOActividad();
				horario.setActividades((ArrayList<TransferActividad>) daoActividad.buscarTodos(this.cdHorario));
				
			}
			horario.setCdHorario(this.cdHorario);
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		return horario;
	}
	
	/**
	 * Busca el horario del socio para el dia indicado.
	 * @param String fecha
	 * @param correoSocio 
	 * @return Horario del socio correspondiente al dia indicado.
	 */
	public TransferObject buscarHorarioSocio(String fecha, String correoSocio) throws ConnectionFailure, DataBaseError 
	{
		TransferHorario horario = null;
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			DAOSocio daoSocio = new DAOSocio();
			TransferSocio socio = (TransferSocio) daoSocio.buscar(correoSocio);
			resultado = sentencia.executeQuery(QUERY_BUSCAR_HORARIO_USUARIO + daoSocio.getUsrId() +  " AND DIA = '" + fecha + "'");
			if(resultado.next()) 
			{
				this.cdHorario = resultado.getInt("CD_HORARIO");
				horario = new TransferHorario(resultado.getString("DIA"), // Fecha del horario
						null); // Lo seteamos despues.
				horario.setCdHorario(this.cdHorario);
				// Buscamos las actividades del horario.
				DAOActividad daoActividad = new DAOActividad();
				horario.setActividades((ArrayList<TransferActividad>) daoActividad.buscarTodos(this.cdHorario));
				
			}
			
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		return horario;
	}
	
	/**
	 * Busca el horario del entrenador para el dia indicado.
	 * @param String fecha
	 * @param correo
	 * @return Horario del entrenador correspondiente ald ai indicado.
	 */
	public TransferObject buscarHorarioEntrenador(String fecha, String correo) throws ConnectionFailure, DataBaseError 
	{
		TransferHorario horario = null;
		Statement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			
		}
		try 
		{
			ResultSet resultado;
			DAOEntrenador daoEntrenador = new DAOEntrenador();
			TransferEntrenador entrenador = (TransferEntrenador) daoEntrenador.buscar(correo);
			resultado = sentencia.executeQuery(QUERY_BUSCAR_HORARIO_USUARIO + daoEntrenador.getUsrId() +  " AND DIA = '" + fecha + "'");
			if(resultado.next()) 
			{
				this.cdHorario = resultado.getInt("CD_HORARIO");
				horario = new TransferHorario(resultado.getString("DIA"), // Fecha del horario
						null); // Lo seteamos despues.
				horario.setCdHorario(this.cdHorario);
				// Buscamos las actividades del horario.
				DAOActividad daoActividad = new DAOActividad();
				horario.setActividades((ArrayList<TransferActividad>) daoActividad.buscarTodos(this.cdHorario));
				
			}
			
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		return horario;
	}
	
	/**
	 * Inserta el horario de un socio dado su correo para el dia actual.
	 * @param correoSocio
	 */
	public void insertaHorarioSocio(String correoSocio) throws ConnectionFailure, AlreadyExists, WrongDataFormat, DataBaseError
	{
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_HORARIO);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			Date date=new Date();
			SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
			String fecha = formato.format(date);
			DAOSocio daoSocio  = new DAOSocio();
			TransferSocio socio = (TransferSocio) daoSocio.buscar(correoSocio);
			sentencia.setInt(1, daoSocio.getUsrId());
			sentencia.setString(2, fecha); // Dia
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El socio ya tiene un horario para esa fecha", e.getCause());
		}	
		
	}
	
	/**
	 * Inserta el horario de un entrenador dado su dni.
	 * @param fecha
	 * @param correoEntrenador
	 */
	public void insertaHorarioEntrenador(TransferHorario horario, String correoEntrenador) throws ConnectionFailure, DataBaseError, AlreadyExists
	{
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_HORARIO);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			DAOEntrenador daoEntrenador  = new DAOEntrenador();
			TransferEntrenador entrenador = (TransferEntrenador) daoEntrenador.buscar(correoEntrenador);
			sentencia.setInt(1, daoEntrenador.getUsrId()); // ID_USR
			sentencia.setString(2, horario.getFecha()); // Dia
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El entrenador ya tiene un horario para esa fecha.", e.getCause());
		}	
		
	}
	
	/**
	 * Inserta una actividad en un horario dado su id.
	 * @param idActividad 
	 * @param cdHorario
	 */
	public void insertaActividadHorario(int cdHorario, int idActividad) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError
	{
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERTAR_ACTIVIDADES_HORARIO);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setInt(1, idActividad); // ID_SERVICIO
			sentencia.setInt(2, cdHorario); // CD_HORARIO
			
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("Error intentando asignarle actividades al horario.", e.getCause());
		}	
	}
	
	/**
	 * Inserta un conjunto de actividades en un horario dado su id.
	 * @param listActividades - ArrayList<TransferActividad>
	 * @param cdHorario
	 */
	public void insertaActividadesHorario(ArrayList<TransferActividad> listActividades, int cdHorario) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError
	{
		Iterator<TransferActividad> it = listActividades.iterator();
		while(it.hasNext())
		{
			TransferActividad aux = it.next();
			insertaActividadHorario(aux.getIdActividad(),cdHorario);
		}
	}

	/**
	 * Devuelve las actividades asignadas a un horario
	 * @param cdHorario
	 * @throws DataBaseError 
	 * @throws ConnectionFailure 
	 */
	public ArrayList<TransferActividad> buscarActividades(int cdHorario) throws ConnectionFailure, DataBaseError
	{
		DAOActividad daoActividad = new DAOActividad();
		ArrayList<TransferActividad> listActividades = (ArrayList<TransferActividad>) daoActividad.buscarTodos(cdHorario);
		
		return listActividades;
		
	}
}
