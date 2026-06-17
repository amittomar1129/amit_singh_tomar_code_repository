package datastructure_algorithms.datastructure.linear.singlylinkedlist;


import java.util.function.Consumer;

public final class SinglyLinkedList<E> implements LinkedList<E> {

  // First node of the Singly Linked List
  private Node<E> first;
  private int size;

  /**
   *
   */
  @Override
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
  @Override
  public void addFirst(E element) {
    Node<E> newNode = new Node<>(element);
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
      return;
    }
    if (index == size) {
      add(element);
      return;
    }
    Node<E> node = this.first;
    for (int i = 1; i < index; i++) {
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
  @Override
  public boolean addAll(LinkedList<E> list) {
    return addAll(list.iterator());
  }

  /**
   *
   */
  @Override
  public boolean addAll(Iterator<E> iterator) {
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
    validateIndexInRange(index);
    if (index == size) {
      add(element);
    }
    Node<E> node = this.first;
    for (int i = 1; i <= index; i++) {
      node = node.getNextNode();
    }
    node.setData(element);
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
    Node<E> node = this.first;
    while (node.getNextNode() != null) {
      node = node.getNextNode();
    }
    return node.getData();
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
    for (int i = 1; node.getNextNode() != null; i++) {
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

    while (node.getNextNode() != null) {
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
    return new SinglyLinkedListIterator<E>();
  }

  /**
   *
   */
  @Override
  public void forEach(Consumer<? super E> consumer) {
    if(this.first == null) {
      return;
    }
    Node<E> node = this.first;
    consumer.accept(node.getData());
    while(node.getNextNode() != null) {
      node = node.getNextNode();
      consumer.accept(node.getData());
    }
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

  private final class SinglyLinkedListIterator<E> implements Iterator<E> {

    private Node currentNode;
    private Node prevNode;
    private int cursor = -1; 

    /**
     * @return
     */
    @Override
    public boolean hasNext() {
      if(currentNode == null && size > 0) {
        return true;
      }
      return currentNode != null && currentNode.getNextNode() != null;
    }

    /**
     * @return
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
      return (E) currentNode.getData();
    }

    /**
     * @return
     */
    @Override
    public boolean hasPrevious() {
      if(currentNode == null || currentNode == first) {
        return false;
      }
      return true;
    }

    /**
     * @return
     */
    @Override
    public E previous() {
      if(prevNode != null) {
        cursor--;
        return (E) prevNode.getData();
      }
      return null;
    }

    /**
     * @return
     */
    @Override
    public int nextIndex() {
      return cursor + 1;
    }

    /**
     * @return
     */
    @Override
    public int previousIndex() {
      return cursor - 1;
    }
  }
}
