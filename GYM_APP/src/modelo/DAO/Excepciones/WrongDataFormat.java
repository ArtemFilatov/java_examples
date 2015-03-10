package modelo.DAO.Excepciones;

/**
 * 
 * @author Jesús Vázquez
 *
 */
public class WrongDataFormat extends Exception 
{
	public WrongDataFormat() 
	{
		super(); 
	}
	
	public WrongDataFormat(String arg0)
	{ 
		super(arg0); 
	}
	
	public WrongDataFormat(Throwable arg0) 
	{
		super(arg0); 
	}
	
	public WrongDataFormat(String arg0, Throwable arg1) 
	{
		super(arg0, arg1); 
	}

}
