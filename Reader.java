package cal;
import java.io.*;
import java.util.ArrayList;

public class Reader {
	
	public static ArrayList<Appointment> readFile() throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(new File(String.format("src%sdata.csv", File.separator)))));
		String s = "";
		ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();
		s =r.readLine();
		while( (s = r.readLine()) != null){
			String rawData[] = s.replaceAll("\"","").split(",");
			/*	0+1 is date
			 *	4 is time
			 *	6 is name
			 * 11 is type
			 */
			Appointment a = new Appointment(rawData[0]+ rawData[1], rawData[4],rawData[6], rawData[11]);
			if(!(allAppointments.contains(a)))
					allAppointments.add(a);
		}
		
		
		r.close();
		
		
		return allAppointments;
	}

}
