package modelo.AppService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import modelo.transfer.TransferActividad;
import modelo.transfer.TransferEjercicio;
import modelo.transfer.TransferFactura;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.TransferRutina;
import modelo.transfer.users.TransferAdministrador;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;
import modelo.transfer.users.TransferUsuario;


/**
 * Esta clase es temporal. Todos los metodos que se encuentren aqui deberan estar en otras clases appservice y
 *  en caso de que sean comunes a todas las clases deberan encontrarse en la interfaz. 
 */

public class AppServiceGym extends Observable {
	
	AppServiceHorario horarioAppServ;
	AppServiceTienda shopAppServ;
	AppServiceUsuario userAppServ;
	AppServiceRutina rutinaAppServ;
	AppServiceFactura facturaAppServ;
	
	public AppServiceGym(){
		this.horarioAppServ=new AppServiceHorario();
		this.shopAppServ=new AppServiceTienda();
		this.userAppServ=new AppServiceUsuario();
		this.rutinaAppServ = new AppServiceRutina();
		this.facturaAppServ = new AppServiceFactura();
	}
	
	/**
	 * Llama al metodo buscarSocio de AppServiceUsuario 
	 * @param correo - Correo del socio.
	 * @return Socio.
	 */
	public TransferObject buscarSocio(String correo)
	{
		TransferObject socio = this.userAppServ.buscarSocio(correo);
		return socio;
	}
	
	/**
	 * Llama al metodo buscarAdministrador de AppServiceUsuario
	 * @param correoLogin - Correo del administrador.
	 * @return Administrador.
	 */
	public TransferObject buscarAdministrador(String correo) 
	{
		TransferObject admin = this.userAppServ.buscarAdministrador(correo);
		return admin;
	}

	/**
	 * Llama al metodo buscarEntrenador de AppServiceUsuario
	 * @param correoLogin - Correo del entrenador.
	 * @return Entrenador.
	 */
	public TransferObject buscarEntrenador(String correo) 
	{
		TransferObject entrenador = this.userAppServ.buscarEntrenador(correo);
		return entrenador;
	}
	
	/**
	 * Llama al metodo verHorarioGym de AppServiceHorario
	 * @return horario del dia.
	 */
	public TransferHorario buscarHorarioOficial()
	{
		TransferHorario horario = this.horarioAppServ.verHorarioGym();
		return horario;
	}
	/**
	 * Llama al metodo verHorarioEntrenador de AppServiceHorario.
	 * @return - Horario del entrenador.
	 */
	public TransferHorario buscarHorarioEntrenador(TransferEntrenador entrenador) 
	{
		TransferHorario horario = this.horarioAppServ.verHorarioEntrenador(entrenador.getCorreo());
		return horario;
	}
	/**
	 * Llama al metodo verHorarioSocio de AppServiceHorario
	 * @return horario del dia
	 */
	public TransferHorario buscarHorarioSocio(TransferSocio socio)
	{
		TransferHorario horario = this.horarioAppServ.verHorarioSocio(socio.getCorreo());
		return horario;
	}
	
	/**
	 * Llama al metodo insertaHorarioSocio de AppServiceHorario para crear un nuevo horario de Socio.
	 * @param socio - El Socio.
	 */
	public void insertaHorarioSocio(TransferSocio socio)
	{
		this.horarioAppServ.insertaHorarioSocio(socio);
	}
	
	/**
	 * Llama al metodo insertaSocioEnActividad de AppServiceHorario
	 * @param socio - Socio del sistema.
	 * @param actividad - Actividad seleccionada por el socio.
	 */
	public void insertaUsuarioActividad(TransferSocio socio, TransferActividad actividad)
	{
		this.horarioAppServ.insertaSocioEnActividad(socio, actividad);
	}
	
	
	
	public boolean creaActividad(TransferActividad activity){
		boolean ok=false;
		TransferHorario horarioOficial= this.horarioAppServ.verHorarioGym();
		ok=this.horarioAppServ.insertaActividadOficial(horarioOficial, activity);
		return ok;
	}
	
	
	/**
	 * Llama al metodo insertaHorarioActividad de AppServiceHorario.
	 * @param horarioDelUsuario - Horario del usuario en el que se va a insertar la actividad.
	 * @param actividadSeleccionada - Actividad que queremos insertar.
	 */
	public void insertaHorarioActividad(TransferHorario horarioDelUsuario, TransferActividad actividadSeleccionada) 
	{
		this.horarioAppServ.insertaHorarioActividad(horarioDelUsuario, actividadSeleccionada);
		
	}
	
	/**
	 * Metodo que llama a buscarEjercicios del AppServiceRutina
	 * @return List<TransferEjercicio> ejercicios
	 */
	public List<TransferEjercicio> buscarEjercicios() 
	{
		return this.rutinaAppServ.buscarEjercicios();
	}
	

	/**
	 * Metodo que llamara a buscarEjerciciosRutina del AppServiceRutina.
	 * @return List<TransferEjercicio> ejercicios
	 */
	public List<TransferEjercicio> buscarEjerciciosRutina(TransferRutina rutina)
	{
		return this.rutinaAppServ.buscarEjerciciosRutina(rutina);
	}
	
	/**
	 * Metodo que llamara a insertarRutina del AppServiceRutina
	 * @param rutina
	 */
	public boolean insertarRutina(TransferRutina rutina) 
	{
		return this.rutinaAppServ.insertarRutina(rutina);
	}
	


	/**
	 * Metodo que llamara a la funcion buscarRutinas de AppServiceRutinas
	 * @return ArrayList<TransferRutina> - ArrayList con las rutinas disponibles.
	 */
	public List<TransferRutina> buscarRutinas() 
	{
		return this.rutinaAppServ.buscarRutinas();
	}
	
