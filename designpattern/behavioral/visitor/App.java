package designpattern.behavioral.visitor;

public class App {

//  It lets you separate algorithms from the objects on which they operate.
//	It is used when we have to perform an operation on a group of similar kind of Objects.
//	With the help of visitor pattern,	We can move the operational logic from the objects to another class.

//	The visitor pattern consists of two parts: A method which is implemented by the visitor and
//	is called for every element in the data structure is called Visit().
// 	Visitable classes providing Accept() methods that accept a visitor.

//	Advantage:
//		We need to make change only in the visitor implementation rather than doing it in all the item classes.
//		Adding a new item to the system is easy, it will require change only in visitor interface and implementation 
//		and existing item classes will not be affected.

//	Disadvantages:
//  	we should know the return type of visit() methods at the time of designing otherwise 
//		we will have to change the interface and all of its implementations.
//		If there are too many implementations of visitor interface, it makes it hard to extend.

//	Problem:
//	Imagine that your team develops an app which works with geographic information structured as one colossal graph. Each node of the 
//	graph may represent a complex entity such as a city,
//	The nodes are connected with others if there’s a road between the real objects that they represent. 
//	At some point, you got a task to implement exporting the graph into XML format.
//	At first, the job seemed pretty straightforward.
//	You planned to add an export method to each node class and then leverage recursion to go over each node of the graph.
//	Unfortunately, the system architect refused to allow you to alter existing node classes.
//	He said that the code was already in production and he didn’t want to risk breaking it
//	because of a potential bug in your changes.
//	Besides, he questioned whether it makes sense to have the XML export code within the node classes.
	
//	Solution:
//	The Visitor pattern suggests that you place the new behavior into a separate class called visitor, instead of trying 
//	to integrate it into existing classes.
//	The original object that had to perform the behavior is now passed to one of the visitor’s methods as an argument, 
//	providing the method access to all necessary data contained within the object.
	
	public static void main(String[] args) {

		ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
		Item[] items = new Item[] { new Book(20, "1234"), new Book(100, "5678"),
				new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple") };

		int total = 0;
		for (Item item : items) {
			total = total + item.accept(visitor);
		}

		System.out.println("Total Cost = " + total);
	}
}
