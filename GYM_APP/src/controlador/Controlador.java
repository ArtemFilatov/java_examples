package controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import modelo.AppService.AppServiceGym;
import modelo.enums.Cuota;
import modelo.enums.Sueldo;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferEjercicio;
import modelo.transfer.TransferFactura;
import modelo.transfer.TransferHorario;
import modelo.transfer.TransferObject;
import modelo.transfer.TransferRutina;
import modelo.transfer.users.TransferAdministrador;
import modelo.transfer.users.TransferEntrenador;
import modelo.transfer.users.TransferSocio;

import vista.VistaPrincipal;
import vista.vistaadmin.VerModificarTipo;
import vista.vistaadmin.VistaAdmin;
import vista.vistaadmin.VistaDarDeAlta;
import vista.vistaadmin.VistaNuevaActividad;
import vista.vistaentrena.VistaCrearRutina;
import vista.vistaentrena.VistaEntrenador;
import vista.vistaentrena.VistaVerRutina;
import vista.vistasocio.VistaSocio;

/**
 * Controlador de la aplicacion.
 * Esta clase se encargara de capturar los cambios en la vista, notificar al modelo y recibir y actualizar
 * los cambios necesarios en las vistas.
 */


public class Controlador  implements ActionListener, // para los botones
									 KeyListener, // para los jtextfield
									 MouseListener // Para los jtable y sus jtablemodel.
{
	
	private AppServiceGym modelo; // Referencia al modelo.
	private VistaPrincipal vista; // Referencia a la vista.
	private TransferObject usuarioSesion; // Referencia al usuario que inicie sesion.
	private int cdArranque; // Modo de arranque, socio, entrenador o admin
	private String correoLogin;
	private String contrasenaLogin;
	private TransferActividad actividadSeleccionada; // referencia a una actividad que el socio haya seleccionado.
	private TransferHorario horarioDelDia,actividades; // Referencia al horario del dia.
	private TransferHorario horarioDelUsuario; 
	private TransferObject usrSistema; // Referencia al socio que loguea
	private TransferEntrenador entrenadorSeleccionado; // Referencia al entrenador seleccionado por el usuario.
	private ArrayList<TransferEntrenador> listEntrenadores; // Referencia a la lista de entrenadores recientemente cargada.
	private TransferObject usuarioSeleccionado;
	private ArrayList<TransferSocio> socios;
	private ArrayList<TransferEntrenador> entrenadores;
	private ArrayList<TransferActividad> act;
	private int cdGestion;
	private String modificarNombre;
	private String modificarApellidos;
	private String modificarDireccion,dni,correo,cuenta,inicio,fin;
	private VistaDarDeAlta da;
	private TransferSocio nuevoSocio;
	private String modificarTelefono;
	private String cuota;
	private VistaNuevaActividad vActividad;
	private VerModificarTipo vmt;
	private ArrayList<TransferRutina> listRutinas;
	private TransferRutina rutinaSeleccionada;
	private VistaCrearRutina vcr;
	private ArrayList<TransferEjercicio> listEjercicios;
	private ArrayList<TransferEjercicio> listEjerciciosEscogidos;
	private String nombreRutina;
	private TransferEjercicio ejercicioSeleccionado;
	private String repeticionesRutina;
	private ArrayList<TransferFactura> listFacturas;
	/**
	 * Constructor sin parametros.
	 */
	public Controlador()
	{
		this.modelo = null;
	}
	
	/**
	 * Constructor con parametros.
	 * @param modelo InterfaceAppService - Referencia al modelo de la aplicacion
	 */
	public Controlador(AppServiceGym modelo)
	{
		setModelo(modelo);
	}
	
	 /**
     * Establece el modelo que esta controlando esta clase. 
     * @param elModelo el modelo a controlar
     */
    public void setModelo(AppServiceGym elModelo)
    {
	        this.modelo = elModelo;
	}
    
    /**
     * Establece la referencia a la vista principal. 
     * @param laVista Referencia a la vista principal.
     */
    public void setVistaPrincipal(VistaPrincipal laVista)
    {
	        this.vista = laVista;
	}
    
    

	private void cambiarModelo(Component fuente) 
	{
		
		if(fuente.getName().equals("jButtonConectar"))
        {
			if(tryLogin())
			{
				
				this.vista.getLoginPanel().setLoginMsg("Iniciando aplicacion en modo " + this.modoArranqueToStr(), Color.RED);
				selector();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
				this.vista.getLoginPanel().setLoginMsg("Correo o Password incorrectos", Color.red);
        }
		else if(fuente.getName().equals("jButtonCancelar"))
			requestExit();
		else if(fuente.getName().equals("jTextFieldUsuario"))
		{
    		JTextField campo=(JTextField)fuente;
    		this.correoLogin = campo.getText();
		}
		else if(fuente.getName().equals("jTextFieldContrasena"))
		{
    		JTextField campo=(JTextField)fuente;
    		this.contrasenaLogin = campo.getText();
		}else if(fuente.getName().equals("jFileMenuSocioMiHorario"))
		{//TODO:
			nuevoSocio = (TransferSocio) this.usrSistema;
			this.horarioDelUsuario = this.modelo.buscarHorarioSocio(nuevoSocio);
			horarioDelDia =this.modelo.buscarHorarioSocio(nuevoSocio);
			this.vista.getSocioPanel().verMiHorario(this.horarioDelUsuario);
		}
		else if(fuente.getName().equals("jTableVistaSocio"))
			//TODO:
		{
				JTable table = (JTable)fuente;
			JTableHeader th = table.getTableHeader();  
	        TableColumnModel tcm = th.getColumnModel();  
	        TableColumn tc = tcm.getColumn(0);
	        if(tc.getHeaderValue().toString().equalsIgnoreCase("Nombre")) // tabla de entrenadores.
	        {
	        	this.entrenadorSeleccionado = this.listEntrenadores.get(table.convertRowIndexToModel(table.getSelectedRow()));
	        }
	        else if(tc.getHeaderValue().toString().equalsIgnoreCase("Rutina")) 
	            this.rutinaSeleccionada = this.listRutinas.get(table.convertRowIndexToModel(table.getSelectedRow())); 
	        else // Tabla de actividades.
	        	 this.actividadSeleccionada = this.horarioDelDia.getActividades().get(table.convertRowIndexToModel(table.getSelectedRow()));
		}
		else if(fuente.getName().equals("jButtonApuntarseVistaSocio"))
		  {
		   boolean apuntarse=true;
		   TransferSocio socio = (TransferSocio) this.usrSistema;
		   TransferHorario horario = this.modelo.buscarHorarioSocio(socio);
		   if(horario == null) // El usuario no tiene horario.
		   {
		    this.modelo.insertaHorarioSocio(socio);
		    horario = this.modelo.buscarHorarioSocio(socio);
		   }
		   else
		   {
		    this.horarioDelUsuario = horario;
		    for(int i=0; i<horario.getActividades().size(); i++){
		     if(this.horarioDelUsuario.getActividades().get(i).getHoraInicio().equals(this.actividadSeleccionada.getHoraInicio())){
		      apuntarse=false;
		     }
		    }
		    if(apuntarse==true){
		     this.modelo.insertaUsuarioActividad((TransferSocio) this.usrSistema, this.actividadSeleccionada); // El usuario ahora tiene la actividad.
		     this.modelo.insertaHorarioActividad(this.horarioDelUsuario, this.actividadSeleccionada); // El horario del usuario tiene la actividad.
		     this.vista.muestraVentanita("Se ha apuntado a una nueva actividad");
		    }else this.vista.muestraVentanita("Error al apuntarse");
		   }
		  }
		else if(fuente.getName().equals("jFileMenuSocioHorarioSistema"))
		{
			this.horarioDelDia = this.modelo.buscarHorarioOficial();
			this.vista.getSocioPanel().verListaActividades(this.horarioDelDia);
		}
		else if(fuente.getName().equals("jButtonVerEntrenadores"))
		{
			this.listEntrenadores = (ArrayList<TransferEntrenador>) this.modelo.mostrarEntrenadores();
			this.vista.getSocioPanel().gestionListaEntrenador(this.listEntrenadores);
		}
		else if(fuente.getName().equals("jButtonEntrenamientoPersonal"))
			{
		//   TransferActividad actividad = new TransferActividad()
		   if(this.entrenadorSeleccionado!=null &&this.usrSistema!=null ){
		    boolean entrenamiento=false;
		    for(int i=0; i<this.horarioDelUsuario.getActividades().size(); i++){
		     if(this.horarioDelUsuario.getActividades().get(i).getNombre().equalsIgnoreCase("Entrenamiento Personal")) entrenamiento=true;
		    }
		    if(entrenamiento) this.vista.muestraVentanita("Ya tienes un entrenamiento personal para hoy");
		    else{
		     this.modelo.entrenamientoPersonal(this.entrenadorSeleccionado, (TransferSocio)this.usrSistema);
		     this.vista.muestraVentanita("Correcto");
		    }
		    
		    }else{this.vista.muestraVentanita("No ha seleccionado ninguna fila");}
			  }
		else if(fuente.getName().equals("jTableActividadesSocio"))
		{
			JTable table = (JTable)fuente;
			this.actividadSeleccionada = this.act.get(table.convertRowIndexToModel(table.getSelectedRow()));
		}
		else if(fuente.getName().equals("jMenuVistaAdminGestionSocios")){
		
			this.socios=(ArrayList<TransferSocio>)this.modelo.mostrarSocios();
			this.vista.getAdminpanel().gestionSocio(socios);
			this.cdGestion = 1;
			
		}
		else if(fuente.getName().equals("jTableVistaAdmin")){ 
			JTable table = (JTable)fuente;
			switch(this.cdGestion)
			{
				case 1:{
					this.usuarioSeleccionado = this.socios.get(table.convertRowIndexToModel(table.getSelectedRow()));
					break;
				}
				case 2:{
					this.usuarioSeleccionado = this.entrenadores.get(table.convertRowIndexToModel(table.getSelectedRow()));
					break;
					
				}
				case 3:{
					this.usuarioSeleccionado= this.actividades.getActividades().get(table.convertRowIndexToModel(table.getSelectedRow()));
					break;
				}
			}
			
			
		}
		else if(fuente.getName().equals("jMenuVistaAdminGestionarEntrenadores")){
			this.entrenadores = (ArrayList<TransferEntrenador>)this.modelo.mostrarEntrenadores();
			this.vista.getAdminpanel().gestionEntrenador(entrenadores);
			this.cdGestion = 2;
			 
		}
		else if(fuente.getName().equals("btnVer"))
		{
			switch(this.cdGestion)
			{
				case 1:{
					if(this.usuarioSeleccionado!=null){
					
					VerModificarTipo vmt = new VerModificarTipo("ver",(TransferSocio) this.usuarioSeleccionado);
					vmt.fijarControlador(this);
					vmt.arranca();	
					
				} else{this.vista.muestraVentanita("No ha seleccionado ninguna fila");}
					break;
				}
				case 2:{
					if(this.usuarioSeleccionado!=null){
						
					VerModificarTipo vmt = new VerModificarTipo("ver",(TransferEntrenador) this.usuarioSeleccionado);
					vmt.fijarControlador(this);
					vmt.arranca();
				} else{this.vista.muestraVentanita("No ha seleccionado ninguna fila");}
					break;
				}
			}
		}
		else if(fuente.getName().equals("jButtonModificar"))
		{
			switch(this.cdGestion)
			{
				case 1:{
					if( this.usuarioSeleccionado!=null){
					
					vmt = new VerModificarTipo("modificar",(TransferSocio) this.usuarioSeleccionado);
					vmt.fijarControlador(this);
					vmt.arranca();
					
				} else{this.vista.muestraVentanita("No ha seleccionado ninguna fila");}
					break;
				}
				case 2:{
					
					if( this.usuarioSeleccionado!=null){
						
					
					vmt = new VerModificarTipo("modificar",(TransferEntrenador) this.usuarioSeleccionado);
					vmt.fijarControlador(this);
					vmt.arranca();
					
					} else{this.vista.muestraVentanita("No ha seleccionado ninguna fila");}
					break;
				}
			}
			
		}
		else if(fuente.getName().equals("jTextNombre"))
		{
			JTextField jTextField = (JTextField)fuente;
			this.modificarNombre = jTextField.getText();
		}
		else if(fuente.getName().equals("jTextApellidos"))
		{
			JTextField jTextField = (JTextField)fuente;
			this.modificarApellidos= jTextField.getText();
		}
		else if(fuente.getName().equals("jTextTlf"))
		{
			JTextField jTextField = (JTextField)fuente;
			this.modificarTelefono= jTextField.getText();
		}
		else if(fuente.getName().equals("jTextDireccion"))
		{
			JTextField jTextField = (JTextField)fuente;
			this.modificarDireccion= jTextField.getText();
		}
		else if(fuente.getName().equals("jButtonGuardarModificar"))
		  {
		   if (JOptionPane.showConfirmDialog(null, "Estas seguro?") == JOptionPane.OK_OPTION) {
		    
		   switch(this.cdGestion)
		   {
		   case 1:{
		    
		    TransferSocio socio = (TransferSocio) this.usuarioSeleccionado;
		    if((this.modificarNombre!=null)&&(this.modificarApellidos!=null)
		      &&(this.modificarDireccion!=null)){
		     
		     socio.setNombre(this.modificarNombre);
//		    if(this.modificarApellidos!=null)
		     socio.setApellidos(this.modificarApellidos);
		    //socio.setTelefono(Integer.parseInt(this.modificarTelefono));
//		    if(this.modificarDireccion!=null)
		     socio.setDireccion(this.modificarDireccion);
		    this.modelo.modificarUsuario((TransferSocio)this.usuarioSeleccionado);
		    this.socios=(ArrayList<TransferSocio>)this.modelo.mostrarSocios();
		    this.vista.getAdminpanel().gestionSocio(socios);
		    
		    this.cdGestion = 1;
		    vmt.dispose();
		    }else{this.vista.muestraVentanita("Debe rellenar todos los campos.");}
		   break;
		  }
		  case 2:{
		    TransferEntrenador entrenador = (TransferEntrenador) this.usuarioSeleccionado;
		    if((this.modificarNombre!=null)&&(this.modificarApellidos!=null)
		      &&(this.modificarDireccion!=null)){
		     
//		     if(this.modificarNombre!=null)
		      entrenador.setNombre(this.modificarNombre);
//		     if(this.modificarApellidos!=null)
		      entrenador.setApellidos(this.modificarApellidos);
		     //entrenador.setTelefono(Integer.parseInt(this.modificarTelefono));
//		     if(this.modificarDireccion!=null)
		      entrenador.setDireccion(this.modificarDireccion);
		     this.modelo.modificarUsuario((TransferEntrenador)this.usuarioSeleccionado);
		     this.entrenadores = (ArrayList<TransferEntrenador>)this.modelo.mostrarEntrenadores();
		     this.vista.getAdminpanel().gestionEntrenador(entrenadores);
		     this.cdGestion = 2;
		     vmt.dispose();
		    }else{this.vista.muestraVentanita("Debe rellenar todos los campos.");}
		   break;
		  }
		   
		   }
		   }
			
		}else if(fuente.getName().equals("jBotonDarBaja")){
			//TODO:
			if (JOptionPane.showConfirmDialog(null, "Estas seguro?") == JOptionPane.OK_OPTION) {
			 switch(this.cdGestion)
		       {
		       case 1:{
		         
		         if(this.usuarioSeleccionado!=null){
		         
		          this.modelo.darBaja(((TransferSocio) this.usuarioSeleccionado).getCorreo(), 1);
		      
		          ArrayList<TransferSocio>socios=(ArrayList<TransferSocio>) this.modelo.mostrarSocios();
		          this.vista.muestraVentanita("Socio Eliminado");
		      
		          this.vista.getAdminpanel().gestionSocio(socios);
		          
		      
		         } else{this.vista.muestraVentanita("No ha seleccionado ninguna fila");}
		         break;
		        }
		        case 2:{
		         if((this.usuarioSeleccionado!=null)){
		        
		          this.modelo.darBaja(((TransferEntrenador) this.usuarioSeleccionado).getCorreo(), 2);
		          this.entrenadores = (ArrayList<TransferEntrenador>)this.modelo.mostrarEntrenadores();
		          this.vista.getAdminpanel().gestionEntrenador(entrenadores);
		          this.vista.muestraVentanita("Entrenador Eliminado");
		        }else{
		         this.vista.muestraVentanita("No ha seleccionado ninguna fila");
		         }
		         
		         break;
		        }
	      }
			}
		}
		else if(fuente.getName().equals("jMenuVistaAdminDarAlta")){
			this.vista.getAdminpanel().gestionAlta();
		}
		else if(fuente.getName().equals("jButtonDarAltaEntrenador")){
			this.cdGestion=2;
			da = new VistaDarDeAlta(this.cdGestion);
			da.fijarControlador(this);
			da.arranca();
//			this.vista.getAdminpanel().gestionAltaUsuarios(this.cdGestion);
			//this.modelo.darAlta(this.vista.getAdminpanel().getEntrenadorNuevo());
		}
		else if(fuente.getName().equals("jButtonDarAltaSocio")){
			this.cdGestion=1;
			da = new VistaDarDeAlta(this.cdGestion);
			da.fijarControlador(this);
			da.arranca();
			//this.vista.getAdminpanel().gestionAltaUsuarios(this.cdGestion);
		}
		else if(fuente.getName().equals("JtextNameDA")){
			JTextField jTextField = (JTextField)fuente;
			this.modificarNombre= jTextField.getText();
		}
		else if(fuente.getName().equals("JtextDniDA")){
			JTextField jTextField = (JTextField)fuente;
			this.dni= jTextField.getText();
		}
		else if(fuente.getName().equals("JtextCorreoDA")){
			JTextField jTextField = (JTextField)fuente;
			this.correo= jTextField.getText();
		}
		else if(fuente.getName().equals("JtextContrasenaDA")){
			JTextField jTextField = (JTextField)fuente;
			this.contrasenaLogin= jTextField.getText();
		}
		else if(fuente.getName().equals("JtextApellidosDA")){
			JTextField jTextField = (JTextField)fuente;
			this.modificarApellidos= jTextField.getText();
		}
		else if(fuente.getName().equals("JtextCuotaDA")){
			JTextField jTextField = (JTextField)fuente;
			this.cuota= jTextField.getText();
		}
		else if(fuente.getName().equals("JtextCuentaDA")){
			JTextField jTextField = (JTextField)fuente;
			this.cuenta= jTextField.getText();
		}
		else if(fuente.getName().equals("jButonGuardarDarAlta")){
			if(this.cdGestion==2){
				if(this.modificarNombre!=null && this.modificarApellidos!=null && this.correo!=null 
						&& this.contrasenaLogin!=null && this.dni!=null &&  this.cuenta!=null){
					
					entrenadorSeleccionado = new TransferEntrenador(this.modificarNombre, this.modificarApellidos,this.correo, this.contrasenaLogin,this.dni,Integer.parseInt(this.cuenta), Sueldo.SUELDOBASE,"",1);
					this.modelo.darAlta(entrenadorSeleccionado);
					da.dispose();
				}else{this.vista.muestraVentanita("Debe rellenar todos los campos.");}
			}
			if(this.cdGestion==1){
				if(this.modificarNombre!=null && this.modificarApellidos!=null && this.correo!=null 
						&& this.contrasenaLogin!=null && this.dni!=null &&  this.cuenta!=null){
					
					Cuota cu = Cuota.MENSUAL;
				switch(Integer.parseInt(this.cuenta)){
				case 1: cu= Cuota.MENSUAL;
					break;
				case 2: cu= Cuota.TRIMESTRAL;
					break;
				case 3: cu= Cuota.SEMESTRAL;
					break;
				case 4: cu= Cuota.ANUAL;
					break;
				
				}
					
					nuevoSocio = new TransferSocio(this.modificarNombre,this.modificarApellidos,this.correo, this.contrasenaLogin,this.dni, Integer.parseInt(this.cuenta),cu,"",1);
					this.modelo.darAlta(nuevoSocio);
					this.vista.muestraVentanita("Guardado Correctamente");
					da.dispose();
				}else{this.vista.muestraVentanita("Debe rellenar todos los campos.");}
			}
		}
		else if(fuente.getName().equals("jMenuHorarioAdmin"))
		  {
			this.cdGestion=3;
			this.actividades=this.modelo.buscarHorarioOficial();
		   this.vista.getAdminpanel().gestionHorario(this.modelo.buscarHorarioOficial());
		  }
		else if(fuente.getName().equals("jBorraAc"))
		  {
			if(usuarioSeleccionado!=null){ 
			TransferActividad actividad=(TransferActividad) usuarioSeleccionado;
			 if (JOptionPane.showConfirmDialog(null,	"Estas seguro?") == JOptionPane.OK_OPTION) {
				this.modelo.borraActividad(String.valueOf(actividad.getIdActividad()));
				this.actividades=this.modelo.buscarHorarioOficial();
				this.vista.getAdminpanel().gestionHorario(actividades);
				}
			 }else this.vista.muestraVentanita("Debe seleccionar una fila");
		  }
		else if(fuente.getName().equals("btnNewAc")){
			vActividad = new VistaNuevaActividad();
			vActividad.fijarControlador(this);
			ArrayList<TransferEntrenador> listEntrenadores = (ArrayList<TransferEntrenador>) this.modelo.buscarEntrenadores();
			vActividad.fijarEntrenadores(listEntrenadores);
			vActividad.arranca();
			
		}
		else if(fuente.getName().equals("JtextNameAC")){
			JTextField jTextField = (JTextField)fuente;
			this.modificarNombre= jTextField.getText();
			
		}
		else if(fuente.getName().equals("JtextEntrenadorAC")){
			JTextField jTextField = (JTextField)fuente;
			this.correo= jTextField.getText();
			this.entrenadorSeleccionado=(TransferEntrenador) this.modelo.buscarEntrenador(this.correo);
			
		}
		else if(fuente.getName().equals("JtextInicioAC")){
			JTextField jTextField = (JTextField)fuente;
			this.inicio= jTextField.getText();
			
		}
		else if(fuente.getName().equals("JtextFinAC")){
			JTextField jTextField = (JTextField)fuente;
			this.fin= jTextField.getText();
			
		}
		else if(fuente.getName().equals("jButonGuardarActividad")){
			TransferActividad actividad = new TransferActividad(this.modificarNombre,this.entrenadorSeleccionado, this.inicio, this.fin);
			/*
			if(!comprobarTieneActividad(this.entrenadorSeleccionado,actividad))
			{
				
				TransferHorario horarioAux = this.modelo.buscarHorarioEntrenador(this.entrenadorSeleccionado);
				ArrayList<TransferActividad> listActividades = horarioAux.getActividades();
				listActividades.add(actividad);
			}
			this.modelo.creaActividad(actividad);
			this.actividades=this.modelo.buscarHorarioOficial();
			this.vista.getAdminpanel().gestionHorario(actividades);
			this.vista.muestraVentanita("Actividad guardada correctamente");
			vActividad.dispose(); */
		
		}
		else if(fuente.getName().equals("jFileMenuEntrenadorMiHorario"))
		{
			TransferEntrenador entrenador= (TransferEntrenador) this.usrSistema;
			this.horarioDelUsuario = this.modelo.buscarHorarioEntrenador(entrenador);
			this.vista.getEntrenadorPanel().verMiHorario(this.horarioDelUsuario);
		}
		else if(fuente.getName().equals("jFileMenuEntrenadorHorarioSistema"))
		{
			this.horarioDelDia = this.modelo.buscarHorarioOficial();
			this.vista.getEntrenadorPanel().verListaActividades(this.horarioDelDia);
		}
		else if(fuente.getName().equals("jButtonGestionarRutinas"))
		{
			this.listRutinas = (ArrayList<TransferRutina>) this.modelo.buscarRutinas();
			this.vista.getEntrenadorPanel().gestionRutinas(this.listRutinas);
		}
		else if(fuente.getName().equals("jTableVistaEntrenador"))
		{
			JTable table = (JTable)fuente;
			JTableHeader th = table.getTableHeader();  
	        TableColumnModel tcm = th.getColumnModel();  
	        TableColumn tc = tcm.getColumn(1);
	        if(tc.getHeaderValue().toString().equalsIgnoreCase("Ejercicios")) // tabla de rutinas.
	        {
	        	this.rutinaSeleccionada= this.listRutinas.get(table.convertRowIndexToModel(table.getSelectedRow()));
	        }

		}
		else if(fuente.getName().equals("jButtonEliminarRutina"))
		{
			boolean success = true;
			if(this.rutinaSeleccionada!=null){
				success = this.modelo.eliminarRutina(this.rutinaSeleccionada);
				if(!success)
				{
					 this.vista.muestraVentanita("No ha seleccionado ninguna fila");
				}
				if (JOptionPane.showConfirmDialog(null,	"Estas seguro?") == JOptionPane.OK_OPTION) {
					this.listRutinas = (ArrayList<TransferRutina>) this.modelo.buscarRutinas();
					this.vista.getEntrenadorPanel().gestionRutinas(this.listRutinas);
					this.vista.muestraVentanita("Eliminado Correctamente");
				}
				
			}else{
		    	   this.vista.muestraVentanita("No ha seleccionado ninguna fila");
		    	   }
		}
		else if(fuente.getName().equals("jButtonCrearRutina"))
		{
			this.vcr = new VistaCrearRutina();
			this.vcr.fijarControlador(this);
			this.vcr.arranca();
			//this.listEjercicios = (ArrayList<TransferEjercicio>) this.modelo.buscarEjerciciosRutina(this.rutinaSeleccionada);
			this.listEjercicios = (ArrayList<TransferEjercicio>) this.modelo.buscarEjercicios();
			this.vcr.setTableEjercicios(this.listEjercicios);
			this.listEjerciciosEscogidos = new ArrayList<TransferEjercicio>();

		}// VISTA CREARRUTINA
		else if(fuente.getName().equals("jButtonGuardarRutina"))
		{
			if(this.nombreRutina!=null){
				TransferRutina rutina = new TransferRutina(this.nombreRutina, this.listEjerciciosEscogidos);
				boolean success = this.modelo.insertarRutina(rutina);
				if(!success)
					this.vista.muestraVentanita("Debes rellenar todos los campos");
				else
				{
					this.listRutinas = (ArrayList<TransferRutina>) this.modelo.buscarRutinas();
					this.vista.getEntrenadorPanel().gestionRutinas(this.listRutinas);
					this.vista.muestraVentanita("Guardado Correctamente");
					vcr.dispose();
				}
			}else this.vista.muestraVentanita("Debes rellenar el campo de nombre rutina");
		}
		else if(fuente.getName().equals("jButtonAddEjercicio"))
		  {
		   
		   boolean ok=true;
		   for(int i=0; i<listEjerciciosEscogidos.size();i++){
		    if(listEjerciciosEscogidos.contains(ejercicioSeleccionado)) ok=false;
		   }
		   if(this.ejercicioSeleccionado == null || this.repeticionesRutina.isEmpty())
		   {
		     this.vista.muestraVentanita("Debe tener una fila seleccionada y un valor en las repeticiones");
		   }else if(ok==false){
		    this.vista.muestraVentanita("No se puede a�adir el mismo ejercicio varias veces");
		   }
		   else
		   {
		    // Controlar que repeticiones rutina tenga valor.
		    this.ejercicioSeleccionado.setRepeticiones(this.repeticionesRutina);
		    this.listEjerciciosEscogidos.add(this.ejercicioSeleccionado);
		    this.vcr.setTableEjerciciosEscogidos(listEjerciciosEscogidos);
		   }
		   
		  }
		else if(fuente.getName().equals("jTextFieldNombreRutina"))
		{
			JTextField jTextField = (JTextField)fuente;
			this.nombreRutina = jTextField.getText();
		}
		else if(fuente.getName().equals("jTextFieldRepeticiones"))
		{
			JTextField jTextField = (JTextField)fuente;
			this.repeticionesRutina = jTextField.getText();
		}
		else if(fuente.getName().equals("jTableEjercicios"))
		{
			JTable table = (JTable)fuente;
			this.ejercicioSeleccionado = this.listEjercicios.get(table.convertRowIndexToModel(table.getSelectedRow()));
		}
		else if(fuente.getName().equals("jButtonVerRutina"))
		{//TODO
			VistaVerRutina vvr = new VistaVerRutina();
			if(this.rutinaSeleccionada!=null){
				vvr.setTableEjercicios(this.rutinaSeleccionada.getEjercicios());
				vvr.setNombreRutina(this.rutinaSeleccionada.getNombre());
				vvr.arranca();
			}else{
		    	   this.vista.muestraVentanita("No ha seleccionado ninguna fila");
		    	   }
		}
		else if(fuente.getName().equals("jFileMenuSocioVerRutinas"))
		{
			this.listRutinas = (ArrayList<TransferRutina>) this.modelo.buscarRutinas();
			this.vista.getSocioPanel().verRutinas(this.listRutinas);
		}
		else if(fuente.getName().equals("jButtonVerRutinaSocio"))
		{
			
			VistaVerRutina vvr = new VistaVerRutina();
			vvr.setNombreRutina(this.rutinaSeleccionada.getNombre());
			vvr.setTableEjercicios(this.rutinaSeleccionada.getEjercicios());
			vvr.arranca();
		}else if(fuente.getName().equals("jButtonFacturasSocios"))
		  {
			   this.listFacturas = (ArrayList<TransferFactura>) this.modelo.buscarFacturasSocio((TransferSocio) this.usuarioSesion);
			   this.vista.getSocioPanel().verFacturas(this.listFacturas);
		  }
		else if(fuente.getName().equals("jButtonDesapuntarseSocio")){
			//TODO: actividadSeleccionada,horarioDelDia,String.valueOf(actividad.getIdActividad())
//			if(actividadSeleccionada!=null){
//				 if (JOptionPane.showConfirmDialog(null,	"Estas seguro?") == JOptionPane.OK_OPTION) {
//					 	this.modelo.borraActividad(String.valueOf(this.actividadSeleccionada.getIdActividad()));
//						}
//			}else this.vista.muestraVentanita("Debe seleccionar una Actividad");
//			
		}
		else if(fuente.getName().equals("jButtonVerFacturasEntrenador"))
		  {
		   this.listFacturas = (ArrayList<TransferFactura>) this.modelo.buscarFacturasEntrenador((TransferEntrenador) this.usuarioSesion);
		   this.vista.getEntrenadorPanel().verFacturas(this.listFacturas);
		   
		  }else if(fuente.getName().equals("jMenuVistaUnlogin")){
			  if (JOptionPane.showConfirmDialog(null, "Estas seguro?") == JOptionPane.OK_OPTION)
				  System.exit(0);
		  }
		  else if(fuente.getName().equals("jTableVistaNuevaActividad"))
		  {
			  JTable table = (JTable)fuente;
			  ArrayList<TransferEntrenador> listEntrenadores = (ArrayList<TransferEntrenador>) this.modelo.buscarEntrenadores();
			  this.entrenadorSeleccionado = listEntrenadores.get(table.convertRowIndexToModel(table.getSelectedRow()));
			  
		  }
		
		
	}
    
	/**
	 * Comprueba si el entrenador tiene una actividad que entre en conflicto con la nueva.
	 */
    private boolean comprobarTieneActividad(TransferEntrenador entrenadorSeleccionado2,TransferActividad actividad) 
    {
    	boolean fail = false;
    	ArrayList<TransferActividad> listActividades = entrenadorSeleccionado2.getHorarioEntreno().getActividades();
    	for(int i = 0; i < listActividades.size(); i++)
    	{
    		if(listActividades.get(i).comprobarConcurrecia(actividad))
    			fail = true;
    	}
		return fail;
	}

	/**
     * Se encargara de inicializar los componentes de la vista relacionados con el modo de ejecucion de la aplicacion.
     */
	private void selector() {
		switch(cdArranque)
		{
		case 1:	{
			
			VistaSocio vs = new VistaSocio(usuarioSesion);
			this.vista.setSocioPanel(vs);
			this.vista.getSocioPanel().fijarControlador(this);
			//this.vista.setResizable(true);
			//this.vista.setBounds(10, 10, 798,582);
			this.vista.add(vs);
			this.vista.setJMenuBar(vs.getJMenu());
			this.vista.setContentPane(vs.getContentPane());
			this.vista.revalidate();
			this.vista.repaint();
			this.vista.getLoginPanel().setVisible(false);
			break;
			}
		case 2: {
			// Modo entrenador
			
			VistaEntrenador ve = new VistaEntrenador(usuarioSesion);
			this.vista.setEntrenadorPanel(ve);
			this.vista.getEntrenadorPanel().fijarControlador(this);
			this.vista.setBounds(10, 10, 798,582);
			this.vista.add(ve);
			this.vista.setJMenuBar(ve.getJMenu());
			this.vista.setContentPane(ve.getContentPane());
			this.vista.revalidate();
			this.vista.repaint();
			this.vista.getLoginPanel().setVisible(false);

			break;
		}
		case 3: {
			// Modo admin.
			
			VistaAdmin va = new VistaAdmin(usuarioSesion);
			this.vista.setAdminpanel(va);
			this.vista.getAdminpanel().fijarControlador(this);
			this.vista.setBounds(10, 10, 798,582);
			this.vista.add(va);
			this.vista.setJMenuBar(va.getJMenu());
			this.vista.setContentPane(va.getContentPane());
			this.vista.revalidate();
			this.vista.repaint();
			this.vista.getLoginPanel().setVisible(false);

			break;
		}
		}
		// TODO Auto-generated method stub
		
	}
	
	
	private void checkPayEntrenador(TransferObject usuario) 
	{
		String fechaFin = this.modelo.obtenerUltimoSueldo((TransferEntrenador)usuario);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaF = null;
		try {
			fechaF = formatoDelTexto.parse(fechaFin);
		} catch (ParseException ex) {
		ex.printStackTrace();
		}
		Date dateActual = new Date();
		if(dateActual.compareTo(fechaF) > 0)
		{
			this.modelo.pagarNuevoSueldo((TransferEntrenador)usuario);
			System.out.println("La fecha es menor");
		}
		
		
	}

	private void checkPaySocio(TransferObject usuario) 
	{
		String fechaFin = this.modelo.obtenerUltimaCuota((TransferSocio)usuario);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaF = null;
		try {
			fechaF = formatoDelTexto.parse(fechaFin);
		} catch (ParseException ex) {
		ex.printStackTrace();
		}
		Date dateActual = new Date();
		if(dateActual.compareTo(fechaF) > 0)
		{
			this.modelo.pagarNuevaCuota((TransferSocio)usuario);
		}
	}

	/**
	 * Cierre de la aplicacion.
	 */
	private void requestExit() {
		System.exit(0);
	}

	/**
	 * Este metodo busca que tipo de usuario acaba de intentar de conectarse y lo almacena en memoria.
	 * @return True si ha encontrado el usuario, False en caso contrario.
	 */
	private boolean tryLogin() 
	{
		boolean success = false;
		TransferSocio socio = (TransferSocio) this.modelo.buscarSocio(this.correoLogin);
		if(socio != null)
		{
			this.usrSistema = socio;
			this.usuarioSesion = socio;
			this.cdArranque = 1;
			success = this.contrasenaLogin.equals(socio.getContrasena());
		}
		else
		{
			TransferEntrenador entrenador = (TransferEntrenador) this.modelo.buscarEntrenador(this.correoLogin);
			if(entrenador != null)
			{
				this.usrSistema = entrenador;
				this.cdArranque = 2;
				this.usuarioSesion = entrenador;
				success = this.contrasenaLogin.equals(entrenador.getContrasena()); 
			}
			else
			{
				TransferAdministrador admin = (TransferAdministrador) this.modelo.buscarAdministrador(this.correoLogin);
				if(admin != null)
				{
					this.usrSistema = admin;
					this.cdArranque = 3;
					this.usuarioSesion = admin;
					success = this.contrasenaLogin.equals(admin.getContrasena());
				}
			}
		}
		return success;
	}

	/**
     * Método para tratar los eventos de forma genérica.
     * Se encarga tanto de solicitar la modificaci�n al modelo como de informar a la vista
     * @param e el evento a tratar
     */
    private void tratarEventoGenerico(EventObject event){
        Component fuente = (Component) event.getSource(); // el que gener� el evento
		System.err.println(fuente.getName());
        cambiarModelo(fuente);
	}

    private String modoArranqueToStr()
    {
    	String str = null;
    	switch(this.cdArranque)
    	{
	    	case 1:
	    		str = "Socio";
	    		break;
	    	case 2:
	    		str = "Entrenador";
	    		break;
	    	case 3:
	    		str = "Administrador";
	    		break;
			default:
	    		str = "";
	    		break;
    	}
    	return str;
    }
    
	@Override
	public void actionPerformed(ActionEvent a) {
		tratarEventoGenerico(a);
	}
	
	@Override
	public void keyReleased(KeyEvent a) {
		tratarEventoGenerico(a);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		tratarEventoGenerico(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
