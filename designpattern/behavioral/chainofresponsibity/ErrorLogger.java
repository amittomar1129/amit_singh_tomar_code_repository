package designpattern.behavioral.chainofresponsibity;

public class ErrorLogger extends Logger {

	public ErrorLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("Error::::Logger: " + message);
	}
}