package designpattern.creational.prototype;

public class Employee implements Prototype<Employee> {

	private int id;
	private String name;
	private String designation;
	private double salary;
	private String address;

	public Employee(int id, String name, String designation, double salary, String address) {
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.address = address;
	}

	public String showDetails() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary
				+ ", address=" + address + "]";
	}

	@Override
	public Employee getClone() {
		return new Employee(id, name, designation, salary, address);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
