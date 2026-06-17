package datastructure_algorithms.datastructure.linear.queue.usingarraylist;

import datastructure_algorithms.datastructure.linear.queue.Queue;

import java.util.ArrayList;

public class CustomQueue<E> implements Queue<E> {

  private int front = -1;
  private int rear = -1;
  private final ArrayList<E> arrayList = new ArrayList<>();


  /**
   *
   */
  @Override
  public void offer(E element) {
    arrayList.add(element);
    front = 0;
    rear++;
  }

  /**
   *
   */
  @Override
  public E poll() {
    if (!arrayList.isEmpty()) {
      E first = arrayList.get(0);
      arrayList.remove(0);
      rear--;
      return first;
    }
    return null;
  }

  /**
   *
   */
  @Override
  public E peek() {
    return !arrayList.isEmpty() ? arrayList.get(0) : null;
  }

  /**
   *
   */
  @Override
  public E last() {
    return !arrayList.isEmpty() ? arrayList.get(rear) : null;
  }

  /**
   *
   */
  @Override
  public int size() {
    return arrayList.size();
  }

  /**
   *
   */
  @Override
  public boolean isEmpty() {
    return rear == -1;
  }

  /**
   *
   */
  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public String toString() {
    return arrayList.toString().replaceAll("ArrayList", "Queue");
  }

  public static void main(String[] args) {

    CustomQueue<String> queue = new CustomQueue<>();

    //Empty Queue Testing...
    System.out.println(queue.peek());
    System.out.println(queue.poll());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());


    queue.offer("Amit1");
    queue.offer("Amit2");
    queue.offer("Amit3");
    queue.offer("Amit4");

    // Not Empty Testing...
    System.out.println(queue);
    System.out.println(queue.peek());
    System.out.println(queue.poll());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());
    System.out.println(queue);

    System.out.println("------------------");
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.peek());
    System.out.println(queue.poll());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());

  }
}
