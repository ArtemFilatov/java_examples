package modelo.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.DAO.Excepciones.ConnectionFailure;




/**
 * Clase que crea la conexi�n con la base de datos y ejecuta las consultas.
 * @author Jesus Vazquez
 *
 */

public class DbConnector 
{
	private Connection connection;
	private String dbUser;
	private String dbPassword;
	
	
	/**
	 * Constructor sin parametros.
	 */
	public DbConnector()
	{
		this.connection = null;
		this.dbUser = "root"; // Usuario por defecto.
		this.dbPassword = ""; // Password por defecto.
	}
	
	/**
	 * Clase que inicializa la conexi�n a la base de datos.
	 * @return boolean success - True si se ha podido realizar la conexi�n, falso en caso contrario.
	 * @throws ConnectionFailure Exception en caso de que no haya podido conectar.
	 */
	public boolean connectDb() throws ConnectionFailure
	{
		boolean success = false;
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // Seleccionamos el Driver que nos permitir� realizar la conexi�n.
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/gymgestor", this.dbUser, this.dbPassword);
			success = true;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			throw new ConnectionFailure("Ha fallado la conexion con la Base de Datos", e.getCause());
		}
		
		return success;
	}
	
	/**
	 * Clase que destruye la conexi�n a la base de datos.
	 */
	public boolean disconnectDb() throws ConnectionFailure
	{
		boolean success = false;
		try
		{
			this.connection.close();
			success = true;
		}
		catch(SQLException e)
		{
			throw new ConnectionFailure("Ha fallado la conexion con la Base de Datos.", e.getCause());
			
		}
		return success;
	}
	
	/**
	 * Este m�todo ejecutar� las consultas de tipo SELECT SQL. No es necesario haber creado la conexi�n con anterioridad.
	 * @param String query - SQL query
	 * @return ResultSet st - Devolvera el resultado de la query o NULL en caso de que no haya encontrado nada.
	 */
	public ResultSet executeSQL(String query)
	{
		ResultSet st = null;
		try
		{
			if(this.connection==null)
				this.connectDb();
			Statement statement = this.connection.createStatement();
			st = statement.executeQuery(query);
		}
		catch(ConnectionFailure e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return st;
	}
	
	/**
	 * Este metodo ejecutara las consultas de tipo DELETE, INSERT, UPDATE. No es necesario haber creado la conexi�n con anterioridad.
	 * @param String query - SQL query
	 * @return Boolean success - Devolvera True si se ha realizado la operacion con exito.
	 */
	public boolean executeUpdate(String query)
	{
		boolean success = false;
		try
		{
			if(this.connection==null)
			{
				try
				{
					this.connectDb();
				}
				catch(ConnectionFailure e)
				{
					// Tratarla.
				}
				
			}
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			success = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return success;
	}
	
	/**
	 * Metodo que devuelve el atributo connection 
	 * @return Connection connection - Conexi�n actual.
	 */
	public Connection getConnection()
	{
		return this.connection;
	}
	
	/* Main de ejemplo para ver si conecta.
	public static void main(String[] args)
	{
		DbConnector db = new DbConnector();
		db.connectDb();
		System.out.println("Connection Success");
		db.disconnectDb();
		System.out.println("Connection Closed");
	}*/
	
}