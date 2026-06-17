package designpattern.structural.bridge;

public class App {

//	The Bridge design pattern allows to separate the abstraction from the implementation so they can vary independently.
// 	It splits a large class or a set of closely related classes into two separate hierarchies abstraction
// 	and implementation which can be developed independently of each other.
//	It is mostly used in those places where changes are made in the implementation does not affect the clients.
// 	When you don't want a permanent binding between the functional abstraction and its implementation.

//	Advantage:
//		It enables the separation of implementation from the interface.
//		It allows the hiding of implementation details from the client.
//		It improves the extensibility.

//	Disadvantages:
//  	Extra complexity, especially if the number of abstractions and implementations is high.
//		Increased complexity due to over use of HAS-A principle.	

//	Problem:
//	Say you have a geometric Shape class with a pair of subclasses: Circle and Square. You want to extend this class hierarchy to 
//	incorporate colors, so you plan to create Red and Blue shape subclasses. However, since you already have two subclasses, 
//	you’ll need to create four class combinations such as BlueCircle and RedSquare.
//	Adding new shape types and colors to the hierarchy will grow it exponentially. 
//	Solves this recurring design problems to design flexible and reusable object-oriented software, that is,
//	objects that are easier to implement, change, test, and reuse.	
	
	public static void main(String[] args) {

		Vehicle vehicle1 = new Car(new Produce(), new Assemble());
		vehicle1.manufacture();
		Vehicle vehicle2 = new Bike(new Produce(), new Assemble());
		vehicle2.manufacture();
	}
}
