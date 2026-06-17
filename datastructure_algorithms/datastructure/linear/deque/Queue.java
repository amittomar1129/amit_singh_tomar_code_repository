package datastructure_algorithms.datastructure.linear.deque;

public interface Queue<E> {

  void offer(E element);

  E poll();

  E peek();

  E last();

  int size();

  boolean isEmpty();

  boolean isFull();
}
