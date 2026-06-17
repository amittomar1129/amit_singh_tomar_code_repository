package datastructure_algorithms.patterns.stackqueue.slidingwindow;

//  Design your implementation of the circular double-ended queue (deque).
//
//  Example 1:
//  Input: ['MyCircularDeque', 'insertLast', 'insertLast', 'insertFront', 'insertFront', 'getRear', 'isFull', 'deleteLast', 'insertFront', 'getFront'] [[3], [1], [2], [3], [4], [], [], [], [4], []]
//  Output: [null, true, true, true, false, 2, true, true, true, 4]
//  Explanation: Example 1
//
//  Example 2:
//  Input: ['MyCircularDeque', 'insertFront', 'getRear', 'isFull', 'deleteLast', 'insertFront', 'getFront'] [[3], [1], [], [], [], [2], []]
//  Output: [null, true, 1, false, true, true, 2]
//  Explanation: Example 2
//
//  Example 3:
//  Input: ['MyCircularDeque', 'insertFront', 'getFront', 'isEmpty'] [[3], [1], [], []]
//  Output: [null, true, 1, false]
//  Explanation: Example 3

//  All operations
//  Time: O(1)
//  Space: O(k)

public class DesignCircularDeque {

  private int[] deque;
  private int front;
  private int rear;
  private int size;
  private int capacity;

  public DesignCircularDeque(int k) {
    capacity = k;
    deque = new int[k];
    front = 0;
    rear = 0;
    size = 0;
  }

  // Insert at front
  public boolean insertFront(int value) {
    if (isFull()) return false;

    front = (front - 1 + capacity) % capacity;
    deque[front] = value;
    size++;
    return true;
  }

  // Insert at rear
  public boolean insertLast(int value) {
    if (isFull()) return false;

    deque[rear] = value;
    rear = (rear + 1) % capacity;
    size++;
    return true;
  }

  // Delete front
  public boolean deleteFront() {
    if (isEmpty()) return false;

    front = (front + 1) % capacity;
    size--;
    return true;
  }

  // Delete rear
  public boolean deleteLast() {
    if (isEmpty()) return false;

    rear = (rear - 1 + capacity) % capacity;
    size--;
    return true;
  }

  // Get front value
  public int getFront() {
    if (isEmpty()) return -1;
    return deque[front];
  }

  // Get rear value
  public int getRear() {
    if (isEmpty()) return -1;
    int index = (rear - 1 + capacity) % capacity;
    return deque[index];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == capacity;
  }

  // ------------------ MAIN METHOD ------------------
  public static void main(String[] args) {

    DesignCircularDeque deque = new DesignCircularDeque(3);

    System.out.println(deque.insertLast(1));  // true
    System.out.println(deque.insertLast(2));  // true
    System.out.println(deque.insertFront(3)); // true
    System.out.println(deque.insertFront(4)); // false (full)

    System.out.println(deque.getRear());  // 2
    System.out.println(deque.isFull());   // true

    System.out.println(deque.deleteLast()); // true
    System.out.println(deque.insertFront(4)); // true

    System.out.println(deque.getFront()); // 4
  }

}
