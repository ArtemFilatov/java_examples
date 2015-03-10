package tp.pr3.comandos;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr3.Main;
import tp.pr3.control.Controlador;

/**
 * Clase que contiene todo los comandos.
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class GestorComandos {

	private static ArrayList<Comando> comandos;
	
	private boolean salir;
	
	/**
	 * Constructor por defecto
	 * @param controlador - Referencia al modelo.
	 */
	public GestorComandos(Controlador controlador) {
		comandos = new ArrayList<Comando>();
		comandos.add(new ComandoPoner(controlador));
		comandos.add(new ComandoDeshacer(controlador));
		comandos.add(new ComandoReiniciar(controlador));
		comandos.add(new ComandoJugar(controlador));
		comandos.add(new ComandoJugador(controlador));
		comandos.add(new ComandoSalir(controlador));
		comandos.add(new ComandoAyuda(controlador));
		salir = false;
	}

	public boolean ejecutar(String cmd){
		boolean cmdUsado=false;
		int i=0;
		
		while(i<comandos.size() && !cmdUsado){
			if(comandos.get(i).analizar(cmd)){
				comandos.get(i).ejecutar();
				salir =  comandos.get(i).terminar();
				cmdUsado=true;
			}
			else
				i++;
		}
		return cmdUsado;
	}
	
	public boolean terminar(){
		return salir;
	}
	
	/**
	 * @return String - Devuelve la informacion de todas las instrucciones contenidas en el gestor de comandos
	 */
	
	public static String ayudaGestorComandos()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Los comandos disponibles son:" + Main.LINE_SEPARATOR + Main.LINE_SEPARATOR);
		Iterator<Comando> it = comandos.iterator();
		while(it.hasNext())
		{
			sb.append(it.next().ayuda());
		}
		return sb.toString();
	}
}
