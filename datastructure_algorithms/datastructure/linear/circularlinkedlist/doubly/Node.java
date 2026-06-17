package datastructure_algorithms.datastructure.linear.circularlinkedlist.doubly;

final class Node<E> {

  public E data;
  public Node<E> nextNode;
  public Node<E> prevNode;

  // Created an empty node
  public Node() {
  }

  public Node(E data) {
    this.data = data;
  }

  public Node(E data, Node<E> nextNode) {
    this.data = data;
    this.nextNode = nextNode;
  }

  public Node(Node<E> prevNode, E data) {
    this.data = data;
    this.prevNode = prevNode;
  }

  public Node(E data, Node<E> nextNode, Node<E> prevNode) {
    this.data = data;
    this.nextNode = nextNode;
    this.prevNode = prevNode;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public Node<E> getNextNode() {
    return nextNode;
  }

  public void setNextNode(Node<E> nextNode) {
    this.nextNode = nextNode;
  }

  public Node<E> getPrevNode() {
    return prevNode;
  }

  public void setPrevNode(Node<E> prevNode) {
    this.prevNode = prevNode;
  }

  @Override
  public String toString() {
    return String.format("Node{ data= %s}", data);
  }
}
