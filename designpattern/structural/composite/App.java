package designpattern.structural.composite;

public class App {

//	Composite pattern is used where we need to treat a group of objects in similar way as a single object.
//	The main idea behind the Composite Pattern is to build a tree structure of objects, where individual objects
//	and composite objects share a common interface.
//	When the responsibilities(may vary from time to time) to be added dynamically to the individual objects
//	without affecting other objects.

//	Advantage:
//		It defines class hierarchies that contain primitive and complex objects.
//		Provides flexible structure with manageable class or interface and easier to add new kinds of components.

//	Disadvantages:
  //  	Simplifies client code, it can introduce complexity in the implementation of composite objects (composites).
  //		complexity can make the code harder to maintain.

//	Problem:
//	Using the Composite pattern makes sense only when the core model of your app can be represented as a tree.
//	Imagine that you have two types of objects: Products and Boxes. A Box can contain several Products as well as a number of smaller Boxes. 
//	These little Boxes can also hold some Products or even smaller Boxes, and so on.
//	Say you decide to create an ordering system that uses these classes. Orders could contain simple products without any wrapping, 
//	as well as boxes stuffed with products...and other boxes. How would you determine the total price of such an order?

//	You could try the direct approach: unwrap all the boxes, go over all the products and then calculate the total. 
//	That would be doable in the real world;	
//	but in a program, it’s not as simple as running a loop. You have to know the classes of Products and Boxes you’re going through,
//	the nesting level of the boxes and other nasty details beforehand. All of this makes the direct approach either too awkward or even impossible.	

//	Solution:
//	The Composite pattern suggests that you work with Products and Boxes through a common interface which declares a method for calculating 
//	the total price.
//	How would this method work? For a product, it’d simply return the product’s price. For a box, it’d go over each item the box contains, 
//	ask its price and then return a total for this box. If one of these items were a smaller box, that box would also start going over 
//	its contents and so on, until the prices of all inner components were calculated. A box could even add some extra cost to the final price, 
//	such as packaging cost.	
//	The greatest benefit of this approach is that you don’t need to care about the concrete classes of objects that compose the tree. 	

  public static void main(String[] args) {
    // Create products
    Item phone = new Product("Phone", 799);
    Item charger = new Product("Charger", 25);
    Item earphones = new Product("Earphones", 50);

    // Small inner box
    Box accessoriesBox = new Box("Accessories Box", 10);
    accessoriesBox.addItem(charger);
    accessoriesBox.addItem(earphones);

    // Bigger box containing phone + accessories box
    Box mainBox = new Box("Main Box", 15);
    mainBox.addItem(phone);
    mainBox.addItem(accessoriesBox);

    System.out.println("Total order price = $" + mainBox.getPrice());
  }
}
