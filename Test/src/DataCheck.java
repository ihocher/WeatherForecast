
public class DataCheck {

	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	DataCheck(String name){
		this.setName(name);
	}
	
	public String toString(){
		return "Name - " + this.name + "; " + "Age - " + this.age;
	}
}