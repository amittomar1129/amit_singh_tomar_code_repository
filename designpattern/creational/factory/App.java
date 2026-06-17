package designpattern.creational.factory;


public class App {

//	Also known as Virtual Constructor.
//	Defines an interface/abstract class for creating an object but let the subclasses decide which class to be instantiated.
//	Takes out the responsibility of the instantiation of a class from the client program to the factory class.
//	When a class wants that its sub-classes specify the objects to be created.

//	Advantage:
//		It promotes the loose-coupling. Eliminates the need of binding application specific classes.

//	Disadvantages:
//  	This can result in decreased performance if the creation of objects is a performance bottleneck.
//		It can increase the complexity and size of the code.
//		Introduce an extra level of abstraction, which can make the code harder to understand and debug.
	
//	Problem:
//	Imagine that you’re creating a logistics management application. The first version of your app can only 
//	handle transportation by trucks, so the bulk of your code lives inside the Truck class. 
//	After a while, your app becomes pretty popular. Each day you receive dozens of requests 
//	from Sea transportation companies to incorporate sea logistics into the app. But how about the code?	
//	At present, most of your code is coupled to the Truck class. Adding Ships into the app would require
//	making changes to the entire codebase. As a result, you will end up with pretty nasty code.
	
	public static void main(String[] args) throws Exception {

		CollegeFee collegeFee1 = CollegeFeeFactory.getCollegeFee("MERIT");
		CollegeFee collegeFee2 = CollegeFeeFactory.getCollegeFee("SPECIAL");
		CollegeFee collegeFee3 = CollegeFeeFactory.getCollegeFee("MANAGEMENT");
		CollegeFee collegeFee4 = CollegeFeeFactory.getCollegeFee("NONE");
		collegeFee1.calculateFee();
		collegeFee2.calculateFee();
		collegeFee3.calculateFee();
		collegeFee4.calculateFee();
	}
}
