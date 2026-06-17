package datastructure_algorithms.patterns.strings.regex;

//  Given an encoded string, return its decoded string. The encoding rule is: k[encoded_string],
//  where the encoded_string inside the square brackets is being repeated exactly k times.
//  Note that k is guaranteed to be a positive integer. You may assume that the input string is
// always valid;
//  no extra white spaces, square brackets are well-formed, etc.
//
//  Example 1:
//  Input: 3[a]2[bc]
//  Output: aaabcbc
//  Explanation: Return 'aaabcbc'. The string 'a' is repeated 3 times, then the string 'bc' is
// repeated 2 times.
//
//      Example 2:
//  Input: 3[a2[c]]
//  Output: accaccacc
//  Explanation: Return 'accaccacc'. The string 'a' is repeated 3 times, then the string 'c' is
// repeated 2 times.
//
//      Example 3:
//  Input: 2[abc]3[cd]ef
//  Output: abcabccdcdcdef
//  Explanation: Return 'abcabccdcdcdef'. The string 'abc' is repeated 2 times, the string 'cd' is
// repeated 3 times, then 'ef' is added.

//  Time -> O(n * k) in worst case (when large repeats)
//  Space -> O(n) due to stacks

//  Walkthrough:
//    char            action
//      3	          currentNum = 3
//      [	          push 3, push ""
//      a	          currentString = "a"
//      2         	currentNum = 2
//      [	          push 2, push "a"
//      c         	currentString = "c"
//      ]	          repeat "c" twice -> "cc"
//      ]	          repeat "acc" 3 times -> "accaccacc"

import java.util.Stack;

public class DecodeString {

  public static String decodeString(String s) {
    Stack<Integer> countStack = new Stack<>();
    Stack<StringBuilder> stringStack = new Stack<>();
    StringBuilder result = new StringBuilder();
    int count = 0;

    for (char ch : s.toCharArray()) {
      //      2[abc]3[cd]ef
      //      3[a2[c]]
      if (Character.isDigit(ch)) {
        count = count * 10 + (ch - '0');
      }
      else if (ch == '[') {
        countStack.push(count);
        stringStack.push(result);
        count = 0;
        result = new StringBuilder();
      }
      else if (ch == ']') {
        int repeat = countStack.pop();
        StringBuilder string = stringStack.pop();

        for (int i = 0; i < repeat; i++) {
          string.append(result);
        }
        result = string;
      }
      else {
        result.append(ch);
      }
    }

    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println(decodeString("3[a]")); // aaa
    System.out.println(decodeString("3[a2[c]]")); // accaccacc
    System.out.println(decodeString("2[abc]3[cd]ef")); // abcabccdcdcdef
  }
}
