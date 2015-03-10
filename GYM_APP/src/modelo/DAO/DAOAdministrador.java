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
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferAdministrador;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;




/**
 * 
 * @author Jes�s V�zquez
 *
 */
public class DAOAdministrador extends DAO 
{
	private final int ADMINISTRADOR_ROLL = 3; // Predefinido roll administrador 3
	// Inserts
	private final String QUERY_INSERT_ADMINISTRADOR = "INSERT INTO usuarios_table (CD_ROLL, NOMBRE, APELLIDOS, PASSWORD, DNI, CORREO) VALUES (?,?,?,?,?,?) ";
    // Selects
    private final String QUERY_BUSCAR_ADMINISTRADOR = "SELECT * FROM usuarios_table WHERE CD_ROLL = "+ ADMINISTRADOR_ROLL + " AND CORREO = ";
    private final String QUERY_BUSCAR_ADMINISTRADORES = "SELECT * FROM usuarios_table WHERE CD_ROLL = "+ ADMINISTRADOR_ROLL;
    // Deletes
    private final String QUERY_BORRAR_ADMINISTRADOR = "DELETE FROM usuarios_table WHERE ID_USR = ";
    
    private int usrId;
	
	/**
	 * Constructor sin par�metros.
	 */
	public DAOAdministrador()
	{
		this.dbConnector = new DbConnector();
	}
	
	@Override
	public void inserta(TransferObject transferObject) throws ConnectionFailure, AlreadyExists, WrongDataFormat
	{
		TransferAdministrador admin = (TransferAdministrador)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_ADMINISTRADOR);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setInt(1, ADMINISTRADOR_ROLL); // CD_ROLL
			sentencia.setString(2, admin.getNombre()); // NOMBRES 
			sentencia.setString(3, admin.getApellidos()); // APELLIDOS
			if(admin.getDni() == null)
				throw new WrongDataFormat("El dni del socio es invalido");
			sentencia.setString(4, admin.getContrasena()); // CONTRASENA
			sentencia.setString(5, admin.getDni()); // DNI
			sentencia.setString(6,  admin.getCorreo()); // CORREO 	
						
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			throw new AlreadyExists("El usuario insertado ya existe (DNI Duplicado)", e.getCause());
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
			TransferAdministrador admin= (TransferAdministrador) buscar(correo);
			sentencia.executeUpdate(QUERY_BORRAR_ADMINISTRADOR + this.usrId);
			
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
		TransferAdministrador admin = (TransferAdministrador)transferObject;
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();
			sentencia.executeUpdate("UPDATE usuarios_table SET" +
					" NOMBRE = '" + admin.getNombre() +
					"', APELLIDOS = '" + admin.getApellidos() +
					"', CUENTABANCARIA = '" + admin.getCuenta() +
					"', DIRECCION = '" + admin.getDireccion() +
					"', TELEFONO = " + admin.getTelefono() +
					" WHERE DNI = '" + admin.getDni() +"'");
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
		TransferAdministrador admin = null;
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
			resultado = sentencia.executeQuery(QUERY_BUSCAR_ADMINISTRADOR + "'"+correo+"'");
			if(resultado.next()) 
			{
				this.usrId = resultado.getInt("ID_USR");
				admin = new TransferAdministrador(resultado.getString("NOMBRE"),
						resultado.getString("APELLIDOS"),
						resultado.getString("CORREO"),
						resultado.getString("PASSWORD"),
						resultado.getString("DNI"),
						resultado.getInt("CUENTABANCARIA"),
						resultado.getString("DIRECCION"),
						resultado.getInt("TELEFONO"));
			}
			
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		
		return admin;
	}

	@Override
	public List<TransferObject> buscarTodos() throws ConnectionFailure,	DataBaseError 
	{
		List<TransferObject> listAdmins = new ArrayList<TransferObject>();
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
			TransferAdministrador admin = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_ADMINISTRADORES);
			while (resultado.next()) 
			{
				this.usrId = resultado.getInt("ID_USR");
				admin = new TransferAdministrador(resultado.getString("NOMBRE"),
						resultado.getString("APELLIDOS"),
						resultado.getString("CORREO"),
						resultado.getString("PASSWORD"),
						resultado.getString("DNI"),
						resultado.getInt("CUENTABANCARIA"),
						resultado.getString("DIRECCION"),
						resultado.getInt("TELEFONO"));
				// Falta entrenamiento y horario.
				listAdmins.add(admin);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listAdmins;
	}

	

}
