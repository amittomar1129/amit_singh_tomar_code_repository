package designpattern.creational.builder;

public class App {

//	Builder Pattern says that "construct a complex object from simple objects using step-by-step approach".
//	It is mostly used when object can't be created in single step like in the de-serialization of a complex object.

//	Advantage:
//		Clear separation between the construction and representation of an object.
//		Provides better control over construction process.
//		The builder pattern is most commonly used to create immutable objects.

//	Disadvantages:
//  	Builder pattern can make the code more verbose or harder to read, because it involves instantiation of separate classes
//		for the builder.

//	Problem:
//	Imagine a complex object that requires step-by-step initialization of many fields and nested objects. 
//	For example, let’s think about how to create a House object. To build a simple house, you need to construct four walls,
//	a floor, install a door, Xfit a pair of windows, and build a roof.

//	The simplest solution is to extend the base House class and create a set of subclasses to cover all combinations of the parameters. 
//	But eventually you’ll end up with a considerable number of subclasses. 
//	Any new parameter, such as the porch style, will require growing this hierarchy even more.	
	
	
	public static void main(String[] args) throws Exception {
		Computer computer = Computer.newBuilder().setRAM("12gb").setHDD("1tb")
				.setBluetoothEnabled(true).build();

		System.out.println(computer);
	}
}
