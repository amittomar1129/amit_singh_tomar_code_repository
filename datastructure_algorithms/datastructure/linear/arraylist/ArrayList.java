package datastructure_algorithms.datastructure.linear.arraylist;

import java.util.Arrays;
import java.util.function.Consumer;

public final class ArrayList<E> implements List<E> {

  // After filling the 80% of total capacity, ArrayList will be reinitialized.
  private static final int LOAD_FACTOR = 80;
  private E[] elements;
  private int size;
  private int currentCapacity;


  public ArrayList() {
    this(10);
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException(
          String.format("Illegal size of ArrayList: %s", initialCapacity));
    }
    this.currentCapacity = initialCapacity;
    this.elements = (E[]) new Object[initialCapacity];
  }

  /**
   *
   */
  @Override
  public boolean add(E element) {
    elements[size++] = element;
    ensureCapacity();
    return true;
  }

  /**
   *
   */
  @Override
  public boolean add(int index, E element) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException(
          String.format("ArrayIndex Out Of Bounds with size:%s, index:%s", size, index));
    }
    if (index == size) {
      return add(element);
    }
    return shiftElementsForwardAndPlaceAtIndex(index, element);
  }

  /**
   *
   */
  @Override
  public boolean addAll(List<E> list) {
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
    validateIndex(index);
    elements[index] = element;
  }

  /**
   *
   */
  @Override
  public E get(int index) {
    validateIndex(index);
    return elements[index];
  }

  /**
   *
   */
  @Override
  public boolean contains(E element) {
    return indexOf(element) > 0;
  }

  /**
   *
   */
  @Override
  public Object[] toArray() {
    Object[] copiedObjects = new Object[size];
    for (int i = 0; i < size; i++) {
      copiedObjects[i] = this.elements[i];
    }
    return copiedObjects;
  }

  /**
   *
   */
  @Override
  public int indexOf(E element) {
    if (element == null) {
      for (int i = 0; i < size - 1; i++) {
        if (elements[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size - 1; i++) {
        if (elements[i] != null && elements[i].equals(element)) {
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
    return this.size <= 0;
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
  public boolean remove(E element) {
    int index = indexOf(element);
    if (index >= 0) {
      removeAt(index);
      return true;
    }
    return false;
  }

  /**
   *
   */
  @Override
  public void removeAt(int index) {
    validateIndex(index);
    for (int i = index; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }
    size--;
    elements[size] = null;
  }

  /**
   *
   */
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      this.elements[i] = null;
    }
    size = 0;
  }

  @Override
  public String toString() {
    Object[] actualElements = Arrays.stream(this.elements).limit(size).toArray();
    return String.format("ArrayList{ elements= %s, size= %s }", Arrays.toString(actualElements),
        this.size);
  }

  private void ensureCapacity() {
    int maxAllowedSize = currentCapacity * LOAD_FACTOR / 100;
    if (size >= maxAllowedSize) {
      this.currentCapacity = currentCapacity * 2 + 1;
      E[] oldElements = elements;
      this.elements = Arrays.copyOf(oldElements, currentCapacity);
    }
  }

  /**
   *
   */
  @Override
  public Iterator<E> iterator() {
    return new ArrayListIterator<E>();
  }

  /**
   *
   */
  @Override
  public void forEach(Consumer<? super E> consumer) {
    for (int i = 0; i < size; i++) {
      consumer.accept(elements[i]);
    }
  }

  private boolean shiftElementsForwardAndPlaceAtIndex(int index, E element) {
    for (int i = size - 1; i >= index; i--) {
      elements[i + 1] = elements[i];
    }
    elements[index] = element;
    size++;
    ensureCapacity();
    return true;
  }

  private void validateIndex(int index) {
    if (index < 0 || index > size - 1) {
      throw new IllegalArgumentException(
          String.format("ArrayIndex Out Of Bounds with size:%s, index:%s", size, index));
    }
  }

  private final class ArrayListIterator<E> implements Iterator {

    private int cursor = -1;

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
      if (cursor >= size - 1) {
        throw new IllegalArgumentException(
            String.format("ArrayIndex Out Of Bounds with size:%s, index:%s", size, cursor + 1));
      }
      E element = (E) elements[++cursor];
      return element;
    }

    /**
     *
     */
    @Override
    public boolean hasPrevious() {
      return cursor > 0;
    }

    /**
     *
     */
    @Override
    public Object previous() {
      if (cursor <= 0) {
        throw new IllegalArgumentException(
            String.format("ArrayIndex Out Of Bounds with size:%s, index:%s", size, cursor + 1));
      }
      return (E) elements[--cursor];
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
      return cursor;
    }
  }
}
