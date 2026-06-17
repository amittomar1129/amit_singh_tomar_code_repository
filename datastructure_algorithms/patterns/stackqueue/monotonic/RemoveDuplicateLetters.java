package datastructure_algorithms.patterns.stackqueue.monotonic;

//  Given a string s, remove duplicate letters so that every letter appears once and only once.
//  You must make sure your result is the smallest in lexicographical order among all possible results.
//
//  Example 1:
//  Input: s = "bcabc"
//  Output: abc
//  Explanation: The optimal result is "abc" which is the smallest in lexicographical order.
//
//      Example 2:
//  Input: s = "cbacdcbc"
//  Output: acdb
//  Explanation: The optimal result is "acdb" which is the smallest in lexicographical order.
//
//      Example 3:
//  Input: s = "abacb"
//  Output: abc
//  Explanation: The optimal result is "abc" which is the smallest in lexicographical order.

//  Solution: I use a greedy approach with a monotonic stack. When a smaller character arrives,
//  I remove larger characters from the stack if they appear again later, ensuring lexicographically
//  smallest order while keeping each character once.
//  Time: O(n) (Each character pushed and popped at most once)
//  Space: O(1)

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class RemoveDuplicateLetters {

//  cbacdcbc
  public static String removeDuplicateLetters(String s) {
    int[] count = new int[26];
    boolean[] inStack = new boolean[26];
    for (char c : s.toCharArray()) {
      count[c - 'a']++;
    }

    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      int index = c - 'a';
      count[index]--;
      if (inStack[index]) {
        continue;
      }
      while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
        inStack[stack.pop() - 'a'] = false;
      }
      stack.push(c);
      inStack[index] = true;
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    String s = "cbacdcbc";
    System.out.println(removeDuplicateLetters(s));
  }
}
