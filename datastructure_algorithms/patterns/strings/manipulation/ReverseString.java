package datastructure_algorithms.patterns.strings.manipulation;

//  Write a function that reverses a string. The input string is given as an array of characters s.
//
//  Example 1:
//  Input: ['h','e','l','l','o']
//  Output: ['o','l','l','e','h']
//  Explanation: Example 1: Input: s = ['h','e','l','l','o'], Output: ['o','l','l','e','h']
//
//  Example 2:
//  Input: ['H','a','n','n','a','h']
//  Output: ['h','a','n','n','a','H']
//  Explanation: Example 2: Input: s = ['H','a','n','n','a','h'], Output: ['h','a','n','n','a','H']
//
//  Example 3:
//  Input: ['a']
//  Output: ['a']
//  Explanation: Example 3: Input: s = ['a'], Output: ['a']

//  Time -> O(n)
//  Space -> O(1) (in-place)

import java.sql.Array;
import java.util.Arrays;

public class ReverseString {

  public static void reverseString(char[] s) {
    int left = 0;
    int right = s.length - 1;

    while (left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;

      left++;
      right--;
    }
  }

  public static void main(String[] args) {
    char[] s = {'h', 'e', 'l', 'l', 'o'};
    reverseString(s);

    System.out.println(Arrays.toString(s));
  }

}
