package modelo.DBMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import modelo.Serie.Episodio;
import modelo.Serie.EpisodioKey;
import modelo.Serie.Serie;

import org.apache.commons.lang3.StringUtils;

public class EpisodioMapper extends AbstractMapper<Episodio, EpisodioKey>{
	
	private static final String EPISODIO_KEY_COLUMN_NAME_1 = "ID_SERIE";
	private static final String EPISODIO_KEY_COLUMN_NAME_2 = "ID_EPISODIO";
    private static final String[] EPISODIO_COLUMN_NAMES = new String[] {EPISODIO_KEY_COLUMN_NAME_1,
    	EPISODIO_KEY_COLUMN_NAME_2,"TEMPORADA", "TITULO", "SINOPSIS", "FECHA_ESTRENO"};
    private static final String EPISODIO_TABLE_NAME = "episodio";

	public EpisodioMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return EPISODIO_TABLE_NAME;
	}

	@Override
	protected String[] getColumnNames() {
		return EPISODIO_COLUMN_NAMES;
	}

	@Override
	protected Episodio buildObject(ResultSet rs) throws SQLException {
        Episodio result;
        int idSerie = rs.getInt(EPISODIO_COLUMN_NAMES[0]);
        int idEpisodio = rs.getInt(EPISODIO_COLUMN_NAMES[1]); 
        int temporada = rs.getInt(EPISODIO_COLUMN_NAMES[2]);
        String titulo = rs.getString(EPISODIO_COLUMN_NAMES[3]);
        String sinopsis = rs.getString(EPISODIO_COLUMN_NAMES[4]);
        Date fEstreno = rs.getDate(EPISODIO_COLUMN_NAMES[5]);
        EpisodioKey key = new EpisodioKey(idSerie,idEpisodio);
        result = new Episodio(key,temporada,titulo,sinopsis,fEstreno);
        return result;
	}
	
	public ArrayList<Episodio> findAllByIdSerie(Serie serie) {
    	Connection con        = null;
        PreparedStatement pst = null;
        ResultSet rs          = null;
        ArrayList<Episodio> result       = new ArrayList<Episodio>();
        try {
            con = ds.getConnection();
            String[] columnNames = getColumnNames();
            String columnNamesWithCommas = StringUtils.join(columnNames, ", ");
            String keyColumn = EPISODIO_KEY_COLUMN_NAME_1; 
            String tableName = getTableName();
            pst = con.prepareStatement(
                    "SELECT " + columnNamesWithCommas + " FROM " + tableName +  
                    " WHERE " + keyColumn + " = " + serie.getId());
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
	protected String[] getKeyColumnNames() {
		String[] aux = {EPISODIO_KEY_COLUMN_NAME_1, EPISODIO_KEY_COLUMN_NAME_2};
		return aux;
	}

	@Override
	protected Object[] serializeKey(EpisodioKey key) {
		Integer[] aux = {key.getIdSerie(), key.getIdEpisodio()};
		return aux;
	}

	@Override
	protected EpisodioKey getKeyFromObject(Episodio episodio) {
		return episodio.getId();
	}

	@Override
	protected Object[] serializeObject(Episodio objeto) {
		Object[] aux = {objeto.getIdSerie(), 
				objeto.getIdEpisodio(), 
				objeto.getTemporada(),
				objeto.getTitulo(),
				objeto.getSinopsis(),
				objeto.getfEstreno()};
		return aux;
	}

	@Override
	protected String getIncrementalColumnName() {
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(Episodio objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
