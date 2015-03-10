package modelo.DAO;

import java.sql.Date;
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
import modelo.enums.Cuota;
import modelo.enums.Sueldo;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;




/**
 * 
 * @author Jesï¿½s Vï¿½zquez
 *
 */
public class DAOEntrenador extends DAO
{
	private final int ENTRENADOR_ROLL = 2; // Predefinido roll entrenador = 2.
	// Inserts
    private final String QUERY_INSERT_ENTRENADOR = "INSERT INTO usuarios_table (CD_ROLL, NOMBRE, APELLIDOS, PASSWORD, DNI, CORREO, CUENTABANCARIA, " +
    		"DIRECCION, TELEFONO) VALUES (?,?,?,?,?,?,?,?,?) ";
    private final String QUERY_INSERT_SUELDO_ENTRENADOR = "INSERT INTO sueldos_entrenadores_table (CD_SUELDO, ID_USR, FECHA_INICIO, FECHA_FIN) VALUES (?,?,?,?)";
    // Selects
    private final String QUERY_BUSCAR_ENTRENADOR = "SELECT * FROM usuarios_table WHERE CD_ROLL = "+ ENTRENADOR_ROLL + " AND CORREO = ";
    private final String QUERY_BUSCAR_ENTRENADORES = "SELECT * FROM usuarios_table WHERE CD_ROLL = "+ ENTRENADOR_ROLL;
    private final String QUERY_BUSCAR_SUELDO_ENTRENADOR = "SELECT CD_SUELDO from sueldos_entrenadores_table WHERE ID_USR = ";
    private final String QUERY_BUSCAR_FIN_SUELDO = "SELECT MAX(FECHA_FIN) AS FECHA_FIN FROM sueldos_entrenadores_table WHERE ID_USR = ";
    // Deletes
    private final String QUERY_BORRAR_ENTRENADOR = "DELETE FROM usuarios_table WHERE ID_USR = ";
    
	
    private int usrId;
	

	/**
	 * Constructor sin parï¿½metros.
	 */
	public DAOEntrenador()
	{
		this.dbConnector = new DbConnector();
	}
	
