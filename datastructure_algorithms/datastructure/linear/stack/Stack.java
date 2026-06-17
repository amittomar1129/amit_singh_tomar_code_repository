package datastructure_algorithms.datastructure.linear.stack;

import java.util.Arrays;
import java.util.Vector;
import java.util.stream.IntStream;

public class Stack<E> extends Vector<E> {

  private E[] elements;
  private int top = -1;
  private int currentCapacity;
  private static final int LOAD_FACTOR = 80;

  Stack() {
    this(10);
  }

  Stack(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException(
          String.format("Illegal size of Stack: %s", initialCapacity));
    }
    this.currentCapacity = initialCapacity;
    this.elements = (E[]) new Object[initialCapacity];
  }


  public E push(E element) {
    elements[++top] = element;
    ensureCapacity();
    return element;
  }

  public void pop() {
    if (top >= 0) {
      elements[top] = null;
      top--;
    }
  }

  public E peek() {
    return elements[top];
  }

  public int search(E element) {
    int index = 0;
    while (top >= 0) {
      if (elements[top].equals(element)) {
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
    return top + 1;
  }

  @Override
  public synchronized String toString() {
    StringBuilder builder = new StringBuilder("[");
    Arrays.stream(this.elements).filter(element -> element != null)
        .forEach(element -> builder.append(element).append(", "));
    return size() != 0 ? builder.toString().substring(0, builder.length() - 2) + "]" :
        builder.append("]").toString();
  }

  private void ensureCapacity() {
    int maxAllowedSize = currentCapacity * LOAD_FACTOR / 100 - 1;
    if (top >= maxAllowedSize) {
      this.currentCapacity = this.currentCapacity * 2 + 1;
      E[] newArray = (E[]) new Object[this.currentCapacity];
      IntStream.range(0, this.elements.length)
          .forEach(index -> newArray[index] = this.elements[index]);
      this.elements = newArray;
    }
  }
}
