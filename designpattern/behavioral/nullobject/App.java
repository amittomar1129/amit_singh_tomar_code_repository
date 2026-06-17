package designpattern.behavioral.nullobject;

public class App {

//  It avoids check of NULL object instance. Instead of putting check for a null value,
//	Null Object reflects a do nothing relationship. Such Null object can also be used to provide default
//	behavior in case data is not available.
//	Null objects can be used in place of real objects when the object is expected to do nothing.
//	Whenever client code expects a real object, it can also take a null object. Also makes the client code simple.

//	Advantage:
//		Null object is very predictable and has no side effects: it does nothing.

//	Disadvantages:
//  	Increased Memory Consumption.
//		It introduces additional objects into the system, even when objects are absent.
	
//	Problem:
//	The null object pattern is a design pattern used to handle the case when an object is null or undefined. The basic idea behind
//	the pattern is to create a null object that has the same interface as the original object but does nothing when its methods are called.

	public static void main(String[] args) {

		AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
		AbstractCustomer customer2 = CustomerFactory.getCustomer("Amit");
		AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
		AbstractCustomer customer4 = CustomerFactory.getCustomer("Tomar");

		System.out.println("Customers:");
		System.out.println(customer1.getName());
		System.out.println(customer2.getName());
		System.out.println(customer3.getName());
		System.out.println(customer4.getName());
	}
}
