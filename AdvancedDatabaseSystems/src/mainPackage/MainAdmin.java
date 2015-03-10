package mainPackage;

import java.beans.PropertyVetoException;
import java.sql.Connection;

import javax.sql.DataSource;

import modelo.AppService.AppServiceAplicacion;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import controlador.ControladorVistas;


public class MainAdmin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
    	DataSource ds;
    	Connection con = null;
        try {
			cpds.setDriverClass("org.gjt.mm.mysql.Driver");
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
        cpds.setJdbcUrl("jdbc:mysql://localhost/serie");
        cpds.setUser("AdminP1");
        cpds.setPassword("AdminP1");
        cpds.setAcquireRetryAttempts(1);
        cpds.setAcquireRetryDelay(1);
        cpds.setBreakAfterAcquireFailure(true);
        ds = cpds;
        AppServiceAplicacion modelo = new AppServiceAplicacion(ds);
		new ControladorVistas(modelo, false);
	}
}
