package datastructure_algorithms.patterns.stackqueue.expression;

//  Implement a basic calculator to evaluate a simple expression string. The expression string contains only non-negative
//  integers, '+', '-', '*', and '/' operators. The integer division should truncate toward zero.
//
//  Example 1:
//  Input: 3+2*2
//  Output: 7
//  Explanation: 3 + 2 * 2 = 7
//
//  Example 2:
//  Input: 3/2
//  Output: 1
//  Explanation: 3 / 2 = 1
//
//  Example 3:
//  Input: 3+5/2
//  Output: 5
//  Explanation: 3 + 5 / 2 = 5 (integer division)

//  Solution: I scan the string once, using a stack to defer addition and subtraction while resolving multiplication
//  and division immediately, which respects operator precedence in linear time.
//  Time: O(n)
//  Space: O(n) (stack)

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {

  public static int calculate(String s) {
    Deque<Integer> stack = new ArrayDeque<>();
    int num = 0;
    char prevOperator = '+';

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        num = num * 10 + (c - '0');
      }
      // If operator OR end of string
      if ((!Character.isDigit(c) && c != ' ') || (i == s.length() - 1)) {
        if (prevOperator == '+') {
          stack.push(num);
        } else if (prevOperator == '-') {
          stack.push(-num);
        } else if (prevOperator == '*') {
          stack.push(stack.pop() * num);
        } else if (prevOperator == '/') {
          stack.push(stack.pop() / num);
        }
        prevOperator = c;
        num = 0;
      }
    }
    int result = 0;
    while (!stack.isEmpty()) {
      result += stack.pop();
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(calculate("3+2*2"));     // 7
    System.out.println(calculate("14-3/2"));    // 13
    System.out.println(calculate(" 3/2 "));     // 1
    System.out.println(calculate(" 3+5 / 2 ")); // 5
  }
}
