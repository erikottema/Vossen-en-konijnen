package foxesandrabbits;

import sun.audio.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class Music{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(200, 200);
		JButton button = new JButton("bomb");
		frame.add(button);
		button.addActionListener(new AL());
		frame.show(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static class AL implements ActionListener{
		public final void actionPerformed(ActionEvent e){
			music();
		}
	}
	
	public static void music()
	{
		AudioPlayer MGP = null;
		AudioStream BGM;
		AudioData MD;
	ContinuousAudioDataStream loop = null;
	
	try{
	BGM = new AudioStream(new FileInputStream("bomb.wav"));
	MD = BGM.getData();
	loop = new ContinuousAudioDataStream(MD);
	}catch(IOException error){}
	
	MGP.start(loop);
	}
}