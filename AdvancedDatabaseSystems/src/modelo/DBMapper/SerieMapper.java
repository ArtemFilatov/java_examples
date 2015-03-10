package modelo.DBMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Serie.Serie;

/**
 * SerieMapper - Clase que sigue el patron Mapper para la ayuda en la abstraccion del acceso y obtencion de datos
 * en lo referente a la entidad serie.
 */
public class SerieMapper extends AbstractMapper<Serie, Integer> {

	private static final String SERIE_KEY_COLUMN_NAME = "ID_SERIE";
	private static final String SERIE_INCREMENTAL_COLUMN_NAME = "ID_SERIE";
    private static final String[] SERIE_COLUMN_NAMES = new String[] {SERIE_KEY_COLUMN_NAME, "NOMBRE", "TITULAR", "SINOPSIS", "FECHA_ESTRENO", "FECHA_FINALIZACION"};
    private static final String SERIE_TABLE_NAME = "serie";	
	
	public SerieMapper(DataSource ds) {
		super(ds);
	}
	
	@Override
	protected Serie buildObject(ResultSet rs) throws SQLException {
        Serie result;
        int id = rs.getInt(SERIE_COLUMN_NAMES[0]);
        String nombre = rs.getString(SERIE_COLUMN_NAMES[1]);
        String titular = rs.getString(SERIE_COLUMN_NAMES[2]);
        String sinopsis = rs.getString(SERIE_COLUMN_NAMES[3]);
        Date fEstreno = rs.getDate(SERIE_COLUMN_NAMES[4]);
        Date fFinal= rs.getDate(SERIE_COLUMN_NAMES[5]);
        result = new Serie(id, nombre, titular, sinopsis, fEstreno, fFinal);
        return result;
	}

	@Override
	protected String[] getKeyColumnNames() {
    	String[] aux = {SERIE_KEY_COLUMN_NAME};
        return aux;
	}

	@Override
	protected Object[] serializeKey(Integer key) {
		Integer[] aux = {key};
		return aux;
	}

	@Override
	protected Integer getKeyFromObject(Serie serie) {
		return serie.getId();
	}

	@Override
	protected Object[] serializeObject(Serie objeto) {
		Object[] aux = { 
				objeto.getId(),
				objeto.getNombre(), 
				objeto.getTitular(),
				objeto.getSinopsis(),
				objeto.getfEstreno(),
				objeto.getfFinal()};
		return aux;
	}


	@Override
	protected String getTableName() {
		return SERIE_TABLE_NAME;
	}

	@Override
	protected String[] getColumnNames() {
		return SERIE_COLUMN_NAMES;
	}

	@Override
	protected String getIncrementalColumnName() {
		return SERIE_INCREMENTAL_COLUMN_NAME;
	}

	@Override
	protected Object[] serializeObjectForIncremental(Serie objeto) {
		Object[] aux = { 
				objeto.getNombre(), 
				objeto.getTitular(),
				objeto.getSinopsis(),
				objeto.getfEstreno(),
				objeto.getfFinal()};
		return aux;
	}
	
}


