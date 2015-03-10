package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.StormType;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class StormInstruction implements Instruction{

	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private StormType stormType;
	
	private static final String StormInstructionHelp = "STORM < ACIDRAIN|SANDSTORM|TORNADO >";
	
	/**
	 * Constructor sin parametros.
	 */
	public StormInstruction (){
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
		this.stormType = null;
	}
	
	/**
	 * Constructor con parametro: Tipo de tormenta
	 * @param stormType - StormType
	 */
	public StormInstruction(StormType stormType)
	{
		this.stormType = stormType;
		this.engine = null;
		this.navigation = null;
		this.robotContainer = null;
	}
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		float damageCaused = 0;
		if(this.stormType == stormType.ACIDRAIN){
			// Aquí habria que chequear en que sitio estamos y restarle el porcentaje correspondiente.
			damageCaused = this.stormType.getStormTypeDamage();
		}
		else if(this.stormType == stormType.SANDSTORM){
			damageCaused = this.stormType.getStormTypeDamage();
		}
		else if(this.stormType == stormType.TORNADO){
			damageCaused = this.stormType.getStormTypeDamage();
		}	
		else
			throw new InstructionExecutionException();
		int protection = this.navigation.getCurrentPlace().getPlaceType().getPlaceTypeProtection(); // En porcentaje
		float difference = damageCaused*protection/100;
		damageCaused -= difference;
		this.engine.addShield(-damageCaused);
		this.engine.robotUpdate();
		
	}

	@Override
	public String getHelp() {
		return StormInstructionHelp;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		
		String aux[] = cad.split(" ");
		int length = aux.length;
		if (length == 2 && (aux[0].equalsIgnoreCase("STORM") || aux[0].equalsIgnoreCase("TORMENTA")))
		{
			if(aux[1].equalsIgnoreCase("ACIDSTORM"))
				return new StormInstruction(StormType.ACIDRAIN);
			else if(aux[1].equalsIgnoreCase("SANDSTORM"))
				return new StormInstruction(StormType.SANDSTORM);
			else if(aux[1].equalsIgnoreCase("TORNADO"))
				return new StormInstruction(StormType.TORNADO);
			else
				throw new WrongInstructionFormatException();
		}
		else if(length == 1 && (aux[0].equalsIgnoreCase("TURN") || aux[0].equalsIgnoreCase("GIRAR")))
		{
			throw new WrongInstructionFormatException();
		}
		else
		{
			throw new WrongInstructionFormatException();
		}
	}
}
