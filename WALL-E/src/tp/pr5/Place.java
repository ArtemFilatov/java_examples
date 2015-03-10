package tp.pr5;


import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * It represents a place in the city. Places are connected by streets according to the 4 compass directions, North, 
 * East, South and West. Every place has a name and a textual description about itself. This description is displayed when the robot arrives at the place.
 * A place can represent the spaceship where the robot is safe. When the robot arrives at this place, the application is over.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martin Duque
 */

public class Place implements PlaceInfo {
	
    private String name = null;
    private Boolean isSpaceShip= false;
    private String description;
    private ItemContainer items;
    private PlaceType placeType;
    
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    /**
     * Creates the place
     * @param name - Place name
     * @param isSpaceShip - Is it a spaceship?
     * @param description - Place description
     */
	public Place (String name, boolean isSpaceShip, String description)
	{
		this.name = name;
		this.isSpaceShip = isSpaceShip;
		this.description = description;
		this.items = new ItemContainer();
		this.placeType = PlaceType.PLACE;
	}
	
	public Place (String name, boolean isSpaceShip, String description, PlaceType placeType)
	{
		this.name = name;
		this.isSpaceShip = isSpaceShip;
		this.description = description;
		this.items = new ItemContainer();
		this.placeType = placeType;
	}
	
	/**
	 * Tries to add an item to the place. The operation can fail (if the place already contains an item with the same name)
	 * @param item - The item to be added
	 * @return
	 */
	public boolean addItem(Item item)
	{
		boolean added_item = false;
		if(this.placeType.equals(PlaceType.BUNKER) || this.placeType.equals(PlaceType.TRENCH))
			added_item = false;
		else if(this.items.addItem(item))
			added_item = true;
		return added_item;
	}
		
	/**
	 * Drop an item in this place. The operation can fail, returning false
	 * @param item - The name of the item to be dropped.
	 * @return true if and only if the item is dropped in the place, i.e., an item with the same identifier does not exists in the place
	 */
	public boolean dropItem(Item item)
	{
		return this.addItem(item);
	}
	
	/**
	 * Checks if an item is in this place
	 * @param id - Identifier of an item
	 * @return true if and only if the place contains the item identified by id
	 */
	public boolean existItem(String id)
	{
		return this.items.containsItem(id);
	}
	
	/**
	 * Tries to pick an item characterized by a given identifier from the place. If the action was completed the item must be removed from the place.
	 * @param id - The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public  Item pickItem(String id)
	{
		Item it = this.items.pickItem(id);
		return it;
	}
	
	/**
	 * Is this place the space ship?
	 * @return true if the place represents a space ship
	 */
	public boolean isSpaceship()
	{
		return isSpaceShip;
	}
	
	/**
	 * Overrides toString method. Returns the place name, its description and the list of items contained in the place
	 */
	public java.lang.String toString()
	{
		String show = "";
		if(this.items.numberOfItems() > 0)
		{
			show =  this.name + LINE_SEPARATOR + this.description + LINE_SEPARATOR + "The place contains these objects:" + LINE_SEPARATOR + this.items.toString() + LINE_SEPARATOR;
		}
		else if (this.items.numberOfItems() == 0)
		{
			show = this.name + LINE_SEPARATOR + this.description + LINE_SEPARATOR + "The place is empty. There are no objects to pick"+ LINE_SEPARATOR;
		}
		if(this.placeType.equals(PlaceType.BUNKER))
			show += LINE_SEPARATOR + "This place is a Bunker";
		else if(this.placeType.equals(PlaceType.TRENCH))
			show += LINE_SEPARATOR + "This place is a Trench";
		return show;
	}

	
	/**
	 * Place's ItemContainer Getter
	 * @return items - The place's itemcontainer
	 */
	public ItemContainer getInventory()
	{
		return this.items;
	}
	
	/**
	 * Place's name getter
	 * @return name - String, place's name.
	 */
	public String getPlaceName()
	{
		return this.name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public PlaceType getPlaceType() 
	{
		return this.placeType;
	}
	
}