package datastructure_algorithms.patterns.stackqueue.twostack;

//  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//  Example 1:
//  Input: ['MinStack','push','push','push','getMin','pop','top','getMin']
//  Output: [null,null,null,null,0,null,2,0]
//  Explanation: Example 1
//
//  Example 2:
//  Input: ['MinStack','push','push','push','push','getMin','pop','getMin','pop','getMin']
//  Output: [null,null,null,null,null,0,null,0,null,0]
//  Explanation: Example 2
//
//  Example 3:
//  Input: ['MinStack','push','push','push','push','getMin','pop','getMin','pop','getMin','pop','getMin']
//  Output: [null,null,null,null,null,0,null,0,null,0,null,2]
//  Explanation: Example 3

//  Solution: “I maintain an auxiliary stack that keeps track of the minimum value at each level,
//  allowing constant-time minimum retrieval.”
//  push	O(1)
//  pop	O(1)
//  top	O(1)
//  getMin	O(1)
//  Space: O(n)

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

  private Deque<Integer> stack;
  private Deque<Integer> minStack;

  public MinStack() {
    stack = new ArrayDeque<>();
    minStack = new ArrayDeque<>();
  }

  public void push(int val) {
    stack.push(val);
    if (minStack.isEmpty() || val <= minStack.peek()) {
      minStack.push(val);
    }
  }

  public void pop() {
    if (stack.pop().equals(minStack.peek())) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();

    minStack.push(5);
    minStack.push(3);
    minStack.push(7);

    System.out.println(minStack.getMin()); // 3
    System.out.println(minStack.top());
    minStack.pop();
    System.out.println(minStack.getMin()); // 3
    System.out.println(minStack.top());
    minStack.pop();
    System.out.println(minStack.getMin()); // 5
    System.out.println(minStack.top());
  }

}
