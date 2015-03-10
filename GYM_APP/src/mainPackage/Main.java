package mainPackage;

import java.awt.Dimension;
import java.util.List;

import modelo.AppService.AppServiceGym;
import modelo.AppService.InterfaceAppService;
import modelo.DAO.DAOActividad;
import modelo.DAO.DAOAdministrador;
import modelo.DAO.DAOEntrenador;
import modelo.DAO.DAOHorario;
import modelo.DAO.DAOSocio;
import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.enums.Cuota;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferAdministrador;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;

import controlador.Controlador;

import vista.VistaPrincipal;

public class Main {
	
	public static void main(String[] args)
	{
		
		AppServiceGym modelo = new AppServiceGym();
		VistaPrincipal vista = new VistaPrincipal();
		Controlador controlador = new Controlador(modelo);
		modelo.addObserver(vista);
		vista.fijarControlador(controlador);
		controlador.setVistaPrincipal(vista);
		vista.arranca();

		
		
		
		
	}

}
