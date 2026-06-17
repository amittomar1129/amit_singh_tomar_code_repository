package datastructure_algorithms.datastructure.nonlinear.tree.splay;

final class Node<E extends Comparable> {

  private E data;
  private Node<E> left;
  private Node<E> right;
  private Node<E> parent;

  public Node(E data) {
    this.data = data;
  }

  public Node(E data, Node<E> leftChild) {
    this.data = data;
    this.left = leftChild;
  }

  public Node(Node<E> leftChild, Node<E> rightChild) {
    this.left = leftChild;
    this.right = rightChild;
  }

  public Node(E data, Node<E> leftChild, Node<E> rightChild) {
    this.data = data;
    this.left = leftChild;
    this.right = rightChild;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public Node<E> getLeft() {
    return left;
  }

  public void setLeft(Node<E> left) {
    this.left = left;
  }

  public Node<E> getRight() {
    return right;
  }

  public void setRight(Node<E> right) {
    this.right = right;
  }

  public Node<E> getParent() {
    return parent;
  }

  public void setParent(Node<E> parent) {
    this.parent = parent;
  }

  @Override
  public String toString() {
    return data.toString();
  }
}
