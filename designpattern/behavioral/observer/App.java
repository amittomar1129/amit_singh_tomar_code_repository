package designpattern.behavioral.observer;

public class App {

//  The observer pattern is also known as Dependents or Publish-Subscribe. 	
//	It is useful when you are interested in the state of an Object and want to get notified
//	whenever there is any change. In the observer pattern, the Object that watches the state of
//	another Object is called observer and the Object that is being watched is called subject.
//	When the change of a state in one object must be reflected in another object without keeping the objects tight coupled.

//	Advantage:
//		It describes the coupling between the objects and the observer.
//		It provides the support for broadcast-type communication.

//	Disadvantages:
//  	If not correctly implemented, the Observer can add complexity and lead to performance issues.
// 		It varies subjects and observers independently. You can reuse subjects without reusing their observers, and vice versa.
	
//	Problem:
//	Imagine that you have two types of objects: a Customer and a Store. The customer is very interested in a particular
//	brand of product. The customer could visit the store every day and check product availability.
//	But while the product is still en route, most of these trips would be pointless.
//	On the other hand, the store could send tons of emails to all customers each time a new product becomes available. 

	public static void main(String[] args) {

		Subject subject = new Subject();
		
		new CurrentStatisticsObserver(subject);
		new RunrateObserver(subject);
		new WinningPercentageObserver(subject);

		System.out.println("first time");
		subject.setState(49, 6, 0);
		System.out.println("second time");
		subject.setState(121, 20, 3);
		System.out.println("third time");
		subject.setState(380, 40, 6);
	}

}
