package modelo.DBMapper;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.ImageIcon;

import mainPackage.Utilities;
import modelo.Serie.Episodio;
import modelo.Serie.Serie;
import modelo.Usuario.Usuario;
import vista.usuario.VistaUsuario;

/**
 * UsuarioMapper - Clase que sigue el patron Mapper para la ayuda en la abstraccion del acceso y obtencion de datos
 * en lo referente a la entidad usuario.
 *
 */
public class UsuarioMapper extends AbstractMapper<Usuario, String> {
	
	private static final String USUARIO_KEY_COLUMN_NAME = "NICK";
    private static final String[] USUARIO_COLUMN_NAMES = new String[] {USUARIO_KEY_COLUMN_NAME, "PASSWORD", "DOB", "AVATAR"};
    private static final String USUARIO_TABLE_NAME = "usuario";
	

	/**
	 * Constructor no params
	 */
    public UsuarioMapper(DataSource ds) {
		super(ds);
	}

    @Override
    protected Usuario buildObject(ResultSet rs) throws SQLException {
        Usuario result;
        ImageIcon avatar = null;
        String nick = rs.getString(USUARIO_COLUMN_NAMES[0]); // NICK
        String password     = rs.getString(USUARIO_COLUMN_NAMES[1]); 
        Date dob        = rs.getDate(USUARIO_COLUMN_NAMES[2]);
        Blob avatarBlob         = (Blob) rs.getBlob(USUARIO_COLUMN_NAMES[3]);
        if(avatarBlob != null)
        	avatar = Utilities.blobToImageIcon(avatarBlob);
        else
        	avatar = new ImageIcon(VistaUsuario.class.getResource("/controlador/defecto.png"));
        result = new Usuario(nick,password,dob,avatar);
        return result;
    }
    
    /**
     * Devuelve las ids de los episodio no visualizaos de la serie y usuario especificados por parametros.
     * @param serie - Serie a la que pertenecen los capitulos que estamos buscando
     * @param user  - Usuario que requiere la informacion de los capitulos no vistos.
     * @return ArrayList<Integer> - result
     */
	public ArrayList<Integer> misEpisodiosPendientes(Serie serie, Usuario user) {
    	Connection con        = null;
        PreparedStatement pst = null;
        ResultSet rs          = null;
        ArrayList<Integer> result       = new ArrayList<Integer>();
        try {
            con = ds.getConnection();
            pst = con.prepareStatement("SELECT E.ID_EPISODIO " +
							"FROM episodio E " +
							"WHERE E.ID_SERIE = "+ serie.getId() +" AND " +
							"E.ID_EPISODIO NOT IN ( " +
								"SELECT U.ID_EPISODIO " +
								"FROM usuario_episodio U " +
								"WHERE U.ID_SERIE = "+ serie.getId() + " AND " + 
								"NICK = '" + user.getNick()+ "')"
                    );
            rs = pst.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt(1));
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
    protected String[] getKeyColumnNames() {
    	String[] aux = {USUARIO_KEY_COLUMN_NAME};
        return aux;
    }
    
	@Override
	protected Object[] serializeKey(String key) {
		String[] aux = {key};
		return aux;
	}
	
	@Override
	protected String getKeyFromObject(Usuario usuario) {
		return usuario.getNick();
	}
	
	@Override
	protected Object[] serializeObject(Usuario objeto) {
		Object[] aux = {objeto.getNick(), 
				objeto.getPass(),
				objeto.getBirth(), 
				Utilities.imageIconToByteArrayInputStream(objeto.getAvatar())};
		return aux;
	}

    @Override
    protected String[] getColumnNames() {
        return USUARIO_COLUMN_NAMES;
    }


    @Override
    protected String getTableName() {
        return USUARIO_TABLE_NAME;
    }

	@Override
	protected String getIncrementalColumnName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] serializeObjectForIncremental(Usuario objeto) {
		// TODO Auto-generated method stub
		return null;
	}






}
