package designprinciple.solid.liskov_substitution;

public class App {

//	Imagine buying a new set of headphones. You plug them into your laptop, and the screen displays an error message saying
//	you have to modify your computer. What is wrong? This issue happens because the old and the new headphones don’t respect the same
//	interface, so they are not interchangeable without the laptop losing some of its functionality.

//	Definition:
//	A child class must be substitutable for its parent class without breaking the program.
//	If B extends A, then any object of type A can be replaced with an object of type B without changing correctness,
//	behavior, or expectations.
//	The LSP is about child classes fulfilling the contract of their parent class.
//	Breaking the defined contract violates the LSP.
//	The LSP focuses on “IS SUBSTITUTABLE FOR A” rather than “IS A.”

//	Inheritance and the IS-A Relationship:
//	Even if a new object can fulfill the IS-A relationship with an old object, it does not always mean that the new object's
//	class should be derived from the old object’s class.
//	For example, The app requires multiple types of birds, so you create the abstract Bird base class.
//	To get the subclass Eagle, you inherit from the base. The inheritance is appropriate in this case because an eagle is still a bird.
//	Eagle IS A Bird.
//	The LSP suggests you should consider whether the base class is replaceable with the subclass in terms of behavior
//	in all expected situations.

//	Substitutability:
//	For substitutability to work, make sure that the derived classes extend the old classes.
//	The specific functionality of the subclass may be different, but it must conform to the base class expected behavior.
//	Otherwise, the new classes can produce undesired effects when you use them in existing program modules.
//	To avoid those effects, Child classes must not:
//	1. Remove behavior of the parent class;
// 	2. Violate invariants of the parent class;

//	Invariants: To make subclasses compatible with the base class, you should determine the invariants of the base class.
//	Invariants are conditions or properties assumed to hold true at all times for all base class states. Examine how to determine,

//	Violating the LSP:
//	If it looks like a duck, swims like a duck, and quacks like a duck, then it probably is a duck.
//	If it looks like a duck and quacks like a duck, but it needs batteries, you probably have the wrong abstraction.
//	Non-substitutable code breaks polymorphism.

  //	Detects LSP Violations:
//	1. Type verification - is one way to detect an LSP violation. A subclass should not change the types
//	(input or output) of methods in a way that breaks behavior or expectation of the base type.

//	class Notifier {
//		Notification send(Message msg) { ... }
//	}
//	Subclass violating LSP:
//	class EmailNotifier extends Notifier {
//		EmailNotification send(EmailMessage msg) { ... }
//	}

//	2. Incomplete Implementation - A subclass is forced to override a method but cannot provide a meaningful,
//	correct, or valid implementation.
//	This is one of the strongest red flags that inheritance hierarchy is broken or incorrectly modeled.

//	class Rectangle {
//		void setWidth(int w) { ... }
//		void setHeight(int h) { ... }
//	}
//	class Rectangle {
//		void setWidth(int w) { ... } // must force height = width
//		void setHeight(int h) { ... } // must force width = height
//	}
//	Another Example:
//	abstract class Bird {
//		abstract void fly();
//	}
//	class Penguin extends Bird {
//		@Override
//		void fly() {
//			throw new UnsupportedOperationException("Penguins can't fly");
//		}
//	}

//	Fixing LSP Violations: If a subclass cannot behave exactly like the parent class,
//	then the inheritance hierarchy is WRONG and must be redesigned. We fix LSP by changing the model,
//	not by patching the subclass.
//	-Avoid objects interrogating their internals. Instead, Move that behavior into the object
//	that has the state and the behavior collected together.
//	-When you have two classes that share a lot of behavior but are not substitutable:
//	Create a third base class that both existing classes can derive from
//	Ensure substitutability between each of the existing class and the new base class.

//  Fix:
//  interface Bird { }
//
//  interface Flyable {
//    void fly();
//  }

//  class Sparrow implements Bird, Flyable {
//    @Override
//    public void fly() { }
//  }
//
//  class Penguin implements Bird {
//    // no fly() method ? no violation
//  }

//  Fix:
//  class Rectangle {
//    private final int width;
//    private final int height;
//
//    Rectangle(int width, int height) {
//      this.width = width;
//      this.height = height;
//    }
//
//    int getWidth() { return width; }
//    int getHeight() { return height; }
//  }

//  class Square extends Rectangle {
//    Square(int side) {
//      super(side, side);
//    }
//  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
  }
}
