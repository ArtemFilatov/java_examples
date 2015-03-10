package tp.pr5.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * A container of items. It can be employed by any class that stores items. A container cannot store two items with the same identifier
 * It provides methods to add new items, access them and remove them from the container.
 * @author Jesus Vazquez Pigueiras
 * @author Guillermo Martín Duque
 */
public class ItemContainer extends Observable implements Collection<Item>, InventoryObserver {
	
	private ArrayList <Item> items;
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Creates the empty container
	 */
	public ItemContainer()
	{
		items = new ArrayList <Item>();
	}
	
	/**
	 * Returns the container's size
	 * @return The number of items in the container
	 */
	public int numberOfItems()
	{
		return items.size();
	}
	
	/**
	 * Add an item to the container. The operation can fail, returning false
	 * @param item - The name of the item to be added.
	 * @return true if and only if the item is added, i.e., an item with the same identifier does not exists in the container
	 */
	public boolean addItem(Item item)
	{
		boolean added_item = false;
		int pos = Collections.binarySearch(this.items, item);
		if(pos < 0)
		{
			pos = (pos * -1) -1;
			this.items.add(pos,  item);
			added_item = true;
		}
		return added_item;
	}
	
	/**
	 *Deletes an item from the inventory.
	 * @param id - Name of the item
	 * @return true item if and only if the item identified exists in the inventory. Otherwise it returns false
	 */
	public boolean removeItem(Item item)
	{
		return this.items.remove(item);
	}

	/**
	 * Checks if the Item with this id exists in the container.
	 * @param id - Name of the item.
	 * @return true if the container as an item with that name.
	 */
	public boolean containsItem(String id)
	{
		return Collections.binarySearch(this.items, new GenericItem(id,null)) >= 0;
	}
	
	/**
	 * Returns and deletes an item from the inventory. This operation can fail, returning null
	 * @param id - Name of the item
	 * @return An item if and only if the item identified by id exists in the inventory. Otherwise it returns null
	 */
	public Item pickItem(String id)
	{
		Item item = getItem(id);
		if(item!=null)
			this.items.remove(item);
		return item;
	}
	
	/**
	 * Returns the item from the container according to the item name
	 * @param id - Item name
	 * @return Item with that name or null if the container does not store an item with that name.
	 */
	public Item getItem(String id)
	{
		Item aux = new GenericItem(id,null);
		Item ret = null;
		int pos = Collections.binarySearch(this.items, aux);
		if(pos >= 0)
			ret = this.items.get(pos);
		return ret;
	}
	
	/**
	 * Generates a String with information about the items contained in the container. Note that the items must appear sorted but the item name.
	 */
	public String toString()
	{
		String show = "";
		if(this.numberOfItems()> 0)
		{
			for(int i = 0; i < this.items.size(); i++)
			{
				if(i == 0)
					show += "   " + this.items.get(i).getId();
				else
					show += LINE_SEPARATOR + "   " + this.items.get(i).getId();
				
				
			}
		}
		return show;
	}
	
	/**
	 * Devuelve el arraylist de items en forma de array de dos dimensiones con id en la primera y la descripciï¿½n en la segunda.
	 * @return
	 */
	public ArrayList<Item> getInventory()
	{
		return this.items;
	}
	
    /**
     * Indicar a los observadores que se ha actualizado el modelo.
     * 
     */
    private void informarObservadores()
    {
        setChanged(); // establece que ha habido un cambio.
        notifyObservers(); // notifica a los observadores.	
    }
	
	
	
	/////////////////////////////////////////////// COLLECTION METHODS /////////////////////////////////////////////

	@Override
	public boolean add(Item e) 
	{
		return this.addItem(e);
	}

	@Override
	public boolean addAll(Collection<? extends Item> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) 
	{
		return this.contains(((Item)o).toString());
				
	}

	@Override
	public boolean containsAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() 
	{
		return this.size() == 0;
	}

	@Override
	public Iterator<Item> iterator() 
	{
		return this.items.iterator();
	}

	@Override
	public boolean remove(Object o) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() 
	{
		return this.numberOfItems();
	}

	@Override
	public Object[] toArray() 
	{
		return this.items.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) 
	{
		return this.items.toArray(a);
	}

	///////////////////////////////////////////////// INVENTORY OBSERSVER METHODS
	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}



	
}
