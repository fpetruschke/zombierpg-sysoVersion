package zombieRPG;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Start
 * 
 * Main class for starting the game. 
 * 
 * @author f.petruschke
 */
public class Start {

	public static String gamestate="startmenu";
	public static boolean music=true;
	
	/**
	 * main
	 * 
	 * class for startmenu and executing players choices
	 * 
	 * @param args
	 * @throws InterruptedException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		
		// playing background music
		Sound backgroundMusic = new Sound("src/audio/title.wav");
		
		while(Start.gamestate == "startmenu") {
			
			System.out.println("###############################");
			System.out.println("#     Z O M B I E   R P G     #");
			System.out.println("###############################");
			System.out.println("#                             #");
			System.out.println("#                             #");
			System.out.println("#      [1] Spiel starten      #");
			System.out.println("#      [2] Credits            #");
			System.out.println("#                             #");
			System.out.println("#      [3] Spiel beenden      #");
			System.out.println("#                             #");
			System.out.println("###############################");
			
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			
			switch(choice) {
				case(1):	Start.gamestate = "mainmenu";
							Gamestart Game = new Gamestart(backgroundMusic);
							Game.start();
							break;
				case(2):	System.out.println();
							System.out.println();
							System.out.println("###############################");
							System.out.println("#     Z O M B I E   R P G     #");
							System.out.println("#        C R E D I T S        #");
							System.out.println("###############################");
							System.out.println("#                             #");
							System.out.println("#  Das Zombie RPG ist ein     #");
							System.out.println("#  simples rundenbasiertes    #");
							System.out.println("#  RPG - geschrieben in Java. #");
							System.out.println("#                             #");
							System.out.println("#      Autoren:               #");
							System.out.println("#      A. Neeven              #");
							System.out.println("#      F.Petruschke           #");
							System.out.println("#                             #");
							System.out.println("#                             #");
							System.out.println("#                             #");
							System.out.println("###############################");
							break;
				case(3):	System.exit(0);
							break;
				default:    System.exit(1);
							break;
			}
		}	
	}
	
	public static void showCredits() {
		System.out.println("###############################");
		System.out.println("#     Z O M B I E   R P G     #");
		System.out.println("#        C R E D I T S        #");
		System.out.println("###############################");
		System.out.println("#                             #");
		System.out.println("#  Das Zombie RPG ist ein     #");
		System.out.println("#  simples rundenbasiertes    #");
		System.out.println("#  RPG - geschrieben in Java. #");
		System.out.println("#                             #");
		System.out.println("#      Autoren:               #");
		System.out.println("#      A. Neeven              #");
		System.out.println("#      F.Petruschke           #");
		System.out.println("#                             #");
		System.out.println("#      [1] zurück             #");
		System.out.println("#                             #");
		System.out.println("###############################");
		
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		switch(choice) {
			case(1): 	Start.gamestate = "startmenu";
						break;
			default:	Start.gamestate = "startmenu";
						break;
		}
	}
}
