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
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferFactura;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;

public class DAOFactura extends DAO
{
	
	private String QUERY_INSERT_FACTURA = "INSERT INTO facturas_table (ID_USR, DS_FACTURA, IMPORTE) VALUES (?, ?, ?)";
	private String QUERY_ULTIMA_FACTURA ="SELECT MAX(CD_FACTURA) AS CD_FACTURA FROM facturas_table";
	private String QUERY_BUSCAR_FACTURAS = "SELECT * FROM facturas_table";
	private String QUERY_BUSCAR_FACTURAS_USUARIO = "SELECT * FROM facturas_table " +
												   "WHERE ID_USR = ";
	
	private int cdFactura;
	private int lastCdFactura;
	
	public DAOFactura()
	{
		this.dbConnector = new DbConnector();
	}
	
	@Override
	public void inserta(TransferObject transferObject)throws ConnectionFailure, WrongDataFormat, AlreadyExists,DataBaseError 
	{
		TransferFactura factura = (TransferFactura)transferObject;
		PreparedStatement sentencia;
		try 
		{
			this.dbConnector.connectDb(); // Conexi�n establecida.
			sentencia = this.dbConnector.getConnection().prepareStatement(QUERY_INSERT_FACTURA);
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		try 
		{
			sentencia.setInt(1, factura.getIdUsrFactura()); // ID_USR
			sentencia.setString(2, factura.getDsFactura()); // DS_FACTURA 
			sentencia.setInt(3, factura.getImporte()); // IMPORTE
						
			sentencia.executeUpdate();
			
			sentencia.close(); // Cerramos el statement
			
			this.dbConnector.disconnectDb(); // Desconectamos
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AlreadyExists("La factura insertada ya existe (DNI Duplicado)", e.getCause());
		}	
		
	}

	@Override
	public void borrar(String identificador) throws ConnectionFailure,	AlreadyExists {
		// TODO Auto-generated method stub
		
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
		List<TransferObject> listFacturas = new ArrayList<TransferObject>();
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
			TransferFactura factura = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_FACTURAS);
			while (resultado.next()) 
			{
				this.cdFactura = resultado.getInt("CD_FACTURA");
				factura = new TransferFactura(resultado.getInt("CD_FACTURA"),
						resultado.getInt("ID_USR"),
						resultado.getString("DS_FACTURA"),
						resultado.getInt("IMPORTE"));
				listFacturas.add(factura);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listFacturas;
	}
	
	/**
	 * Devuelve todas las facturas de un usuario.
	 * @param idUsr - Usuario del que se desea obtener las facturas.
	 */
	public List<TransferFactura> buscarFacturasUsuario(int idUsr) throws ConnectionFailure, DataBaseError
	{
		List<TransferFactura> listFacturas = new ArrayList<TransferFactura>();
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
			TransferFactura factura = null;
			resultado = sentencia.executeQuery(QUERY_BUSCAR_FACTURAS_USUARIO + idUsr);
			while (resultado.next()) 
			{
				this.cdFactura = resultado.getInt("CD_FACTURA");
				factura = new TransferFactura(resultado.getInt("CD_FACTURA"),
						resultado.getInt("ID_USR"),
						resultado.getString("DS_FACTURA"),
						resultado.getInt("IMPORTE"));
				listFacturas.add(factura);
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
		return listFacturas;
	}
	
	/**
	 * Busca la ultima factura insertada.
	 * @return Devuelve la ultima factura insertada.
	 */
	public void buscarUltimaFactura() throws ConnectionFailure, DataBaseError
	{
		TransferFactura factura= null;
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
			resultado = sentencia.executeQuery(QUERY_ULTIMA_FACTURA);
			if(resultado.next()) 
			{
				this.lastCdFactura = resultado.getInt("CD_FACTURA"); // ID Servicio
			}
		
			sentencia.close();
			this.dbConnector.disconnectDb();
		} 
		catch (SQLException e) 
		{
			throw new DataBaseError("Error en la base de datos", e.getCause());
		}
		
	}

	public int getLastCdFactura() {
		return lastCdFactura;
	}

	public void setLastCdFactura(int lastCdFactura) {
		this.lastCdFactura = lastCdFactura;
	}
	

}
