package datastructure_algorithms.patterns.strings.slidingwindow;

//  You are given a string s and an integer k. You can choose any character of the string and change
//  it to any other uppercase English character. You can perform this operation at most k times.
//  Return the length of the longest substring containing the same letter you can get after performing the above operations.
//
//      Example 1:
//  Input: s = 'ABAB', k = 2
//  Output: 4
//  Explanation: Replace the two 'A's with two 'B's to make s = 'BBBB'.
//
//      Example 2:
//  Input: s = 'AABABBA', k = 1
//  Output: 4
//  Explanation: Replace the 'B' with 'A' to make s = 'AAAABBA'.
//
//  Example 3:
//  Input: s = 'ABBB', k = 2
//  Output: 4
//  Explanation: Replace the two 'B's with 'A's to make s = 'AAAA'.

//  Solution: I use a sliding window.
//  For each window, I only need to replace all characters except the most frequent one.
//  If replacements exceed k, I shrink the window.
//  Time	O(n)
//  Space	O(1)
public class LongestRepeatingCharacter {
  public static int characterReplacement(String s, int k) {
    int[] count = new int[26];

    int left = 0;
    int maxFreq = 0;
    int maxLen = 0;
//  AABABBA
    for (int right = 0; right < s.length(); right++) {
      char c = s.charAt(right);
      count[c - 'A']++;
      // track max frequency in current window
      maxFreq = Math.max(maxFreq, count[c - 'A']);
      // if replacements needed > k, shrink window
      if (right - left + 1 - maxFreq > k) {
        count[s.charAt(left) - 'A']--;
        left++;
      }

      maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
  }

  public static void main(String[] args) {
    System.out.println(characterReplacement("AABABBA", 1)); // 4
    System.out.println(characterReplacement("ABAB", 2));    // 4
  }
}
