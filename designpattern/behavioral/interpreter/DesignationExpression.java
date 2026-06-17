package designpattern.behavioral.interpreter;

public class DesignationExpression extends TerminalExpression {

	String designation;
	
	public DesignationExpression(String designation) {
		this.designation = designation;
	}
	
	@Override
	public boolean interpret(Employee context) {
		return context.getDesignation().equalsIgnoreCase(this.designation);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DesignationExpression designation is "+this.designation;
	}
}
