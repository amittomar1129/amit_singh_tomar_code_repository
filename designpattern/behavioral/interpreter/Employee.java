package designpattern.behavioral.interpreter;

public abstract class Employee {

  private long id;
  private String name;
  private String designation;
  private Department department;
  private int salary;

  public Employee(long id, String name, String designation, Department department, int salary) {
    super();
    this.id = id;
    this.name = name;
    this.designation = designation;
    this.department = department;
    this.salary = salary;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }


  public abstract int teamSize();

  public abstract String teamNames();

  public abstract boolean isManager();

  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", designation=" + designation
        + ", department=" + department
        + ", salary=" + salary + "]";
  }
}
