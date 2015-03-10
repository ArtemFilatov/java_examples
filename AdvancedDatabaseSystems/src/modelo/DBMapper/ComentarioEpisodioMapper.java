package modelo.DBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import modelo.Serie.Episodio;
import modelo.Usuario.ComentarioEpisodio;
import modelo.Usuario.Usuario;

public class ComentarioEpisodioMapper extends AbstractMapper<ComentarioEpisodio, Integer> {
	
	private static final String COMENTARIO_EPISODIO_KEY_COLUMN_NAME = "ID_COMENTARIO_E";
	private static final String COMENTARIO_INCREMENTAL_COLUMN_NAME = "ID_COMENTARIO_E";
    private static final String[] COMENTARIO_EPISODIO_COLUMN_NAMES = new String[] {COMENTARIO_EPISODIO_KEY_COLUMN_NAME, "NICK", "ID_SERIE","ID_EPISODIO", "DESCRIPCION", "FECHA"};
    private static final String COMENTARIO_EPISODIO_TABLE_NAME = "comentario_episodio";
	
	public ComentarioEpisodioMapper(DataSource ds) {
		super(ds);
	}
	
	@Override
	protected String getTableName() {
		return COMENTARIO_EPISODIO_TABLE_NAME;
	}
	@Override
	protected String[] getColumnNames() {
		return COMENTARIO_EPISODIO_COLUMN_NAMES;
	}
	@Override
	protected ComentarioEpisodio buildObject(ResultSet rs) throws SQLException {
		int idComentario = rs.getInt(COMENTARIO_EPISODIO_COLUMN_NAMES[0]);
		String nick= rs.getString(COMENTARIO_EPISODIO_COLUMN_NAMES[1]);
		int idSerie= rs.getInt(COMENTARIO_EPISODIO_COLUMN_NAMES[2]);
		int idEpisodio = rs.getInt(COMENTARIO_EPISODIO_COLUMN_NAMES[3]);
		String descripcion = rs.getString(COMENTARIO_EPISODIO_COLUMN_NAMES[4]);
		Date date = rs.getDate(COMENTARIO_EPISODIO_COLUMN_NAMES[5]);
		ComentarioEpisodio comentario = new ComentarioEpisodio(idComentario, nick, idSerie, idEpisodio, descripcion, date);
		return comentario;
	}
	@Override
	protected String[] getKeyColumnNames() {
		String[] aux = new String[] {COMENTARIO_EPISODIO_KEY_COLUMN_NAME};
		return aux;
	}
	@Override
	protected Object[] serializeKey(Integer key) {
		Integer[] aux = new Integer[]{key};
		return aux;
	}
	@Override
	protected Integer getKeyFromObject(ComentarioEpisodio objeto) {
		return objeto.getIdComentario();
	}
	@Override
	protected Object[] serializeObject(ComentarioEpisodio objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie(),
				objeto.getIdEpisodio(),
				objeto.getDescripcion(),
				objeto.getFecha()
		};
		return aux;
	}
	@Override
	protected String getIncrementalColumnName() {
		return COMENTARIO_INCREMENTAL_COLUMN_NAME;
	}

	public ArrayList<ComentarioEpisodio> findComentariosOrdenados(
			Episodio episodio, Usuario user) {
    	Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		ArrayList<ComentarioEpisodio> result  = new ArrayList<ComentarioEpisodio>();
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT id_comentario_e, nick, id_serie, id_episodio, descripcion, fecha " +
					"FROM comentario_episodio WHERE " +
					"id_serie = ? AND " +
					"id_episodio = ?" +
					" ORDER BY fecha DESC");
			pst.setInt(1, episodio.getIdSerie());
			pst.setInt(2, episodio.getIdEpisodio());
			rs = pst.executeQuery();
			while(rs.next())
				result.add(buildObject(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return result;
	}

	@Override
	protected Object[] serializeObjectForIncremental(ComentarioEpisodio objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie(),
				objeto.getIdEpisodio(),
				objeto.getDescripcion(),
				objeto.getFecha()
		};
		return aux;
	}
	
}