	/**
	 * Metodo que llamara a la funcion obtenerUltimaCuota de AppServiceUsuario 
	 */
	public String obtenerUltimaCuota(TransferSocio usuario) 
	{
		return this.userAppServ.obtenerUltimaCuota(usuario);
	}
	
	public String obtenerUltimoSueldo(TransferEntrenador entrenador)
	{
		return this.userAppServ.obtenerUltimoSueldo(entrenador);
	}
	/**
	 * Da de alta tanto Socios como entrenadores. Le entra un usuario que habremo creado en el controlador con los datos de la vista
	 * @param usuario El usuario que hay que dar de alta
	 * @return True si se complet� con �xito
	 */
	public boolean darAlta(TransferUsuario usuario)	{
		boolean correcto=false;
		if(usuario.getClass()==TransferSocio.class){
			correcto=userAppServ.altaSocios((TransferSocio) usuario);
			
		}else if(usuario.getClass()== TransferEntrenador.class){
			correcto=userAppServ.altaEntrenador((TransferEntrenador) usuario);
			
		}
		return correcto;
	}
	
	/**
	 * Llama al metodo buscarFacturasSocio de AppServiceFactura
	 * @param usuarioSesion - Usuario del cual se quieren obtener las facturas.
	 */
	public List<TransferFactura> buscarFacturasSocio(TransferSocio usuario) 
	{
		return this.facturaAppServ.buscarFacturasSocio(usuario);
	}
	
	/**
	 * Llama al metodo buscarFacturasEntrenador de AppServiceFactura
	 * @param usuarioSesion - Entrenador del cual se quieren obtener las facturas.
	 */
	public List<TransferFactura> buscarFacturasEntrenador(TransferEntrenador entrenador) 
	{
		return this.facturaAppServ.buscarFacturasEntrenador(entrenador);
	}
	
	public List<TransferEntrenador> buscarEntrenadores()
	{
		return this.userAppServ.buscarEntrenadores();
	}
	
	/**
	 * Llama al metodo pagarNuevaCuota de AppServiceFacturas
	 * @param usuario
	 */
	public void pagarNuevaCuota(TransferSocio usuario) 
	{
		this.facturaAppServ.pagarNuevaCuota(usuario);
		
	}
	
	/**
	 * Llama al metodo pagarNuevoSueldo de AppServiceFacturas
	 * @param usuario
	 */
	public void pagarNuevoSueldo(TransferEntrenador usuario)
	{
		this.facturaAppServ.pagarNuevoSueldo(usuario);
		
	}
	
	/**
	 * Sirve para dar de baja tanto Socios como Entrenadores
	 * @param dni Del usuario que queremos dar de baja
	 * @param tipo Entero que determina si el usuario es un socio o un entrenador. Ser� 1 si es socio, y 2 si es entrenador
	 * @return true si se pudo completar
	 */
	public boolean darBaja(String correo, int tipo){
		
		boolean correcto=false;
		if(tipo==1){
		
			correcto=userAppServ.borraSocio(correo);
			
		}else if(tipo==2){
			correcto=userAppServ.borraEntrenador(correo);
			
		}
		return correcto;
	}
	
	/**
	 * Sirve para modificar tanto socios como entrenadores
	 * @param usuario El usuario que queremos modificar, con los datos ya cambiados
	 * @return True si se realiz� correctamente
	 */
	public boolean modificarUsuario(TransferSocio socio)
	{
		return this.userAppServ.modificaSocio((TransferSocio)socio);
	}
	
	/**
	 * Sirve para modificar tanto socios como entrenadores
	 * @param usuario El usuario que queremos modificar, con los datos ya cambiados
	 * @return True si se realiz� correctamente
	 */
	public boolean modificarUsuario(TransferEntrenador usuario)
	{
		return this.userAppServ.modificaEntrenador((TransferEntrenador)usuario);
	}
	/**
	 * Muestra la lista de Socios
	 * @return la lista de todos los socios de la base de datos
	 */
	public List<TransferSocio> mostrarSocios(){
		return userAppServ.mostrarSocios();
	}
	/**
	 * 
	 * @return la lista de todos los entrenadores de la base de datos
	 */
	public List<TransferEntrenador> mostrarEntrenadores(){
		return userAppServ.mostrarEntrenadores();
	}
	/**
	 * Busca el usuario, tanto socio como entrenador, y lo devuelve.
	 * @param tipo 1 si es socio, 2 si es entrenador
	 * @param dni el dni del usuario que buscamos
	 * @return el usuario
	 */
	public TransferUsuario mostrarUsuario(int tipo,String dni){
		TransferUsuario usuario=null;
		if(tipo==1){
			usuario= userAppServ.mostrarSocio(dni);
		}else if(tipo==2){
			usuario= userAppServ.mostrarEntrenador(dni);
		}
		return usuario;
	}

	/**
	 * Metodo que llamara a eliminarRutina del AppServiceRutina
	 * @param rutina
	 */
	public boolean eliminarRutina(TransferRutina rutina) 
	{
		return this.rutinaAppServ.eliminarRutina(rutina);
		
	}
	/**
	 * Metodo que llamara a la funcion crearEntrenamientoPersonal de AppServiceHorario.
	 * @param entrenadorSeleccionado - El entrenador que impartira la clase.
	 * @param socio - El usuario que solicita el entrenamiento.
	 */
	public void entrenamientoPersonal(TransferEntrenador entrenadorSeleccionado, TransferSocio socio) 
	{
		this.horarioAppServ.creaEntrenamientoPersonal(entrenadorSeleccionado, socio);		
	}

	public boolean borraActividad(String id){
		  return this.horarioAppServ.borraActividad(id);
		 }

	



	



	
	


}