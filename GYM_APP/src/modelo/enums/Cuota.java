package modelo.enums;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que contiene los tipos de cuotas que un socio puede contratar.
 * @author Jesus Vazquez
 *
 */

public enum Cuota {
	
	
	MENSUAL(1), TRIMESTRAL(2), SEMESTRAL(3), ANUAL(4);
	
	private int codigoCuota;
	private static Date fechaInicial;
	private static Date fechaFinal;
	
	
	/**
	 * Constructor con parametro precio.
	 * @param codCuota
	 */
	private Cuota(int codCuota)
	{
		this.codigoCuota = codCuota;
	}
	
	public int getCodigoCuota()
	{
		return this.codigoCuota;
	}

	/**
	 * Devuelve el primer día en el que el usuario podrá empezar a usar el gimnasio.
	 * @return
	 */
	public static Date getFechaInicial()
	{
		fechaInicial = new Date();
		return fechaInicial;
	}
	
	/**
	 * Devuelve el dia en el que termina el periodo pagado por el usuario.
	 * @param cdCuota
	 * @return 
	 */
	public static Date getFechaFinal(int cdCuota)
	{
		fechaFinal = new Date();
		switch(cdCuota)
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
			importe = 20;
			break;
		case 2:
			importe = 54;
			break;
		case 3:
			importe = 100;
			break;
		case 4:
			importe = 190;
			break;
		default:
			importe = 20;
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
