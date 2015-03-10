package modelo.enums;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que contiene los tipos de cuotas que un socio puede contratar.
 * @author Jesus Vazquez
 *
 */

public enum Sueldo {
	
	
	SUELDOBASE(1);
	
	private int codigoSueldo;
	private static Date fechaInicial;
	private static Date fechaFinal;
	
	
	/**
	 * Constructor con parametro precio.
	 * @param codSueldo
	 */
	private Sueldo(int codSueldo)
	{
		this.codigoSueldo = codSueldo;
	}
	
	public int getCodigoSueldo()
	{
		return this.codigoSueldo;
	}

	/**
	 * Devuelve el primer dia de pago al entrenador.
	 * @return
	 */
	public static Date getFechaInicial()
	{
		fechaInicial = new Date();
		return fechaInicial;
	}
	
	/**
	 * Devuelve el dia en el que termina el periodo pagado.
	 * @param cdSueldo
	 * @return 
	 */
	public static Date getFechaFinal(int cdSueldo)
	{
		fechaFinal = new Date();
		switch(cdSueldo)
		{
			case 1:
				fechaFinal = sumarAFecha(1);
				break;
			case 2:
				fechaFinal = sumarAFecha(3);
				break;
			case 3:
				fechaFinal = sumarAFecha(6);
				break;
			case 4:
				fechaFinal = sumarAFecha(12);
				break;
			default:
				fechaFinal = fechaInicial;
				break;
		}
		return fechaFinal;
	}
	
	public int getImporte(int cdCuota)
	{
		int importe = 0;
		switch(cdCuota)
		{
		case 1:
			importe = 650;
			break;
		default:
			importe = 650;
			break;
		}
		return importe;
	}

	/**
	 * Método para sumar meses a una fecha.
	 * @param meses
	 * @return Devuelve la suma de x meses a una fecha.
	 */
	public static Date sumarAFecha(int meses)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(fechaInicial); 
		cal.add(Calendar.DAY_OF_YEAR, meses*30); 
		fechaFinal.setTime(cal.getTimeInMillis());
		return 	fechaFinal;
	}
}
