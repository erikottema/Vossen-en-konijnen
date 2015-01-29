package foxesandrabbits;

import java.io.*;

import sun.audio.*;

public class Music2 {
	
	public Music2() {
		
		sound();

	}
	
	public static void sound() 
	{
		try{
		String soundEffect = "toone.wav";
		InputStream in = new FileInputStream(soundEffect);

		AudioStream stream = new AudioStream(in);

		AudioPlayer.player.start(stream);
		}catch(IOException error){}
	}
}