package datastructure_algorithms.patterns.stackqueue.design;

//  Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the
//  functions of a normal queue (push, peek, pop, and empty).
//
//  Example 1:
//  Input: ["MyQueue", "push", "push", "peek", "pop", "empty"] [[], [1], [2], [], [], []]
//  Output: [null, null, null, 1, 1, false]
//  Explanation: MyQueue myQueue = new MyQueue(); myQueue.push(1); // queue is: [1] myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue) myQueue.peek(); // return 1 myQueue.pop(); // return 1, queue is [2] myQueue.empty(); // return false

//  Solution: Using two stacks, we reverse the order twice to simulate FIFO.
//  We use two stacks to reverse insertion order. Elements are pushed into the input stack and only transferred to
//  the output stack when needed, ensuring amortized constant time for all operations.

//      ? Why not move on every pop?
//      ? To keep amortized O(1) instead of O(n)
//      ? What if we reverse push logic?
//      ? Then push becomes expensive instead of pop
//      ? Thread-safe?
//      ? No, Stack is synchronized but logic isn't atomic

import java.util.Stack;

public class ImplementQueueUsingStacks {

  Stack<Integer> inStack;
  Stack<Integer> outStack;

  public ImplementQueueUsingStacks() {
    inStack = new Stack<>();
    outStack = new Stack<>();
  }

  // Push element x to the back of queue
  public void push(int x) {
    inStack.push(x);
  }

  // Removes the element from the front of queue
  public int pop() {
    moveIfNeeded();
    return outStack.pop();
  }

  // Get the front element
  public int peek() {
    moveIfNeeded();
    return outStack.peek();
  }

  // Returns true if the queue is empty
  public boolean empty() {
    return inStack.isEmpty() && outStack.isEmpty();
  }

  private void moveIfNeeded() {
    if (outStack.isEmpty()) {
      while (!inStack.isEmpty()) {
        outStack.push(inStack.pop());
      }
    }
  }

  public static void main(String[] args) {
    ImplementQueueUsingStacks queue = new ImplementQueueUsingStacks();

    queue.push(1);
    queue.push(2);
    queue.push(3);

    System.out.println(queue.peek()); // 1
    System.out.println(queue.pop());  // 1
    System.out.println(queue.pop());  // 2
    System.out.println(queue.empty()); // false
    System.out.println(queue.pop());  // 3
    System.out.println(queue.empty()); // true
  }
}
