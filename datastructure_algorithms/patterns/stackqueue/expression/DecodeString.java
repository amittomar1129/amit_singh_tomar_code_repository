package datastructure_algorithms.patterns.stackqueue.expression;

//  Given an encoded string, return its decoded string. The encoding rule is: k[encoded_string],
//  where the encoded_string inside the square brackets is being repeated exactly k times.
//  Note that k is guaranteed to be a positive integer. You may assume that the input string is always valid;
//  no extra white spaces, square brackets are well-formed, etc.
//
//  Example 1:
//  Input: 3[a]2[bc]
//  Output: aaabcbc
//  Explanation: Return 'aaabcbc'. The string is encoded as '3[a]2[bc]'.
//
//  Example 2:
//  Input: 3[a2[c]]
//  Output: accaccacc
//  Explanation: Return 'accaccacc'. The string is encoded as '3[a2[c]]'.
//
//  Example 3:
//  Input: 2[abc]3[cd]ef
//  Output: abcabccdcdcdef
//  Explanation: Return 'abcabccdcdcdef'. The string is encoded as '2[abc]3[cd]ef'.

//  Solution: “I use a stack to store previous strings and repeat counts. Each bracket opens a new decoding context,
//  and on closing I repeat the decoded substring and merge it back.”
//  Time: O(n) (Each character processed once; output size dominates)
//  Space: O(n) (stack + result)

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {

  public static String decodeString(String s) {
    Deque<StringBuilder> stringStack = new ArrayDeque<>();
    Deque<Integer> countStack = new ArrayDeque<>();
    StringBuilder result = new StringBuilder();
    int k = 0;
    for (char c : s.toCharArray()) {
      if (Character.isDigit(c)) {
        k = k * 10 + (c - '0');
      } else if (c == '[') {
        stringStack.push(result);
        countStack.push(k);
        result = new StringBuilder();
        k = 0;
      } else if (c == ']') {
        int repeat = countStack.pop();
        StringBuilder prev = stringStack.pop();

        for (int i = 0; i < repeat; i++) {
          prev.append(result);
        }
        result = prev;
      } else {
        result.append(c);
      }
    }
    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println(decodeString("3[a]"));          // aaa
    System.out.println(decodeString("3[a2[c]]"));      // accaccacc
    System.out.println(decodeString("2[abc]3[cd]ef")); // abcabccdcdcdef
  }
}
