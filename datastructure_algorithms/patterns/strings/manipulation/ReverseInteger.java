package datastructure_algorithms.patterns.strings.manipulation;

//  Given a 32-bit signed integer, reverse digits of an integer.
//
//  Example 1:
//  Input: 123
//  Output: 321
//  Explanation: Example 1: Input: 123 Output: 321
//
//  Example 2:
//  Input: -123
//  Output: -321
//  Explanation: Example 2: Input: -123 Output: -321
//
//  Example 3:
//  Input: 120
//  Output: 21
//  Explanation: Example 3: Input: 120 Output: 21

//  Solution: I reverse digits using modulo and division while checking for overflow before each multiplication.
//  Time -> O(number of digits) ? O(1)
//  Space -> O(1)

public class ReverseInteger {

  public static int reverse(int x) {
    int result = 0;

    while (x != 0) {
      int digit = x % 10;
      x = x / 10;

      // Overflow check
      if (result > Integer.MAX_VALUE / 10 ||
          (result == Integer.MAX_VALUE / 10 && digit > 7)) {
        return 0;
      }

      if (result < Integer.MIN_VALUE / 10 ||
          (result == Integer.MIN_VALUE / 10 && digit < -8)) {
        return 0;
      }

      result = result * 10 + digit;
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(reverse(123));          // 321
    System.out.println(reverse(-123));         // -321
    System.out.println(reverse(120));          // 21
    System.out.println(reverse(1534236469));   // 0
  }
}
