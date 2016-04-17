package zombieRPG;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * class Fight
 * 
 * This class contains the heart of the game.
 * It holds the methods for fighting system.
 * 
 * @author f.petruschke
 *
 */
public class Fight {
	
	public static boolean gamecircle;

	/**
	 * Fight
	 * 
	 * Method controlling the fight.
	 * Contains methods for user choices and corresponding attacks.
	 * Also includes the generated enemy attakcs.
	 * 
	 * @param zombie	Zombie object
	 * @param human		Human object
	 * @param beginner	Either "zombie" or different.
	 * 					Controls whether zombie or enemy starts attack first
	 * @throws InterruptedException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public Fight(Zombie zombie, String beginner) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		
		Human human = new Human();
		
		/*
		 * @toDo : create a nextRoundBonus mechanism
		 */
		// current state before fight
		int zombieNextAttackBonus = 0;
		int currentZombieStrength = zombie.strength + zombieNextAttackBonus;
		int currentHumanStrength = human.strength + human.itemStrength;
		double currentZombieDecay = zombie.decay;
		int currentHumanLives = human.lives;
		
		// actual fight
		Fight.gamecircle = true;
		while (true == Fight.gamecircle) {
			getCurrentStatus(zombie, human);
			if("zombie" == beginner) {
				if(true == Fight.gamecircle){
					zombiesAttackChoice(zombie, human);
					if(true == Fight.gamecircle){
						TimeUnit.SECONDS.sleep(2);
						humansAttackChoice(zombie, human);
					}
				}
			} else {
				TimeUnit.SECONDS.sleep(2);
				humansAttackChoice(zombie, human);
				if(true == Fight.gamecircle){
					zombiesAttackChoice(zombie, human);
					
				}
			}
		}
	}
	
	/**
	 * zombiesAttackChoice
	 * 
	 * Gives the user ability to choose the attack.
	 * Attack is called after choice
	 * 
	 * @param zombie	Zombie Object
	 * @param human		Human Object
	 * @throws InterruptedException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public void zombiesAttackChoice(Zombie zombie, Human human) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		// showing provisionally Main Menu
		System.out.println("Du greifst an...");
		System.out.println("[1] Kratzen       ");
		System.out.println("[2] Beißen        ");
		System.out.println("[3] Anspringen    ");
		if(human.hasItem){
			System.out.println("[4] Entwaffnen    ");
		}
		System.out.println("[9] Fluchversuch  ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		// switch case for menu choices
		if (1 == choice) {
			attack(human, zombie, 11);
		} else if (2 == choice){
			attack(human, zombie, 13);
		} else if (3 == choice) {
			attack(human, zombie, 14);
		} else if (4 == choice) {
			if(human.hasItem) {
				attack(human, zombie, 12);
			} else {
				System.out.println("Du bist gestolpert...");
				zombie.setDecay(zombie.getDecay() + 5.0);
			}
		} else if (9 == choice) {
			Escape escpae = new Escape(zombie, human);
		} else {
			System.out.println("Netter Versuch... G A M E  O V E R. Du bist verrottet.");
			System.exit(0);
		}
	}
	
	/**
	 * 
 	 * humansAttackChoice
	 * 
	 * Humans attack will be generated.
	 * Attack is called after generation.
	 * 
	 * @param zombie	Zombie Object
	 * @param human		Human Object
	 * @throws InterruptedException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public void humansAttackChoice(Zombie zombie, Human human) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		// humans' attack
		int dice = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		switch(dice){
			case(1): 	System.out.println("Du wirst angegriffen...");
						attack(human, zombie, 21);
						break;
			case(2): 	System.out.println("Du wirst angegriffen...");
						attack(human, zombie, 22);
						break;
			case(3): 	System.out.println("Du wirst angegriffen...");
						attack(human, zombie, 23);
						break;
			default:	System.out.println("Der Mensch verpasst seine Chance...");
						break;
		}

	}
	
	/**
	 * attack
	 * 
	 * Method for executing the actual attack with its probabilities and results.
	 * 
	 * AttackTypes: 
	 * ZOMBIE			HUMAN
	 * 11 scratch		21 useItem
	 * 12 disarm		22 punch
	 * 13 bite			23 kick
	 * 14 pounceOn
	 * 
	 * @param attackType
	 * @throws InterruptedException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public void attack(Human human, Zombie zombie, int attackType) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		switch(attackType) {
			case(11):
				// scratch successful with probability 90%
				if( new Random().nextDouble() <= 0.9 ){
					human.setLives(human.getLives() - (1*zombie.strength));
					Sound zombieSound = new Sound("src/audio/zombieAttack.wav");
					System.out.println("Kratzen erfolgreich");
					break;
				} else {
					zombie.setDecay(zombie.getDecay() + 1);
					System.out.println("Kratzen abgewehrt");
					break;
				}
			case(12):
				// disarm successful with probability 50%
				if( new Random().nextDouble() <= 0.5 ){
					human.setHasItem(false);
					human.setItemName("nichts");
					human.setItemStrength(0);
					Sound zombieSound = new Sound("src/audio/zombieAttack.wav");
					System.out.println("Entwaffnung erfolgreich");
					break;
				} else {
					zombie.setDecay(zombie.getDecay() + 3);
					System.out.println("Entwaffnung nicht geglückt");
					break;
				}
			case(13):
				// bite successful with probability 70%
				if( new Random().nextDouble() <= 0.7 ){
					human.setLives(human.getLives() - (2*zombie.strength));
					zombie.setBrainHunger(zombie.getBrainHunger() - (0.25 * human.getBrainStrength()));
					Sound zombieSound = new Sound("src/audio/zombieAttack.wav");
					System.out.println("Beißen erfolgreich");
					break;
				} else {
					zombie.setDecay(zombie.getDecay() + 1);
					System.out.println("Biss abgewehrt");
					break;
				}
			case(14):
				// pounce on successful with probability 40%
				if( new Random().nextDouble() <= 0.4 ){
					zombie.setStrength(zombie.getStrength() + (human.strength));
					Sound zombieSound = new Sound("src/audio/zombieAttack.wav");
					System.out.println("Anspringen geglückt");
					break;
				} else {
					zombie.setDecay(zombie.getDecay() + 5);
					System.out.println("Anspringen abgewehrt");
					break;
				}
			case(21):
				if(human.hasItem) {
					// useItem successful with probability 40%
					if( new Random().nextDouble() <= 0.4 ){
						zombie.decay = zombie.getDecay() + (3*(human.strength));
						Sound punchSound = new Sound("src/audio/punch.wav");
						System.out.println("Du wurdest von einem Gegenstand getroffen");
						break;
					} else {
						System.out.println("Gegenstand verfehlt sein Ziel");
						break;
					}
				} else {
					System.out.println("Dein Gegner ist gestolpert.");
					break;
				}
				
			case(22):
				// punch successful with probability 80%
				if( new Random().nextDouble() <= 0.8 ){
					zombie.decay = zombie.getDecay() + (human.strength);
					Sound punchSound = new Sound("src/audio/punch.wav");
					System.out.println("Du wurdest geschlagen");
					break;
				} else {
					System.out.println("Mensch schlägt an Dir vorbei");
					break;
				}
			case(23):
				// kick successful with probability 60% 
				if( new Random().nextDouble() <= 0.6 ){
					zombie.decay = zombie.getDecay() + (2*(human.strength));
					Sound punchSound = new Sound("src/audio/punch.wav");
					System.out.println("Du wurdest getreten");
					break;
				} else {
					System.out.println("Ein Tritt verfehlt sein Ziel");
					break;
				}
			default:
				System.out.println("Ihr starrt euch an...");
				break;
		}		
	}
	
	/**
	 * getCurrentStatus
	 * 
	 * Checks if one of the contrahends is dead and prints corresponding message
	 * or shows current stats
	 * 
	 * @param zombie	zombie object
	 * @param human		human object
	 * @throws InterruptedException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public void getCurrentStatus(Zombie zombie, Human human) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		if(human.lives<=0) {
			System.out.println();
			System.out.println("######################################");
			System.out.println("                S I E G               ");
			System.out.println("--------------------------------------");
			System.out.println("Du hast den " + human.name + "besiegt.");
			System.out.println("######################################");
			System.out.println();
			zombie.setBrainHunger(zombie.getBrainHunger() - human.brainStrength);
			zombie.setDecay(zombie.getDecay() - human.brainStrength);
			zombie.setStrength(zombie.getStrength() + (2/human.getStrength()));
			zombie.humansKilled ++;
			if("douchebag" == human.type) {
				zombie.killedDouchbags ++;
			} else if ("craftsman" == human.type) {
				zombie.killedCraftsmen ++;
			} else if ("academic" == human.type) {
				zombie.killedAcademics ++;
			} else if ("soldier" == human.type) {
				zombie.killedSoldiers ++;
			}
			Sound victorySound = new Sound("src/audio/victory.wav");
			Fight.gamecircle = false;
			// going back to main menu if fight is over
			Start.gamestate = "mainmenu";
		} else if(zombie.decay>=100) {
			/**
			 * @toDo: add mechanic for hunger increasing/decreasing decay and v.v
			 */
			Sound defeatSound = new Sound("src/audio/defeat.wav");
			System.out.println();
			System.out.println("######################################");
			System.out.println("         N I E D E R L A G E          ");
			System.out.println("--------------------------------------");
			System.out.println("Du wurdest besiegt.");
			System.out.println();
			System.out.println("           G A M E  O V E R           ");
			System.out.println("Du hast " + zombie.humansKilled + " Menschen getötet.");
			System.out.println();
			System.out.println("      [1] zurück zum Hauptmenü        ");
			System.out.println("######################################");
			System.out.println();
			Scanner input = new Scanner(System.in);
			Fight.gamecircle = false;
			int choice = input.nextInt();
			switch(choice){
			case(1):Start.gamestate = "startmenu";
					break;
			default:Start.gamestate = "startmenu";
					break;
			}
		} else {
			System.out.println("##################################       |     ##################################");
			System.out.println(human.getName() +"                         |     " + zombie.getName());
			System.out.println("----------------------------------       |     ----------------------------------");
			System.out.println("Leben:      " + human.getLives() +                 "                            |     Zerfall:    " + zombie.getDecay() + "%");
			System.out.println("Stärke:     " + human.getStrength() + "              		 |     Stärke:     " + zombie.getStrength());
			System.out.println("Hirnstärke: " + human.getBrainStrength() + "         		         |     Gehirn-Hunger: " + zombie.getBrainHunger() + "%");
			System.out.println("##################################       |     ##################################");
			System.out.println("+ " + human.itemStrength + " Stärke durch " + human.itemName + "          | + " + 0 + "Stärke durch Blutrausch"  );
			System.out.println("----------------------------------       |     ----------------------------------");
			System.out.println();
		}	
	}
}
