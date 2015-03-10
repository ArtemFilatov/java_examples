package modelo.DBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import modelo.Serie.Genero;
import modelo.Serie.Serie;
import modelo.Serie.SerieGenero;
import modelo.Usuario.Usuario;

public class SerieGeneroMapper extends AbstractMapper<SerieGenero, SerieGenero> {
	
	private static final String SERIE_GENERO_KEY_COLUMN_NAME_1 = "ID_SERIE";
	private static final String SERIE_GENERO_KEY_COLUMN_NAME_2 = "DS_GENERO";
    private static final String[] SERIE_GENERO_COLUMN_NAMES = new String[] {SERIE_GENERO_KEY_COLUMN_NAME_1, SERIE_GENERO_KEY_COLUMN_NAME_2};
    private static final String SERIE_GENERO_TABLE_NAME = "serie_genero";
    
	public SerieGeneroMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return SERIE_GENERO_TABLE_NAME;
	}

	@Override
	protected String[] getColumnNames() {
		return SERIE_GENERO_COLUMN_NAMES;
	}

	@Override
	protected SerieGenero buildObject(ResultSet rs) throws SQLException {
		int idSerie = rs.getInt(SERIE_GENERO_COLUMN_NAMES[0]);
		String strGenero = rs.getString(SERIE_GENERO_COLUMN_NAMES[1]);
		Genero genero = Genero.createFromString(strGenero);
		SerieGenero sg = new SerieGenero(idSerie, genero);
		return sg;
	}

	@Override
	protected String[] getKeyColumnNames() {
		return SERIE_GENERO_COLUMN_NAMES;
	}

	@Override
	protected Object[] serializeKey(SerieGenero key) {
		Object[] aux = new Object[]{key.getIdSerie(), key.getGenero().toString()};
		return aux;
	}

	@Override
	protected SerieGenero getKeyFromObject(SerieGenero objeto) {
		return objeto;
	}

	@Override
	protected Object[] serializeObject(SerieGenero objeto) {
		Object[] aux = new Object[] {
				objeto.getIdSerie(),
				objeto.getGenero().toString()
		};
		return aux;
	}

	@Override
	protected String getIncrementalColumnName() {
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(SerieGenero objeto) {
		return null;
	}

	public void eliminaGenerosSerie(Serie s) {
		// delete from serie_genero where id_serie = 31;
		Connection con = null;
		PreparedStatement pst= null;
		try{
			con = ds.getConnection();
			pst = con.prepareStatement(
					"DELETE FROM " + getTableName() + " WHERE ID_SERIE = ?");
			pst.setInt(1, s.getId());
			pst.execute(); 
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		
	}
	
	
    
}
