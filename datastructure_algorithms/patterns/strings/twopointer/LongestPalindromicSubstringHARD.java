package datastructure_algorithms.patterns.strings.twopointer;

//  Given a string s, return the longest palindromic substring in s.
//
//  Example 1:
//  Input: "babad"
//  Output: "bab"
//  Explanation: The longest palindromic substring in s is "bab".
//
//  Example 2:
//  Input: "cbbd"
//  Output: "bb"
//  Explanation: The longest palindromic substring in s is "bb".
//
//  Example 3:
//  Input: "a"
//  Output: "a"
//  Explanation: The longest palindromic substring in s is "a".

//  Solution: Handles both odd/even length palindromes.
//  time O(n²)
//  space O(1)

public class LongestPalindromicSubstringHARD {

//  "babad"
  public static String longestPalindrome(String input) {
    int start = 0, end = 0;
    for (int i = 0; i < input.length(); i++) {
      int len1 = expandFromCenter(input, i, i);     // odd length
      int len2 = expandFromCenter(input, i, i + 1); // even length
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1) / 2;  // update start index
        end = i + len / 2;          // update end index
      }
    }
    return input.substring(start, end + 1);
  }

  private static int expandFromCenter(String input, int left, int right) {
    while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
      left--;
      right++;
    }
    return right - left - 1; // length of palindrome
  }

  public static String longestPalindromeManacher(String s) {
    if (s.isBlank()) {
      return "";
    }
    // Step 1: Preprocess
    String string = preprocess(s); // ^#b#a#b#a#d#$
    int[] array = new int[string.length()];
    int center = 0, centerIndex = 0, right = 0, maxLen = 0;
    // Step 2: Expand around center
    for (int i = 1; i < string.length() - 1; i++) {

      int mirror = 2 * center - i; // mirror of i around C
      if (i < right) {
        array[i] = Math.min(right - i, array[mirror]);
      }
      // Expand palindrome centered at i
      while (string.charAt(i + (1 + array[i])) == string.charAt(i - (1 + array[i]))) {
        array[i]++;
      }
      // Update C and R if palindrome expanded past R
      if (right < i + array[i]) {
        center = i;
        right = i + array[i];
      }
      // Track maximum length
      if (maxLen < array[i]) {
        maxLen = array[i];
        centerIndex = i;
      }
    }
    // Step 3: Extract substring from original string
    int start = (centerIndex - maxLen) / 2;
    return s.substring(start, start + maxLen);
  }

  private static String preprocess(String s) {
    StringBuilder sb = new StringBuilder();
    sb.append('^');
    for (char c : s.toCharArray()) {
      sb.append('#').append(c);
    }
    sb.append("#$");
    return sb.toString();
  }

  public static void main(String[] args) {
    String s = "babad";
    System.out.println(longestPalindrome(s)); // Output -> "bab" or "aba"

//      Time Complexity: O(n)
//      Space Complexity: O(n)
//      Manacher’s Algorithm:
//      Problem:
//      Odd palindrome: "aba"
//      Even palindrome: "abba"
//      Manacher removes this difference.
//      After inserting #,
//      #a#b#a#
//      #a#b#b#a#
//      Every palindrome has a single center. Even and odd palindromes are handled the same.
//    System.out.println(longestPalindromeManacher(s));
  }

}
