package modelo.DBMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Usuario.UsuarioSerie;

public class UsuarioSerieMapper extends AbstractMapper<UsuarioSerie, UsuarioSerie> {
	
	private static final String USUARIO_SERIE_KEY_COLUMN_NAME_1 = "NICK";
	private static final String USUARIO_SERIE_KEY_COLUMN_NAME_2 = "ID_SERIE";
    private static final String[] USUARIO_SERIE_COLUMN_NAMES = new String[] {
    	USUARIO_SERIE_KEY_COLUMN_NAME_1,
    	USUARIO_SERIE_KEY_COLUMN_NAME_2};
    
    private static final String USUARIO_SERIE_TABLE_NAME = "usuario_serie";
    
    public UsuarioSerieMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return USUARIO_SERIE_TABLE_NAME;
	}

	@Override
	protected String[] getColumnNames() {
		return USUARIO_SERIE_COLUMN_NAMES;
	}

	@Override
	protected UsuarioSerie buildObject(ResultSet rs) throws SQLException {
		String nick = rs.getString(USUARIO_SERIE_COLUMN_NAMES[0]);
		int idSerie = rs.getInt(USUARIO_SERIE_COLUMN_NAMES[1]);
		UsuarioSerie us = new UsuarioSerie(nick, idSerie);
		return us;
	}

	@Override
	protected String[] getKeyColumnNames() {
		return USUARIO_SERIE_COLUMN_NAMES;
	}

	@Override
	protected Object[] serializeKey(UsuarioSerie key) {
		Object[] aux = new Object[] {
				key.getNick(),
				key.getIdSerie(),
		};
		return aux;
	}

	@Override
	protected UsuarioSerie getKeyFromObject(UsuarioSerie objeto) {
		return objeto;
	}

	@Override
	protected Object[] serializeObject(UsuarioSerie objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie()
		};
		return aux;
	}

	@Override
	protected String getIncrementalColumnName() {
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(UsuarioSerie objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
