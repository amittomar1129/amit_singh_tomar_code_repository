package designpattern.behavioral.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Manager extends Employee {
	
	List<Employee> list = new ArrayList<Employee>();

	public Manager(long id, String name, String designation, Department department, int salary) {
		super(id, name, designation, department, salary);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int teamSize() {
		return list.stream().mapToInt(l -> l.teamSize()).sum();
	}

	@Override
	public String teamNames() {
		return String.join(", ", list.stream().map(l -> l.isManager() ? l.teamNames() : l.getName()).collect(Collectors.toList()));
	}

	@Override
	public boolean isManager() {
		return true;
	}
	
	public void manages(Employee employee)
	{
		list.add(employee);
	}
	
	public void removeEmployee(Employee employee)
	{
		list.remove(employee);
	}
	

}
