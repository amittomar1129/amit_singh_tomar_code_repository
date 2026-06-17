package datastructure_algorithms.patterns.stackqueue.design;

//  Use two queues
//  push(x) -> O(1)
//  pop() -> O(n)
//  top() -> O(n)
//  empty() -> O(1)

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues2 {

  Queue<Integer> inQueue;
  Queue<Integer> outQueue;

  public ImplementStackUsingQueues2() {
    inQueue = new LinkedList<>();
    outQueue = new LinkedList<>();
  }

  // Push element x onto stack
  public void push(int x) {
    inQueue.offer(x);
  }

  // Removes the element on top of the stack
  public int pop() {
    while (inQueue.size() > 1) {
      outQueue.offer(inQueue.poll());
    }
    int top = inQueue.poll(); // last element
    // swap q1 and q2
    Queue<Integer> temp = inQueue;
    inQueue = outQueue;
    outQueue = temp;

    return top;
  }

  // Get the top element
  public int top() {
    while (inQueue.size() > 1) {
      outQueue.offer(inQueue.poll());
    }
    int top = inQueue.peek();
    outQueue.offer(inQueue.poll());
    // swap q1 and q2
    Queue<Integer> temp = inQueue;
    inQueue = outQueue;
    outQueue = temp;
    return top;
  }

  // Returns whether the stack is empty
  public boolean empty() {
    return inQueue.isEmpty();
  }

  public static void main(String[] args) {
    ImplementStackUsingQueues2 stack = new ImplementStackUsingQueues2();

    stack.push(1);
    stack.push(2);
    stack.push(3);

    System.out.println(stack.top()); // 3
    System.out.println(stack.pop()); // 3
    System.out.println(stack.pop()); // 2
    System.out.println(stack.empty()); // false
    System.out.println(stack.pop()); // 1
    System.out.println(stack.empty()); // true
  }
}
