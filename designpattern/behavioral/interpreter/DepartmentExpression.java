package designpattern.behavioral.interpreter;

public class DepartmentExpression extends TerminalExpression {

	String department;
	
	public DepartmentExpression(String department) {
		this.department = department;
	}

	@Override
	public boolean interpret(Employee context) {
		return context.getDepartment().name().equalsIgnoreCase(this.department);
	}
	
	@Override
	public String toString() {
		return "DepartmentExpression department is "+this.department;
	}
	

}
