package designpattern.creational.singleton;

/*
 * 	To overcome this situation with Reflection, Joshua Bloch suggests the use of enum to implement the singleton design pattern 
 *	as Java ensures that any enum value is instantiated only once in a Java program. Since Java Enum values are globally accessible, 
 *	so is the singleton. The drawback is that the enum type is somewhat inflexible (for example, it does not allow lazy initialization).
 *  Java guarantees thread-safety + serialization protection using enum. No reflection, cloning, or serialization can break it.
 */
public enum EnumSingleton {

	INSTANCE;

	public void doSomething() {
		// do some operations
		System.out.println("Enum Do something");
	}

	public void doBusinessLogics() {
		// do some operations
		System.out.println("Enum Do business logics");
	}
}
