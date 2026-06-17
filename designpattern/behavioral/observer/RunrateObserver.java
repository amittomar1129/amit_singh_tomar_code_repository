package designpattern.behavioral.observer;

public class RunrateObserver implements Observer {

	Subject subject;

	RunrateObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		int run = subject.getState().getRun();
		int over = subject.getState().getOver();
		System.out.println(String.format("Current Run rate: [Runrate: %s]", (float) run / over));
	}
}
