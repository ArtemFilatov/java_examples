package tp.pr5;

/**
 * An enumerated type that represents the compass directions (north, east, south and west) plus a value that represents an unknown direction.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */
public enum Direction 
{
	NORTH, EAST, SOUTH, WEST, UNKNOWN;
	
	////////////////////////////////////////////// Unofficial Methods ///////////////////////////////////
	
	/**
	 * Returns the opposite direction.
	 * @param direction 
	 * @return the opposite direction from the direction given
	 */
	
	public Direction opposite(Direction direction){
		Direction opposite = null;
		switch(direction){
		case NORTH:
			opposite = SOUTH;
			break;
		case EAST:
			opposite = WEST;
			break;
		case SOUTH:
			opposite = NORTH;
			break;
		case WEST:
			opposite = EAST;
			break;
		default: 
			opposite = UNKNOWN;
			break;
		}
		return opposite;
	}
	
	/**
	 * Turn the robot direction
	 * @param direction
	 * @return new direction
	 */
	public Direction turnLeft(Direction direction){
		Direction opposite = null;
		switch(direction){
		case NORTH:
			opposite = WEST;
			break;
		case EAST:
			opposite = NORTH;
			break;
		case SOUTH:
			opposite = EAST;
			break;
		case WEST:
			opposite = SOUTH;
			break;
		default: 
			opposite = UNKNOWN;
			break;
		
		}
		return opposite;
	}
	
	/**
	 * Turn the robot direction
	 * @param direction
	 * @return new direction
	 */
	public Direction turnRight(Direction direction){
		Direction opposite = null;
		switch(direction){
		case NORTH:
			opposite = EAST;
			break;
		case EAST:
			opposite = SOUTH;
			break;
		case SOUTH:
			opposite = WEST;
			break;
		case WEST:
			opposite = NORTH;
			break;
		default: 
			opposite = UNKNOWN;
			break;
		
		}
		return opposite;
	}
	
	/**
	 * Parse a string to check if it's a correct direction or not.
	 * @param s - String cad
	 * @return Direction
	 */
	public Direction parseDirection(String s)
	{
		Direction dir;
		if(s.equalsIgnoreCase("NORTH"))
			dir = Direction.NORTH;
		else if(s.equalsIgnoreCase("EAST"))
			dir = Direction.EAST;
		else if(s.equalsIgnoreCase("SOUTH"))
			dir = Direction.SOUTH;
		else if(s.equalsIgnoreCase("WEST"))
			dir = Direction.WEST;
		else
			dir = Direction.UNKNOWN;
		return dir;
	}
}
