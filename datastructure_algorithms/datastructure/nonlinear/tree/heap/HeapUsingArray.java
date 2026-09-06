package datastructure_algorithms.datastructure.nonlinear.tree.heap;

// Heap Using ArrayList implementing in similar way but easier comparatively due to inbuild list
// methods.
public class HeapUsingArray<E extends Comparable> implements Heap<E> {

  private Data<E>[] elements;
  private static int size;
  private static int currentCapacity = 20;
  private static final int LOAD_FACTOR = 70;

  public HeapUsingArray() {
    this.elements = new Data[currentCapacity];
  }

  public HeapUsingArray(int capacity) {
    this.elements = new Data[capacity];
    this.currentCapacity = capacity;
  }

  private int parent(int index) {
    return (index - 1) / 2;
  }

  private int left(int index) {
    return (2 * index) + 1;
  }

  private int right(int index) {
    return (2 * index) + 2;
  }

  /** */
  @Override
  public void insert(E element) {
    Data<E> newElement = new Data<>(element);
    elements[size] = newElement;
    heapifyUp(size);
    size++;
    ensureCapacity();
  }

  private void heapifyUp(int index) {
    while (index > 0) {
      int parent = parent(index);
      if (elements[parent].getValue().compareTo(elements[index].getValue()) < 0) {
        swap(index, parent);
      }
      index = parent;
    }
  }

  private void swap(int index1, int index2) {
    Data temp = elements[index2];
    elements[index2] = elements[index1];
    elements[index1] = temp;
  }

  /** */
  @Override
  public E remove() {
    if (size == 0) {
      return null;
    }
    Data<E> root = elements[0];
    elements[0] = elements[size - 1];
    elements[size - 1] = null;
    size--;
    heapifyDown(0);
    return root.getValue();
  }

  /** */
  @Override
  public boolean remove(E element) {
    if (size == 0) {
      return false;
    }
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (elements[i].getValue().compareTo(element) == 0) {
        index = i;
      }
    }
    if (index != -1) {
      Data<E> found = elements[index];
      elements[index] = elements[size - 1];
      elements[size - 1] = null;
      size--;

      if (index > 0 && elements[parent(index)].getValue().compareTo(found.getValue()) < 0) {
        heapifyUp(index);
      } else {
        heapifyDown(index);
      }
      return true;
    }
    return false;
  }

  // compare element with all its children and replace with the largest one.
  private void heapifyDown(int index) {
    while (left(index) < size) {
      int largest = left(index);
      int right = right(index);

      if (elements[right] != null && elements[largest].getValue().compareTo(elements[right].getValue()) < 0) {
        largest = right;
      }

      if (elements[index].getValue().compareTo(elements[largest].getValue()) >= 0) {
        break;
      }

      swap(index, largest);
      index = largest;
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Heap: ");
    for (int i = 0; i < size; i++) {
      builder.append(elements[i] + ", ");
    }
    return builder.toString();
  }

  private void ensureCapacity() {
    if (size >= (currentCapacity * LOAD_FACTOR) / 100) {
      int newCapacity = currentCapacity * 2;
      Data[] newData = new Data[newCapacity];
      for (int i = 0; i < this.elements.length; i++) {
        newData[i] = this.elements[i];
      }
      this.elements = newData;
      currentCapacity = newCapacity;
    }
  }

  public static void main(String[] args) {
    HeapUsingArray<Integer> heap = new HeapUsingArray<>();
    heap.insert(15);
    heap.insert(20);
    heap.insert(2);
    heap.insert(9);
    heap.insert(16);
    heap.insert(35);
    heap.insert(4);
    heap.insert(45);
    System.out.println(heap);

    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap);

    System.out.println(heap.remove(35));
    System.out.println(heap);
    System.out.println(heap.remove());
    System.out.println(heap.remove());
    System.out.println(heap.remove());
    System.out.println(heap.remove());
    System.out.println(heap);
  }
}
