package modelo.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.DAO.DAO;
import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.enums.Cuota;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferSocio;





/**
 * 
 * @author Jesus Vazquez
 *
 */
public class DAOSocio extends DAO 
{
	private final int SOCIO_ROLL = 1; // Predefinido roll socio = 1.
	
	// Inserts
    private final String QUERY_INSERT_SOCIO = "INSERT INTO usuarios_table (CD_ROLL, NOMBRE, APELLIDOS, PASSWORD, DNI, CORREO, CUENTABANCARIA, " +
    		"DIRECCION, TELEFONO) VALUES (?,?,?,?,?,?,?,?,?) ";
    private final String QUERY_INSTERT_CUOTA_SOCIO = "INSERT INTO cuotas_socios_table (CD_CUOTA, ID_USR_SOCIO, FECHA_INICIO, FECHA_FIN) VALUES (?,?,?,?)";
    // Selects
    private final String QUERY_BUSCAR_SOCIO = "SELECT * FROM usuarios_table WHERE CD_ROLL = " + SOCIO_ROLL + " AND CORREO = ";
    private final String QUERY_BUSCAR_SOCIOS = "SELECT * FROM usuarios_table WHERE CD_ROLL = " + SOCIO_ROLL;
    private final String QUERY_BUSCAR_CUOTA_SOCIO = "SELECT CD_CUOTA FROM cuotas_socios_table WHERE ID_USR_SOCIO = ";
    private final String QUERY_BUSCAR_FIN_CUOTA = "SELECT MAX(FECHA_FIN) AS FECHA_FIN FROM cuotas_socios_table WHERE ID_USR_SOCIO = ";
    // Deletes
    private final String QUERY_BORRAR_SOCIO = "DELETE FROM usuarios_table WHERE ID_USR = ";
    
    
    private int usrId;
    
	/**
	 * Constructor sin parametros.
	 */
	public DAOSocio()
	{
		this.dbConnector = new DbConnector();
	}

	@Override
	public void inserta(TransferObject transferObject) throws ConnectionFailure, WrongDataFormat, AlreadyExists, DataBaseError
	{
		TransferSocio socio = (TransferSocio)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_SOCIO);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setInt(1, SOCIO_ROLL); // CD_ROLL
			sentencia.setString(2, socio.getNombre()); // NOMBRES 
			sentencia.setString(3, socio.getApellidos()); // APELLIDOS
			if(socio.getDni() == null)
				throw new WrongDataFormat("El dni del socio es invalido");
			sentencia.setString(4, socio.getContrasena()); // CONTRASENA
			sentencia.setString(5, socio.getDni()); // DNI
			sentencia.setString(6,  socio.getCorreo()); // CORREO 	
			sentencia.setInt(7,  socio.getCuenta()); // CUENTA BANCARIA
			sentencia.setString(8, socio.getDireccion()); // DIRECCI�N
			sentencia.setInt(9, socio.getTelefono()); // TELEFONO
			
			sentencia.executeUpdate();
			
