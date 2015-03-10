package modelo.DBMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Serie.Personaje;

public class PersonajeMapper extends AbstractMapper<Personaje, String> {
	
	private static final String PERSONAJE_KEY_COLUMN_NAME = "ID_PERSONAJE";
	private static final String PERSONAJE_INCREMENTAL_COLUMN_NAME = "ID_PERSONAJE";
    private static final String[] PERSONAJE_COLUMN_NAMES = new String[] {PERSONAJE_KEY_COLUMN_NAME,"NOMBRE_PERSONAJE","DESCRIPCION"};
    private static final String PERSONAJE_TABLE_NAME = "personaje";
	
	public PersonajeMapper(DataSource ds) {
		super(ds);
	}
	
	
    @Override
	protected String getTableName() {
		return PERSONAJE_TABLE_NAME;
	}
	@Override
	protected String[] getColumnNames() {
		return PERSONAJE_COLUMN_NAMES;
	}
	@Override
	protected Personaje buildObject(ResultSet rs) throws SQLException {
		int idPersonaje = rs.getInt(PERSONAJE_COLUMN_NAMES[0]);
		String nombre = rs.getString(PERSONAJE_COLUMN_NAMES[1]);
		String des = rs.getString(PERSONAJE_COLUMN_NAMES[2]);
		Personaje personaje = new Personaje(idPersonaje, nombre, des);
		return personaje;
	}
	@Override
	protected String[] getKeyColumnNames() {
		String[] aux = new String[] {PERSONAJE_KEY_COLUMN_NAME};
		return aux;
	}

	@Override
	protected Object[] serializeObject(Personaje objeto) {
		Object[] aux = new Object[] {
				objeto.getIdPersonaje(),
				objeto.getNombre(),
				objeto.getDes()};
		return aux;
	}


	@Override
	protected Object[] serializeKey(String key) {
		String[] aux = new String[] {key};
		return aux;
	}


	@Override
	protected String getKeyFromObject(Personaje objeto) {
		return objeto.getNombre();
	}


	@Override
	protected String getIncrementalColumnName() {
		return PERSONAJE_INCREMENTAL_COLUMN_NAME;
	}


	@Override
	protected Object[] serializeObjectForIncremental(Personaje objeto) {
		Object[] aux = new Object[] {
				objeto.getNombre(),
				objeto.getDes()};
		return aux;
	}
	
}
