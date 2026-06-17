package datastructure_algorithms.patterns.dynamicprogrammimg.probability;

//  Given an input string (s) and a pattern (p), implement wildcard pattern matching with
//  support for '?' and '*'. '?' Matches any single character. '*' Matches any sequence of
// characters
//  (including the empty sequence). The matching should cover the entire input string (not partial).
//
//  Example 1:
//  Input: aa a
//  Output: false
//  Explanation: 'a' does not match the entire input 'aa'.
//
//  Example 2:
//  Input: aa *
//  Output: true
//  Explanation: The '*' matches the entire input 'aa'.
//
//  Example 3:
//  Input: cb ?b
//  Output: true
//  Explanation: '?' matches 'c', and 'b' matches 'b'.

//  s = "aa",  p = "a"      -> false
//  s = "aa",  p = "*"      -> true
//  s = "cb",  p = "?a"     -> false
//  s = "adceb", p = "*a*b" -> true
//  s = "acdcb", p = "a*c?b" -> false

//  Time	O(m * n)
//  Space	O(m * n)

public class WildcardMatching {

  public static boolean isMatch(String string, String pattern) {
    int m = string.length();
    int n = pattern.length();

    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    // Handle leading '*'
    for (int i = 1; i <= n; i++) {
      if (pattern.charAt(i - 1) == '*') {
        dp[0][i] = dp[0][i - 1];
      }
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (pattern.charAt(j - 1) == '?' || pattern.charAt(j - 1) == string.charAt(i - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (pattern.charAt(j - 1) == '*') {
          dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
        }
      }
    }

    return dp[m][n];
  }

  public static void main(String[] args) {
    System.out.println(isMatch("aa", "*")); // true
    System.out.println(isMatch("cb", "?a")); // false
    System.out.println(isMatch("adceb", "*a*b")); // true
    System.out.println(isMatch("acdcb", "a*c?b")); // false
  }
}
