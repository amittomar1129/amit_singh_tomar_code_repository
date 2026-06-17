package datastructure_algorithms.datastructure.nonlinear.tree.heap;

public interface Heap<E extends Comparable> {

  void insert(E element);

  // Removes top element
  E remove();

  // Removes node present anywhere in heap.
  //
  // @return false if node not found or else true
  boolean remove(E element);
}
