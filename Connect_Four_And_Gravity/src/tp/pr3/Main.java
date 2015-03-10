package tp.pr3;

import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import tp.pr3.control.Controlador;
import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaGravity;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.logica.Partida;

/**
 * Clase que contiene el punto de entrada a la aplicacion
 * 
 * @author Guillermo Martin Duque
 * @author Jesus Vazquez Pigueiras
 * @since Practica 2
 */
public class Main {

	/**
	 * Mensaje de ayuda
	 */
	private static final String	HELP = "Use -h|--help para más detalles.";
	
	private static final String INCORRECTO= "Uso incorrecto: ";
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public static Scanner sc;
	/**
	 * Método principal de la aplicacion.
	 * @param args - Argumentos pasados a la aplicacion. No se utilizan.
	 */
	public static void main(java.lang.String[] args) {
		

		String mode="c4";
		
		int numfil = 10;
		int numcol = 10;
		
		FactoriaTipoJuego factoria = new FactoriaConecta4();
		
		Options options = buildOptions();
		
		CommandLineParser parser = new BasicParser(); 
		
		try {
			
			CommandLine cmdLine = parser.parse(options, args);
	
			if(cmdLine.hasOption("h")){
				org.apache.commons.cli.HelpFormatter formatter = new org.apache.commons.cli.HelpFormatter();
				formatter.printHelp( Main.class.getName(), options, true); // 1-> sintaxis de la aplicacion 2-> options 3-> sentencia uso
				System.exit(0);
			}
			if(cmdLine.hasOption("g"))
				mode = cmdLine.getOptionValue("g", "c4");

			if(cmdLine.hasOption("x"))
				numcol = Integer.parseInt(cmdLine.getOptionValue("x", "10"));
			
			if(cmdLine.hasOption("y"))
				numfil = Integer.parseInt(cmdLine.getOptionValue("y", "10"));
	
		} catch (org.apache.commons.cli.ParseException e) {
			System.err.println(INCORRECTO + e.getMessage());
			System.err.println(HELP);
			System.exit(1);

		} catch (NumberFormatException e) {
			System.err.println("Error valores no validos");
		}
		
		// Comprobar errores 
		if(!mode.equals("c4") && !mode.equals("co") && !mode.equals("gr")){
			System.err.println(INCORRECTO + "Juego '" + getArgumento(1, args) + "' incorrecto.");
			System.err.println(HELP);
			System.exit(1);
		}
		
		if( (mode.equals("c4") || mode.equals("co")) &&  args.length > 2){
			
			System.err.println(INCORRECTO + " Argumentos no entendidos: "+ getArgumento(2, args));
			System.err.println(HELP);
			System.exit(1);
		}
		
		if(mode.equals("gr")  &&  (args.length != 2 &&  args.length != 4 && args.length != 6)){	
			System.err.println(INCORRECTO + " Argumentos no entendidos: "+ getArgumento(2, args));
			System.err.println(HELP);
			System.exit(1);
		}
		
		// Juegos disponibles
		if(mode.equals("c4")){
			factoria = new FactoriaConecta4();
		}
		
		else if(mode.equals("co")){
			factoria = new FactoriaComplica();
		}
		
		else if(mode.equals("gr")){
			factoria = new FactoriaGravity(numcol, numfil);
		}
			
		
        // Empieza la partida
		Partida p = new Partida(factoria.creaReglas());
		sc = new Scanner(System.in);
		Controlador controlador = new Controlador(factoria, p, sc);
		controlador.run();
		sc.close();
	}
	
	public static String getArgumento(int i, String[] args){
		String frase="";
		for(int j=i; j< args.length; j++){
			frase+= args[j] + " ";
		}
		return frase.trim();
	}
	
	@SuppressWarnings("static-access")
	private static Options buildOptions() {
		OptionBuilder.withLongOpt("game");
		OptionBuilder
				.hasArg();
		Option game = OptionBuilder
				.withArgName("game")
				.withDescription("Tipo de juego (c4, co, gr). Por defecto, c4.")
                .create("g");
		Option help = OptionBuilder.withLongOpt("help")
				.withDescription("Muestra esta ayuda.")
                .create("h");
		Option x = OptionBuilder.withLongOpt("tamX")
				.hasArg()
				.withArgName("columnNumber")
				.withDescription("Número de columnas del tablero (sólo para Gravity). Por defecto, 10.")
                .create("x");
		Option y = OptionBuilder.withLongOpt("tamY")
				.hasArg()
				.withArgName("rowNumber")
				.withDescription("Número de filas del tablero (sólo para Gravity). Por defecto, 10.")
                .create("y");
		Options options = new Options();
		options.addOption(game);
		options.addOption(help);
		options.addOption(x);
		options.addOption(y);
		return options;
	}
}