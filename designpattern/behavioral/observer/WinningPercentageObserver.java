package designpattern.behavioral.observer;

public class WinningPercentageObserver implements Observer {

	Subject subject;

	WinningPercentageObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		int run = subject.getState().getRun();
		int over = subject.getState().getOver();
		int wicket = subject.getState().getWicket();
		System.out.println(String.format("Winning Percentage: [%s", (run/over - wicket/2) * 10)+"%]");
	}
}
