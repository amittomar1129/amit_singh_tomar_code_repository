package datastructure_algorithms.patterns.recursion;

//  Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
//
//  Example 1:
//  Input: x = 2.00000, n = 10
//  Output: 1024.00000
//  Explanation: 2^10 = 1024
//
//  Example 2:
//  Input: x = 2.10000, n = 3
//  Output: 9.26100
//  Explanation: 2.1^3 = 9.261
//
//  Example 3:
//  Input: x = 2.00000, n = -2
//  Output: 0.25000
//  Explanation: 2^-2 = 1/(2^2) = 1/4 = 0.25

//  Solution: I use binary exponentiation which reduces the power by half at each step, achieving O(log n) time.
//  I convert n to long to safely handle Integer.MIN_VALUE.
//  time O(log n)
//  space O(log n)

public class Power {

  public static double myPow(double x, int n) {
    long N = n;  // avoid overflow

    if (N < 0) {
      x = 1 / x;
      N = -N;
    }

    return fastPow(x, N);
  }

  private static double fastPow(double x, long n) {
    if (n == 0) return 1.0;

    double half = fastPow(x, n / 2);

    if (n % 2 == 0) { // even
      return half * half;
    } else { // odd
      return half * half * x;
    }
  }

  public static void main(String[] args) {
    System.out.println(myPow(2.0, 10));   // 1024.0
    System.out.println(myPow(2.0, -2));   // 0.25
    System.out.println(myPow(2.0, 0));    // 1.0
    System.out.println(myPow(8.0, 2));    // 64.0
  }
}
