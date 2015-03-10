package modelo.AppService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.DAO.DAOActividad;
import modelo.DAO.DAOEntrenador;
import modelo.DAO.DAOHorario;
import modelo.DAO.DAOSocio;
import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;


public class AppServiceHorario implements InterfaceAppService  {
	
	
	private DAOActividad daoActividad;
	private DAOHorario daoHorario;
	
	public AppServiceHorario()
	{
		this.daoActividad = new DAOActividad();
		this.daoHorario = new DAOHorario();
	}
	
	/**
	 * El Administrador crea una nueva actividad disponible en el gimnasio.
	 * Se la asigna a un entrenador.
	 */
	public boolean creaActividad(TransferActividad activity){
		
		boolean correcto=false;
		 DAOActividad baseActividad=new DAOActividad();
		 try {
			baseActividad.inserta(activity);
			correcto=true;
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongDataFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return correcto;
	}
	
	/**
	 * El socio podr� solicitar tanto actividades individuales como grupales.
	 * @param dni El dni del socio que est� solicitando la actividad
	 * @param activity La actividad que se solicita
	 * @return tur si se realiz� con �xito
	 */
	public boolean solicitarActividad(String dni, TransferActividad activity){
		boolean correcto=false;
		DAOActividad baseActividad=new DAOActividad();
		AppServiceUsuario userGest=new AppServiceUsuario();
		TransferSocio socio;
		socio=userGest.mostrarSocio(dni);
		try {
			baseActividad.insertaSocioActividad(socio,activity.getIdActividad());
			correcto=true;
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongDataFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return correcto;
		
		
	}
	
	
	/**
	 * 
	 * @param nombreActividad
	 * @return
	 */
	public TransferActividad buscarActividad(String nombreActividad){
		TransferActividad buscada=null;
		DAOActividad baseActividad= new DAOActividad();
		
		try {
			buscada=(TransferActividad) baseActividad.buscar(nombreActividad);
		} catch (ConnectionFailure | DataBaseError e) {
			
			e.printStackTrace();
		}
		
		return buscada;
	}
	/**
	 * Muestra el horario general del gimnasio para el dia de hoy
	 * @return el horario
	 */
	public TransferHorario verHorarioGym(){
		TransferHorario horario=null;
		Date date=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		
		String fecha = formato.format(date);
		DAOHorario daoHorario= new DAOHorario();
		try {
			horario=(TransferHorario) daoHorario.buscarHorarioOficial(fecha);
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return horario;
	}
	/**
	 * Muestra el horario del socio para el dia de hoy
	 * @param correo del socio del que queremos consultar el horario
	 * @return El horario del socio
	 */
	public TransferHorario verHorarioSocio(String correo){
		DAOSocio baseSocio= new DAOSocio();
		DAOHorario baseHorario=new DAOHorario(); 
		TransferHorario horario=null;
		TransferSocio socio=null;
		Date date=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		String fecha = formato.format(date);
	
		try {
			socio=(TransferSocio) baseSocio.buscar(correo);
			
			horario= (TransferHorario) baseHorario.buscarHorarioSocio(fecha, socio.getCorreo());
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return horario;
	}
	
	/**
	 * Muestra el horario del entrenador para el dia de hoy
	 * @param correo Del entrenador del que queremos consultar el horario
	 * @return El horario del entrenador
	 */
	public TransferHorario verHorarioEntrenador(String correo){
		DAOEntrenador baseEntrenador= new DAOEntrenador();
		DAOHorario baseHorario=new DAOHorario(); 
		TransferHorario horario=null;
		TransferEntrenador entrenador=null;
		Date date=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		String fecha = formato.format(date);
	
		try {
			entrenador=(TransferEntrenador) baseEntrenador.buscar(correo);
			
			horario= (TransferHorario) baseHorario.buscarHorarioEntrenador(fecha, entrenador.getCorreo());
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return horario;
	}
	/**
	 * Devuelve un array de string de 2 columnas en el que en la primera columna tendremos el id de la actividad, y en la segunda una breve descripcion
	 * @param activity La actividad que queremos mostrar
	 * @return El array con la descripcion de la actividad
	 */
	public String[] mostrarActividad(TransferActividad activity){
		String[] cadena=new String[2];
		cadena[0]=activity.getNombre().toString();
		cadena[1]="Entrenador: "+activity.getEntrenador()+" Hora de inicio: "+activity.getHoraInicio()+" Hora de fin: "+activity.getHoraFin();
		return cadena;
		
	}
	
	/**
	 * Inserta un socio en una actividad.
	 * @param socio - Socio a insertar.
	 * @param actividad - Actividad en la que hay que insertar el socio.
	 */
	public boolean insertaSocioEnActividad(TransferSocio socio, TransferActividad actividad)
	{
		boolean correcto=false;
		try {
			this.daoActividad.insertaSocioActividad(socio, actividad.getIdActividad());
			correcto=true;
		} catch (ConnectionFailure | WrongDataFormat | AlreadyExists
				| DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correcto;
	}
	
	/**
	 * Crea un nuevo horario para el socio en el dia actual.
	 * @param socio - El Socio.
	 */
	public boolean insertaHorarioSocio(TransferSocio socio)
	{
		boolean correcto=false;
		try {
			this.daoHorario.insertaHorarioSocio(socio.getCorreo());
			correcto=true;
		} catch (ConnectionFailure | AlreadyExists | WrongDataFormat
				| DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correcto;
	}

	/**
	 * Inserta una actividad en el horario del socio.
	 * @param horarioDelUsuario - Horario del socio.
	 * @param actividadSeleccionada - Actividad a insertar.
	 */
	
	public boolean insertaHorarioActividad(TransferHorario horarioDelUsuario,TransferActividad actividadSeleccionada) 
	{
		boolean correcto=false;
		try 
		{
			this.daoHorario.insertaActividadHorario(horarioDelUsuario.getCdHorario(), actividadSeleccionada.getIdActividad());
			correcto=true;
		} catch (ConnectionFailure | WrongDataFormat | AlreadyExists
				| DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correcto;
		
	}
	/**
	 * Inserta una actividad en el horario oficial
	 * @param horarioDelUsuario
	 * @param actividadSeleccionada
	 */
	public boolean insertaActividadOficial(TransferHorario horarioOficial,TransferActividad actividadSeleccionada) 
	{
		boolean correcto=false;
		 try {
			this.daoActividad.inserta(actividadSeleccionada);
			this.daoActividad.buscarUltimaActividad();
		} catch (ConnectionFailure e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WrongDataFormat e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AlreadyExists e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DataBaseError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
		   int idActividad = this.daoActividad.getUltimaId();
		 
		try 
		{
			this.daoHorario.insertaActividadHorario(horarioOficial.getCdHorario(), idActividad);
			correcto=true;
		} catch (ConnectionFailure | WrongDataFormat | AlreadyExists
				| DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correcto;
	}

	public void creaEntrenamientoPersonal(TransferEntrenador entrenadorSeleccionado, TransferSocio socio) 
	{
		List<TransferObject> listSocios = new ArrayList<TransferObject>();
		listSocios.add(socio);
		// Creamos la actividad de entrenamiento personal asignandole el entrenador y el socio.
		TransferActividad actividad = new TransferActividad("Entrenamiento Personal", entrenadorSeleccionado, listSocios, "17:00:00", "18:00:00");
		try {
			this.daoActividad.inserta(actividad);
			this.daoActividad.buscarUltimaActividad();
			int idActividad = this.daoActividad.getUltimaId();
			Date date=new Date();
			SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
			String fecha = formato.format(date);
			TransferHorario horario = (TransferHorario) this.daoHorario.buscarHorarioSocio(fecha, socio.getCorreo());
			this.daoHorario.insertaActividadHorario(horario.getCdHorario(), idActividad);
			horario = (TransferHorario) this.daoHorario.buscarHorarioEntrenador(fecha, entrenadorSeleccionado.getCorreo());
			this.daoHorario.insertaActividadHorario(horario.getCdHorario(), idActividad);
		} catch (ConnectionFailure | WrongDataFormat | AlreadyExists
				| DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean borraActividad(String id){
		  
		   
		   boolean correcto=false;
		    DAOActividad baseActividad=new DAOActividad();
		    
		    try {
		     baseActividad.borrar(id);
		     correcto=true;
		    } catch (ConnectionFailure e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } catch (AlreadyExists e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		    return correcto;
		  
		 }
	
	
}
