package designpattern.behavioral.interpreter;

public class SalaryExpression extends TerminalExpression {

	String salary; // g, >, l, <, ge >=, le <= will be first char. In case of missing, it refers to equals only.

	public SalaryExpression(String salary) {
		this.salary = salary;
	}

	@Override
	public boolean interpret(Employee context) {
		return interpretSalary(this.salary, context);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "SalaryExpression salary is "+this.salary;
	}

	private boolean interpretSalary(String salary, Employee context) {
		// TODO Auto-generated method stub
		try {
			int sal = Integer.parseInt(salary);
			return context.getSalary() == sal;
		} catch (Exception e) {

		}
		try {
			int  sal = Integer.parseInt(salary.substring(1));
			char operator = salary.charAt(0);
			switch(operator) {
			case 'l' : return context.getSalary() < sal;
			case '<' : return context.getSalary() < sal;
			case 'g' : return context.getSalary() > sal;
			case '>' : return context.getSalary() > sal;
			default : return context.getSalary() == sal;
			}
		} catch (Exception e) {

		}
		try {
			int  sal = Integer.parseInt(salary.substring(2));
			String operator2 = salary.substring(0, 2);
			switch(operator2) {
			case "le" : return context.getSalary() <= sal;
			case "<=" : return context.getSalary() <= sal;
			case "ge" : return context.getSalary() >= sal;
			case ">=" : return context.getSalary() >= sal;
			default : return context.getSalary() == sal;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
