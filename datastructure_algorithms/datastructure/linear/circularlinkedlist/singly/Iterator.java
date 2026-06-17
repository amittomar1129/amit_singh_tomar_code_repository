package datastructure_algorithms.datastructure.linear.circularlinkedlist.singly;

public interface Iterator<E> {

    boolean hasNext();

    E next();

    boolean hasPrevious();

    E previous();

    int nextIndex();

    int previousIndex();
}
