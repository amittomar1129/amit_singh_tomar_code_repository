package designpattern.behavioral.interpreter;

public class Engineer extends Employee {

	public Engineer(long id, String name, String designation, Department department, int salary) {
		super(id, name, designation, department, salary);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int teamSize() {
		return 1;
	}

	@Override
	public String teamNames() {
		return "NA";
	}

	@Override
	public boolean isManager() {
		return false;
	}

}
