package modelo.AppService;

import java.util.ArrayList;
import java.util.List;

import modelo.DAO.DAOAdministrador;
import modelo.DAO.DAOEntrenador;
import modelo.DAO.DAOSocio;
import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.transfer.TransferObject;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;





public class AppServiceUsuario implements InterfaceAppService {
	
	private DAOSocio daoSocio;
	private DAOEntrenador daoEntrenador;
	private DAOAdministrador daoAdministrador;
	
	/**
	 * Constructor sin parametros.
	 */
	public AppServiceUsuario()
	{
		this.daoSocio = new DAOSocio();
		this.daoAdministrador = new DAOAdministrador();
		this.daoEntrenador = new DAOEntrenador();
	}
	
	/**
	 * Metodo que establece conexion con la base de datos a traves de DAOSocio para buscar un socio dado su correo.
	 * @param correo - Correo del socio.
	 * @return Socio en caso de que exista.
	 */
	public TransferObject buscarSocio(String correo) 
	{
		TransferObject socio = null;
		try 
		{
			socio = daoSocio.buscar(correo);
		} catch (ConnectionFailure e) {
			e.printStackTrace();
		} catch (DataBaseError e) {
			e.printStackTrace();
		}
		return socio;
	}
	
	/**
	 * Metodo que establece conexion con la base de datos a traves de DAOAdministrador para buscar un administrador dado su correo.
	 * @param correo - Correo del administrador.
	 * @return Administrador en caso de que exista.
	 */
	public TransferObject buscarAdministrador(String correo) 
	{
		TransferObject admin = null;
		try {
			admin = this.daoAdministrador.buscar(correo);
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return admin;
	}

	/**
	 * Metodo que establece conexion con la base de datos a traves de DAOEntrenador para buscar un entrenador dado su correo.
	 * @param correo - Correo del entrenador.
	 * @return Entrenador en caso de que exista.
	 */
	public TransferObject buscarEntrenador(String correo) 
	{
		TransferObject entrenador = null;
		try {
			entrenador = this.daoEntrenador.buscar(correo);
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entrenador;
	}
	
	
	
	/**
	 * Funcion que da de alta al socio
	 * @param socio TransferSocio que meteremos para dar de alta
	 * @return true si se pudo dar de alta
	 */
	 public boolean altaSocios(TransferSocio socio){
		 
		 boolean correcto=false;
		 TransferObject buscado=null;
		 DAOSocio basesocio=new DAOSocio();
		 
		 //El dni del socio, será el q se seleccione en la ventana de dar de alta
		 try {
			buscado=basesocio.buscar(socio.getDni());
		} catch (ConnectionFailure e) {
			e.printStackTrace();
		} catch (DataBaseError e) {
			e.printStackTrace();
		}
		 
		 if(buscado==null){
			 try {
				basesocio.inserta(socio);
				correcto=true;
			} catch (ConnectionFailure e) {
				e.printStackTrace();
			} catch (WrongDataFormat e) {
				e.printStackTrace();
			} catch (AlreadyExists e) {
				e.printStackTrace();
			} catch (DataBaseError e) {
				e.printStackTrace();
			}
		 }
		return correcto;
			  
	 }
	 /**
	  * Borra el socio
	  * @param dni del socio que queremos borrar
	  * @return true si la operacion de realizo con exito
	  */
	 public boolean borraSocio(String dni){
		 boolean correcto=false;
		 TransferObject socio=null;
		 DAOSocio basesocio=new DAOSocio();
		 
		 //El dni del socio, se seleccionará de la interfaz en la lista de socios
		 try {
			 //buscamos el socio
			socio=basesocio.buscar(dni);
		} catch (ConnectionFailure e) {
			e.printStackTrace();
		} catch (DataBaseError e) {
			e.printStackTrace();
		}
		 //Si el socio existe
		 if(socio!=null){
			 try {
				 //Se borra
				basesocio.borrar(dni);
				correcto=true;
			} catch (ConnectionFailure e) {
				e.printStackTrace();
			}
		 }
		return correcto;
	 }
	 
	 /**
	  * Modifica los datos del socio
	  * @param socio El socio, con los nuevos datos ya modificados
	  * @return true si se pudo modificar
	  */
	 public boolean modificaSocio(TransferSocio socio){
		
		 DAOSocio basesocio=new DAOSocio();
		 TransferObject buscado=null;
		 boolean correcto=false;
		 
		 try {
		//El dni del socio no se puede modificar. Por eso, hacemos la busqueda con ese parámetro
			buscado= basesocio.buscar(socio.getCorreo());
		} catch (ConnectionFailure e) {
			e.printStackTrace();
		} catch (DataBaseError e) {
			e.printStackTrace();
		}
		 //Si el socio existe, se modifica
		 if(buscado!=null){
			
			 try {
				basesocio.modificar(socio);
			} catch (ConnectionFailure e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 correcto=true;
		 }
		return correcto;
			  
	 }
	 /**
		 * Funcion que da de alta al socio
		 * @param entrena TransferEntrenador que meteremos para dar de alta
		 * @return true si se pudo dar de alta
		 */
	 public boolean altaEntrenador(TransferEntrenador entrena){
		 
		 boolean correcto=false;
		 TransferObject buscado=null;
		 DAOEntrenador baseEntrenador=new DAOEntrenador();
		 
		 try {
			buscado=baseEntrenador.buscar(entrena.getCorreo());
		} catch (ConnectionFailure e) {
			e.printStackTrace();
		} catch (DataBaseError e) {
			e.printStackTrace();
		}
		 
		 if(buscado==null){
			 try {
				baseEntrenador.inserta(entrena);
				correcto=true;
			} catch (ConnectionFailure e) {
				e.printStackTrace();
			} catch (WrongDataFormat e) {
				e.printStackTrace();
			} catch (AlreadyExists e) {
				e.printStackTrace();
			}
		 }
		return correcto;
			  
	 }
	 /**
	  * Borra el entrenador
	  * @param dni del entrenador que queremos borrar
	  * @return true si la operacion de realizo con exito
	  */
	 public boolean borraEntrenador(String dni){
		 boolean correcto=false;
		 TransferObject buscado=null;
		 DAOEntrenador baseEntrena=new DAOEntrenador();
		 
		 //El dni del socio, se seleccionará de la interfaz en la lista de socios
		 try {
			 //buscamos el Entrenador
			buscado=baseEntrena.buscar(dni);
		} catch (ConnectionFailure e) {
			e.printStackTrace();
		} catch (DataBaseError e) {
			e.printStackTrace();
		}
		 //Si el entrenador existe
		 if(buscado!=null){
		
				 //Se borra
				try {
					baseEntrena.borrar(dni);
				} catch (ConnectionFailure e) {
					
					e.printStackTrace();
				}
				correcto=true;
		 }
		return correcto;
	 }
	 /**
	  * Modifica los datos del entrenador
	  * @param entrena El entrenador, con los nuevos datos ya modificados
	  * @return true si se pudo modificar
	  */
	 public boolean modificaEntrenador(TransferEntrenador entrena){
			
		 DAOEntrenador baseEntrenador=new DAOEntrenador();
		 TransferObject buscado=null;
		 boolean correcto=false;
		 
		 try {
			 //El dni del entrenador no se puede modificar. Por eso, hacemos la busqueda con ese parámetro
			buscado= (TransferEntrenador) baseEntrenador.buscar(entrena.getDni());
		} catch (ConnectionFailure e) {
			e.printStackTrace();
		} catch (DataBaseError e) {
			e.printStackTrace();
		}
		 //Si existe en la base
		 if(buscado!=null){
			//Se modifica
			 try {
				baseEntrenador.modificar(entrena);
				correcto=true;
			} catch (ConnectionFailure e) {
				
				e.printStackTrace();
			}
			 
		 }
		return correcto;
			  
	 }
	 
	 /**
	  * 
	  * @return La lista de todos los entrenadores que hay en la base de datos.
	  */
	 public List <TransferEntrenador> mostrarEntrenadores(){
			List<TransferObject> listaObjects= new ArrayList<TransferObject>();
			List<TransferEntrenador> entrenadores= new ArrayList <TransferEntrenador>();
			DAOEntrenador baseEntrenador= new DAOEntrenador();
			try {
				listaObjects=baseEntrenador.buscarTodos();
			} catch (ConnectionFailure e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0; i<listaObjects.size(); i++){
				//Vamos recorriendo la lista de entrenadores, que estará en tipo TransferObject,
				//lo pasamos a TransferEntrenador, y lo guardamos en la lista de entrenadores de tipo TransferEntrenador.
				
				entrenadores.add((TransferEntrenador) listaObjects.get(i));
				
			}
			return entrenadores;

		}
		
	public TransferEntrenador mostrarEntrenador(String dni){
			TransferEntrenador entrenador=null;
			DAOEntrenador baseEntrenador= new DAOEntrenador();
			
			try {
				entrenador=(TransferEntrenador) baseEntrenador.buscar(dni);
			} catch (ConnectionFailure e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return entrenador;
			
		}
		
		/**
		 * Devuelve toda la lista de Socios
		 * @return Lista de Transfer Socios
		 */
	public List<TransferSocio> mostrarSocios(){
			List<TransferSocio>listaSocios=new ArrayList<TransferSocio>();
			List<TransferObject> listaObjects=new ArrayList<TransferObject>();
		
			DAOSocio baseSocio= new DAOSocio();
			try {
				listaObjects=baseSocio.buscarTodos();
			} catch (ConnectionFailure e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0; i<listaObjects.size(); i++){
				//Vamos recorriendo la lista de socios, que estará en tipo TransferObject,
				//lo pasamos a TransferSocio, y lo guardamos en la variable socio.
				//Luego, tal vez haya que hacer un toString, o algo asi.
				listaSocios.add((TransferSocio) listaObjects.get(i));
				
			}
			return listaSocios;
		
		}
		
		/**
		 * Devuelve un solo socio
		 * @param dni del socio que buscamos
		 * @return El socio con el dni introducido
		 */
	public TransferSocio mostrarSocio(String dni){
			TransferSocio socio=null;
			DAOSocio baseSocio= new DAOSocio();
			
			try {
				socio=(TransferSocio) baseSocio.buscar(dni);
			} catch (ConnectionFailure e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return socio;
			
		}

	/**
	 * Obtiene el final de cuota del socio.
	 */
	public String obtenerUltimaCuota(TransferSocio usuario) 
	{
		String fechaFin ="";
		try {
			this.daoSocio.buscar(usuario.getCorreo());
			fechaFin = this.daoSocio.obtenerFinCuota(this.daoSocio.getUsrId());
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fechaFin;
	
	}
	
	/**
	 * Devuelve el ultimo sueldo del entrenador especificado
	 */
	public String obtenerUltimoSueldo(TransferEntrenador entrenador)
	{
		String fechaFin ="";
		try {
			this.daoEntrenador.buscar(entrenador.getCorreo());
			fechaFin = this.daoEntrenador.obtenerFinSueldo(this.daoEntrenador.getUsrId());
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fechaFin;
		
	}

	/**
	 * Devuelve todos los entrenadores del gimnasio.
	 */
	public List<TransferEntrenador> buscarEntrenadores() 
	{
		List<TransferObject> listObjects = new ArrayList<TransferObject>();
		List<TransferEntrenador> listEntrenadores = new ArrayList<TransferEntrenador>();
		try {
			listObjects = this.daoEntrenador.buscarTodos();
		} catch (ConnectionFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<listObjects.size(); i++){
			//Vamos recorriendo la lista de socios, que estará en tipo TransferObject,
			//lo pasamos a TransferSocio, y lo guardamos en la variable socio.
			//Luego, tal vez haya que hacer un toString, o algo asi.
			listEntrenadores.add((TransferEntrenador) listObjects.get(i));
			
		}
		
		return listEntrenadores;
	}




}
