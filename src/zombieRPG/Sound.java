package zombieRPG;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.sound.sampled.LineEvent.Type;

/**
 * Sound
 * 
 * class for playing in game sounds
 * 
 * Idea for solution found on:
 * http://stackoverflow.com/questions/577724/trouble-playing-wav-in-java/577926#577926
 * 
 * All included Soundfiles are selfmade or from
 * https://www.freesound.org
 * 
 * Main background title from 
 * Track Name: " DOWN "
 * Composed by: Marcus Dellicompagni
 * Website: www.PoundSound.co.uk
 * 
 * @author f.petruschke
 *
 */
public class Sound {

	public File audiofile;
	public Thread musicThread;
	
	public void setThread(Thread backgroundMusic) {
		this.musicThread = backgroundMusic;
	}
	
	public File getAudioFile() {
		return audiofile;
	}

	public void setAudiofile(String pathToSoundfile) {
		File audioFile = new File(pathToSoundfile);
		this.audiofile = audioFile;
	}
	
	/**
	 * constructor
	 * 
	 * It takes the path to a sound file, get's the file and plays it.
	 * 
	 * @param pathToSoundfile
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	public Sound(final String pathToSoundfile) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		this.setAudiofile(pathToSoundfile);
		
		/**
		 * backgroundMusic
		 * 
		 * additional thread for playing background music
		 */
		Thread backgroundMusic = new Thread() {
		    public void run() {
		        try {
		        	try {
		        		
		        		setAudiofile(pathToSoundfile);
		        		playClip();
						//Sound music = new Sound("src/audio/title.wav");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
		        } catch(InterruptedException v) {
		            System.out.println(v);
		        }
		    }  
		};
		this.musicThread = backgroundMusic; 
		backgroundMusic.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stopClip() {
		musicThread.stop();
	}
	
	/**
	 * playClip
	 * 
	 * Method for playing the audio file given in the class' constructor
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	public void playClip() throws IOException, 
	  UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
	  class AudioListener implements LineListener {
	    private boolean done = false;
	    @Override public synchronized void update(LineEvent event) {
	      Type eventType = event.getType();
	      if (eventType == Type.STOP || eventType == Type.CLOSE) {
	        done = true;
	        notifyAll();
	      }
	    }
	    public synchronized void waitUntilDone() throws InterruptedException {
	      while (!done) { wait(); }
	    }
	  }
	  AudioListener listener = new AudioListener();
	  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getAudioFile());
	  try {
	    Clip clip = AudioSystem.getClip();
	    clip.addLineListener(listener);
	    clip.open(audioInputStream);
	    try {
	      clip.start();
	      listener.waitUntilDone();
	    } finally {
	      clip.close();
	    }
	  } finally {
	    audioInputStream.close();
	  }
	}
}
