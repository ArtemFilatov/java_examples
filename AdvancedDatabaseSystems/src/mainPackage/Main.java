package mainPackage;

import java.beans.PropertyVetoException;
import java.sql.Connection;

import javax.sql.DataSource;

import modelo.AppService.AppServiceAplicacion;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import controlador.ControladorVistas;


/**
 * @title main
 * @author Jesus
 * @description main class
 */
public class Main {
    
	/**
     * Application entry-point
     * @param args -
     */
    public static void main(String[] args)
    {
    	ComboPooledDataSource cpds = new ComboPooledDataSource();
    	DataSource ds;
    	Connection con = null;
        try {
			cpds.setDriverClass("org.gjt.mm.mysql.Driver");
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
        cpds.setJdbcUrl("jdbc:mysql://localhost/serie");
        cpds.setUser("UsuarioP1");
        cpds.setPassword("UsuarioP1");
        cpds.setAcquireRetryAttempts(1);
        cpds.setAcquireRetryDelay(1);
        cpds.setBreakAfterAcquireFailure(true);
        ds = cpds;
        AppServiceAplicacion modelo = new AppServiceAplicacion(ds);
		new ControladorVistas(modelo, true);
		
		
    }




}
