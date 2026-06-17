package datastructure_algorithms.datastructure.linear.arraylist;

import java.util.function.Consumer;

public interface List<E> {

    boolean add(E element);

    boolean add(int index, E element);

    boolean addAll(List<E> list);

    boolean addAll(Iterator<E> iterator);

    void set(int index, E element);

    E get(int index);

    boolean contains(E element);

    Object[] toArray();

    int indexOf(E element);

    boolean isEmpty();

    int size();

    boolean remove(E element);

    void removeAt(int index);

    void clear();

    Iterator<E> iterator();

    void forEach(Consumer<? super E> consumer);
}
