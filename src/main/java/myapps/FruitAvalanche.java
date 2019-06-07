package myapps;

public class FruitAvalanche {

	private int overflow;

	public FruitAvalanche() {}
	
	public FruitAvalanche(int overflow) {
		this.overflow = overflow;
	}
	
	public int getOverflow() {
		return overflow;
	}

	public void setOverflow(int overflow) {
		this.overflow = overflow;
	}
	
	
	public String toString() {
		return "FruitAvalanche{"
				+ "\n\toverflow: " + this.overflow
				+ "\n}";
	}
}
