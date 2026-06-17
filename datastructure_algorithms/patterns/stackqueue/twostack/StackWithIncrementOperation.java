package datastructure_algorithms.patterns.stackqueue.twostack;

//  Design a stack which supports the following operations.
//  Implement the CustomStack class:
//  - CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the
//  stack or do nothing if the stack reached the maxSize.
//  - void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
//  - int pop() Pops and returns the top of stack or -1 if the stack is empty.
//  - void increment(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements
//  in the stack, increment all the elements in the stack.
//
//  Example 1:
//  Input: CustomStack customStack = new CustomStack(3); customStack.push(1); customStack.push(2); customStack.pop(); customStack.push(2); customStack.push(3); customStack.push(4); customStack.increment(5, 100); customStack.increment(2, 100); customStack.pop(); customStack.pop(); customStack.pop(); customStack.pop();
//  Output: [null,null,null,2,null,null,null,null,103,202,201,-1]
//  Explanation: CustomStack customStack = new CustomStack(3); // Stack is Empty [] customStack.push(1); // stack becomes [1] customStack.push(2); // stack becomes [1, 2] customStack.pop(); // return 2 --> Return top of the stack 2, stack becomes [1] customStack.push(2); // stack becomes [1, 2] customStack.push(3); // stack becomes [1, 2, 3] customStack.push(4); // stack still [1, 2, 3], Don't add 4 because the stack reached the maxSize customStack.increment(5, 100); // stack becomes [101, 102, 103] customStack.increment(2, 100); // stack becomes [201, 202, 103] customStack.pop(); // return 103 --> Return top of the stack 103, stack becomes [201, 202] customStack.pop(); // return 202 --> Return top of the stack 202, stack becomes [201] customStack.pop(); // return 201 --> Return top of the stack 201, stack becomes [] customStack.pop(); // return -1 --> Stack is empty
//
//  Example 2:
//  Input: CustomStack customStack = new CustomStack(0); customStack.push(1); customStack.pop();
//  Output: [null,null,-1]
//  Explanation: CustomStack customStack = new CustomStack(0); // Stack is Empty and maxSize is 0 customStack.push(1); // stack still [], can't add because maxSize is 0 customStack.pop(); // return -1 --> Stack is empty
//
//  Example 3:
//  Input: CustomStack customStack = new CustomStack(2); customStack.push(5); customStack.increment(1, 10); customStack.pop(); customStack.pop();
//  Output: [null,null,null,15,-1]
//  Explanation: CustomStack customStack = new CustomStack(2); // Stack is Empty [] customStack.push(5); // stack becomes [5] customStack.increment(1, 10); // stack becomes [15] customStack.pop(); // return 15 --> Return top of the stack 15, stack becomes [] customStack.pop(); // return -1 --> Stack is empty

//  Solution: “Instead of eagerly incrementing bottom elements, I lazily store increments at a boundary index and
//  propagate them downward only when popping.”
//  push	O(1)
//  pop	O(1)
//  increment	O(1)
//  overall Space		O(n)


public class StackWithIncrementOperation {

  private int[] stack;
  private int[] inc;
  private int maxSize;
  private int top;

  public StackWithIncrementOperation(int maxSize) {
    this.maxSize = maxSize;
    stack = new int[maxSize];
    inc = new int[maxSize];
    top = -1;
  }

  public void push(int x) {
    if (top + 1 == maxSize) {
      return; // stack full
    }
    stack[++top] = x;
  }

  public int pop() {
    if (top == -1) {
      return -1; // stack empty
    }

    int result = stack[top] + inc[top];

    if (top > 0) {
      inc[top - 1] += inc[top]; // pass increment down
    }

    inc[top] = 0;
    top--;
    return result;
  }

  public void increment(int k, int val) {
    int idx = Math.min(k - 1, top);
    if (idx >= 0) {
      inc[idx] += val;
    }
  }

  // ------------------ MAIN METHOD ------------------
  public static void main(String[] args) {

    StackWithIncrementOperation cs = new StackWithIncrementOperation(3);

    cs.push(1);
    cs.push(2);
    System.out.println(cs.pop()); // 2

    cs.push(2);
    cs.push(3);
    cs.push(4);   // ignored (stack full)

    cs.increment(5, 100); // increment all elements
    cs.increment(2, 100); // increment bottom 2

    System.out.println(cs.pop()); // 103
    System.out.println(cs.pop()); // 202
    System.out.println(cs.pop()); // 201
    System.out.println(cs.pop()); // -1
  }

}
