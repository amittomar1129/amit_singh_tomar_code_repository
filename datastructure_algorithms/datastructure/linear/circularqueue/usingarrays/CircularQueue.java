package datastructure_algorithms.datastructure.linear.circularqueue.usingarrays;

import datastructure_algorithms.datastructure.linear.circularqueue.Queue;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CircularQueue<E> implements Queue<E> {

  private int front = -1;
  private int rear = -1;
  private E[] data;
  private int size;


  public CircularQueue(int capacity) {
    this.data = (E[]) new Object[capacity];
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
      data[front] = element;
      rear = circularIndex(front + 1);
    } else {
      data[rear] = element;
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
      E peekElement = data[front];
      data[front] = null;
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
    return isEmpty() ? null : data[front];
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
      return rear == 0 ? data[size - 1] : data[rear - 1];
    }
    return data[rear - 1];
  }

  /**
   *
   */
  @Override
  public boolean isEmpty() {
    return (front == -1 && rear == -1) || (front == circularIndex(rear + 1)
        && data[circularIndex(front)] == null);
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
    return front != -1 && rear != -1 && front == rear && data[circularIndex(front)] != null;
  }


  @Override
  public String toString() {
    return Arrays.stream(data).filter(val -> val != null).collect(Collectors.toList()).toString();
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
//    queue.offer("Amit4");
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
