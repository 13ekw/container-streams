package shipping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContainerMessage {
	
	private String containerId;
	private int tempC;
	private int amp;
	private double cumulativePowerConsumption;
	private int contentType;
	private double humidity;
	private double co2;
	@JsonProperty("Tproduce")
	private long Tproduce;
	private String shipID;
	private String timestampMillis;
	
	public ContainerMessage() {}
	
// {"containerId":"c_0","tempC":41,"amp":21,"cumulativePowerConsumption":0.0,"contentType":0,"humidity":0.0,"co2":0.0,"Tproduce":0,"shipID":"JimminyCricket","timestampMillis":1561986397873}

	public ContainerMessage(String containerId, int tempC, int amp, double cumulativePowerConsumption, int contentType, double humidity, double c02, long Tproduce, String shipID, String timestampMillis) {
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
	
	public long getTproduce() {
		return Tproduce;
	}

	public void setTproduce(int Tproduce) {
		this.Tproduce = Tproduce;
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
