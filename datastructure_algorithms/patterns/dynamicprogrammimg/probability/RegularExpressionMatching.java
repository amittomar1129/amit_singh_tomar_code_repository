package datastructure_algorithms.patterns.dynamicprogrammimg.probability;

//  Given an input string (s) and a pattern (p), implement regular expression matching with support for 'and'. 'And' is
//  defined as follows: 'and' of two characters is true if both characters are the same, and false otherwise.
//  The matching should cover the entire input string (not partial).
//
//  Example 1:
//  Input: s = 'aa', p = 'a'
//  Output: false
//  Explanation: Explanation: 'a' does not match 'aa'.
//
//  Example 2:
//  Input: s = 'aa', p = 'aa'
//  Output: true
//  Explanation: Explanation: 'aa' matches 'aa'.
//
//  Example 3:
//  Input: s = 'aa', p = 'a*'
//  Output: true
//  Explanation: Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes 'aa'.

//  s = "aa", p = "a"      -> false
//  s = "aa", p = "a*"     -> true
//  s = "ab", p = ".*"     -> true
//  s = "aab", p = "c*a*b" -> true

//  Solution: Use DP where dp[i][j] represents whether first i characters of s match first j characters of p,
//  carefully handling * as zero or multiple occurrences. This DP avoids exponential backtracking and guarantees polynomial time,
//  unlike naive recursion.
//  Time	O(m * n)
//  Space	O(m * n)


public class RegularExpressionMatching {

  public static boolean isMatch(String string, String pattern) {
    int m = string.length();
    int n = pattern.length();

    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    // Handle patterns like a*, a*b*, a*b*c*
    for (int i = 2; i <= n; i++) {
      if (pattern.charAt(i - 1) == '*') {
        dp[0][i] = dp[0][i - 2];
      }
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (pattern.charAt(j - 1) == '.' ||
            pattern.charAt(j - 1) == string.charAt(i - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (pattern.charAt(j - 1) == '*') {
          // zero occurrence
          dp[i][j] = dp[i][j - 2];
          // one or more occurrence
          if (pattern.charAt(j - 2) == '.' ||
              pattern.charAt(j - 2) == string.charAt(i - 1)) {
            dp[i][j] = dp[i][j] || dp[i - 1][j];
          }
        }
      }
    }
    return dp[m][n];
  }

  public static void main(String[] args) {
    System.out.println(isMatch("aa", "a*"));      // true
    System.out.println(isMatch("ab", ".*"));      // true
    System.out.println(isMatch("aab", "c*a*b"));  // true
    System.out.println(isMatch("miss", "mis*"));  // true
  }
}
