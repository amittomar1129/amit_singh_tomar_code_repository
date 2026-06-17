package datastructure_algorithms.datastructure.linear.stack;



//  Stack is a linear data structure in which insertion and deletion are done at one end, called as top.
//  The last element inserted is the first one to be deleted. Hence, it is called Last in First Out(LIFO) or
//  First in Last Out(FILO) list.
//  Note: Time Complexity is of order 1 for all operations of the stack

//  Advantages of Using LinkedList for Stack Implemenation:
//  Grows and shrinks gracefully.
//  Every operation takes constant time O(1).
//  Every operation uses extra space and time to deal wih references.

public class App {

  public static void main(String[] args) {

    Stack<String> s = new Stack<>();
    s.push("Amit1");
    s.push("Amit2");
    s.push("Amit3");
    s.push("Amit4");
    s.push("Amit5");
    System.out.println(s);
    System.out.println(s.search("Amit3"));

    StackUsingLinkedList<String> a = new StackUsingLinkedList<>();
    a.push("Amit");
    a.push("Amit1");
    a.push("Amit2");
    a.push("Amit3");
    System.out.println(a);
    System.out.println(a.search("Amit2"));
  }
}
