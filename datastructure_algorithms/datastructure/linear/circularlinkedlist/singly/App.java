package datastructure_algorithms.datastructure.linear.circularlinkedlist.singly;

import java.util.LinkedList;

public class App {

  public static void main(String[] args) {

    CircularLinkedList<String> circularLinkedList = new CircularLinkedList();
    circularLinkedList.add("Amit1");
    circularLinkedList.add("Amit2");
    circularLinkedList.add("Amit3");
    circularLinkedList.add("Amit4");
    circularLinkedList.add("Amit5");
    circularLinkedList.add("Amit6");
    System.out.println(circularLinkedList);

    CircularLinkedList<String> circularLinkedList1 = new CircularLinkedList();
    circularLinkedList1.add("Amit1");
    circularLinkedList1.add("Amit2");
    circularLinkedList.addAll(circularLinkedList1.iterator());
    System.out.println(circularLinkedList);



//    Iterator Testing...
//    Iterator<String> iterator = circularLinkedList.iterator();
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");
//    System.out.println(iterator.hasPrevious());
//    System.out.println(iterator.hasNext());
//    System.out.println(iterator.previousIndex());
//    System.out.println(iterator.nextIndex());
//    System.out.println(iterator.next());
//    System.out.println("--------------------------");

    LinkedList<String> list = new LinkedList<String>();
    list.add("Amit1");
    list.add("Amit2");
    list.add("Amit3");
    list.add("Amit4");
    list.add("Amit5");
//        System.out.println(list);

  }
}
