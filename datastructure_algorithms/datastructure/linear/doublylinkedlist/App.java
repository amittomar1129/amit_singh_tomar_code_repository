package datastructure_algorithms.datastructure.linear.doublylinkedlist;

import java.util.LinkedList;

public class App {

  public static void main(String[] args) {

    DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList();
    doublyLinkedList.add("Amit1");
    doublyLinkedList.add("Amit2");
    doublyLinkedList.add("Amit3");
    doublyLinkedList.add("Amit4");
    doublyLinkedList.add("Amit5");
    doublyLinkedList.add("Amit6");
    doublyLinkedList.set(5, "Amit99");
    System.out.println(doublyLinkedList);


    /*
    Iterator Testing...
    Iterator<String> iterator = doublyLinkedList.iterator();
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.next());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.next());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.next());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.next());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.next());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.next());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.previous());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.previous());
    System.out.println("--------------------------");
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.previousIndex());
    System.out.println(iterator.nextIndex());
    System.out.println(iterator.previous());
    System.out.println("--------------------------");
*/

    LinkedList<String> list = new LinkedList<String>();
    list.add("Amit1");
    list.add("Amit2");
    list.add("Amit3");
    list.add("Amit4");
    list.add("Amit5");
//        System.out.println(list);

  }
}
