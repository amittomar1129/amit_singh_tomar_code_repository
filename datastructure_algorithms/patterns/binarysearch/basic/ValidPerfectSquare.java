package datastructure_algorithms.patterns.binarysearch.basic;

//  Given a positive integer num, write a function which returns True if num is a perfect square else False.
//
//  Example 1:
//  Input: 16
//  Output: true
//  Explanation: 16 is a perfect square (4 * 4)
//
//  Example 2:
//  Input: 14
//  Output: false
//  Explanation: 14 is not a perfect square
//
//  Example 3:
//  Input: 808201
//  Output: true
//  Explanation: 808201 is a perfect square (899 * 899)

//  Time: O(log n)
//  Space: O(1)

public class ValidPerfectSquare {

  public static boolean isPerfectSquare(int num) {
    if (num < 2) {
      return true; // 1 is a perfect square
    }
    long left = 1;
    long right = num / 2;

    while (left <= right) {
      long mid = left + (right - left) / 2;
      long square = mid * mid;

      if (square == num) {
        return true;
      } else if (square < num) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println(sol(16)); // true
    System.out.println(sol(14)); // false
    System.out.println(sol(1));  // true
  }

  public static boolean sol(int num) {
    if (num == 1) {
      return true;
    }

    int left = 2;
    int right = num / 2;

    while (left < right) {
      int mid = left + (right - left) /2;
      int square = mid * mid;

      if (square == num) {
        return true;
      } else if (square < num) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return false;
  }























}
