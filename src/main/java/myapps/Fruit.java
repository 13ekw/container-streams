package myapps;

public class Fruit {
	
	public static String APPLE = "apple";
	public static String BANANA = "banana";
	private String type;
	private int count;
	
	public Fruit() {}
	
	public Fruit(String type, int count) {
		this.type = type;
		this.count = count;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public String toString() {
		return "Fruit{"
				+ "\n\ttype: " + this.type
				+ "\n\tcount: " + this.count
				+ "\n}";
	}
}