			// Ahora insertamos su cuota y para ello necesitaremos el ID con el que se ha insertado nuestro usuario.
			ResultSet resultado;
			resultado = sentencia.executeQuery("SELECT ID_USR FROM usuarios_table WHERE DNI = '"+socio.getDni()+"'");
			while(resultado.next())
				this.usrId = resultado.getInt("ID_USR");
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSTERT_CUOTA_SOCIO);
			sentencia.setInt(1, socio.getCuota().getCodigoCuota());
			sentencia.setInt(2,  this.usrId);
			sentencia.setDate(3, new Date(Cuota.getFechaInicial().getTime()));
			sentencia.setDate(4, new Date((Cuota.getFechaFinal(socio.getCuota().getCodigoCuota()).getTime())));	
			
			sentencia.executeUpdate();
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AlreadyExists("El usuario insertado ya existe (DNI Duplicado)", e.getCause());
		}	
		
	}



	public void borrar(String correo) throws ConnectionFailure 
	{

		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();
			TransferSocio socio = (TransferSocio) buscar(correo);

			sentencia.executeUpdate(QUERY_BORRAR_SOCIO + this.usrId);
			
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
		TransferSocio socio = (TransferSocio)transferObject;
		this.dbConnector.connectDb(); // Conexion establecida.
		try 
		{
			Statement sentencia;
			sentencia = this.dbConnector.getConnection().createStatement();
			sentencia.executeUpdate("UPDATE usuarios_table SET" +
					" NOMBRE = '" + socio.getNombre() +
					"', APELLIDOS = '" + socio.getApellidos() +
					"', DIRECCION = '" + socio.getDireccion() +
					"', TELEFONO = " + socio.getTelefono() +
					" WHERE DNI = '" + socio.getDni() +"'");
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
		TransferSocio socio = null;
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
			resultado = sentencia.executeQuery(QUERY_BUSCAR_SOCIO + "'"+correo+"'");
			if(resultado.next()) 
			{
				this.usrId = resultado.getInt("ID_USR");
				socio = new TransferSocio(resultado.getString("NOMBRE"),
						resultado.getString("APELLIDOS"),
						resultado.getString("CORREO"),
						resultado.getString("PASSWORD"),
						resultado.getString("DNI"),
						resultado.getInt("CUENTABANCARIA"),
						null,
						resultado.getString("DIRECCION"),
						resultado.getInt("TELEFONO"),
						null);
				ResultSet r2;
				r2 = sentencia.executeQuery(QUERY_BUSCAR_CUOTA_SOCIO + this.usrId);
				if(r2.next())
					socio.setCuota(r2.getInt("CD_CUOTA"));
			}
			
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		
		return socio;
		
	}
	
	@Override
	public List<TransferObject> buscarTodos() throws ConnectionFailure, DataBaseError
	{
		List<TransferObject> listSocios = new ArrayList<TransferObject>();
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
			TransferSocio socio = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_SOCIOS);
			while (resultado.next()) 
			{
				this.usrId = resultado.getInt("ID_USR");
				socio = new TransferSocio(resultado.getString("NOMBRE"),
						resultado.getString("APELLIDOS"),
						resultado.getString("CORREO"),
						resultado.getString("PASSWORD"),
						resultado.getString("DNI"),
						resultado.getInt("CUENTABANCARIA"),
						null,
						resultado.getString("DIRECCION"),
						resultado.getInt("TELEFONO"),
						null);
				ResultSet r2;
				Statement sentencia2;
				sentencia2 = this.dbConnector.getConnection().createStatement();
				r2 = sentencia2.executeQuery(QUERY_BUSCAR_CUOTA_SOCIO + this.usrId);
				if(r2.next())
					socio.setCuota(r2.getInt("CD_CUOTA"));
				listSocios.add(socio);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listSocios;
	}
	
	/**
	 * Devuelve el fin de cuota del socio.
	 * @param idUsr - ID del socio.
	 */
	public String obtenerFinCuota(int idUsr) throws ConnectionFailure, DataBaseError
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
			resultado = sentencia.executeQuery(QUERY_BUSCAR_FIN_CUOTA + idUsr);
			String aux = null;
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
	
	
	public int getUsrId() {
		return usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public void actualizarCuota(int idUsuario, int cdCuota) 
	{
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSTERT_CUOTA_SOCIO);
		} 
		catch (SQLException e) 
		{
			try {
				throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
			} catch (ConnectionFailure e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSTERT_CUOTA_SOCIO);
			sentencia.setInt(1, cdCuota);
			sentencia.setInt(2,  idUsuario);
			sentencia.setDate(3, new Date(Cuota.getFechaInicial().getTime()));
			sentencia.setDate(4, new Date((Cuota.getFechaFinal(cdCuota).getTime())));
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
