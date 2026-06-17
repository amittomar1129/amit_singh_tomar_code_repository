package datastructure_algorithms.patterns.strings.manipulation;

//  Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
//  The algorithm should be able to handle four scenarios: 1. Discard all leading whitespaces. 2. Sign of the number.
//  3. Overflow. 4. Invalid input.
//
//  Example 1:
//  Input: "42"
//  Output: 42
//  Explanation: The digits are read in order, so the result is 42.
//
//  Example 2:
//  Input: " -42"
//  Output: -42
//  Explanation: The first non-whitespace character is '-', which is the sign of the number. Then take as many numerical digits as possible, which gets 42.
//
//  Example 3:
//  Input: "4193 with words"
//  Output: 4193
//  Explanation: Ignore the non-numeric characters and return 4193.

//  Time -> O(n)
//  Space -> O(1)

public class StringToInteger {

  public static int myAtoi(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    int i = 0, length = s.length();
    int sign = 1;
    int result = 0;

    // 1. Skip leading whitespaces
    while (i < length && s.charAt(i) == ' ') {
      i++;
    }

    // 2. Handle sign
    if (i < length && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
      sign = (s.charAt(i) == '-') ? -1 : 1;
      i++;
    }

    // 3. Convert digits and handle overflow
    while (i < length && Character.isDigit(s.charAt(i))) {
      int digit = s.charAt(i) - '0';

      // Overflow check
      if (result > Integer.MAX_VALUE / 10 ||
          (result == Integer.MAX_VALUE / 10 && digit > 7)) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }

      result = result * 10 + digit;
      i++;
    }

    // 4. Apply sign
    return result * sign;
  }

  public static void main(String[] args) {
    System.out.println(myAtoi("42"));             // 42
    System.out.println(myAtoi("   -42"));         // -42
    System.out.println(myAtoi("4193 with words"));// 4193
    System.out.println(myAtoi("words and 987"));  // 0
    System.out.println(myAtoi("-91283472332"));   // -2147483648
  }
}
