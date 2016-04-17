package zombieRPG;

import java.util.Hashtable;
import java.util.Random;

/**
 * class Item
 * 
 * Represents the item a human can hold in a fight
 * 
 * @author f.petruschke
 *
 */
public class Item {

	public String type;
	public String name;
	public int itemStrength;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemStrength() {
		return itemStrength;
	}
	public void setItemStrength(int itemStrength) {
		this.itemStrength = itemStrength;
	}
	
	/**
	 * constructor
	 * 
	 * Will randomly pick an item from the below ones
	 */
	public Item() {
		// mapping for associative two-dimensional arrays with hashtables
		Hashtable stone = new Hashtable();
		// insert all elements into hashtable
		stone.put( "type", "stone");
		stone.put( "name", "Stein");
		stone.put( "itemStrength", "2");
		
		Hashtable knife = new Hashtable();
		// insert all elements into hashtable
		knife.put( "type", "knife");
		knife.put( "name", "Messer");
		knife.put( "itemStrength", "5");
		
		Hashtable baseballbat = new Hashtable();
		// insert all elements into hashtable
		baseballbat.put( "type", "baseballbat");
		baseballbat.put( "name", "Baseball-Schläger");
		baseballbat.put( "itemStrength", "10");
		
		Hashtable extinguisher = new Hashtable();
		// insert all elements into hashtable
		extinguisher.put( "type", "extinguisher");
		extinguisher.put( "name", "Feuerlöscher");
		extinguisher.put( "itemStrength", "12");
		
		Hashtable axe = new Hashtable();
		// insert all elements into hashtable
		axe.put( "type", "axe");
		axe.put( "name", "Axt");
		axe.put( "itemStrength", "15");
		
		Hashtable gun = new Hashtable();
		// insert all elements into hashtable
		gun.put( "type", "gun");
		gun.put( "name", "Schusswaffe");
		gun.put( "itemStrength", "25");
				
		Hashtable[] items = {stone, knife, baseballbat, extinguisher, axe, gun};
		
		// get random item
		Random random = new Random();
		int randomIndex = random.nextInt(items.length);
		String randomItemType = (String) items[randomIndex].get("type");
		String randomItemName = (String) items[randomIndex].get("name");
		String randomItemStrength = (String) items[randomIndex].get("itemStrength");
		
		this.type = randomItemType;
	    this.name = randomItemName;
	    this.itemStrength = Integer.parseInt(randomItemStrength);
	}
	
	
}
