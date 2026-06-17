package datastructure_algorithms.datastructure.linear.circularlinkedlist.singly;

import java.util.function.Consumer;

//Advantage of Circular linked list:
//  1. We can go to any node from any node in the Circular linked list which was not possible in the singly linked list if we reached the last node.
//  2. Easily we can go to head from the last node.
//  3. In a circular list, any node can be starting point means we can traverse each node from any point.
//  4. No requirement for a NULL assignment in the code. The circular list never points to a NULL pointer unless fully deallocated.
//Disadvantages of Circular Linked List:
//  1. Circular lists are complex as compared to singly linked lists.
//  1. Reverse of circular list is a complex as compared to singly or doubly lists.
//  3. If not handled carefully, then the code may go in an infinite loop.
//  4. Harder to find the end of the list and loop control.
//  5. Inserting at Start, we have to traverse the complete list to find the last node. (Implementation Perspective)


public final class CircularLinkedList<E> implements LinkedList<E> {

  // First node of the Singly Circular Linked List
  private Node<E> first;
  // First node of the Singly Circular Linked List
  private Node<E> last;
  private int size;

  /**
   *
   */
  @Override
  public void add(E element) {
    Node<E> newNode = new Node<>(element);
    if (size == 0) {
      this.first = newNode;
      this.last = newNode;
    }
    Node<E> node = this.first;
    for (int i = 1; i < size; i++) {
      node = node.getNextNode();
    }
    node.setNextNode(newNode);
    newNode.setNextNode(this.first);
    this.last = newNode;
    size++;
  }

  /**
   *
   */
  @Override
  public void addFirst(E element) {
    Node<E> newNode = new Node<>(element);
    if (size == 0) {
      this.first = newNode;
      this.last = newNode;
    }
    newNode.setNextNode(this.first);
    this.first = newNode;
    size++;
  }

  /**
   *
   */
  @Override
  public void addLast(E element) {
    add(element);
  }

  /**
   *
   */
  @Override
  public void add(int index, E element) {
    validateIndexInRange(index);
    if (index == 0) {
      addFirst(element);
    } else if (index == size) {
      add(element);
    } else {
      Node<E> node = this.first;
      for (int i = 1; i < index; i++) {
        node = node.getNextNode();
      }
      Node newNode = new Node(element);
      newNode.setNextNode(node.getNextNode());
      node.setNextNode(newNode);
      size++;
    }
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
  @Override
  public boolean addAll(
      LinkedList<E> list) {
    return addAll(list.iterator());
  }

  /**
   *
   */
  @Override
  public boolean addAll(
      Iterator<E> iterator) {
    if (iterator instanceof CircularLinkedListIterator) {
      add(iterator.next());
      while (iterator.hasNext() && iterator.nextIndex() != 0) {
        add(iterator.next());
      }
      return true;
    }
    boolean hasNext = iterator.hasNext();
    while (iterator.hasNext()) {
      add(iterator.next());
    }
    return hasNext;
  }

  /**
   *
   */
  @Override
  public void set(int index, E element) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    Node<E> node = this.first;
    Node newNode = new Node(element);
    if (index == 0) {
      newNode.setNextNode(node.getNextNode());
      this.first = newNode;
      return;
    }
    for (int i = 1; i < index; i++) {
      node = node.getNextNode();
    }
    newNode.setNextNode(node.getNextNode().getNextNode());
    node.setNextNode(newNode);
  }

  /**
   *
   */
  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    Node<E> node = this.first;
    for (int i = 1; i <= index; i++) {
      node = node.getNextNode();
    }
    return node.getData();
  }

  /**
   *
   */
  @Override
  public E getFirst() {
    return this.first.getData();
  }

  /**
   *
   */
  @Override
  public E getLast() {
    return this.last.getData();
  }

  /**
   *
   */
  @Override
  public boolean contains(E element) {
    return indexOf(element) != -1;
  }

  /**
   *
   */
  @Override
  public Object[] toArray() {
    Object[] object = new Object[size];
    Node<E> node = this.first;
    object[0] = node.getData();
    for (int i = 1; i < size; i++) {
      node = node.getNextNode();
      object[i] = node.getData();
    }
    return object;
  }

  /**
   *
   */
  @Override
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
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   *
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   *
   */
  @Override
  public void remove(E element) {
    int index = indexOf(element);
    if (index != -1) {
      removeAt(index);
    }
  }

  /**
   *
   */
  @Override
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
  @Override
  public void removeFirst() {
    this.first = this.first.getNextNode();
    size--;
  }

  /**
   *
   */
  @Override
  public void removeLast() {
    removeAt(size - 1);
  }

  /**
   *
   */
  @Override
  public void clear() {
    clearingAllNodeReferences(); // Optional or else GC will clear all the references.
    this.first = null;
    size = 0;
  }

  private void clearingAllNodeReferences() {
    Node<E> prev;
    Node<E> node = this.first;

    for (int i = 1; i < size; i++) {
      prev = node;
      node = node.getNextNode();
      prev.setData(null);
      prev.setNextNode(null);
      prev = null;
    }
    node.setData(null);
    node.setNextNode(null);
  }

  /**
   *
   */
  @Override
  public Iterator<E> iterator() {
    return new CircularLinkedListIterator<>();
  }

  /**
   *
   */
  @Override
  public void forEach(Consumer<? super E> consumer) {
    if (this.first == null) {
      return;
    }
    Node<E> node = this.first;
    consumer.accept(node.getData());
    for (int i = 1; i < size; i++) {
      node = node.getNextNode();
      consumer.accept(node.getData());
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("CircularSinglyLinkedList{ elements= [");
    Node<E> node = this.first;
    for (int i = 1; i < size; i++) {
      builder.append(String.format("%s, ", node.getData()));
      node = node.getNextNode();
    }
    if (this.first != null) {
      builder.append(String.format("%s, ", node.getData()));
      builder = builder.replace(builder.length() - 2, builder.length(), "");
    }
    return builder
        .append(String.format("], size= %s }", size)).toString();
  }

  private final class CircularLinkedListIterator<E> implements Iterator<E> {

    private Node currentNode;
    private Node prevNode;
    private int cursor = -1;

    /**
     *
     */
    @Override
    public boolean hasNext() {
      return size == 0 || size == 1 ? false : true;
    }

    /**
     *
     */
    @Override
    public E next() {
      if (currentNode == null) {
        currentNode = first;
      } else {
        prevNode = currentNode;
        currentNode = currentNode.getNextNode();
      }
      cursor++;
      if(cursor >= size-1) {
        cursor = -1;
      }
      return (E) currentNode.getData();
    }

    private void check() {
      if(currentNode == null) {
        cursor = 0;
      }
    }

    /**
     *
     */
    @Override
    public boolean hasPrevious() {
      if (size == 0 || size == 1) {
        return false;
      }
      return true;
    }

    /**
     *
     */
    @Override
    public E previous() {
      if (prevNode != null) {
        cursor--;
        return (E) prevNode.getData();
      }
      return null;
    }

    /**
     *
     */
    @Override
    public int nextIndex() {
      return cursor + 1;
    }

    /**
     *
     */
    @Override
    public int previousIndex() {
      if(cursor == -1 || cursor == 0) {
        return size-1;
      }
      return cursor - 1;
    }
  }
}
