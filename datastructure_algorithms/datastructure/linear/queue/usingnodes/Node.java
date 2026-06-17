package datastructure_algorithms.datastructure.linear.queue.usingnodes;

final class Node<E> {

  private E data;
  private Node next;

  public Node(E data, Node next) {
    this.data = data;
    this.next = next;
  }

  public Node(E data) {
    this.data = data;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }
}
