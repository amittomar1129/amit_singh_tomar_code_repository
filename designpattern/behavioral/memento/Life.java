package designpattern.behavioral.memento;

public class Life {

	private String moment;
	private String time;
	
	public Life(String moment, String time) {
		this.moment = moment;
		this.time = time;
	}

	/**
	 * @return the moment
	 */
	public String getMoment() {
		return moment;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param moment the moment to set
	 */
	public void setMoment(String moment) {
		this.moment = moment;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Life [moment=" + moment + ", time=" + time + "]";
	}
	
}
