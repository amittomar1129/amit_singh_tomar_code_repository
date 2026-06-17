package designpattern.creational.prototype;

public class App {

//	Provides mechanism to copy the original object to a new object and then modify it according to our needs.
// 	Prototype design pattern uses java cloning to copy the object.
//	When the cost of creating an object is expensive or complicated and client application needs to be
//	unaware of object creation and representation.
	
//	Advantage:
//		Reduces the need of sub-classing.
//		Hides complexities of creating objects.
//		It lets you add or remove objects at runtime.

//	Disadvantages:
//  	Time-consuming. Timing is one of the most noticeable disadvantages of prototype model.
//		Misunderstanding regarding the final version.
//		Insufficient analysis.
	
//	Problem:
//	you want to create an exact copy of it. How would you do it? 
//	First, In order to create a new object of the same class, you have to go through all the fields of the original object and 
//	copy their values over to the new object.
//	Not all objects can be copied that way because some of the object’s fields may be private and not visible from outside of the object itself.	
//	There’s one more problem with the direct approach. Since you have to know the object’s class to create a duplicate,
//	your code becomes dependent on that class.

//	Difference between Prototype and Cloning:
//  You control what and how to copy (deep, partial, modified).          Default clone() only does shallow copy unless you override.
//	Can avoid constructors completely.																   Calls super.clone() — bypasses constructor.
//	Can control synchronization inside prototype factory                 clone() is not thread-safe
//	Open/Closed Principle: Easy to add new prototypes without touching code    Hard to extend safely
//	Recommended by Java Experts?         YES                                   NO (even Joshua Bloch warns against clone())
//	Works well with immutability / builders

	public static void main(String[] args) throws Exception {
		
		Employee employee = new Employee(1, "Amit", "CEO", 100000000000.0D, "California");
		System.out.println(employee.showDetails());
		
		Employee cloned = employee.getClone();
		cloned.setDesignation("OWNER");
		System.out.println(cloned.showDetails());
	}
}