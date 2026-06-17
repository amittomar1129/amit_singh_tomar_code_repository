package datastructure_algorithms.datastructure.linear.queue.usingarrays;

import datastructure_algorithms.datastructure.linear.queue.Queue;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomQueue<E> implements Queue<E> {

  private int front = 0;
  private int rear = -1;
  private E[] data;


  public CustomQueue(int capacity) {
    this.data = (E[]) new Object[capacity];
  }

  /**
   *
   */
  @Override
  public void offer(E element) {
    if (rear >= this.data.length - 1) {
      throw new IllegalArgumentException("Queue is Full.");
    }
    this.data[++rear] = element;
  }

  /**
   *
   */
  @Override
  public E poll() {
    if (isEmpty()) {
      return null;
    }
    E peekElement = data[front];
    forwardMoveElements();
    rear--;
    return peekElement;
  }

  private void forwardMoveElements() {
    if (this.data.length == 1) {
      this.data[front] = null;
    }
    for (int i = 0; i < data.length; i++) {
      if (i == data.length - 1) {
        data[i] = null;
      } else {
        data[i] = data[i + 1];
      }
    }
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
    return isEmpty() ? null : data[rear];
  }

  /**
   *
   */
  @Override
  public int size() {
    return this.data.length;
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
    return rear + 1 == this.data.length;
  }


  @Override
  public String toString() {
    return Arrays.stream(data).filter(val -> val != null).collect(Collectors.toList()).toString();
  }

  public static void main(String[] args) {

    CustomQueue queue = new CustomQueue(4);

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
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());
    System.out.println(queue.poll());
    System.out.println(queue);

    System.out.println("------------------");
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue);
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.peek());
//    System.out.println(queue.poll());
//    System.out.println(queue.isEmpty());
//    System.out.println(queue.isFull());
//    System.out.println(queue.last());
  }
}
