package modelo.DBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import modelo.Serie.Episodio;
import modelo.Usuario.ValoracionEpisodio;
import modelo.Usuario.ValoracionEpisodioKey;

public class ValoracionEpisodioMapper extends AbstractMapper<ValoracionEpisodio, ValoracionEpisodioKey> {
	
	private static final String VALORACION_EPISODIO_KEY_COLUMN_NAME_1 = "NICK";
	private static final String VALORACION_EPISODIO_KEY_COLUMN_NAME_2 = "ID_SERIE";
	private static final String VALORACION_EPISODIO_KEY_COLUMN_NAME_3 = "ID_EPISODIO";
    private static final String[] VALORACION_EPISODIO_COLUMN_NAMES = new String[] {
    	VALORACION_EPISODIO_KEY_COLUMN_NAME_1,
    	VALORACION_EPISODIO_KEY_COLUMN_NAME_2,
    	VALORACION_EPISODIO_KEY_COLUMN_NAME_3, 
    	"NOTA"};
    private static final String VALORACION_EPISODIO_TABLE_NAME = "valoracion_episodio";
	
	public ValoracionEpisodioMapper(DataSource ds) {
		super(ds);
	}
	
	@Override
	protected String getTableName() {
		return VALORACION_EPISODIO_TABLE_NAME;
	}
	
	@Override
	protected String[] getColumnNames() {
		return VALORACION_EPISODIO_COLUMN_NAMES;
	}
	
	@Override
	protected ValoracionEpisodio buildObject(ResultSet rs) throws SQLException {
		String nick = rs.getString(VALORACION_EPISODIO_COLUMN_NAMES[0]);
		int idSerie = rs.getInt(VALORACION_EPISODIO_COLUMN_NAMES[1]);
		int idEpisodio = rs.getInt(VALORACION_EPISODIO_COLUMN_NAMES[2]);
		int nota = rs.getInt(VALORACION_EPISODIO_COLUMN_NAMES[3]);
		ValoracionEpisodio val = new ValoracionEpisodio(nick, idSerie, idEpisodio, nota);
		return val;
	}
	
	@Override
	protected String[] getKeyColumnNames() {
		String[] aux = new String[] {VALORACION_EPISODIO_KEY_COLUMN_NAME_1,
				VALORACION_EPISODIO_KEY_COLUMN_NAME_2,
				VALORACION_EPISODIO_KEY_COLUMN_NAME_3};
		return aux;
	}
	
	@Override
	protected Object[] serializeKey(ValoracionEpisodioKey key) {
		Object[] aux = new Object[] {key.getNick(),
				key.getIdSerie(),
				key.getIdEpisodio()};
		return aux;
	}
	
	@Override
	protected ValoracionEpisodioKey getKeyFromObject(ValoracionEpisodio objeto) {
		return objeto.getKey();
	}
	
	@Override
	protected Object[] serializeObject(ValoracionEpisodio objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie(),
				objeto.getIdEpisodio(),
				objeto.getNota()
				};
		return aux;
	}
	
	public double findAverage(Episodio episodio ) {
    	Connection con        = null;
        PreparedStatement pst = null;
        ResultSet rs          = null;
        double result = 0;
        try {
            con = ds.getConnection();
            //select ID_SERIE, ID_EPISODIO, AVG(NOTA) AS MEDIA FROM valoracion_episodio WHERE ID_SERIE = 0 AND ID_EPISODIO = 185 GROUP BY ID_SERIE,ID_EPISODIO;
            String sql = "SELECT ID_SERIE, ID_EPISODIO,  AVG(NOTA) AS MEDIA " +
							"FROM valoracion_episodio " +
							"WHERE ID_SERIE = "+ episodio.getIdSerie() +" AND " +
							"ID_EPISODIO = " + episodio.getIdEpisodio() + 
							" GROUP BY ID_SERIE, ID_EPISODIO";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(3);
            } 
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
	protected String getIncrementalColumnName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(ValoracionEpisodio objeto) {
		// TODO Auto-generated method stub
		return null;
	}
}
