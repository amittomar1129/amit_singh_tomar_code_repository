package datastructure_algorithms.patterns.priorityqueue.slidingwindow;

//  Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//
//  Example 1:
//  Input: S = 'ADOBECODEBANC', T = 'ABC'
//  Output: 'BANC'
//  Explanation: The minimum window substring 'BANC' includes all characters 'A', 'B', and 'C' from string T.
//
//  Example 2:
//  Input: S = 'a', T = 'a'
//  Output: 'a'
//  Explanation: The minimum window substring 'a' includes only the character 'a' from string T.

//  Time	O(n)
//  Space	O(1) (since alphabet is fixed)

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

  public static String minWindow(String s, String t) {

    if (s.length() < t.length()) {
      return "";
    }

    Map<Character, Integer> need = new HashMap<>();
    for (char c : t.toCharArray()) {
      need.put(c, need.getOrDefault(c, 0) + 1);
    }

    int required = need.size();
    int formed = 0;

    Map<Character, Integer> window = new HashMap<>();

    int left = 0, right = 0;
    int minLen = Integer.MAX_VALUE;
    int start = 0;

    while (right < s.length()) {
      char c = s.charAt(right);
      window.put(c, window.getOrDefault(c, 0) + 1);

      if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
        formed++;
      }
      while (left <= right && formed == required) {
        if (right - left + 1 < minLen) {
          minLen = right - left + 1;
          start = left;
        }
        char leftChar = s.charAt(left);
        window.put(leftChar, window.get(leftChar) - 1);
        if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
          formed--;
        }
        left++;
      }
      right++;
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
  }

  public static void main(String[] args) {

    String s = "ADOBECODEBANC";
    String t = "ABC";

    System.out.println(minWindow(s, t));
  }
}
