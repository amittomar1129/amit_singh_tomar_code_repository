package datastructure_algorithms.datastructure.linear.circularqueue.usingarraylist;

import datastructure_algorithms.datastructure.linear.circularqueue.Queue;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CircularQueue<E> implements Queue<E> {

  private int front = -1;
  private int rear = -1;
  private ArrayList<E> elements;
  private int size;

  public CircularQueue(int capacity) {
    this.elements = new ArrayList<>(capacity);
    this.size = capacity;
  }

  /**
   *
   */
  @Override
  public void offer(E element) {
    if (isFull()) {
      throw new IllegalArgumentException("Queue is Full...");
    }
    if (isEmpty()) {
      front = circularIndex(++front);
      if (elements.size() >= size) {
        elements.set(front, element);
      } else {
        elements.add(front, element);
      }
      rear = circularIndex(front + 1);
    } else {
      if (elements.size() >= size) {
        elements.set(rear, element);
      } else {
        elements.add(rear, element);
      }
      rear = circularIndex(++rear);
    }
  }

  private int circularIndex(int index) {
    return index >= size ? 0 : index;
  }

  /**
   *
   */
  @Override
  public E poll() {
    if (!isEmpty()) {
      E peekElement = elements.get(front);
      elements.set(front, null);
      front = circularIndex(++front);
      return peekElement;
    }
    return null;
  }

  /**
   *
   */
  @Override
  public E peek() {
    return isEmpty() ? null : elements.get(front);
  }

  /**
   *
   */
  @Override
  public E last() {
    if (isEmpty()) {
      return null;
    }
    if (isFull()) {
      return rear == 0 ? elements.get(size - 1) : elements.get(rear - 1);
    }
    return rear == 0 ? elements.get(front) : elements.get(rear - 1);
  }

  /**
   *
   */
  @Override
  public boolean isEmpty() {
    return !elements.isEmpty() ? front == rear && elements.get(circularIndex(front + 1)) == null
        : true;
  }

  /**
   *
   */
  @Override
  public int size() {
    if (isEmpty()) {
      return 0;
    }
    if (isFull()) {
      return size;
    }
    if (rear > front) {
      return rear - front;
    }
    int distance = 0;
    int nextIndex = circularIndex(front + 1);
    while (nextIndex != rear) {
      nextIndex = circularIndex(nextIndex + 1);
      distance++;
    }
    return ++distance;
  }

  /**
   *
   */
  @Override
  public boolean isFull() {
    return !elements.isEmpty() ? front == rear && elements.get(circularIndex(front + 1)) != null
        : false;
  }


  @Override
  public String toString() {
    return elements.stream().filter(val -> val != null).collect(Collectors.toList()).toString();
  }

  public static void main(String[] args) {

    CircularQueue queue = new CircularQueue(4);

    //Empty Queue Testing...
    System.out.println(queue);
    System.out.println(queue.peek());
    System.out.println(queue.poll());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());

    System.out.println("Scenario 1........................");
    queue.offer("Amit1");
    queue.offer("Amit2");
    queue.offer("Amit3");
    queue.offer("Amit4");
    System.out.println(queue);
    System.out.println(queue.size());
    System.out.println(queue.peek());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());

    System.out.println("Scenario 2........................");
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue);
    System.out.println(queue.size());
    System.out.println(queue.peek());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());

    System.out.println("Scenario 3........................");
    queue.offer("Amit1");
    queue.offer("Amit2");
    queue.offer("Amit3");
    queue.offer("Amit4");
    System.out.println(queue);
    System.out.println(queue.size());
    System.out.println(queue.peek());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());

    System.out.println("Scenario 4........................");
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue);
    System.out.println(queue.size());
    System.out.println(queue.peek());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());

    System.out.println("Scenario 5........................");
    queue.offer("Amit1");
    queue.offer("Amit2");
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    queue.offer("Amit3");
    queue.offer("Amit4");
    System.out.println(queue);
    System.out.println(queue.size());
    System.out.println(queue.peek());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());

    System.out.println("Congratulations... Testing passed!");
  }
}
