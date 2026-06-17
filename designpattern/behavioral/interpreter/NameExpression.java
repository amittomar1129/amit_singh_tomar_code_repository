package designpattern.behavioral.interpreter;

public class NameExpression extends TerminalExpression {

	String name;
	
	public NameExpression(String name) {
		this.name = name;
	}
	
	@Override
	public boolean interpret(Employee context) {
		return context.getName().equalsIgnoreCase(this.name);
	}
	
	@Override
	public String toString() {
		return "NameExpression name is "+this.name;
	}

}
