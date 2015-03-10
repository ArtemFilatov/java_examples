package scripts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import modelo.DAO.DAOActividad;
import modelo.DAO.DAOHorario;
import modelo.DAO.Excepciones.AlreadyExists;
import modelo.DAO.Excepciones.ConnectionFailure;
import modelo.DAO.Excepciones.DataBaseError;
import modelo.DAO.Excepciones.WrongDataFormat;
import modelo.enums.Sueldo;
import modelo.transfer.TransferActividad;
import modelo.transfer.TransferHorario;
import modelo.transfer.users.TransferEntrenador;



/**
 * Con este script poblaremos la base de datos de actividades.
 * Habra las mismas actividades todas las semanas.
 */

public class PoblarDb 
{
	// Entrenadores de ejemplo.
	public static TransferEntrenador arnold = new TransferEntrenador("", "", "arnold@arnold.com", "","",1,Sueldo.SUELDOBASE,"",1);
	public static TransferEntrenador ronnie = new TransferEntrenador("", "", "ronnie@ronnie.com", "","",1,Sueldo.SUELDOBASE,"",1);
	public static TransferEntrenador michael = new TransferEntrenador("", "", "mike@mike.com", "","",1,Sueldo.SUELDOBASE,"",1);
	public static TransferEntrenador maradona = new TransferEntrenador("", "", "maradona@maradona.com", "","",1,Sueldo.SUELDOBASE,"",1);
	public static TransferEntrenador ronaldinho = new TransferEntrenador("", "", "ronaldinho@ronaldinho.com", "","",1,Sueldo.SUELDOBASE,"",1);
	
	public static void main(String[] args)
	{
		// Cogemos la fecha actual y le damos formato.
		Calendar cal = Calendar.getInstance(); 
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(cal.getTime());
		int i = 0;
		
		while(!formatted.equalsIgnoreCase("2013-07-31"))
		{
			List<TransferActividad> listActividades;
			if(i%2 == 0)
			{
				listActividades = actividades1();
			}
			else
			{
				listActividades = actividades2();
			}
			DAOActividad daoActividad = new DAOActividad();
			
			Iterator<TransferActividad> it = listActividades.iterator();
			while(it.hasNext())
			{
				try {
					daoActividad.inserta(it.next());
				} catch (ConnectionFailure | WrongDataFormat | AlreadyExists | DataBaseError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ArrayList<TransferEntrenador> listEntrenadores = new ArrayList<TransferEntrenador>();
			TransferHorario horario = new TransferHorario(formatted); // Creo el horario
			DAOHorario daoHorario = new DAOHorario();
			try {
				daoHorario.inserta(horario);
				horario = (TransferHorario) daoHorario.buscarHorarioOficial(formatted);// Lo busco para obtener su id.
				int cdHorario = daoHorario.getCdHorario();
				List<TransferActividad> listAct = daoActividad.buscarTodasSinHorario(); // Ya llevan id de actividad.
				Iterator<TransferActividad> it2 = listAct.iterator();
				while(it2.hasNext())
				{
					// Inserto las actividades en el horario.
					TransferActividad auxActividad = it2.next();
					daoHorario.insertaActividadHorario(cdHorario, auxActividad.getIdActividad());
					
					// No nos olvidemos del horario de los entrenadores.
					boolean contiene = false;
					Iterator<TransferEntrenador> itE = listEntrenadores.iterator();
					while(itE.hasNext())
					{
						if(itE.next().equals(auxActividad.getEntrenador()))
							contiene = true;
					}
					if(!contiene) // El entrenador no tiene un horario para hoy.
					{
						listEntrenadores.add(auxActividad.getEntrenador());
						TransferHorario auxHorario = new TransferHorario(formatted);
						daoHorario.insertaHorarioEntrenador(auxHorario, auxActividad.getEntrenador().getCorreo());
						auxHorario = (TransferHorario) daoHorario.buscarHorarioEntrenador(formatted, auxActividad.getEntrenador().getCorreo());
						int cdHorarioAux = daoHorario.getCdHorario();
						daoHorario.insertaActividadHorario(cdHorarioAux, auxActividad.getIdActividad());
					}
					else // Si lo tiene
					{
						TransferHorario auxHorario = (TransferHorario) daoHorario.buscarHorarioEntrenador(formatted, auxActividad.getEntrenador().getCorreo());
						int cdHorarioAux = daoHorario.getCdHorario();
						daoHorario.insertaActividadHorario(cdHorarioAux, auxActividad.getIdActividad());
					}
					
					
				}
			} catch (ConnectionFailure | DataBaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (WrongDataFormat e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlreadyExists e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Incremento del bucle.
			i += 1;
			cal.add(cal.DATE, 1);
			formatted = format1.format(cal.getTime());
			System.out.println("Actividades y horarios creados para la fecha: " + formatted);
		}
	}
	
	public static List<TransferActividad> actividades1()
	{
		TransferActividad a1 = new TransferActividad("Piscina", michael,"10:00:00", "12:00:00");
		TransferActividad a2 = new TransferActividad("Aerobic", arnold,"11:00:00", "12:00:00");
		TransferActividad a3 = new TransferActividad("Piscina", michael,"18:00:00", "19:00:00");
		TransferActividad a4 = new TransferActividad("Spinning", ronaldinho,"18:00:00", "19:00:00");
		TransferActividad a5 = new TransferActividad("Cardio kick boxing", maradona,"19:00:00", "20:00:00");
		List<TransferActividad> listActividades = new ArrayList<TransferActividad>();
		listActividades.add(a1);
		listActividades.add(a2);
		listActividades.add(a3);
		listActividades.add(a4);
		listActividades.add(a5);
		return listActividades;
	}
	
	public static List<TransferActividad> actividades2()
	{
		TransferActividad a1 = new TransferActividad("Piscina", michael,"10:00:00", "12:00:00");
		TransferActividad a2 = new TransferActividad("Aerobic", ronnie,"11:00:00", "12:00:00");
		TransferActividad a3 = new TransferActividad("Piscina", michael,"18:00:00", "19:00:00");
		TransferActividad a4 = new TransferActividad("Spinning", maradona,"18:00:00", "19:00:00");
		TransferActividad a5 = new TransferActividad("Cardio kick boxing", ronaldinho,"19:00:00", "20:00:00");
		List<TransferActividad> listActividades = new ArrayList<TransferActividad>();
		listActividades.add(a1);
		listActividades.add(a2);
		listActividades.add(a3);
		listActividades.add(a4);
		listActividades.add(a5);
		return listActividades;
	}
	

}
