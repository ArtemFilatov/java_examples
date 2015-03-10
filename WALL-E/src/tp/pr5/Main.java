package tp.pr5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GuiController;
import tp.pr5.gui.MainWindow;

/**
 * Application entry-point. The app admits a parameter with the name of the map file to be used. 
 * If no arg is specified (or more than one file is given), an error message is printed (in System.err) 
 * and the application finishes with an error code (-1). If the map file cannot be read (or it does not exist), 
 * the app ends with a distinct error code (-2). Otherwise, the game is played and eventually the application will end normally (return code 0).
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */
public class Main {
	
	public static Options options;
	public static String fileName = "";
	public static Place initialPlace;
	public static City city;

	/**
	 * Application entry-point
	 * @param args -
	 */
	public static void main(String[] args)
	{
		CommandLineParser parser = new GnuParser(); // Command Line Parser.
		initializeOptions();
		try 
		{
			CommandLine line = parser.parse( options, args );  // parse the command line arguments
			if(args.length == 0){
				System.err.println("Map file not specified");
				System.exit(1);
			}
			else{
				if(line.hasOption("h")){
					help();
					System.exit(0);
				}else if(line.hasOption( "i" ) && line.hasOption("m")){
			    	Main.fileName = line.getOptionValue("m");
			    	if(line.getOptionValue( "i").equalsIgnoreCase("swing")){
			    		Main.city = Main.loadCity();
						RobotEngine model = new RobotEngine(Main.city,Main.initialPlace, Direction.NORTH);
						GuiController guiController = new GuiController(model);
						MainWindow mainView = new MainWindow(guiController);
						guiController.addGuiView(mainView);
						model.setController(guiController, null);
						model.requestStart();
			    	}else if(line.getOptionValue( "i").equalsIgnoreCase("console")){
			    		Main.city = Main.loadCity();
						RobotEngine model = new RobotEngine(Main.city,Main.initialPlace, Direction.NORTH);
						ConsoleController consoleController = new ConsoleController(model);
						model.setController(null, consoleController);
						model.requestStart();
			    	}else if(line.getOptionValue( "i").equalsIgnoreCase("both")){ // Both interface modes.
			    		Main.city = Main.loadCity();
						RobotEngine model = new RobotEngine(Main.city,Main.initialPlace, Direction.NORTH);
						ConsoleController consoleController = new ConsoleController(model);	// Console
						GuiController guiController = new GuiController(model);// Gui
						MainWindow mainView = new MainWindow(guiController);
						guiController.addGuiView(mainView);
						model.setController(guiController, consoleController); // Set the controllers
						model.requestStart(); // Start both
			    	}else{
			    		System.err.println("Wrong type of interface");
			    		System.exit(3);
			    	}
			    }else{
			    	if(line.hasOption("i"))
			    		System.err.println("Map file not specified");
			    	else
			    		System.err.println("Interface not specified");
			    	System.exit(1);
			    }
			}
		}catch( ParseException exp ){
		    System.out.println( "Unexpected exception:" + exp.getMessage() );
		}catch(WrongCityFormatException e){
			System.err.println(e.getMessage());
			System.exit(2);
		}catch(IOException e){
			System.err.println("Error reading the map file: " + fileName + " (No existe el fichero o el directorio)");
			System.exit(2);
		}
	}
	
	/**
	 * Creamos las opciones posibles como parametros de entrada para ejecutar la practica.
	 */
	private static void initializeOptions() {
		Main.options = new Options();
		options.addOption( "h", "help", false, "Show this help message" );
		options.addOption( "i", "interface", true, "The type of interface: console, swing or both");
		options.addOption( "m", "map", true, "File with the description of the city");
	}

	/**
	 * Reads the city form the mapfile
	 * @return City
	 * @throws IOException
	 */
	private static City loadCity() throws IOException {
		City city;
		CityLoaderFromTxtFile cityLoader = new CityLoaderFromTxtFile();
		FileInputStream file = new FileInputStream(new File(Main.fileName));
		city = cityLoader.loadCity(file);
		Main.initialPlace = cityLoader.getInitialPlace();
		return city;
	}

	/**
	 * Prints the badParams message.
	 */
	private static void badParams()
	{
		System.err.println("Bad params.");
		System.err.println("Usage: java tp.pr5.Main tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]");
		System.err.println();
		System.err.println(" -h,--help               Shows this help message");
		System.err.println(" -i,--interface <type>   The type of interface: console, swing or both");
		System.err.println(" -m,--map <mapfile>      File with the description of the city");
	}
	
	/**
	 * Prints the help message.
	 */
	private static void help()
	{
		System.out.println("Execute this assignment with these parameters:");
		System.out.println("usage: tp.pr5.Main [-h] [-i <type>] [-m <mapfile>]");
		System.out.println(" -h,--help               Shows this help message");
		System.out.println(" -i,--interface <type>   The type of interface: console, swing or both");
		System.out.println(" -m,--map <mapfile>      File with the description of the city");
	}
}


