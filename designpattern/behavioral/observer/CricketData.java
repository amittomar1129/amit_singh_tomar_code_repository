package designpattern.behavioral.observer;

public class CricketData {

	private int run;
	private int over;
	private int wicket;
	
	public CricketData(int run, int over, int wicket) {
		this.run = run;
		this.over = over;
		this.wicket = wicket;
	}

	public int getRun() {
		return run;
	}

	public int getOver() {
		return over;
	}

	public int getWicket() {
		return wicket;
	}

	public void setRun(int run) {
		this.run = run;
	}

	public void setOver(int over) {
		this.over = over;
	}

	public void setWicket(int wicket) {
		this.wicket = wicket;
	}
}
