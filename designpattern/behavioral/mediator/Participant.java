package designpattern.behavioral.mediator;

public abstract class Participant {

	public abstract void setname(String name);

	public abstract String getName();

	public abstract void sendMsg(String msg);
}