package cal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Tester {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		ArrayList<Appointment> a = Reader.readFile();
		checkReminder(a);
		
//		final JFrame f = new JFrame();
//		final JOptionPane j = new JOptionPane("Hello!");
//		final JDialog dialog = new JDialog(f,"Test", true);
//		dialog.setContentPane(j);
//		
//		dialog.pack();
//		
//		Thread t = new Thread(new Runnable(){
//			public void run(){
//				dialog.setVisible(true);
//			}
//		});
//		t.start();
//		TimeUnit.SECONDS.sleep(3);
//		dialog.dispose();
//
//		System.exit(0);
		
		//JOptionPane.showMessageDialog(null, "hello!");
		
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
		
		System.out.println(currentDate);
		
		Appointment test = new Appointment("Apr 04 2018", "03:49:00", "Jeff", "3D print");
		System.out.println(test.getDate());
		
		String timeData[] = s[3].split(":");
		//hr,min,sec
		int currentHr = Integer.parseInt(timeData[0]);
		int currentMin = Integer.parseInt(timeData[1]);
		
		int currentMH = currentHr*60 + currentMin;
		//total min
		
		System.out.println(test.toString());
		
		String testData[] = test.getTime().split(":");
		int dateHr = Integer.parseInt(testData[0]);
		int dateMin = Integer.parseInt(testData[1]);
		int dateMH = dateHr*60 + dateMin;
		
		createReminder(test);
		
//		for(Appointment i: a){
//			String iData[] = i.getDate().split(":");
//			int iHr = Integer.parseInt(iData[0]);
//			int iMin = Integer.parseInt(iData[1]);
//			int iMH = iHr*60 + iMH;
//			
//			while(dateMH - iMH <= 10)
//
//				createReminder(i);
//		}
	}
	
	public static void createReminder(Appointment input) throws InterruptedException{
		
		final JFrame f = new JFrame();
		final JOptionPane j = new JOptionPane(String.format("%s has an appointment at %s", input.getName(), input.getTime()));
		final JDialog dialog = new JDialog(f,"Appointment", true);
		dialog.setContentPane(j);
		
		dialog.pack();
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				dialog.setVisible(true);
			}
		});
		t.start();
		TimeUnit.SECONDS.sleep(3);
		dialog.dispose();
		
		//System.exit(0);

	}

}
