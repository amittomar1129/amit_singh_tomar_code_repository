package datastructure_algorithms.datastructure.linear.deque;

public interface Deque<E> extends Queue<E> {

  void addFirst(E element);

  void addLast(E element);

  E removeFirst();

  E removeLast();

  // Methods to have a Stack support using Deque
  void push(E element);

  E pop();
}
