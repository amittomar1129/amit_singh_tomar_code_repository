package datastructure_algorithms.datastructure.nonlinear.tree.redblack;

final class Node<E extends Comparable> {

  private E data;
  private Node<E> leftChild;
  private Node<E> rightChild;
  private Node<E> parent;
  private Color color;

  public Node(E data) {
    this.data = data;
  }

  public Node(E data, Color color) {
    this.data = data;
    this.color = color;
  }

  public Node(E data, Node<E> parent, Color color) {
    this.data = data;
    this.parent = parent;
    this.color = color;
  }

  public Node(E data, Node<E> leftChild) {
    this.data = data;
    this.leftChild = leftChild;
  }

  public Node(Node<E> leftChild, Node<E> rightChild) {
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  public Node(E data, Node<E> leftChild, Node<E> rightChild) {
    this.data = data;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public Node<E> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(Node<E> leftChild) {
    this.leftChild = leftChild;
  }

  public Node<E> getRightChild() {
    return rightChild;
  }

  public void setRightChild(Node<E> rightChild) {
    this.rightChild = rightChild;
  }

  public Node<E> getParent() {
    return parent;
  }

  public void setParent(Node<E> parent) {
    this.parent = parent;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Node{" + "data=" + data + ", leftChild=" + leftChild + ", rightChild=" + rightChild
        + ", color=" + color + '}';
  }
}

enum Color {
  RED, BLACK, DOUBLE_BLACK;
}
