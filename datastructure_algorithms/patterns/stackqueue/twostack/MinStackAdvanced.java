package datastructure_algorithms.patterns.stackqueue.twostack;

import java.util.ArrayDeque;
import java.util.Deque;


//  Solution: “I encode values when a new minimum is pushed so that the stack implicitly stores both the current and previous minimums.
//  This allows all operations in O(1) time using a single stack.”
//  push	O(1)	O(1)
//  pop	O(1)	O(1)
//  top	O(1)	O(1)
//  getMin	O(1)	O(1)

public class MinStackAdvanced {

  private Deque<Long> stack;
  private long min;

  public MinStackAdvanced() {
    stack = new ArrayDeque<>();
  }

  public void push(int val) {
    if (stack.isEmpty()) {
      stack.push((long) val);
      min = val;
    }
    else if (val >= min) {
      stack.push((long) val);
    }
    else {
      // encode
      stack.push(2L * val - min);
      min = val;
    }
  }

  public void pop() {
    long top = stack.pop();
    if (top < min) {
      // encoded value
      min = 2 * min - top;
    }
  }

  public int top() {
    long top = stack.peek();
    if (top >= min) {
      return (int) top;
    }
    return (int) min;
  }

  public int getMin() {
    return (int) min;
  }

  public static void main(String[] args) {
    MinStackAdvanced s = new MinStackAdvanced();

    s.push(5);
    s.push(3);
    s.push(7);

    System.out.println(s.getMin()); // 3
    s.pop();
    System.out.println(s.getMin()); // 3
    s.pop();
    System.out.println(s.getMin()); // 5
  }

}
