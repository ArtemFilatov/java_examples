package modelo.DBMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Usuario.UsuarioEpisodio;
import modelo.Usuario.UsuarioSerie;

public class UsuarioEpisodioMapper extends AbstractMapper<UsuarioEpisodio, UsuarioEpisodio> {
	
	private static final String USUARIO_EPISODIO_KEY_COLUMN_NAME_1 = "NICK";
	private static final String USUARIO_EPISODIO_KEY_COLUMN_NAME_2 = "ID_SERIE";
	private static final String USUARIO_EPISODIO_KEY_COLUMN_NAME_3 = "ID_EPISODIO";
    private static final String[] USUARIO_EPISODIO_COLUMN_NAMES = new String[] {
    	USUARIO_EPISODIO_KEY_COLUMN_NAME_1,
    	USUARIO_EPISODIO_KEY_COLUMN_NAME_2,
    	USUARIO_EPISODIO_KEY_COLUMN_NAME_3};
    
    private static final String USUARIO_EPISODIO_TABLE_NAME = "usuario_episodio";
    
    public UsuarioEpisodioMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return USUARIO_EPISODIO_TABLE_NAME;
	}

	@Override
	protected String[] getColumnNames() {
		return USUARIO_EPISODIO_COLUMN_NAMES;
	}

	@Override
	protected UsuarioEpisodio buildObject(ResultSet rs) throws SQLException {
		String nick = rs.getString(USUARIO_EPISODIO_COLUMN_NAMES[0]);
		int idSerie = rs.getInt(USUARIO_EPISODIO_COLUMN_NAMES[1]);
		int idEpisodio = rs.getInt(USUARIO_EPISODIO_COLUMN_NAMES[2]);
		UsuarioEpisodio us = new UsuarioEpisodio(nick, idSerie, idEpisodio); 
		return us;
	}

	@Override
	protected String[] getKeyColumnNames() {
		return USUARIO_EPISODIO_COLUMN_NAMES;
	}

	@Override
	protected Object[] serializeKey(UsuarioEpisodio key) {
		Object[] aux = new Object[] {
				key.getNick(),
				key.getIdSerie(),
				key.getIdEpisodio()
		};
		return aux;
	}

	@Override
	protected UsuarioEpisodio getKeyFromObject(UsuarioEpisodio objeto) {
		return objeto;
	}

	@Override
	protected Object[] serializeObject(UsuarioEpisodio objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie(),
				objeto.getIdEpisodio()
		};
		return aux;
	}

	@Override
	protected String getIncrementalColumnName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(UsuarioEpisodio objeto) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
	
}
