package datastructure_algorithms.patterns.strings.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//  Given a string s and an integer k, return the length of the longest substring of s that contains
// at most k distinct characters.
//
//  Example 1:
//  Input: s = 'eceba', k = 2
//  Output: 3
//  Explanation: The substring 'ece' contains 2 distinct characters.
//
//  Example 2:
//  Input: s = 'aa', k = 1
//  Output: 2
//  Explanation: The substring 'aa' contains 1 distinct character.
//
//  Example 3:
//  Input: s = 'aa', k = 2
//  Output: 2
//  Explanation: The substring 'aa' contains 1 distinct character.

//  Solution: This is a variable-length sliding window problem.
//  I expand the window using a for loop and track character frequencies in a HashMap.
//  If the number of distinct characters exceeds k, I shrink the window from the left.
//  Time	O(n)
//  Space	O(k)

public class LongestSubstringWithAtMostKDistinctCharacters {

  public static int lengthOfLongestSubstringKDistinct(String string, int k) {
    if (k == 0 || string.isBlank()) {
      return 0;
    }

    int[] count = new int[26];
    int left = 0;
    int distinct = 0;
    int maxLength = 0;

    for (int right = 0; right < string.length(); right++) {
      int ch = string.charAt(right) - 'a';
      if (count[ch] == 0) {
        distinct++;
      }
      count[ch]++;
      // shrink window if distinct > k
      while (distinct > k) {
        int leftChar = string.charAt(left) - 'a';
        count[leftChar]--;
        if (count[leftChar] == 0) {
          distinct--;
        }
        left++;
      }
      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // 3
    System.out.println(lengthOfLongestSubstringKDistinct("aa", 1)); // 2
  }
}
