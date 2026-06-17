package datastructure_algorithms.patterns.stackqueue.design;

//  Implement the following operations of a stack using queues. - push(x) -- Push element x onto stack. - pop() --
//  Removes the element on top of the stack. - top() -- Get the top element. - empty() -- Return whether the stack is empty.
//
//  Example 1:
//  Input: ["MyStack", "push", "push", "top", "pop", "empty"] [[], [1], [2], [], [], []]
//  Output: [null, null, null, 2, 2, false]
//  Explanation: MyStack myStack = new MyStack(); myStack.push(1); myStack.push(2); myStack.top(); // return 2 myStack.pop(); // return 2 myStack.empty(); // return False

//  Solution:
//  Approach 1 (Preferred in FAANG): Make push costly, keep pop O(1)
//  Approach 2:Make pop costly, push O(1), Less preferred because top() also becomes costly.

import java.util.LinkedList;
import java.util.Queue;

//  Approach 1
//      push	O(n)
//      pop	O(1)
//      top	O(1)
//      space	O(n)
public class ImplementStackUsingQueues {

  Queue<Integer> queue;

  public ImplementStackUsingQueues() {
    queue = new LinkedList<>();
  }

  // Push element x onto stack
  public void push(int x) {
    queue.offer(x);
    int size = queue.size();

    // Rotate elements
    for (int i = 0; i < size - 1; i++) {
      queue.offer(queue.poll());
    }
  }

  // Removes the element on top of the stack
  public int pop() {
    return queue.poll();
  }

  // Get the top element
  public int top() {
    return queue.peek();
  }

  // Returns whether the stack is empty
  public boolean empty() {
    return queue.isEmpty();
  }

  public static void main(String[] args) {
    ImplementStackUsingQueues stack = new ImplementStackUsingQueues();

    stack.push(10);
    stack.push(20);
    stack.push(30);

    System.out.println(stack.top()); // 30
    System.out.println(stack.pop()); // 30
    System.out.println(stack.pop()); // 20
    System.out.println(stack.empty()); // false
    System.out.println(stack.pop()); // 10
    System.out.println(stack.empty()); // true
  }

}
