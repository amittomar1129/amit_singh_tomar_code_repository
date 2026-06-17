package datastructure_algorithms.datastructure.linear.arraylist;

import java.util.NoSuchElementException;

public interface Iterator<E> {

    boolean hasNext();

    E next();

    boolean hasPrevious();

    E previous();

    int nextIndex();

    int previousIndex();
}
