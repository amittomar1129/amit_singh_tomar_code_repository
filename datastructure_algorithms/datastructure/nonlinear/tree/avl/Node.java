package datastructure_algorithms.datastructure.nonlinear.tree.avl;

final class Node<E extends Comparable> {

  private E data;
  private Node<E> leftChild;
  private Node<E> rightChild;
  private int height;

  public Node(E data) {
    this.data = data;
  }

  public Node(E data, int height) {
    this.data = data;
    this.height = height;
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

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public String toString() {
    return "Node{" +
        "data=" + data +
        ", leftChild=" + leftChild +
        ", rightChild=" + rightChild +
        ", height=" + height +
        '}';
  }
}
