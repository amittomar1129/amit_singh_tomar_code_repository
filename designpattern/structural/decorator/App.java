package designpattern.structural.decorator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

  // Use to modify the functionality of an object at runtime. At the same time other instances
  // of the same class will not be affected by this, so individual object gets the modified behavior.
  // It attaches a flexible additional responsibilities to an object dynamically.

  // Advantage:
  // Provides greater flexibility rather than static inheritance.
  // Enhances the extensibility of the object, because changes are made by coding new classes.

  // Disadvantages:
  // Decorators can introduce significant complexity to the code, making it harder to understand and maintain.
  // High number of objects.

//	Problem:
//	Use the Decorator pattern when you need to be able to assign extra behaviors to objects at runtime 
//	without breaking the code that uses these objects.
//	Use the pattern when it's awkward or not possible to extend an object's behavior using inheritance.

  private static int choice;

  public static void main(String[] args) throws NumberFormatException, IOException {

    System.out.print("========= Food Menu ============ \n");
    System.out.print("            1. Vegetarian Food.   \n");
    System.out.print("            2. Special Vegetarian Food.   \n");
    System.out.print("            3. Non-Vegetarian Food.\n");
    System.out.print("            4. Chinese Food.         \n");
    System.out.print("            5. Exit                        \n");
    System.out.print("Enter your choice: ");

    Food vf = new VegFood();
    System.out.println(vf.prepareFood());
    System.out.println(vf.foodPrice());

    Food f = new SpecialVegFood(new VegFood());
    System.out.println(f.prepareFood());
    System.out.println(f.foodPrice());

    Food f1 = new NonVegFood();
    System.out.println(f1.prepareFood());
    System.out.println(f1.foodPrice());

    Food f2 = new ChineeseFood(new NonVegFood());
    System.out.println(f2.prepareFood());
    System.out.println(f2.foodPrice());
  }
}
