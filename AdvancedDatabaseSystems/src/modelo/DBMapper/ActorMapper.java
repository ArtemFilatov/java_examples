package modelo.DBMapper;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.ImageIcon;

import mainPackage.Utilities;
import modelo.Serie.Actor;
import vista.usuario.VistaUsuario;

/**
 * Created by n on 12/03/14.
 */
public class ActorMapper  extends AbstractMapper<Actor, String> {

    private static final String ACTOR_KEY_COLUMN_NAME = "NIF";
    private static final String[] ACTOR_COLUMN_NAMES = new String[] {ACTOR_KEY_COLUMN_NAME, "NOMBRE", "DOB", "FOTO"};
    private static final String ACTOR_TABLE_NAME = "actor";

    public ActorMapper(DataSource ds) {
        super(ds);
    }


    @Override
    protected String[] getKeyColumnNames() {
    	String[] aux = {ACTOR_KEY_COLUMN_NAME};
        return aux;
    }


    @Override
    protected Actor buildObject(ResultSet rs) throws SQLException {
        Actor result;
        String nif = rs.getString(ACTOR_COLUMN_NAMES[0]);
        String nombre     = rs.getString(ACTOR_COLUMN_NAMES[1]);
        Date dob = rs.getDate(ACTOR_COLUMN_NAMES[2]);
        Blob foto         = rs.getBlob(ACTOR_COLUMN_NAMES[3]);
        ImageIcon avatar = null;
        if(foto != null)
        	avatar = Utilities.blobToImageIcon(foto);
        else
        	avatar = new ImageIcon(VistaUsuario.class.getResource("/vista/img/defecto.png"));
        result = new Actor(nif, nombre, dob, avatar);
        if (!rs.wasNull()) {
            //result.setImagen(foto.getBytes(1, (int) foto.length()));
        }
        return result;
    }


    @Override
    protected String[] getColumnNames() {
        return ACTOR_COLUMN_NAMES;
    }


    @Override
    protected String getTableName() {
        return ACTOR_TABLE_NAME;
    }


	@Override
	protected Object[] serializeKey(String key) {
		String[] aux = {key};
		return aux;
	}


	@Override
	protected String getKeyFromObject(Actor objeto) {
		return objeto.getNif();
	}


	@Override
	protected Object[] serializeObject(Actor objeto) {
		
		Object[] aux = {objeto.getNif(), 
				objeto.getNombre(),
				objeto.getDob(), 
				Utilities.imageIconToByteArrayInputStream(objeto.getFoto())};
		return aux;
	}


	@Override
	protected String getIncrementalColumnName() {
		return null;
	}


	@Override
	protected Object[] serializeObjectForIncremental(Actor objeto) {
		// TODO Auto-generated method stub
		return null;
	}


}
