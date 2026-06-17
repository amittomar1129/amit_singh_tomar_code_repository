package datastructure_algorithms.patterns.strings.slidingwindow;

import java.util.HashSet;
import java.util.Set;

//  Given a string s, find the length of the longest substring without repeating characters.
//
//  Example 1:
//  Input: 'abcabcbb'
//  Output: 3
//  Explanation: The answer is 'abc', with the length of 3.
//
//  Example 2:
//  Input: 'bbbbb'
//  Output: 1
//  Explanation: The answer is 'b', with the length of 1.
//
//  Example 3:
//  Input: 'pwwkew'
//  Output: 3
//  Explanation: The answer is 'wke', with the length of 3. Notice that the answer must be a substring, 'pwke' is a
//  subsequence and not a substring.

//  Solution: I use a sliding window with two pointers.
//  A set tracks characters in the window.
//  If a duplicate appears, I shrink the window from the left until it becomes valid again.
//  Time	O(n)
//  Space	O(1) (max 128 chars)

public class LongestSubstring {

  public static int lengthOfLongestSubstring(String string) {
    Set<Character> set = new HashSet<>();
    int left = 0;
    int maxLen = 0;
    // right pointer moves using for loop
    for (int right = 0; right < string.length(); right++) {
      char currChar = string.charAt(right);
      // If duplicate found, remove from left
      while (set.contains(currChar)) {
        set.remove(string.charAt(left));
        left++;
      }
      set.add(currChar);
      maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
    System.out.println(lengthOfLongestSubstring("bbbbb"));    // 1
    System.out.println(lengthOfLongestSubstring("pwwkew"));   // 3
  }
}
