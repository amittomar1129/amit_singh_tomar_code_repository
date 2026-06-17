package designpattern.structural.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

//	Facade pattern hides the complexities of the system and provides an interface to a set of interfaces
//	to the client using which the client can access the system. Provides simple interface to a complex sub-system.
// 	This pattern involves a single class which provides simplified methods required by client and
// 	delegates calls to methods of existing system classes.
//	When several dependencies exist between clients and the implementation classes of an abstraction.

// Advantage:
// 		It shields the clients from the complexities of the sub-system components.
//		It promotes loose coupling between subsystems and its clients.

// Disadvantages:
// 		It can also add an extra layer of complexity to the system and may not always be appropriate for smaller systems or projects.
//		Reduced Flexibility: The facade acts as a single point of access to the underlying system.

//	Problem:
//	Imagine that you must make your code work with a broad set of objects that belong to a sophisticated library or framework. 
//	Ordinarily, you’d need to initialize all of those objects, keep track of dependencies, execute methods in the correct order, and so on.	
//	when a system is very complex or difficult to understand because the system has many interdependent classes or 
//	because its source code is unavailable. This pattern hides the complexities of the larger system and provides a simpler interface to the client.

  public static void main(String[] args) {

    ShapeMaker shapeMaker = new ShapeMaker();

    shapeMaker.drawCircle();
    shapeMaker.drawRectangle();
    shapeMaker.drawSquare();
  }
}