	public int getUsrId() {
		return usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	@Override
	public void inserta(TransferObject transferObject) throws ConnectionFailure, AlreadyExists, WrongDataFormat
	{
		TransferEntrenador entrenador = (TransferEntrenador)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexion establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_ENTRENADOR);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setInt(1, ENTRENADOR_ROLL); // CD_ROLL
			sentencia.setString(2, entrenador.getNombre()); // NOMBRES 
			sentencia.setString(3, entrenador.getApellidos()); // APELLIDOS
			sentencia.setString(4, entrenador.getContrasena()); // CONTRASENA
			if(entrenador.getDni() == null)
				throw new WrongDataFormat("El dni del socio no puede ser nulo");
			sentencia.setString(5, entrenador.getDni()); // DNI
			if(entrenador.getCorreo() == null)
				throw new WrongDataFormat("El dni del socio no puede ser nulo");
			sentencia.setString(6,  entrenador.getCorreo()); // CORREO
			sentencia.setInt(7,  entrenador.getCuenta()); // CUENTA BANCARIA
			sentencia.setString(8, entrenador.getDireccion()); // DIRECCIï¿½N
			sentencia.setInt(9, entrenador.getTelefono()); // TELEFONO
			
			sentencia.executeUpdate();
			
			// Ahora insertamos su sueldo y para ello necesitaremos el ID con el que se ha insertado nuestro usuario.
			ResultSet resultado;
			resultado = sentencia.executeQuery("SELECT ID_USR FROM usuarios_table WHERE DNI = '"+entrenador.getDni()+"'");
			while(resultado.next())
				this.usrId = resultado.getInt("ID_USR");
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_SUELDO_ENTRENADOR);
			sentencia.setInt(1, 1);
			sentencia.setInt(2,  this.usrId);
			sentencia.setDate(3, new Date(Sueldo.getFechaInicial().getTime()));
			sentencia.setDate(4, new Date((Sueldo.getFechaFinal(1).getTime())));	
			
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El usuario insertado ya existe (DNI o Correo Duplicado)", e.getCause());
		}	
	}

	@Override
	public void borrar(String correo) throws ConnectionFailure 
	{
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();
			TransferEntrenador entrenador= (TransferEntrenador) buscar(correo);
			sentencia.executeUpdate(QUERY_BORRAR_ENTRENADOR + this.usrId);
			
			sentencia.close(); // Cerramos el statement
			this.dbConnector.disconnectDb(); // Desconectamos
		} 
		catch (DataBaseError e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}

	@Override
	public void modificar(TransferObject transferObject) throws ConnectionFailure 
	{
		TransferEntrenador entrenador = (TransferEntrenador)transferObject;
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();
			sentencia.executeUpdate("UPDATE usuarios_table SET" +
					" NOMBRE = '" + entrenador.getNombre() +
					"', APELLIDOS = '" + entrenador.getApellidos() +
					"', DIRECCION = '" + entrenador.getDireccion() +
					"', TELEFONO = " + entrenador.getTelefono() +
					" WHERE DNI = '" + entrenador.getDni() +"'");
			sentencia.close(); // Cerramos el statement
			this.dbConnector.disconnectDb(); // Desconectamos
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}

	@Override
	public TransferObject buscar(String correo) throws ConnectionFailure, DataBaseError
	{
		TransferEntrenador entrenador = null;
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
			resultado = sentencia.executeQuery(QUERY_BUSCAR_ENTRENADOR + "'"+correo+"'");
			if(resultado.next()) 
			{
				this.usrId = resultado.getInt("ID_USR");
				entrenador = new TransferEntrenador(resultado.getString("NOMBRE"),
						resultado.getString("APELLIDOS"),
						resultado.getString("CORREO"),
						resultado.getString("PASSWORD"),
						resultado.getString("DNI"),
						resultado.getInt("CUENTABANCARIA"),
						null,
						resultado.getString("DIRECCION"),
						resultado.getInt("TELEFONO"));
				ResultSet r2;
				r2 = sentencia.executeQuery(QUERY_BUSCAR_SUELDO_ENTRENADOR + this.usrId);
				if(r2.next())
					entrenador.setSueldo(r2.getInt("CD_SUELDO"));
				if(entrenador.getSueldo() == null)
					entrenador.setSueldo(1);
			}
			
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		
		return entrenador;
		
	}

	@Override
	public List<TransferObject> buscarTodos() throws ConnectionFailure,	DataBaseError 
	{
		List<TransferObject> listEntrenadores = new ArrayList<TransferObject>();
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
			TransferEntrenador entrenador = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_ENTRENADORES);
			while (resultado.next()) 
			{
				this.usrId = resultado.getInt("ID_USR");
				entrenador = new TransferEntrenador(resultado.getString("NOMBRE"),
						resultado.getString("APELLIDOS"),
						resultado.getString("CORREO"),
						resultado.getString("PASSWORD"),
						resultado.getString("DNI"),
						resultado.getInt("CUENTABANCARIA"),
						null,
						resultado.getString("DIRECCION"),
						resultado.getInt("TELEFONO"));
				ResultSet r2;
				Statement sentencia2;
				sentencia2 = this.dbConnector.getConnection().createStatement();
				r2 = sentencia2.executeQuery(QUERY_BUSCAR_SUELDO_ENTRENADOR + this.usrId);
				if(r2.next())
					entrenador.setSueldo(r2.getInt("CD_SUELDO"));
				listEntrenadores.add(entrenador);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listEntrenadores;
	}

	public String obtenerFinSueldo(int idUsr) throws ConnectionFailure, DataBaseError 
	{
		String finCuota = "2000-01-01";
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
			String aux = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_FIN_SUELDO + idUsr);
			if (resultado.next()) 
			{
				 aux = resultado.getString("FECHA_FIN");
				 
				
			}
			if(aux != null)
				finCuota = aux;
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		return finCuota;
	}
	
	public void actualizarSueldo(int idUsuario, int cdSueldo)
	{
		PreparedStatement sentencia = null;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_SUELDO_ENTRENADOR);
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			sentencia.setInt(1, cdSueldo);
			sentencia.setInt(2,  idUsuario);
			sentencia.setDate(3, new Date(Sueldo.getFechaInicial().getTime()));
			sentencia.setDate(4, new Date((Sueldo.getFechaFinal(cdSueldo).getTime())));
			sentencia.executeUpdate();
			sentencia.close();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				throw new AlreadyExists("El usuario insertado ya existe (DNI Duplicado)", e.getCause());
			} catch (AlreadyExists e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
		
	}

}
