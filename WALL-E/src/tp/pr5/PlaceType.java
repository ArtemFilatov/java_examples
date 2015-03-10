package tp.pr5;

/**
 * Enumerado para declarar los tipos de lugar en el que nos podemos encontrar junto con las protecciones que nos ofrece dicho lugar.
 * @author Jesus
 *
 */

public enum PlaceType {
	PLACE(0), TRENCH(50), BUNKER(100);
	
	private int protection;
	
	private PlaceType(int protection)
	{
		this.protection = protection;
	}
	
	public int getPlaceTypeProtection()
	{
		return this.protection;
	}
}
