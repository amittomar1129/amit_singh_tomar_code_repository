package designpattern.behavioral.strategy;

public class App {

// Strategy pattern is also known as Policy Pattern.
// If we have multiple algorithm for a specific task and client decides the actual implementation to be
// used at runtime. One of the best example of strategy pattern is Collections.sort() method that takes
// Comparator parameter. Based on the different implementations of Comparator interfaces,
// the Objects are getting sorted in different ways.
// It uses delegation rather than inheritance.
// It is used when you need different variations of an algorithm.

//	Benefits:
//		It provides a substitute to sub-classing.
//		Eliminating the need for conditional statements.
//		It makes it easier to extend and incorporate new behavior without changing the application.
//		More clean code because you separate the concerns into classes.

//	Disadvantages:
//  	The application must be aware of all the strategies to select the right one for the right situation.
	
//	Problem:
//	One day you decided to create a navigation app for casual travelers. 
//	One of the most requested features for the app was automatic route planning. A user should be able
//	to enter an address and see the fastest route to that destination displayed on the map.
//	The first version of the app could only build the routes over roads. People who traveled by car were bursting with joy. 
//	Next update, you added an option to build walking routes. And even later, another option for building routes through all 
//	of a city’s tourist attractions.
//	While from a business perspective the app was a success, the technical part caused you many headaches.
//	Each time you added a new routing	algorithm, the main class of the navigator doubled in size.
//	At some point, the beast became too hard to maintain.

//	Solution:
//	The Strategy pattern suggests that you take a class that does something specific in a lot of different ways and extract all 
//	of these algorithms into separate classes called strategies.

	public static void main(String[] args) {
		Product p1 = new Product();
		p1.setId("0001");
		p1.setPrice("1009");

		Product p2 = new Product();
		p2.setId("0002");
		p2.setPrice("259");

		ShoppingCart<Double> shoppingCart = new ShoppingCart<Double>();
		shoppingCart.addItem(p1);
		shoppingCart.addItem(p2);

		PaymentStrategy creditCardStrategy = new CreditCardStrategy("Amit Singh Tomar", "2850 3654 2485 2214", "695", "1225");

		shoppingCart.selectStrategy(creditCardStrategy);
		shoppingCart.processPayment(); // processing payment according to CreditCard Strategy

		PaymentStrategy paypalStrategy = new PaypalStrategy("91.amittomar@gmail.com", "password_xyz");
		shoppingCart.changeStrategy(paypalStrategy);
		shoppingCart.processPayment(); // processing payment according to Paypal Strategy

		ShoppingCart cart = new ShoppingCart();
		cart.addItem(p2);

		PaymentStrategy upiStrategy = new UPIStrategy("9386654253@ybl", "1234");
		cart.selectStrategy(upiStrategy);
		cart.processPayment();
	}
}
