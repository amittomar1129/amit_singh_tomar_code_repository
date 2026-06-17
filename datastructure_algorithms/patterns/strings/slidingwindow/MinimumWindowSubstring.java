package datastructure_algorithms.patterns.strings.slidingwindow;

//  Given a string S and a string T, find the minimum window in S which will contain
//  all the characters in T in complexity O(n).
//
//  Example 1:
//  Input: S = 'ADOBECODEBANC', T = 'ABC'
//  Output: 'BANC'
//  Explanation: The minimum window substring 'BANC' includes all characters 'A', 'B', and 'C' from
// string T.
//
//      Example 2:
//  Input: S = 'a', T = 'a'
//  Output: 'a'
//  Explanation: The minimum window substring 'a' includes only character 'a' from string T.
//
//      Example 3:
//  Input: S = 'a', T = 'aa'
//  Output: ''
//  Explanation: There is no window that contains all characters in 'aa' from string T.

//  Solution: I use a sliding window with frequency counting.
//  I expand the window to include all required characters, then shrink it to get the minimum size,
// ensuring O(n) time.
//  Time	O(n)
//  Space	O(1) (fixed size array)

public class MinimumWindowSubstring {

  public static String minWindow(String string, String t) {
    if (string.isBlank() || t.isBlank()) return "";

    int[] count = new int[26];

    // Count frequency of characters in t
    for (char c : t.toCharArray()) {
      count[c - 'A']++;
    }

    int left = 0;
    int start = 0;
    int needed = t.length();
    int minLen = Integer.MAX_VALUE;

    // RIGHT pointer controlled by for-loop
    for (int right = 0; right < string.length(); right++) {
      char ch = string.charAt(right);
      if (count[ch - 'A'] > 0) {
        needed--;
      }
      count[ch - 'A']--;

      // Try to shrink window from left
      while (needed == 0) {
        if (right - left + 1 < minLen) {
          minLen = right - left + 1;
          start = left;
        }
        char leftChar = string.charAt(left);
        count[leftChar - 'A']++;
        if (count[leftChar - 'A'] > 0) {
          needed++;
        }
        left++;
      }
    }

    return minLen == Integer.MAX_VALUE ? "" : string.substring(start, start + minLen);
  }

  public static void main(String[] args) {
    System.out.println(minWindow("ADOBECODEBANC", "ABC"));
  }
}
