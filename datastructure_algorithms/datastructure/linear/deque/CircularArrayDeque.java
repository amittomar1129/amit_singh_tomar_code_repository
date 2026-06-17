package datastructure_algorithms.datastructure.linear.deque;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CircularArrayDeque<E> implements Deque<E> {

  private int front = -1;
  private int rear = -1;
  private E[] data;
  private final int capacity;

  public CircularArrayDeque(int capacity) {
    this.capacity = capacity;
    this.data = (E[]) new Object[capacity];
  }


  /**
   *
   */
  @Override
  public void addFirst(E element) {
    if (isFull()) {
      throw new IllegalArgumentException("Deque is Full...");
    }
    if (front == -1 && rear == -1) {
      front = rear = 0;
      data[front] = element;
      return;
    }
    front = prevIndex(front);
    data[front] = element;
  }

  /**
   *
   */
  @Override
  public void addLast(E element) {
    if (isFull()) {
      throw new IllegalArgumentException("Deque is Full...");
    }
    if (front == -1 && rear == -1) {
      front = rear = 0;
      data[front] = element;
      return;
    }
    rear = nextIndex(rear);
    data[rear] = element;
  }

  /**
   *
   */
  @Override
  public E removeFirst() {
    if(isEmpty()) {
      return null;
    }
    if(front == rear) {
      E element = data[front];
      data[front] = null;
      front = rear = -1;
      return element;
    }
    E element = data[front];
    data[front] = null;
    front = nextIndex(front);
    return element;
  }

  /**
   *
   */
  @Override
  public E removeLast() {
    if(isEmpty()) {
      return null;
    }
    if(front == rear) {
      E element = data[rear];
      data[rear] = null;
      front = rear = -1;
      return element;
    }
    E element = data[rear];
    data[rear] = null;
    rear = prevIndex(rear);
    return element;
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
    if (isFull()) {
      return capacity;
    }
    if (rear == front || rear > front) {
      return rear - front + 1;
    }
    if (front > rear) {
      return capacity - front + 1 + rear;
    }
    return 0;
  }

  /**
   *
   */
  @Override
  public boolean isEmpty() {
    if (front == -1 && rear == -1) {
      return true;
    }
    if (front == 0 && rear == capacity - 1 && data[front] == null) {
      return true;
    }
    if (front == rear + 1 && data[front] == null) {

      return true;
    }
    return false;
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

  private int nextIndex(int index) {
    return index == capacity - 1 ? 0 : index + 1;
  }

  private int prevIndex(int index) {
    return index == 0 ? capacity - 1 : index - 1;
  }

  /**
   *
   */
  @Override
  public boolean isFull() {
    if (front == 0 && rear == capacity - 1 && data[front] != null) {
      return true;
    }
    if (front == rear + 1 && data[front] != null) {
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

    CircularArrayDeque deque = new CircularArrayDeque(6);
    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());

    System.out.println("Scenario 1.......................");
    deque.addFirst("Amit1");
    deque.addFirst("Amit2");
    deque.addFirst("Amit3");
    deque.addLast("Amit4");
    deque.addLast("Amit5");
    deque.addLast("Amit6");

    System.out.println(deque);
    System.out.println(deque.size());
    System.out.println(deque.peek());
    System.out.println(deque.last());
    System.out.println(deque.isEmpty());
    System.out.println(deque.isFull());

    System.out.println("Scenario 2.......................");
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

    System.out.println("Scenario 3.......................");
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

    System.out.println("Scenario 4............Using Deque like a Stack.........");
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
