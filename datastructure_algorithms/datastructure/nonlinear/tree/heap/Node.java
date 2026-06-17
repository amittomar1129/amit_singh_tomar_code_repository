package datastructure_algorithms.datastructure.nonlinear.tree.heap;

public class Node<E> {

  public E val;
  public Node<E> left;
  public Node<E> right;
  public Node<E> parent;


  public Node(E val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return val.toString();
  }
}
