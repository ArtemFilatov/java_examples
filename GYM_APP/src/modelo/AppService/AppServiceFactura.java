package modelo.AppService;

import java.util.ArrayList;
import java.util.List;

import modelo.DAO.DAOEntrenador;
import modelo.DAO.DAOFactura;
import modelo.DAO.DAOSocio;
import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferFactura;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;

public class AppServiceFactura implements InterfaceAppService
{
	
	private DAOFactura daoFactura;
	
	public AppServiceFactura()
	{
		this.daoFactura = new DAOFactura();
	}

	/**
	 * Devuelve las facturas de un socio
	 */
	public List<TransferFactura> buscarFacturasSocio(TransferSocio usuario) 
	{
		List<TransferFactura> listFacturas = new ArrayList<TransferFactura>();
		DAOSocio daoSocio = new DAOSocio();
		try {
			TransferSocio socio = (TransferSocio) daoSocio.buscar(usuario.getCorreo());
		} catch (ConnectionFailure | DataBaseError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			listFacturas = this.daoFactura.buscarFacturasUsuario(daoSocio.getUsrId());
		} catch (ConnectionFailure | DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFacturas;
	}

	/**
	 * Devuelve las facturas de un entrenador.
	 */
	public List<TransferFactura> buscarFacturasEntrenador(TransferEntrenador usuario) 
	{
		List<TransferFactura> listFacturas = new ArrayList<TransferFactura>();
		DAOEntrenador daoEntrenador = new DAOEntrenador();
		try {
			TransferEntrenador entrenadorAux = (TransferEntrenador) daoEntrenador.buscar(usuario.getCorreo());
		} catch (ConnectionFailure | DataBaseError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			listFacturas = this.daoFactura.buscarFacturasUsuario(daoEntrenador.getUsrId());
		} catch (ConnectionFailure | DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFacturas;
	}

	/**
	 * Metodo que crea una nueva factura con el pago del socio. Mantiene su cuota.
	 * @param usuario
	 */
	public void pagarNuevaCuota(TransferSocio usuario) 
	{
		DAOSocio daoSocio = new DAOSocio();
		TransferSocio socio;
		try {
			socio = (TransferSocio) daoSocio.buscar(usuario.getCorreo());
			TransferFactura factura = new TransferFactura(daoSocio.getUsrId(), "Pago Cuota Gimnasio", 
					socio.getCuota().getImporte(socio.getCuota().getCodigoCuota()));
			daoSocio.actualizarCuota(daoSocio.getUsrId(), socio.getCuota().getCodigoCuota());
			this.daoFactura.inserta(factura);
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongDataFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Metodo que crea una nueva factura con el pago del sueldo de un entrenador. Mantiene su sueldo.
	 * @param usuario
	 */
	public void pagarNuevoSueldo(TransferEntrenador usuario) 
	{
		DAOEntrenador daoEntrenador = new DAOEntrenador();
		TransferEntrenador entrenador;
		try {
			entrenador = (TransferEntrenador) daoEntrenador.buscar(usuario.getCorreo());
			TransferFactura factura = new TransferFactura(daoEntrenador.getUsrId(), "Pago Sueldo Entrenador", 
					entrenador.getSueldo().getImporte(entrenador.getSueldo().getCodigoSueldo()));
			daoEntrenador.actualizarSueldo(daoEntrenador.getUsrId(), entrenador.getSueldo().getCodigoSueldo());
			this.daoFactura.inserta(factura);
			} catch (ConnectionFailure e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WrongDataFormat e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlreadyExists e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	
	

}
