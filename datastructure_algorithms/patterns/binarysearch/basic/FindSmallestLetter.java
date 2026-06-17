package datastructure_algorithms.patterns.binarysearch.basic;

//  Given a list of sorted characters letters containing only lowercase letters, and given a target
// letter target,
//  find the smallest element in the list that is larger than the given target. Letters also wrap
// around.
//  For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
//
//  Example 1:
//  Input: letters = ['c', 'f', 'j'], target = 'a'
//  Output: 'c'
//  Explanation: In this example, the smallest letter greater than 'a' is 'c'.
//
//  Example 2:
//  Input: letters = ['c', 'f', 'j'], target = 'c'
//  Output: 'f'
//  Explanation: In this example, the smallest letter greater than 'c' is 'f'.
//
//  Example 3:
//  Input: letters = ['c', 'f', 'j'], target = 'k'
//  Output: 'c'
//  Explanation: In this example, the smallest letter greater than 'k' is 'c' as letters wrap
// around.

//  Solution: This is a binary search + wrap-around problem.
//  We are finding the first letter > target
//  If none exists -> return letters[0]
//  Why left % letters.length?
//  If target is smaller than all letters, left = 0
//  If target is greater than or equal to all letters, left = letters.length
//  Time: O(log n)
//  Space: O(1)

public class FindSmallestLetter {

  public static char nextGreatestLetter(char[] letters, char target) {
    int left = 0;
    int right = letters.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (letters[mid] <= target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    // Wrap-around using modulo
    return letters[left % letters.length];
  }

  public static void main(String[] args) {
    char[] letters = {'c', 'e', 'f', 'i', 'j', 'q', 't'};

    System.out.println(sol(letters, 'a')); // c
    System.out.println(sol(letters, 'c')); // e
    System.out.println(sol(letters, 'j')); // q
    System.out.println(sol(letters, 'u')); // c
  }

  public static char sol(char[] input, char target) {
    int left = 0;
    int right = input.length-1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if ((input[mid] - 'a') <= (target - 'a') ) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return input[left % input.length];
  }



















}
