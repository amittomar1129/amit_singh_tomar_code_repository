package datastructure_algorithms.patterns.stackqueue.expression;

//  Implement a basic calculator to evaluate a simple expression string. The expression string may
// contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and
// empty spaces.
//
//  Example 1:
//  Input: 1 + 1
//  Output: 2
//  Explanation: 1 + 1 = 2
//
//  Example 2:
//  Input: 2-1 + 2
//  Output: 3
//  Explanation: 2 - 1 + 2 = 3
//
//  Example 3:
//  Input: (1+(4+5+2)-3)+(6+8)
//  Output: 23
//  Explanation: Grouping the parentheses, we have (1 + 11 - 3) + 14 = 23

//  You are given a string expression that may contain:
//  Non-negative integers
//  '+' and '-'
//  '(' and ')'
//  Spaces
//
//  Rules:
//  Parentheses must be respected
//  No * or /
//  Expression is always valid
//  Division is NOT involved

//  Solution: “I evaluate the expression in one pass using a stack to store previous results and
// signs when
//  entering parentheses, and I update the running result based on the current sign.”
//  Time: O(n)
//  Space: O(n) (stack for parentheses)

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

  public static int calculate(String s) {
    return helper(s.toCharArray(), new int[] {0});
  }

  private static int helper(char[] arr, int[] index) {
    Deque<Integer> stack = new ArrayDeque<>();
    int num = 0;
    char sign = '+';

    while (index[0] < arr.length) {
      char c = arr[index[0]];
      // Build number
      if (Character.isDigit(c)) {
        num = num * 10 + (c - '0');
      }
      // Handle '(' with recursion
      if (c == '(') {
        index[0]++;
        num = helper(arr, index);
      }
      // If operator or end
      if (!Character.isDigit(c) && c != ' ' || index[0] == arr.length - 1) {
        switch (sign) {
          case '+':
            stack.push(num);
            break;
          case '-':
            stack.push(-num);
            break;
          case '*':
            stack.push(stack.pop() * num);
            break;
          case '/':
            stack.push(stack.pop() / num);
            break;
        }
        sign = c;
        num = 0;
      }
      // Closing bracket ends recursion
      if (c == ')') {
        break;
      }
      index[0]++;
    }

    int result = 0;
    for (int val : stack) {
      result += val;
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(calculate("1 + 1")); // 2
    System.out.println(calculate(" (2 - 1 + (2 - 1 * 2)) * 2 + 2 ")); // 3
    System.out.println(calculate("(1+(4+5+2)-3)+(6+8)")); // 23
  }
}
