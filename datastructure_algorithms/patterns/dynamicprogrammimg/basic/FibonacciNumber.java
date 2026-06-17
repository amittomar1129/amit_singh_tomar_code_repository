package datastructure_algorithms.patterns.dynamicprogrammimg.basic;

//  The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
//  such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
//  F(0) = 0, F(1) = 1, F(n) = F(n - 1) + F(n - 2) for n > 1. Given n, calculate F(n).
//
//  Example 1:
//  Input: 2
//  Output: 1
//  Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
//
//  Example 2:
//  Input: 3
//  Output: 2
//  Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

//  Solution: dp[i] = dp[i-1] + dp[i-2]
//  Time	O(n)
//  Space	O(1)

public class FibonacciNumber {

  public static int fib(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;

    int a = 0;
    int b = 1;

    for (int i = 2; i <= n; i++) {
      int temp = a + b;

      a = b;
      b = temp;
    }

    return b;
  }

  public static void main(String[] args) {
    int n1 = 5;
    System.out.println("F(" + n1 + ") = " + sol(5)); // 5

    int n2 = 10;
    System.out.println("F(" + n2 + ") = " + sol(n2)); // 55

    int n3 = 0;
    System.out.println("F(" + n3 + ") = " + sol(n3)); // 0

    int n4 = 20;
    System.out.println("F(" + n4 + ") = " + sol(n4)); // 6765
  }

  public static int sol(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    int a = 0;
    int b = 1;

    for(int i = 2; i <= n; i++) {
      int temp = a + b;
      a = b;
      b = temp;
    }
    return b;
  }

















}
