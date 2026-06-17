package designprinciple.clean_design.objects_vs_datastructures;

public class App {

//	Definition:
//	Objects vs Data Structures Principle, same as “Tell, Don’t Ask”.
//	Objects hide their data and expose behavior. Data structures expose their data and have no meaningful behavior.
//	Objects and data structures are essentially opposite of each other.

//	1. In an OBJECT-oriented design: Objects encapsulate data and expose business behavior.
//	interface Shape {
//		double area();
//	}
//	class Circle implements Shape {
//		private double radius;
//		public Circle(double radius) { this.radius = radius; }
//		public double area() { return Math.PI * radius * radius; }
//	}
//	class Square implements Shape {
//		private double side;
//		public Square(double side) { this.side = side; }
//		public double area() { return side * side; }
//	}
//	The calling code doesn't know what data fields exist. It only knows behaviors.
//	Benefits:
//	Open/Closed Principle compliance (easy to add new shapes)
//	LSP preservation (circle behaves like a shape)
//	Reduced conditional logic
//	Polymorphism makes code cleaner

//	2. In a DATA-STRUCTURE-oriented design: Data is exposed, behavior is absent.
//	class Circle {
//		public double radius;
//	}
//	class Square {
//		public double side;
//	}
//	Calling code becomes very procedural:
//	double area(Object shape) {
//		if (shape instanceof Circle c) return Math.PI * c.radius * c.radius;
//		if (shape instanceof Square s) return s.side * s.side;
//		throw new IllegalArgumentException();
//	}
//	When you want flexibility to add new operations, not new types.
//	When the system is more functional/analytical than behavioral.


//	The Anti-Pattern: Mixing Both Halfway, Avoiding Hybrid Structures:
//	Worst design:
//		You create “objects” but keep all fields public.
//		You add getters for EVERYTHING and do logic outside.
//		Your classes expose data AND behavior partially.
//	Hybrid structures are half objects and half data structures.
//	Hybrids eliminate the advantages of objects and data structures and offer only their limitations as they make
//	It hard to add new functions and new data types.

//	Choosing Data Structures or Objects:
//	Need to add new types often and Domain specific behavior, Use OOP (Objects).
//	Need to add new operations often and System is functional/batch/analytics, Use Data Structures (Procedural)

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
