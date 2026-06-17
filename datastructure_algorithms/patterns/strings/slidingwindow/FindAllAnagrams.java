package datastructure_algorithms.patterns.strings.slidingwindow;

//  Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
//  Strings consists of lowercase English letters only.
//
//  Example 1:
//  Input: s = 'cbaebabacd', p = 'abc'
//  Output: [0, 6]
//  Explanation: The substring with start index = 0 is "cba", which is an anagram of "abc". The
// substring with start index = 6 is "bac", which is an anagram of "abc".
//
//  Example 2:
//  Input: s = 'abab', p = 'ab'
//  Output: [0, 1, 2]
//  Explanation: The substring with start index = 0 is "ab", which is an anagram of "ab". The
// substring with start index = 1 is "ba", which is an anagram of "ab". The substring with start
// index = 2 is "ab", which is an anagram of "ab".
//
//  Example 3:
//  Input: s = 'acdcaeccde', p = 'c'
//  Output: [1, 3, 6, 7]
//  Explanation: The substrings with start indices = 1, 3, 6, and 7 are "c", which are anagrams of
// "c".

//  Solution: I use a fixed-size sliding window with a frequency array.
//  When the window size equals the pattern length and all frequencies match, I record the index.
//  Time	O(n)
//  Space	O(1) (26 letters)

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagrams {

  public static List<Integer> findAnagrams(String string, String pattern) {
    List<Integer> result = new ArrayList<>();
    if (string.length() < pattern.length()) return result;

    int[] count = new int[26];

    // Step 1: count frequency of p
    for (char c : pattern.toCharArray()) {
      count[c - 'a']++;
    }

    int left = 0;
    int needed = pattern.length();

    // Step 2: sliding window
    for (int right = 0; right < string.length(); right++) {
      char c = string.charAt(right);
      if (count[c - 'a'] > 0) {
        needed--;
      }
      count[c - 'a']--;
      // window size equals p length
      if (right - left + 1 == pattern.length()) {
        if (needed == 0) {
          result.add(left);
        }
        // remove left character
        char leftChar = string.charAt(left);
        if (count[leftChar - 'a'] >= 0) {
          needed++;
        }
        count[leftChar - 'a']++;
        left++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(findAnagrams("cbaebabacd", "abc")); // [0, 6]
    System.out.println(findAnagrams("abab", "ab")); // [0, 1, 2]
  }
}
