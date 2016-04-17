package zombieRPG;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * class Escape
 * 
 * This class contains the escape mechanism of the game.
 * It holds the methods for users' choice to escape a fight.
 * 
 * @author f.petruschke
 *
 */
public class Escape {

	/**
	 * escape
	 * 
	 * The methode will choose whether the user can escape or not by random
	 * If the users try failes the enemy/human will begin next fight round
	 * 
	 * @param zombie	Zombie object
	 * @param human		Human object
	 * @throws InterruptedException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public Escape(Zombie zombie, Human human) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		
		// throw new dice for randomly choosing the according event
		int dice = ThreadLocalRandom.current().nextInt(1, 5 + 1);
		if(dice <= 3) {
			System.out.println();
			System.out.println("###########################");
			System.out.println("         K A M P F         ");
			System.out.println("---------------------------");
			System.out.println("Flucht nicht geglückt.");
			System.out.println(human.getName() + " greift Dich an...");
			System.out.println("###########################");
			System.out.println();
			Fight fight = new Fight(zombie, "human");
		} else {
			System.out.println();
			System.out.println("###########################");
			System.out.println("        F L U C H T       ");
			System.out.println("---------------------------");
			System.out.println("Flucht geglückt");
			System.out.println("###########################");
			System.out.println();
			zombie.setBrainHunger(zombie.brainHunger ++);
			zombie.setDecay(zombie.decay ++);
			zombie.setStrength(zombie.getStrength() + (2/human.getStrength()));
			Fight.gamecircle = false;
			Start.gamestate = "mainmenu";
		}
	}
}
