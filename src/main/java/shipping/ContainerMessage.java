package shipping;

public class ContainerMessage {
	
	private String containerId;
	private int tempC;
	private int amp;
	private double cumulativePowerConsumption;
	private int contentType;
	private double humidity;
	private double co2;
	private int Tproduce;
	private String shipID;
	private String timestampMillis;
	
	public ContainerMessage() {}
	
	public ContainerMessage(String containerId, int tempC, int amp, double cumulativePowerConsumption, int contentType, double humidity, double c02, int Tproduce, String shipID, String timestampMillis) {
		this.containerId = containerId;
		this.tempC = tempC;
		this.amp = amp;
		this.cumulativePowerConsumption = cumulativePowerConsumption;
		this.contentType = contentType;
		this.humidity = humidity;
		this.co2 = cumulativePowerConsumption;
		this.Tproduce = Tproduce;
		this.shipID = shipID;
		this.timestampMillis = timestampMillis;
	}
		
	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	
	public int getTempC() {
		return tempC;
	}

	public void setTempC(int tempC) {
		this.tempC = tempC;
	}

	public int getAmp() {
		return amp;
	}

	public void setAmp(int amp) {
		this.amp = amp;
	}

	public double getCumulativePowerConsumption() {
		return cumulativePowerConsumption;
	}

	public void setCumulativePowerConsumption(double cumulativePowerConsumption) {
		this.cumulativePowerConsumption = cumulativePowerConsumption;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getCo2() {
		return co2;
	}

	public void setCo2(double co2) {
		this.co2 = co2;
	}
	
	public int getTproduce() {
		return Tproduce;
	}

	public void setTproduce(int tproduce) {
		Tproduce = tproduce;
	}

	public String getShipID() {
		return shipID;
	}

	public void setShipID(String shipID) {
		this.shipID = shipID;
	}

	public String getTimestampMillis() {
		return timestampMillis;
	}

	public void setTimestampMillis(String timestampMillis) {
		this.timestampMillis = timestampMillis;
	}

	public String toString() {
		return "ContainerMessage{"
				+ "\n\ttype: " + this.containerId
				+ "\n\tcount: " + this.tempC
				+ "\n\tcount: " + this.amp
				+ "\n\tcount: " + this.cumulativePowerConsumption
				+ "\n\tcount: " + this.contentType
				+ "\n\tcount: " + this.humidity
				+ "\n\tcount: " + this.co2
				+ "\n\tcount: " + this.Tproduce
				+ "\n\tcount: " + this.shipID
				+ "\n\tcount: " + this.timestampMillis
				+ "\n}";
	}
}
