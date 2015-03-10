package modelo.DBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import modelo.Serie.Episodio;
import modelo.Serie.Serie;
import modelo.Usuario.ComentarioEpisodio;
import modelo.Usuario.ComentarioSerie;
import modelo.Usuario.Usuario;

public class ComentarioSerieMapper extends AbstractMapper<ComentarioSerie, Integer> {
	
	public ComentarioSerieMapper(DataSource ds) {
		super(ds);
	}
	private static final String COMENTARIO_SERIE_KEY_COLUMN_NAME = "ID_COMENTARIO_S";
	private static final String COMENTARIO_SERIE_INCREMENTAL_COLUMN_NAME = "ID_COMENTARIO_S";
    private static final String[] COMENTARIO_SERIE_COLUMN_NAMES = new String[] {COMENTARIO_SERIE_KEY_COLUMN_NAME, "NICK", "ID_SERIE", "DESCRIPCION", "FECHA"};
    private static final String COMENTARIO_SERIE_TABLE_NAME = "comentario_serie";
	@Override
	protected String getTableName() {
		return COMENTARIO_SERIE_TABLE_NAME;
	}
	@Override
	protected String[] getColumnNames() {
		return COMENTARIO_SERIE_COLUMN_NAMES;
	}
	@Override
	protected ComentarioSerie buildObject(ResultSet rs) throws SQLException {
		int idComentario = rs.getInt(COMENTARIO_SERIE_COLUMN_NAMES[0]);
		String nick= rs.getString(COMENTARIO_SERIE_COLUMN_NAMES[1]);
		int idSerie= rs.getInt(COMENTARIO_SERIE_COLUMN_NAMES[2]);
		String descripcion = rs.getString(COMENTARIO_SERIE_COLUMN_NAMES[3]);
		Date fecha = rs.getDate(COMENTARIO_SERIE_COLUMN_NAMES[4]);
		ComentarioSerie comentario = new ComentarioSerie(idComentario, nick, idSerie, descripcion, fecha);
		return comentario;
	}
	@Override
	protected String[] getKeyColumnNames() {
		String[] aux = new String[] {COMENTARIO_SERIE_KEY_COLUMN_NAME};
		return aux;
	}
	@Override
	protected Object[] serializeKey(Integer key) {
		Integer[] aux = new Integer[]{key};
		return aux;
	}
	@Override
	protected Integer getKeyFromObject(ComentarioSerie objeto) {
		return objeto.getIdComentario();
	}
	@Override
	protected Object[] serializeObject(ComentarioSerie objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie(),
				objeto.getDescripcion(),
				objeto.getFecha()
		};
		return aux;
	}
	@Override
	protected String getIncrementalColumnName() {
		return COMENTARIO_SERIE_INCREMENTAL_COLUMN_NAME;
	}
	
	public ArrayList<ComentarioSerie> findComentariosOrdenados(
			Serie serie, Usuario user) {
    	Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		ArrayList<ComentarioSerie> result  = new ArrayList<ComentarioSerie>();
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT id_comentario_s, nick, id_serie, descripcion, fecha " +
					"FROM comentario_serie WHERE " +
					"id_serie = ? " +
					" ORDER BY fecha DESC");
			pst.setInt(1, serie.getId());
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
	protected Object[] serializeObjectForIncremental(ComentarioSerie objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie(),
				objeto.getDescripcion(),
				objeto.getFecha()
		};
		return aux;
	}
	
	
}
