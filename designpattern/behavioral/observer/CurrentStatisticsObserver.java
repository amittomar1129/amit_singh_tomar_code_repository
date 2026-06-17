package designpattern.behavioral.observer;

public class CurrentStatisticsObserver implements Observer {

	Subject subject;

	CurrentStatisticsObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		int run = subject.getState().getRun();
		int over = subject.getState().getOver();
		int wicket = subject.getState().getWicket();
		System.out.println(String.format("Current Statistics: [Runs: %s, Overs: %s, Wickets: %s]", run, over, wicket));
	}
}
