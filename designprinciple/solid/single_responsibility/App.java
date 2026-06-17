package designprinciple.solid.single_responsibility;

import java.time.LocalDate;
import java.util.HashSet;

public class App {

//	Imagine you are planning a party. You have a list of things you need to get done, and you have several friends to help you.
//	But you decide not to divide the tasks between all the participants. You assume that people will communicate, and
//	they will figure everything out by themselves eventually. The day of the party arrives, and it appears that you have
//	more than enough plates and glasses, not enough drinks, and no snacks at all. There was something wrong with
//	your friends’ responsibilities.
//	Just like in ordinary life, neglecting the Single Responsibility Principle (SRP) will cause significant problems.

//	Structuring code without the SRP in mind may seem easy at the beginning of a project. But as an application grows and becomes
//	more sophisticated, problems will appear when you and other developers make modifications. A design that doesn't adhere to
//	the SRP is fragile and results in effects that are hard to predict.
//	To avoid your code getting messy, use the Single Responsibility Principle.

//	Definition:
//	To better understand what the SRP means, first examine the guidelines for a class’s size.
//	Classes and files should be small. According to that rule, functions should be small. In Clean Design the class should be small as well.
//	The class should be small enough that you can describe its behavior by one sentence.
//	Use the criteria below to make sure you follow best practices.

//	1. Can the class be described in one sentence?
//	2. Is it fewer than 25 words?
//	3. Does it have zero instances of “if,“ “or,” and “but”?
//	4. Does it have one responsibility?
//  The responsibility of a class is its reason to change. The Single Responsibility Principle states that each class should have only
//  one reason to change. Everything within a class should do one thing, so there is only one reason for the class to change.
//	Classes that adhere to the SRP tend to have high cohesion.

//	Problem:
//	Consider an application that has several classes. You receive a request to change the existing calculation logic.
//	To make this change, you modify three classes. Later you receive another change request to change reporting in the application.
//	You modify three more classes. Now suppose you receive a third change request to change the visual representation of the site.
//	You modify the class that you altered in the original change of the calculation logic. Unfortunately, there is now a violation
//	of the SRP as there are two reasons to change the class.

//	Achieve SRP: Checking the cohesion of a class allows you to detect if there is an SRP violation.
//	Achieving High Cohesion in Practice.
//	Splitting a Low Cohesive Class into several high cohesive classes.

//	High Cohesion:
//	Consider a class with fields and methods. If almost every method uses all it's fields then It is called High Cohesive class.
//	Low Cohesion:
//	Some sub set of methods usually uses some sub set of fields. It is called Low Cohesive class.

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}


// Employee class does not follow SRP as printDetails method adds one more responsibility to it.
class Employee
{
	private String firstName;
	private String lastName;
	private LocalDate hireDate;
	private HashSet<Employee> subordinates;

	public Employee(String firstName, String lastName, LocalDate hireDate) {}
	public void addSubordinate(Employee subordinate) { }
	public void removeAllSubordinates() {}
	public void printDetails() {}
}

// Account class does not follow SRP as save is a persistence responsibility.
class Account {
	public Double calculateBalance() {return null;}
	public void save() {}
}

// Order class has 4 responsibilities.
class Order {
	public Double calculateTax() {return null;}
	public void applyPromotions() {}
	public Integer checkItemsAvailability() {return null;}
	public String describeOrder() {return null;}
}