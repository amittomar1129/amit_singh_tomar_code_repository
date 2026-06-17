package datastructure_algorithms.datastructure.nonlinear.tree.bst;

public final class Node<E extends Comparable> {

  public E val;
  public Node<E> left;
  public Node<E> right;

  public Node() {
  }

  public Node(E val) {
    this.val = val;
  }

  public Node(E val, Node<E> left) {
    this.val = val;
    this.left = left;
  }

  public Node(Node<E> left, Node<E> right) {
    this.left = left;
    this.right = right;
  }

  public Node(E val, Node<E> left, Node<E> right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return "Node {" +
        "val= " + val +
        ", left= " + left +
        ", right= " + right + " }";
  }
}
