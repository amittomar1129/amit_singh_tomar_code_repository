package datastructure_algorithms.patterns.strings.twopointer;

//  Given an array of characters, compress it in-place. The length after compression must always be
//  smaller than or equal to the original array. Every element of the array should be a character (not int) of length 1.
//  After you are done modifying the input array in-place, return the new length of the array.
//
//  Example 1:
//  Input: ['a','a','b','b','c','c','c']
//  Output: 6
//  Explanation: The array is compressed to ['a','2','b','2','c','3'].
//
//  Example 2:
//  Input: ['a']
//  Output: 1
//  Explanation: The array remains unchanged.
//
//  Example 3:
//  Input: ['a','b','b','b','b','b','b','b','b','b','b','b','b']
//  Output: 4
//  Explanation: The array is compressed to ['a','b','1','2'].
//
//  Solution: I use two pointers: one for reading and one for writing. I count consecutive characters and write
//  the compressed form directly into the input array.
//  Time	O(n)
//  Space	O(1)

import java.util.Arrays;

public class StringCompression {

  public static int compress(char[] chars) {
    int write = 0;
    for (int i = 0; i < chars.length; ) {
      char current = chars[i];
      int count = 0;
      // count consecutive same characters
      while (i < chars.length && chars[i] == current) {
        i++;
        count++;
      }
      // write character
      chars[write++] = current;
      // write count if > 1
      if (count > 1) {
        for (char c : String.valueOf(count).toCharArray()) {
          chars[write++] = c;
        }
      }
    }
    return write;
  }

  public static void main(String[] args) {
    char[] chars = {'a','a','b','b','b','b','b','b','b','b','b','b','b','b','c','c','c'};
    int len = compress(chars);

    System.out.println(len); // 6
    for (int i = 0; i < len; i++) {
      System.out.print(chars[i] + " ");
    }
  }
}
