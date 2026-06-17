package datastructure_algorithms.datastructure.linear.queue.usingnodes;

import datastructure_algorithms.datastructure.linear.queue.Queue;

public class CustomQueue<E> implements Queue<E> {

  private Node<E> first;
  private Node<E> last;
  private int size;

  /**
   *
   */
  @Override
  public void offer(E element) {
    Node newNode = new Node(element);
    if (isEmpty()) {
      first = last = newNode;
      size++;
      return;
    }
    last.setNext(newNode);
    last = newNode;
    size++;
  }

  /**
   *
   */
  @Override
  public E poll() {
    if (isEmpty()) {
      return null;
    }
    E data = first.getData();
    first = first.getNext();
    size--;
    return data;
  }

  /**
   *
   */
  @Override
  public E peek() {
    return isEmpty() ? null : first.getData();
  }

  /**
   *
   */
  @Override
  public E last() {
    return isEmpty() ? null : last.getData();
  }

  /**
   *
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   *
   */
  @Override
  public boolean isEmpty() {
    return size <= 0;
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
    StringBuilder builder = new StringBuilder("Queue [");
    if (!isEmpty()) {
      builder.append(first.getData());
      Node node = this.first;
      while (node.getNext() != null) {
        node = node.getNext();
        builder.append(", ").append(node.getData());
      }
    }
    return builder.append("]").toString();
  }


  public static void main(String[] args) {

    CustomQueue<String> queue = new CustomQueue<>();

    //Empty Queue Testing...
    System.out.println(queue.peek());
    System.out.println(queue.poll());
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.last());
    System.out.println(queue);

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
