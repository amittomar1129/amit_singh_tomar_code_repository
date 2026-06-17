package datastructure_algorithms.patterns.stackqueue.design;

public class CircularQueue {

  private int[] queue;
  private int front;
  private int rear;
  private int size;
  private int capacity;

  public CircularQueue(int k) {
    capacity = k;
    queue = new int[k];
    front = 0;
    rear = 0;
    size = 0;
  }

  // Insert element into the circular queue
  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    }

    queue[rear] = value;
    rear = (rear + 1) % capacity;
    size++;
    return true;
  }

  // Delete element from the circular queue
  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    }

    front = (front + 1) % capacity;
    size--;
    return true;
  }

  // Get front item
  public int Front() {
    if (isEmpty()) {
      return -1;
    }
    return queue[front];
  }

  // Get last item
  public int rear() {
    if (isEmpty()) {
      return -1;
    }
    return queue[(rear - 1 + capacity) % capacity];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == capacity;
  }

  // Test
  public static void main(String[] args) {
    CircularQueue q = new CircularQueue(3);

    System.out.println(q.enQueue(1)); // true
    System.out.println(q.enQueue(2)); // true
    System.out.println(q.enQueue(3)); // true
    System.out.println(q.enQueue(4)); // false
    System.out.println(q.rear());     // 3
    System.out.println(q.isFull());   // true
    System.out.println(q.deQueue());  // true
    System.out.println(q.enQueue(4)); // true
    System.out.println(q.rear());     // 4
  }
}
