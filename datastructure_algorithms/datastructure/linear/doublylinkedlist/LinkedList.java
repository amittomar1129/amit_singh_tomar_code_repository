package datastructure_algorithms.datastructure.linear.doublylinkedlist;

import java.util.function.Consumer;

public interface LinkedList<E> {

  void add(E element);

  void addFirst(E element);

  void addLast(E element);

  void add(int index, E element);

  boolean addAll(LinkedList<E> list);

  boolean addAll(Iterator<E> iterator);

  void set(int index, E element);

  E get(int index);

  E getFirst();

  E getLast();

  boolean contains(E element);

  Object[] toArray();

  int indexOf(E element);

  boolean isEmpty();

  int size();

  void remove(E element);

  void removeAt(int index);

  void removeFirst();

  void removeLast();

  void clear();

  Iterator<E> iterator();

  void forEach(Consumer<? super E> consumer);
}
