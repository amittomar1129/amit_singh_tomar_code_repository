package datastructure_algorithms.patterns.stackqueue.twostack;

//  You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
//  Evaluate the expression. Return an integer that represents the value of the expression.
//  Note that:
//  - The valid operators are '+', '-', '*', and '/'.
//
//  - Each operand may be an integer or another expression.
//
//  - The division between two integers always truncates toward zero.
//
//  - There will not be any division by zero.
//
//  - The input represents a valid arithmetic expression in a reverse polish notation.
//
//  - The answer and all the intermediate calculations can be represented in a 32-bit integer.
//
//  Example 1:
//  Input: ["2","1","+","3","*"]
//  Output: 9
//  Explanation: ((2 + 1) * 3) = 9
//
//  Example 2:
//  Input: ["4","13","5","/","+"]
//  Output: 6
//  Explanation: (4 + (13 / 5)) = 6
//
//  Example 3:
//  Input: ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//  Output: 22
//  Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5 = 22


//  “I process the tokens left to right using a stack. Numbers are pushed, and operators pop two operands,
//  compute the result, and push it back.”
//  Time: O(n)
//      Space: O(n) (stack)

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {

  public static int evalRPN(String[] tokens) {
    Deque<Integer> stack = new ArrayDeque<>();

    for (String token : tokens) {
      if (token.equals("+")) {
        stack.push(stack.pop() + stack.pop());
      } else if (token.equals("-")) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a - b);
      } else if (token.equals("*")) {
        stack.push(stack.pop() * stack.pop());
      } else if (token.equals("/")) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a / b); // truncates toward zero
      } else {
        stack.push(Integer.parseInt(token));
      }
    }
    return stack.pop();
  }

  public static void main(String[] args) {

    String[] tokens1 = {"2", "1", "+", "3", "*"};
    String[] tokens2 = {"4", "13", "5", "/", "+"};

    System.out.println(evalRPN(tokens1)); // 9
    System.out.println(evalRPN(tokens2)); // 6
  }
}
