package datastructure_algorithms.datastructure.linear.deque;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayDeque<E> implements Deque<E> {


  private int front = -1;
  private int rear = -1;
  private E[] data;
  private final int capacity;

  public ArrayDeque(int capacity) {
    this.capacity = capacity;
    this.data = (E[]) new Object[capacity];
  }

  /**
   *
   */
  @Override
  public void addFirst(E element) {
    if (isFull()) {
      throw new IllegalArgumentException("ArrayDeque is Full...");
    }
    if (front == -1 && rear == -1) {
      data[++front] = element;
      rear++;
      return;
    }
    if (front == 0) {
      moveForward();
      data[front] = element;
      rear++;
      return;
    }
    if (front > 0) {
      data[--front] = element;
    }
  }

  private void moveForward() {
    for (int i = rear; i >= 0; i--) {
      data[i + 1] = data[i];
    }
  }

  /**
   *
   */
  @Override
  public void addLast(E element) {
    if (isFull()) {
      throw new IllegalArgumentException("ArrayDeque is Full...");
    }
    if (front == -1 && rear == -1) {
      data[++front] = element;
      rear++;
      return;
    }
    data[++rear] = element;
  }

  /**
   *
   */
  @Override
  public E removeFirst() {
    if (isEmpty()) {
      return null;
    }
    E element = data[front];
    data[front] = null;
    if (front == capacity - 1) {
      front = rear = -1;
    } else {
      front++;
    }
    return element;
  }

  /**
   *
   */
  @Override
  public E removeLast() {
    if (isEmpty()) {
      return null;
    }
    E element = data[rear];
    data[rear] = null;
    if (rear == 0) {
      front = rear = -1;
    } else {
      rear--;
    }
    return element;
  }

  /**
   * @param element
   */
  @Override
  public void push(E element) {
    addLast(element);
  }

  /**
   * @return
   */
  @Override
  public E pop() {
    return removeLast();
  }

  /**
   *
   */
  @Override
  public void offer(E element) {
    addLast(element);
  }

  /**
   *
   */
  @Override
  public E poll() {
    return removeFirst();
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
    if (isEmpty()) {
      return 0;
    }
    return rear - front + 1;
  }

  /**
   *
   */
  @Override
  public boolean isEmpty() {
    if (front == -1 && rear == -1) {
      return true;
    }
    if (front > rear && data[rear] == null) {
      return true;
    }
    return false;
  }

  /**
   *
   */
  @Override
  public boolean isFull() {
    if (front == 0 && rear == capacity - 1) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return Arrays.asList(data).stream().filter(element -> element != null)
        .collect(Collectors.toList()).toString();
  }

  public static void main(String[] args) {

    ArrayDeque deque = new ArrayDeque(10);
    System.out.println(deque);
    System.out.println(deque.peek());
    System.out.println(deque.poll());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());
    System.out.println(deque.last());

    System.out.println("Scenario 1........................");
    deque.addFirst("Amit1");
    deque.addFirst("Amit2");
    deque.addFirst("Amit3");
    deque.addFirst("Amit4");
    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());

    System.out.println("Scenario 2........................");
    deque.addLast("Amit5");
    deque.addLast("Amit6");
    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());

    System.out.println("Scenario 3........................");
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());

    System.out.println("Scenario 4........................");
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());
    System.out.println("Adding-----------------------");
    deque.addFirst("Amit1");
    deque.addFirst("Amit2");
    deque.addFirst("Amit3");
    deque.addFirst("Amit4");
    deque.addFirst("Amit5");
    deque.addFirst("Amit6");
    deque.addFirst("Amit7");
    deque.addFirst("Amit8");
    deque.addFirst("Amit9");
    deque.addFirst("Amit10");
    System.out.println(deque);
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());
    System.out.println(deque.removeFirst());

    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());

    System.out.println("Scenario 5........................");
    deque.addLast("Amit1");
    deque.addLast("Amit2");
    deque.addLast("Amit3");
    deque.addLast("Amit4");
    deque.addLast("Amit5");
    deque.addLast("Amit6");
    deque.addLast("Amit7");
    deque.addLast("Amit8");
    deque.addLast("Amit9");
    deque.addLast("Amit10");

    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());
    System.out.println(deque.removeLast());


    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());

    System.out.println("Scenario 6............Using Deque like a Stack.........");
    deque.push("Amit1");
    deque.push("Amit2");
    deque.push("Amit3");
    deque.push("Amit4");

    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.pop());
    System.out.println(deque.pop());
    System.out.println(deque.pop());
    System.out.println(deque.pop());
    System.out.println(deque);
  }
}
