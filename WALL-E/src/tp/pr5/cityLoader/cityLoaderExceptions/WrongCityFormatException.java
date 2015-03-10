package tp.pr5.cityLoader.cityLoaderExceptions;

/**
 * Exception thrown by the map loader when the file does not adhere to the file format.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class WrongCityFormatException extends java.io.IOException {
	
	public WrongCityFormatException()
	{
		super();
	}
	
	public WrongCityFormatException(java.lang.String msg)
	{
		super(msg);
	}
	
	public WrongCityFormatException(java.lang.String msg, java.lang.Throwable arg)
	{
		super(msg, arg);
	}
	
	public WrongCityFormatException(java.lang.Throwable arg) 
	{
		super(arg);
	}
	
}
