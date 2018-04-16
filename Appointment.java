package cal;

public class Appointment {
	
	private boolean shown;
	private String date;
	private String ap;
	//MMM DD, YYYY
	//Ex: Feb 03, 2018
	private String time;
	//HH:MM:SS
	// 14:30:00
	private String name;
	private String type;
	
	public Appointment(){
		
	}
	public Appointment(String date, String time, String name, String type) {
		this.date = date;
		this.time = time;
		this.name = name;
		this.type = type;
		this.ap = "am";
		this.shown = false;
	}
	public void setShown(boolean shown) {
		this.shown = shown;
	}
	public boolean getShown() {
		return shown;
	}
	public String getAP(){
		return ap;
	}
	public void setAP(String ap){
		this.ap= ap;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object o){
		Appointment a = (Appointment) o;
		return this.name == a.getName() && this.date == a.getDate() &&
				this.time == a.getTime() && this.type == a.getTime();
		
	}
	
	@Override
	public String toString(){
		return String.format("%s\t%s\t%s\t%s\n", this.date, this.time, this.name, this.type);

	}
	

}
