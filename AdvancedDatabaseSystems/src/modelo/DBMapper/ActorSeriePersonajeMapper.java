package modelo.DBMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.EpisodioKey;

public class ActorSeriePersonajeMapper extends AbstractMapper<ActorSeriePersonaje, ActorSeriePersonaje> {
	
	private static final String ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_1 = "NIF";
	private static final String ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_2 = "NOMBRE_PERSONAJE";
	private static final String ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_3 = "ID_SERIE";
	private static final String ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_4 = "ID_EPISODIO";
    private static final String[] ACTOR_SERIE_PERSONAJE_COLUMN_NAMES = new String[] {
    	ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_1,
    	ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_2,
    	ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_3,
    	ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_4};
    private static final String ACTOR_SERIE_PERSONAJE_TABLE_NAME = "actor_serie_personaje";
	
	public ActorSeriePersonajeMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return ACTOR_SERIE_PERSONAJE_TABLE_NAME;
	}

	@Override
	protected String[] getColumnNames() {
		return ACTOR_SERIE_PERSONAJE_COLUMN_NAMES;
	}

	@Override
	protected ActorSeriePersonaje buildObject(ResultSet rs) throws SQLException {
        ActorSeriePersonaje result;
        String nif = rs.getString(ACTOR_SERIE_PERSONAJE_COLUMN_NAMES[0]);
        String nombrePersonaje = rs.getString(ACTOR_SERIE_PERSONAJE_COLUMN_NAMES[1]); 
        int idSerie = rs.getInt(ACTOR_SERIE_PERSONAJE_COLUMN_NAMES[2]);
        int idEpisodio= rs.getInt(ACTOR_SERIE_PERSONAJE_COLUMN_NAMES[3]);
        result = new ActorSeriePersonaje(nif, nombrePersonaje, idSerie, idEpisodio);
        return result;
	}

	@Override
	protected String[] getKeyColumnNames() {
		String[] aux = new String[] { ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_1,
				ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_2,
				ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_3,
				ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_4};
		return aux;
	}

	@Override
	protected Object[] serializeKey(ActorSeriePersonaje key) {
		Object[] aux = new Object[] {
				key.getNif(),
				key.getNombrePersonaje(),
				key.getIdSerie(),
				key.getIdEpisodio()};
		return aux;
	}

	@Override
	protected ActorSeriePersonaje getKeyFromObject(ActorSeriePersonaje objeto) {
		return objeto;
	}

	@Override
	protected Object[] serializeObject(ActorSeriePersonaje objeto) {
		Object[] aux = new Object[] {
				objeto.getNif(),
				objeto.getNombrePersonaje(),
				objeto.getIdSerie(),
				objeto.getIdEpisodio()};
		return aux;
	}

	public ArrayList<ActorSeriePersonaje> findForEpisode(Episodio episodio) {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		ArrayList<ActorSeriePersonaje> result  = new ArrayList<ActorSeriePersonaje>();
		
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM " + getTableName()
					+ " WHERE " + ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_3 + " = " + episodio.getIdSerie() + " AND " 
					+ ACTOR_SERIE_PERSONAJE_KEY_COLUMN_NAME_4 +" = " + episodio.getIdEpisodio();
			pst = con.prepareStatement(sql);			
			rs = pst.executeQuery();
			while(rs.next())
				result.add(buildObject(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(ActorSeriePersonaje objeto) {
		// TODO Auto-generated method stub
		return null;
	}
}
