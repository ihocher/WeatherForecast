


public class DailyDataBase {
	private String date;
	private String city;
	private String sky;
	private String temperature;
	private String downfall;
	
	DailyDataBase(String city)
	{
		this.city = city;
	}
	
	public String getSky() {
		return sky;
	}

	public void setSky(String sky) {
		this.sky = sky;
	}
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getDownfall() {
		return downfall;
	}
	public void setDownfall(String downfall) {
		this.downfall = downfall;
	}
		
	public String getCity() {
		return city;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
		
	public String toString(){
		return "місто: " + getCity() + "\n"
				+ "дата: " + getDate() + "\n"
				+ "небо: " + getSky() + "\n"
				+ "температура: " + getTemperature() + "\n"
				+ "опади: " + getDownfall() + "\n\n";
				
	}
}
