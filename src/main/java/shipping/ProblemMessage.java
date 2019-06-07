package shipping;

public class ProblemMessage {
	
	private String containerId;
	private int tempC;
	private String timestampMillis;
	private String message;
	
	public ProblemMessage() {}
	
	public ProblemMessage(String containerId, int tempC, String timestampMillis, String message) {
		this.containerId = containerId;
		this.tempC = tempC;
		this.timestampMillis = timestampMillis;	
		this.message = message;
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

	public String getTimestampMillis() {
		return timestampMillis;
	}

	public void setTimestampMillis(String timestampMillis) {
		this.timestampMillis = timestampMillis;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "Problem{"
				+ "\n\tcontainer: " + this.containerId
				+ "\n\ttemperature: " + this.tempC + " C"
				+ "\n\ttime/date: " + this.timestampMillis
				+ "\n\tmessage: " + this.message
				+ "\n}";
	}
}
