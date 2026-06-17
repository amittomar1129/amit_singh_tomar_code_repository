package designpattern.behavioral.iterator;

public class App {

// Def:
// Allows sequential traversal through a complex data structure without exposing its internal details.
// The Iterator Design Pattern provides flexible and reusable solution of traversing member objects of a container/collection.
// The main idea of the Iterator pattern is to extract the traversal behavior of a collection into a separate object
// called an iterator.

//	Advantage:
//		It supports variations in the traversal of a collection.
//		It simplifies the interface to the collection.
//		Single Responsibility Principle : This design pattern follows Single Responsibility Principle; the Single Responsibility 
//		Principle allows us to clean up the client and collections of the traversal algorithms into separate classes.
//		The Open/Closed Principle : The Open/Closed Principle allows implementation of new types of collections and
//		iterators without breaking anything.
//		Parallel iteration : You can iterate over the same collection in parallel because each iterator
//		object contains its own iteration state.

//	Disadvantages:
//  	Performance : This pattern is less efficient going through elements of some specialized collections directly.
//  	Uses more memory than direct element access.
//		Not suggested for simple collections.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Topic topics[] = new Topic[5];
		topics[0] = new Topic("Amit");
		topics[1] = new Topic("Computor Science");
		topics[2] = new Topic("Data Structure");
		topics[3] = new Topic("Google");
		topics[4] = new Topic("Excellent");

		List<Topic> list = new ArrayList<Topic>();
		list.addAll(topics);
		Iterator<Topic> iterator = list.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		while(iterator.hasPrevious())
		{
			System.out.println(iterator.previous());
		}


	}

}
