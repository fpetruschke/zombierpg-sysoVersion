package zombieRPG;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Gamestart {
	
	public Sound backgroundMusic;
	
	/**
	 * constructor
	 * 
	 * Constructor sets the background music object
	 * 
	 * @param backgroundMusic Sound object
	 */
	public Gamestart(Sound backgroundMusic) {
		this.backgroundMusic = backgroundMusic;
	}
		
	/**
	 * start
	 * 
	 * Method for starting the actual game.
	 * It gives the user action options to choose from after
	 * instantiating the users' zombie object
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	public void start() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
			
			// Getting the users character name
			System.out.println("Gib Deinen Spielernamen ein: ");
			Scanner input = new Scanner(System.in);
			String userName = input.next();
			
			// Instantiating the users' character "zombie"
			Zombie zombie = new Zombie(userName);
			
			// stop playing background music from main menu
			backgroundMusic.stopClip();
			
			// start new background title
			Sound backgroundTheme = new Sound("src/audio/nature.wav");
			
			// showing provisionally Main Menu
			while(Start.gamestate == "mainmenu") {
				System.out.println();
				System.out.println("##############################");
				System.out.println("     H A U P T M E N Ü        ");
				System.out.println("------------------------------");
				System.out.println("Wähle aus folgenden Optionen: ");
				System.out.println("[1] MENSCHEN AUFSPÜREN        ");
				System.out.println("[2] STATUS        ");
				System.out.println();
				System.out.println("[3] BEENDEN       ");
				System.out.println("##############################");
				System.out.println();
				int choice = input.nextInt();
				
				// switch case for main menu choices
				switch (choice) {
		        	case 1: Start.gamestate = "fight";
		        			// probability of finding human = 70%
		        			if( new Random().nextDouble() <= 0.7 ){
		        				Sound foundHuman = new Sound("src/audio/foundHuman.wav");
		        				Fight fight = new Fight(zombie, "zombie");
		        			} else {
		        				Sound noHuman = new Sound("src/audio/noHuman.wav");
		        				noHuman.playClip();
		        				System.out.println("Niemand zu sehen.");
		        				System.out.println("Hunger und Zerfall steigen...");
		        				zombie.setBrainHunger(zombie.getBrainHunger() + 1.0);
		        				zombie.setDecay(zombie.getDecay() + 2.0);
		        				noHuman.stopClip();
		        				Start.gamestate = "mainmenu";
					    		break;
		        			}
		                 	break;
		        	case 2: Start.gamestate = "state";
		        			System.out.println("##################################");
				    		System.out.println(zombie.getName());
				    		System.out.println("----------------------------------");
				    		System.out.println("Zerfall:       " + zombie.getDecay() + "%");
				    		System.out.println("Stärke:        " + zombie.getStrength());
				    		System.out.println("Gehirn-Hunger: " + zombie.getBrainHunger() + "%");
				    		System.out.println("----------------------------------");
				    		System.out.println("getötete Dummköpfe:  " + zombie.killedDouchbags);
				    		System.out.println("getötete Handwerker: " + zombie.killedCraftsmen);
				    		System.out.println("getötete Akademiker: " + zombie.killedAcademics);
				    		System.out.println("getötete Soldaten:   " + zombie.killedSoldiers);
				    		System.out.println("----------------------------------");
				    		System.out.println("Gesamt:              " + zombie.humansKilled);
				    		System.out.println("##################################");
				    		Start.gamestate = "mainmenu";
				    		break;
		        	case 3: System.exit(0);
					default:
							Start.gamestate = "mainmenu";
							break;
				}
			}
		}
	}
