package datastructure_algorithms.datastructure.linear.stack;

import java.util.function.Consumer;

public final class LinkedList<E> {

  private Node<E> first;
  private int size;


  public void add(E element) {
    Node<E> newNode = new Node<E>(element);
    if (this.first == null) {
      this.first = newNode;
      size++;
      return;
    }
    Node<E> node = this.first;
    while (node.getNextNode() != null) {
      node = node.getNextNode();
    }
    node.setNextNode(newNode);
    size++;
  }

  /**
   *
   */

  public void addFirst(E element) {
    Node<E> newNode = new Node<>(element);
    newNode.setNextNode(this.first);
    this.first = newNode;
    size++;
  }

  /**
   *
   */

  public void addLast(E element) {
    add(element);
  }

  /**
   *
   */

  public void add(int index, E element) {
    validateIndexInRange(index);
    if (index == 0) {
      addFirst(element);
      return;
    }
    if (index == size) {
      add(element);
      return;
    }
    Node<E> node = this.first;
    for (int i = 0; i < index - 1; i++) {
      node = node.getNextNode();
    }
    Node<E> newNode = new Node<>(element, node.getNextNode());
    node.setNextNode(newNode);
    size++;
  }

  private void validateIndexInRange(int index) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
  }

  /**
   *
   */
  public void set(int index, E element) {
    validateIndexInRange(index);
    if (index == size) {
      add(element);
    }
    Node<E> node = this.first;
    for (int i = 0; i < index; i++) {
      node = node.getNextNode();
    }
    node.setData(element);
  }

  /**
   *
   */
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    Node<E> node = this.first;
    for (int i = 0; i < index; i++) {
      node = node.getNextNode();
    }
    return node.getData();
  }

  /**
   *
   */
  public E getFirst() {
    return this.first.getData();
  }

  /**
   *
   */
  public E getLast() {
    Node<E> node = this.first;
    while (node.getNextNode() != null) {
      node = node.getNextNode();
    }
    return node.getData();
  }

  /**
   *
   */
  public boolean contains(E element) {
    return indexOf(element) != -1;
  }

  /**
   *
   */
  public Object[] toArray() {
    Object[] object = new Object[size];
    Node<E> node = this.first;
    object[0] = node.getData();
    for (int i = 1; node.getNextNode() != null; i++) {
      node = node.getNextNode();
      object[i] = node.getData();
    }
    return object;
  }

  /**
   *
   */
  public int indexOf(E element) {
    if (size == 0) {
      return -1;
    }
    Node<E> node = this.first;
    if (element == null) {
      if (node.getData() == null) {
        return 0;
      }
      for (int i = 1; i < size; i++) {
        if (node.getNextNode() != null) {
          node = node.getNextNode();
          if (node.getData() == null) {
            return i;
          }
        }
      }
    } else {
      if (node.getData() != null && node.getData().equals(element)) {
        return 0;
      }
      for (int i = 1; i < size; i++) {
        if (node.getNextNode() != null) {
          node = node.getNextNode();
          if (node.getData() != null && node.getData().equals(element)) {
            return i;
          }
        }
      }
    }
    return -1;
  }

  /**
   *
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   *
   */
  public int size() {
    return this.size;
  }

  /**
   *
   */
  public void remove(E element) {
    int index = indexOf(element);
    if (index != -1) {
      removeAt(index);
    }
  }

  /**
   *
   */
  public void removeAt(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    if (index == 0) {
      removeFirst();
      return;
    }
    Node<E> node = this.first;
    for (int i = 0; i < index - 1; i++) {
      node = node.getNextNode();
    }
    Node<E> nodeToBeRemoved = node.getNextNode();
    node.setNextNode(nodeToBeRemoved.getNextNode());
    size--;
  }

  /**
   *
   */
  public void removeFirst() {
    this.first = this.first.getNextNode();
    size--;
  }

  /**
   *
   */
  public void removeLast() {
    removeAt(size - 1);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("SinglyLinkedList{ elements= [");
    Node<E> node = this.first;
    while (node != null) {
      builder.append(String.format("%s, ", node.getData()));
      node = node.getNextNode();
    }
    if (this.first != null) {
      builder = builder.replace(builder.length() - 2, builder.length(), "");
    }
    return builder
        .append(String.format("], size= %s }", size)).toString();
  }

  public void forEach(Consumer<? super E> consumer) {
    if (this.first == null) {
      return;
    }
    Node<E> node = this.first;
    consumer.accept(node.getData());
    while (node.getNextNode() != null) {
      node = node.getNextNode();
      consumer.accept(node.getData());
    }
  }
}
