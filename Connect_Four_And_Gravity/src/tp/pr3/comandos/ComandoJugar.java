package tp.pr3.comandos;

import tp.pr3.Main;
import tp.pr3.control.Controlador;
import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaGravity;

/**
 * Clase con el comando para jugar co o c4 o gr
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */

public class ComandoJugar implements Comando{
	
	/**
	 * Referencia al numero de filas y columnas del tablero
	 */
	private int numFilas;
	private int numColumnas;
	
	/**
	 * Referencia al controlador
	 */
	private Controlador controlador;
	
	/**
	 * Contiene el tipo de juego
	 */
	private String tipo="";
	
	/**
	 * Constructor que recibe una referencia al controlador..
	 * @param controlador
	 */
	public ComandoJugar(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public boolean analizar(String cadena) {
		boolean ok=false;
		String[] cadenas = cadena.split(" ");
		
		if(cadenas.length == 2){

			if(cadenas[0].equalsIgnoreCase("Jugar") && (cadenas[1].equalsIgnoreCase("gr") 
									|| cadenas[1].equalsIgnoreCase("co")
									|| cadenas[1].equalsIgnoreCase("c4"))){
				tipo=cadenas[1];
				numColumnas = 10;
				numFilas = 10;
				ok=true;
			}
		}
		if(cadenas.length == 4){
			if(cadenas[0].equalsIgnoreCase("Jugar") && cadenas[1].equalsIgnoreCase("gr") && 
			cadenas[2].matches("[0-9]*") && cadenas[2].matches("[0-9]*")){
				tipo=cadenas[1];
				numColumnas = Integer.valueOf(cadenas[2]);
				numFilas = Integer.valueOf(cadenas[3]);
				ok=true;
			}
		}
		return ok;
	}

	@Override
	public void ejecutar() {
		if(tipo.equalsIgnoreCase("gr"))
			controlador.setFactoria(new FactoriaGravity(numColumnas, numFilas));
		else if(tipo.equalsIgnoreCase("co"))
			controlador.setFactoria(new FactoriaComplica());
		else if(tipo.equalsIgnoreCase("c4"))
			controlador.setFactoria(new FactoriaConecta4());

    	controlador.getPartida().reset(controlador.getF().creaReglas());
    	System.out.println("Partida reiniciada.");
		
	}

	@Override
	public boolean terminar() {
		return false;
	}

	@Override
	public String ayuda() {
		return "JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego." + Main.LINE_SEPARATOR;
	}

}
