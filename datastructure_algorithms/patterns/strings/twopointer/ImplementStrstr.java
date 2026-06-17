package datastructure_algorithms.patterns.strings.twopointer;

//  Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of
// haystack.
//
//  Example 1:
//  Input: haystack = 'hello', needle = 'll'
//  Output: 2
//  Explanation: The needle 'll' is found in the haystack at index 2.
//
//  Example 2:
//  Input: haystack = 'aaaaa', needle = 'bba'
//  Output: -1
//  Explanation: The needle 'bba' is not found in the haystack.
//
//  Example 3:
//  Input: haystack = '', needle = ''
//  Output: 0
//  Explanation: An empty needle is always present in an empty haystack at index 0

//  Solution: Simple Sliding Window
//  Time: O(n * m)
//  Space: O(1)

public class ImplementStrstr {

  public static int strStr(String string, String target) {
    if (target.isBlank()) return 0;

    int m = string.length();
    int n = target.length();

    for (int i = 0; i <= m - n; i++) {
      int j = 0;
      while (j < n && string.charAt(i + j) == target.charAt(j)) {
        j++;
      }
      if (j == n) {
        return i; // found
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(strStr("sadbutsad", "sad")); // 0
    System.out.println(strStr("leetcode", "leeto")); // -1
  }
}
