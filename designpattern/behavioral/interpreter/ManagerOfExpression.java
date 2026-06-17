package designpattern.behavioral.interpreter;

public class ManagerOfExpression extends TerminalExpression {

	String name;
	
	public ManagerOfExpression(String name) {
		this.name = name;
	}

	@Override
	public boolean interpret(Employee context) {
		if(context.isManager())
			return context.teamNames().toLowerCase().contains(this.name.toLowerCase());	
		return false;
	}
	
	@Override
	public String toString() {
		return "ManagerOfExpression name is "+this.name;
	}

}