package zombieRPG;

import java.util.Hashtable;
import java.util.Random;

/**
 * Human
 * 
 * Class stands for enemy human
 * 
 * @author f.petruschke
 *
 */
public class Human {

	public String type;
	public String name;
	public int lives;
	public int strength;
	public int brainStrength;
	public Boolean hasItem;
	public int itemStrength;
	public String itemName;
	
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

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getStrength() {
		return strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getBrainStrength() {
		return brainStrength;
	}

	public void setBrainStrength(int brainStrength) {
		this.brainStrength = brainStrength;
	}
	
	public boolean getHasItem() {
		return this.hasItem;
	}
	
	public void setHasItem(boolean hasItem) {
		this.hasItem = hasItem;
	}
	
	public int getItemStrength() {
		return this.itemStrength;
	}
	
	public void setItemStrength(int itemStrength) {
		this.itemStrength = itemStrength;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemName(String name) {
		this.itemName = name;
	}

	/**
	 * getDifficultyStrength
	 * 
	 * Calculates the strength bonus for the human.
	 * 
	 * @param difficulty	difficulty level of the human
	 * @return				difficulty strength will be added to strength in fight situation
	 */
	public String getDifficultyStrength(int difficulty) {
		switch(difficulty) {
			case(1):
				strength = 5 + (int)(Math.random() * 10);
				break;
			case(2):
				strength = 10 + (int)(Math.random() * 20);
				break;
			case(3):
				strength = 20 + (int)(Math.random() * 30);
				break;
			default: strength = 5;
		}
		String difficultyStrength = String.valueOf(strength);
		return difficultyStrength;
	}

	/**
	 * Human constructor
	 * 
	 * Contains the different kinds of humans.
	 * The constructor will choose a kind of human by random and
	 * set the humans' base attributes for fighting.
	 */
	public Human() {
		// mapping for associative two-dimensional arrays with hashtables
		Hashtable douchbag = new Hashtable();
		// insert all elements into hashtable
		douchbag.put( "type", "douchebag");
		douchbag.put( "name", "Dummkopf");
		douchbag.put( "lives", "10");
		douchbag.put( "strength", getDifficultyStrength(1));
		douchbag.put( "brainStrength", "1");
		if( new Random().nextDouble() <= 0.05 ){
			Item item = new Item();
			douchbag.put("hasItem", "true");
			douchbag.put( "itemStrength", Integer.toString(item.getItemStrength()));
			douchbag.put( "itemName", item.getName());
		} else {
			douchbag.put( "hasItem", "false");
			douchbag.put( "itemStrength", "0");
			douchbag.put( "itemName", "nichts");
		}
		
		Hashtable craftsman = new Hashtable();
		// insert all elements into hashtable
		craftsman.put( "type", "craftsman");
		craftsman.put( "name", "Handwerker");
		craftsman.put( "lives", "30");
		craftsman.put( "strength", getDifficultyStrength(2));
		craftsman.put( "brainStrength", "2");
		if( new Random().nextDouble() <= 0.6 ){
			Item item = new Item();
			craftsman.put("hasItem", "true");
			craftsman.put( "itemStrength", Integer.toString(item.getItemStrength()));
			craftsman.put( "itemName", item.getName());
		} else {
			craftsman.put( "hasItem", "false");
			craftsman.put( "itemStrength", "0");
			craftsman.put( "itemName", "nichts");
		}
		
		Hashtable academic = new Hashtable();
		// insert all elements into hashtable
		academic.put( "type", "academic");
		academic.put( "name", "Akademiker");
		academic.put( "lives", "40");
		academic.put( "strength", getDifficultyStrength(2));
		academic.put( "brainStrength", "10");
		if( new Random().nextDouble() <= 0.1 ){
			Item item = new Item();
			academic.put("hasItem", "true");
			academic.put( "itemStrength", Integer.toString(item.getItemStrength()));
			academic.put( "itemName", item.getName());
		} else {
			academic.put( "hasItem", "false");
			academic.put( "itemStrength", "0");
			academic.put( "itemName", "nichts");
		}
		
		Hashtable soldier = new Hashtable();
		// insert all elements into hashtable
		soldier.put( "type", "soldier");
		soldier.put( "name", "Soldat");
		soldier.put( "lives", "100");
		soldier.put( "strength", getDifficultyStrength(3));
		soldier.put( "brainStrength", "10");
		if( new Random().nextDouble() <= 0.7){
			Item item = new Item();
			soldier.put("hasItem", "true");
			soldier.put( "itemStrength", Integer.toString(item.getItemStrength()));
			soldier.put( "itemName", item.getName());
		} else {
			soldier.put( "hasItem", "false");
			soldier.put( "itemStrength", "0");
			soldier.put( "itemName", "nichts");
		}

		Hashtable[] enemies = {douchbag, craftsman, academic, soldier};
		
		// get random enemy
		Random random = new Random();
		int randomIndex = random.nextInt(enemies.length);
		String randomEnemyType = (String) enemies[randomIndex].get("type");
		String randomEnemyName = (String) enemies[randomIndex].get("name");
		String randomEnemyLives = (String) enemies[randomIndex].get("lives");
		String randomEnemyStrength = (String) enemies[randomIndex].get("strength");
		String randomEnemyBrainStrength = (String) enemies[randomIndex].get("brainStrength");
		String randomEnemyItem = (String) enemies[randomIndex].get("hasItem");
		String randomEnemyItemStrength = (String) enemies[randomIndex].get("itemStrength");
		String randomEnemyItemName = (String) enemies[randomIndex].get("itemName");
		
		// setting the enemies attributes
		this.type = randomEnemyType;
		this.name = randomEnemyName;
	    this.lives = Integer.parseInt(randomEnemyLives);
	    this.strength = Integer.parseInt(randomEnemyStrength);
	    this.brainStrength = Integer.parseInt(randomEnemyBrainStrength);
	    this.hasItem = Boolean.parseBoolean(randomEnemyItem);
	    this.itemStrength = Integer.parseInt(randomEnemyItemStrength);
    	this.itemName = randomEnemyItemName;
	}
}
