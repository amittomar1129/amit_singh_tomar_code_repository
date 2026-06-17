package datastructure_algorithms.datastructure.linear.stack;

import java.util.Arrays;

public class StackUsingLinkedList<E> {

  private final LinkedList<E> elements = new LinkedList<>();
  private int size;
  private int top = -1;

  public E push(E element) {
    elements.add(element);
    top++;
    size++;
    return element;
  }

  public void pop() {
    if (!elements.isEmpty()) {
      elements.removeAt(top);
      top--;
      size--;
    }
  }

  public E peek() {
    return elements.isEmpty() ? null : elements.get(top);
  }

  public int search(E element) {
    int index = 0;
    while (top >= 0) {
      if (elements.get(top).equals(element)) {
        return ++index;
      }
      top--;
      index++;
    }
    return -1;
  }

  public boolean empty() {
    return top == -1;
  }

  public int size() {
    return size;
  }

  @Override
  public synchronized String toString() {
    StringBuilder builder = new StringBuilder("[");
    this.elements
        .forEach(element -> builder.append(element).append(", "));
    return size() != 0 ? builder.toString().substring(0, builder.length() - 2) + "]" :
        builder.append("]").toString();
  }
}
