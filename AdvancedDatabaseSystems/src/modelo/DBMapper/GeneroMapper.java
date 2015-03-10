package modelo.DBMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Serie.Genero;
import modelo.Usuario.Usuario;

public class GeneroMapper extends AbstractMapper<Genero, String>{
	
	private static final String GENERO_KEY_COLUMN_NAME = "ID_GENERO";
    private static final String[] GENERO_COLUMN_NAMES = new String[] {GENERO_KEY_COLUMN_NAME};
    private static final String GENERO_TABLE_NAME = "genero";

	public GeneroMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getColumnNames() {
		return GENERO_COLUMN_NAMES;
	}

	@Override
	protected Genero buildObject(ResultSet rs) throws SQLException {
		Genero aux = null;
		String genero = rs.getString(GENERO_COLUMN_NAMES[0]);
		
		return null;
	}

	@Override
	protected String[] getKeyColumnNames() {
		String[] aux = new String[] {GENERO_KEY_COLUMN_NAME};
		return aux;
	}

	@Override
	protected Object[] serializeKey(String key) {
		String[] aux = new String[]{key.toString()};
		return aux;
	}

	@Override
	protected String getKeyFromObject(Genero objeto) {
		return objeto.toString();
	}

	@Override
	protected Object[] serializeObject(Genero objeto) {
		Object[] aux = new Object[] {objeto.toString()};
		return aux;
	}

	@Override
	protected String getIncrementalColumnName() {
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(Genero objeto) {
		return null;
	}

	

}
