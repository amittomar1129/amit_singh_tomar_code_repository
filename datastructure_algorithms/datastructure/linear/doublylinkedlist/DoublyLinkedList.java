package datastructure_algorithms.datastructure.linear.doublylinkedlist;

import java.util.function.Consumer;

public final class DoublyLinkedList<E> implements LinkedList<E> {

  private Node first;
  private Node last;
  private int size;

  /**
   *
   */
  @Override
  public void add(E element) {
    size++;
    if (this.first == null) {
      this.first = new Node(element);
      this.last = this.first;
      return;
    }
    Node node = this.first;
    while (node.getNextNode() != null) {
      node = node.getNextNode();
    }
    Node newNode = new Node(node, element);
    node.setNextNode(newNode);
    this.last = newNode;
  }

  /**
   *
   */
  @Override
  public void addFirst(E element) {
    size++;
    if (this.first == null) {
      this.first = new Node(element);
      this.last = this.first;
      return;
    }
    Node newNode = new Node(element, this.first);
    this.first.setPrevNode(newNode);
    this.first = newNode;
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
    if (index < 0 || index > size) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    if (index == 0) {
      addFirst(element);
      return;
    } else if (index == size) {
      add(element);
      return;
    } else if (index < size / 2) {
      Node node = this.first;
      for (int i = 1; i < index; i++) {
        node = node.getNextNode();
      }
      Node newNode = new Node(node, element);
      newNode.setNextNode(node.getNextNode());
      node.getNextNode().setPrevNode(newNode);
      node.setNextNode(newNode);
      size++;
    } else {
      Node node = this.last;
      for (int i = size - 2; i >= index; i--) {
        node = node.getPrevNode();
      }
      Node newNode = new Node(node.getPrevNode(), element);
      newNode.setNextNode(node);
      node.getPrevNode().setNextNode(newNode);
      node.setPrevNode(newNode);
      size++;
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
    while (iterator.hasNext()) {
      add(iterator.next());
    }
    return true;
  }

  /**
   *
   */
  @Override
  public void set(int index, E element) {
    if (index < 0 || index > size - 1) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    if (index < size / 2) {
      Node node = this.first;
      for (int i = 1; i <= index; i++) {
        node = node.getNextNode();
      }
      Node newNode = new Node(element);
      if (node.getPrevNode() != null) {
        newNode.setPrevNode(node.getPrevNode());
        node.getPrevNode().setNextNode(newNode);
      }
      if (node.getNextNode() != null) {
        newNode.setNextNode(node.getNextNode());
        node.getNextNode().setPrevNode(newNode);
      }
      if (index == 0) {
        this.first = newNode;
      }
    } else {
      Node node = this.last;
      for (int i = size - 2; i >= index; i--) {
        node = node.getPrevNode();
      }
      Node newNode = new Node(element);
      if (node.getPrevNode() != null) {
        newNode.setPrevNode(node.getPrevNode());
        node.getPrevNode().setNextNode(newNode);
      }
      if (node.getNextNode() != null) {
        newNode.setNextNode(node.getNextNode());
        node.getNextNode().setPrevNode(newNode);
      }
      if (index == size - 1) {
        this.last = newNode;
      }
    }
  }

  /**
   *
   */
  @Override
  public E get(int index) {
    if (index < 0 || index > size - 1) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    if (index < size / 2) {
      Node node = this.first;
      for (int i = 1; i <= index; i++) {
        node = node.getNextNode();
      }
      return (E) node.getData();
    } else {
      Node node = this.last;
      for (int i = size - 2; i >= index; i--) {
        node = node.getPrevNode();
      }
      return (E) node.getData();
    }
  }

  /**
   *
   */
  @Override
  public E getFirst() {
    return (E) this.first.getData();
  }

  /**
   *
   */
  @Override
  public E getLast() {
    return (E) this.last.getData();
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
    Object[] objects = new Object[size];
    Node node = this.first;
    objects[0] = node.getData();
    for (int i = 1; i < size; i++) {
      node = node.getNextNode();
      objects[i] = node.getData();
    }
    return objects;
  }

  /**
   *
   */
  @Override
  public int indexOf(E element) {
    Node node = this.first;
    if (element == null) {
      if (node.getData() == null) {
        return 0;
      }
      for (int i = 1; i < size; i++) {
        node = node.getNextNode();
        if (node.getData() == null) {
          return i;
        }
      }
    } else {
      if (element.equals(node.getData())) {
        return 0;
      }
      for (int i = 1; i < size; i++) {
        node = node.getNextNode();
        if (node.getData() != null && element.equals(node.getData())) {
          return i;
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
    if (index < 0 || index > size - 1) {
      throw new IllegalArgumentException(
          String.format("ArraysIndex Out Of Bounds size: %s, index: %s", size, index));
    }
    if (index < size / 2) {
      Node node = this.first;
      if (index == 0) {
        removeFirst();
        return;
      }
      for (int i = 1; i <= index; i++) {
        node = node.getNextNode();
      }
      if (node.getPrevNode() != null) {
        node.getPrevNode().setNextNode(node.getNextNode());
      }
      if (node.getNextNode() != null) {
        node.getNextNode().setPrevNode(node.getPrevNode());
      }
    } else {
      Node node = this.last;
      if (index == size - 1) {
        removeLast();
        return;
      }
      for (int i = size - 2; i >= index; i--) {
        node = node.getPrevNode();
      }
      if (node.getPrevNode() != null) {
        node.getPrevNode().setNextNode(node.getNextNode());
      }
      if (node.getNextNode() != null) {
        node.getNextNode().setPrevNode(node.getPrevNode());
      }
    }
    size--;
  }

  /**
   *
   */
  @Override
  public void removeFirst() {
    Node node = this.first.getNextNode();
    this.first.setNextNode(null);
    node.setPrevNode(null);
    this.first = node;
    size--;
  }

  /**
   *
   */
  @Override
  public void removeLast() {
    Node node = this.last.getPrevNode();
    this.last.setPrevNode(null);
    node.setNextNode(null);
    this.last = node;
    size--;
  }

  /**
   *
   */
  @Override
  public void clear() {
    size = 0;
    this.first = null;
    this.last = null;
  }

  /**
   *
   */
  @Override
  public Iterator<E> iterator() {
    return new DoublyLinkedListIterator<E>();
  }

  /**
   *
   */
  @Override
  public void forEach(Consumer<? super E> consumer) {
    Node node = this.first;
    consumer.accept((E) node.getData());
    while (node.getNextNode() != null) {
      node = node.getNextNode();
      consumer.accept((E) node.getData());
    }
  }

  @Override
  public String toString() {
    Node node = this.first;
    StringBuilder builder = new StringBuilder();
    if (size > 0) {
      builder.append(node.getData());
      while (node.getNextNode() != null) {
        node = node.getNextNode();
        builder.append(", " + node.getData());
      }
    }
    return String.format("DoublyLinkedList{ elements= [%s], size= %s}", builder, size);
  }

  private final class DoublyLinkedListIterator<E> implements Iterator<E> {

    private int cursor = -1;
    private Node currentNode;

    /**
     *
     */
    @Override
    public boolean hasNext() {
      return cursor < size - 1;
    }

    /**
     *
     */
    @Override
    public E next() {
      if (cursor == -1) {
        currentNode = first;
      } else {
        currentNode = currentNode.getNextNode();
      }
      cursor++;
      return (E) currentNode.getData();
    }

    /**
     *
     */
    @Override
    public boolean hasPrevious() {
      return cursor >= 1 && cursor < size;
    }

    /**
     *
     */
    @Override
    public E previous() {
      if (hasPrevious()) {
        currentNode = currentNode.getPrevNode();
        cursor--;
        return (E) currentNode.getData();
      }
      throw new IllegalArgumentException(
          String.format("No such element present at index %s", cursor - 1));
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
      return hasPrevious() ? cursor - 1 : -1;
    }
  }
}
