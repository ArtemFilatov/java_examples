package modelo.DAO.Excepciones;
/**
 * 
 * @author Jesús Vázquez
 *
 */
public class ConnectionFailure extends Exception
{
	public ConnectionFailure() 
	{
		super(); 
	}
	
	public ConnectionFailure(String arg0)
	{ 
		super(arg0); 
	}
	
	public ConnectionFailure(Throwable arg0) 
	{
		super(arg0); 
	}
	
	public ConnectionFailure(String arg0, Throwable arg1) 
	{
		super(arg0, arg1); 
	}
}
