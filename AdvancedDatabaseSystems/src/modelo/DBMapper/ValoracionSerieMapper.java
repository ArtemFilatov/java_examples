package modelo.DBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Serie.Episodio;
import modelo.Serie.Serie;
import modelo.Usuario.ValoracionSerie;
import modelo.Usuario.ValoracionSerieKey;

public class ValoracionSerieMapper extends AbstractMapper<ValoracionSerie, ValoracionSerieKey> {
	
	private static final String VALORACION_SERIE_KEY_COLUMN_NAME_1 = "NICK";
	private static final String VALORACION_SERIE_KEY_COLUMN_NAME_2 = "ID_SERIE";
    private static final String[] VALORACION_SERIE_COLUMN_NAMES = new String[] {
    	VALORACION_SERIE_KEY_COLUMN_NAME_1,
    	VALORACION_SERIE_KEY_COLUMN_NAME_2,
    	"NOTA"};
    private static final String VALORACION_SERIE_TABLE_NAME = "valoracion_serie";
	
	public ValoracionSerieMapper(DataSource ds) {
		super(ds);
	}
	
	@Override
	protected String getTableName() {
		return VALORACION_SERIE_TABLE_NAME;
	}
	
	@Override
	protected String[] getColumnNames() {
		return VALORACION_SERIE_COLUMN_NAMES;
	}
	
	@Override
	protected ValoracionSerie buildObject(ResultSet rs) throws SQLException {
		String nick = rs.getString(VALORACION_SERIE_COLUMN_NAMES[0]);
		int idSerie = rs.getInt(VALORACION_SERIE_COLUMN_NAMES[1]);
		int nota = rs.getInt(VALORACION_SERIE_COLUMN_NAMES[2]);
		ValoracionSerie val = new ValoracionSerie(nick, idSerie, nota);
		return val;
	}
	
	@Override
	protected String[] getKeyColumnNames() {
		String[] aux = new String[] {VALORACION_SERIE_KEY_COLUMN_NAME_1,
				VALORACION_SERIE_KEY_COLUMN_NAME_2};
		return aux;
	}
	
	@Override
	protected Object[] serializeKey(ValoracionSerieKey key) {
		Object[] aux = new Object[] {key.getNick(),
				key.getIdSerie()};
		return aux;
	}
	
	@Override
	protected ValoracionSerieKey getKeyFromObject(ValoracionSerie objeto) {
		return objeto.getKey();
	}
	
	@Override
	protected Object[] serializeObject(ValoracionSerie objeto) {
		Object[] aux = new Object[] {
				objeto.getNick(),
				objeto.getIdSerie(),
				objeto.getNota()
				};
		return aux;
	}
	
	public double findAverage(Serie serie ) {
    	Connection con        = null;
        PreparedStatement pst = null;
        ResultSet rs          = null;
        double result = 0;
        try {
            con = ds.getConnection();
            // SELECT ID_SERIE, AVG(NOTA) AS MEDIA FROM valoracion_serie WHERE ID_SERIE = 0 GROUP BY ID_SERIE;
            String sql = "SELECT ID_SERIE, AVG(NOTA) AS MEDIA " +
							"FROM valoracion_serie " +
							"WHERE ID_SERIE = "+ serie.getId() + 
							" GROUP BY ID_SERIE";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(2);
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
	protected Object[] serializeObjectForIncremental(ValoracionSerie objeto) {
		// TODO Auto-generated method stub
		return null;
	}
}
