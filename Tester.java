package cal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Tester {
	
	private static final int NOTIFICATION_TIME = 5;
	private static volatile boolean popRunning = true;

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ArrayList<Appointment> a = Reader.readFile();
		checkReminder(a);
				
		
		//System.exit(0);
	}
	
	public static void checkReminder(ArrayList<Appointment> a) throws InterruptedException{
		
		TimeZone t = TimeZone.getTimeZone("America/New_York");
		//set timezone
		
		Calendar c = Calendar.getInstance(t);
		String[] s = c.getTime().toString().split(" ");
		// Week, Month, Day in Month, time(24hr), timezone, yr
		System.out.println(Arrays.toString(s));
		
		String currentDate = String.format("%s %s %s", s[1],s[2],s[5]);
		//Month, Day in Month, year
		
		String timeData[] = s[3].split(":");
		//hr,min,sec
		int currentHr = Integer.parseInt(timeData[0]);
		int currentMin = Integer.parseInt(timeData[1]);
		
		int currentMH = currentHr*60 + currentMin;
		//total min
		
		for(Appointment i: a){
			String iData[] = i.getTime().split(":");
			int iHr = Integer.parseInt(iData[0]);
			int iMin = Integer.parseInt(iData[1]);
			int iMH = iHr*60 + iMin;
			
			int x = iMH-currentMH;
						
			if((x >=0 && x<=10)&& currentDate.equals(i.getDate()) ){
				createReminder(i);
				System.out.printf("Current Time = %d\t Appointment Time = %d\n", currentMH, iMH);
			}
		}
	}
		
	public static void createReminder(Appointment input) throws InterruptedException{
	
		
		final JFrame f = new JFrame();
		f.setIconImage(new ImageIcon("src//calendar.png").getImage());		
		try{
			if(Double.parseDouble(input.getTime().substring(0,2))>12){
				input.setAP("pm");
				input.setTime(Integer.toString((int)Double.parseDouble(input.getTime().substring(0,2))%12) + input.getTime().substring(2));
			}
			else if(Double.parseDouble(input.getTime().substring(0,2))==12)
					input.setAP("pm");
		}catch(NumberFormatException e){
			//Do nothing
		}
			
		final JLabel l = new JLabel(String.format("%s has a(n) %s at %s%s", input.getName(), input.getType(), input.getTime(),input.getAP()), JLabel.CENTER);
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Arial", Font.BOLD, 40));
		final JDialog dialog = new JDialog(f,"Appointment", true);
		dialog.setContentPane(l);		
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		dialog.setBackground(new Color(87,6,140));
		dialog.pack();
		int x = dialog.getWidth();
		int y = dialog.getHeight();
		dialog.setLocation(d.width/2-(x/2), d.height/4-(y/2));
		
		File soundFile = new File(String.format("src%sfront-desk-bells-daniel_simon.wav", File.separator));		

		
		try{
			Clip c = AudioSystem.getClip();
			AudioInputStream in = AudioSystem.getAudioInputStream(soundFile);
			c.open(in);
			c.start();
		}catch(LineUnavailableException | IOException |UnsupportedAudioFileException e){
			e.printStackTrace();
		}
		
		
		Thread pop = new Thread(new Runnable(){
			@Override
			public void run(){
				//while(popRunning){
				dialog.setVisible(true);
				//}
			}
		});
		
		pop.start();
		TimeUnit.SECONDS.sleep(NOTIFICATION_TIME);
		dialog.dispose();
		popRunning = false;

		
	}

}
