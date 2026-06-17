package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Given two strings text1 and text2, return the length of their longest common subsequence.
//  A subsequence of a string is a new string generated from the original string with some
//  characters(can be none) deleted without changing the relative order of the remaining characters.
//  (eg, 'ace' is a subsequence of 'abcde' while 'aec' is not). A common subsequence of two strings
//  is a subsequence that is common to both strings.

//  You are given two strings:
//  text1, text2
//  You must find the length of the longest subsequence that appears in both strings.
//  Subsequence:
//  Characters must stay in order.
//  Characters do not need to be contiguous.

//  Example 1:
//  Input: ["abcde","ace"]
//  Output: 3
//  Explanation: The longest common subsequence is 'ace' and its length is 3.
//
//  Example 2:
//  Input: ["abc","abc"]
//  Output: 3
//  Explanation: The longest common subsequence is 'abc' and its length is 3.

//  Solution: Since dp[i][j] only depends on the previous row and the current row, we can compress
// the
//  DP table into two 1D arrays, reducing space from O(m×n) to O(n)
//  dp[i][j] = length of LCS between text1[0..i-1] and text2[0..j-1]

//  DP Formula:
//  Case 1: Characters match
//  if text1[i-1] == text2[j-1]:
//      dp[i][j] = 1 + dp[i-1][j-1]
//  We include this character in LCS.
//
//  Case 2: Characters do NOT match
//  dp[i][j] = max(
//      dp[i-1][j],   // skip char from text1
//      dp[i][j-1]    // skip char from text2
//  )

//  Optimized:
//  If characters match:
//  curr[j] = 1 + prev[j-1]
//
//  Else:
//  curr[j] = max(prev[j], curr[j-1])

//  Time	O(m × n)
//  Space	O(n)

public class LongestCommonSubsequence {

  public static int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();

    int[] dp = new int[n + 1];

    for (int i = 1; i <= m; i++) {

      int prev = 0; // dp[i-1][j-1]

      for (int j = 1; j <= n; j++) {

        int temp = dp[j];

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[j] = 1 + prev;
        } else {
          dp[j] = Math.max(dp[j], dp[j - 1]);
        }

        prev = temp;
      }
    }

    return dp[n];
  }

  //  Time	O(m × n)
  //  Space	O(m × n)
  public static int longestCommonSubsequence2DSolution(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[m][n];
  }

  //  Time	O(m × n)
  //  Space	O(m × n)
  public static String getLongestCommonSubsequence(String text1, String text2) {

    int m = text1.length();
    int n = text2.length();

    int[][] dp = new int[m + 1][n + 1];

    // Build DP table
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // Reconstruct LCS
    StringBuilder sb = new StringBuilder();

    int i = m, j = n;

    while (i > 0 && j > 0) {

      if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
        sb.append(text1.charAt(i - 1));
        i--;
        j--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }

    return sb.reverse().toString();
  }

  //  Time	O(m × n)
  //  Space	O(m × n)
  public static String shortestCommonSupersequence(String text1, String text2) {

    int m = text1.length();
    int n = text2.length();

    int[][] dp = new int[m + 1][n + 1];

    // Step 1: Build LCS table
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // Step 2: Build SCS using backtracking
    StringBuilder sb = new StringBuilder();
    int i = m, j = n;

    while (i > 0 && j > 0) {

      if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
        sb.append(text1.charAt(i - 1));
        i--;
        j--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        sb.append(text1.charAt(i - 1));
        i--;
      } else {
        sb.append(text2.charAt(j - 1));
        j--;
      }
    }

    // Append remaining characters
    while (i > 0) {
      sb.append(text1.charAt(i - 1));
      i--;
    }

    while (j > 0) {
      sb.append(text2.charAt(j - 1));
      j--;
    }

    return sb.reverse().toString();
  }

  //  Return the number of distinct subsequences of s which equal t.
  //  Time	O(m × n)
  //  Space	O(m × n)
  public static int numDistinct(String s, String t) {
    int m = s.length();
    int n = t.length();

    long[][] dp = new long[m + 1][n + 1];

    // Empty string t can always be formed
    for (int i = 0; i <= m; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return (int) dp[m][n];
  }

  //  Find the length of the longest subsequence that appears at least twice.
  //  The two subsequences must use different indices.
  //
  // Order must be preserved
  //  Time  O(n²)
  //  Space O(n²)

  public static int longestRepeatingSubsequence(String s) {

    int n = s.length();
    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {

        if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[n][n];
  }

  public static String getLongestRepeatingSubsequence(String s) {

    int n = s.length();
    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {

        if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // Backtrack to build subsequence
    StringBuilder sb = new StringBuilder();

    int i = n, j = n;

    while (i > 0 && j > 0) {

      if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
        sb.append(s.charAt(i - 1));
        i--;
        j--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }

    return sb.reverse().toString();
  }

  //  Time: O(n²)
  //  Space: O(n²)

  public static int longestPalindromeSubsequence(String s) {
    String rev = new StringBuilder(s).reverse().toString();

    int n = s.length();
    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {

        if (s.charAt(i - 1) == rev.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[n][n];
  }

  //  Time: O(n²)
  //  Space: O(n²)

  public static String getLongestPalindromeSubsequence(String s) {
    String rev = new StringBuilder(s).reverse().toString();

    int n = s.length();
    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {

        if (s.charAt(i - 1) == rev.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // Backtrack to build LPS
    StringBuilder sb = new StringBuilder();

    int i = n, j = n;

    while (i > 0 && j > 0) {

      if (s.charAt(i - 1) == rev.charAt(j - 1)) {
        sb.append(s.charAt(i - 1));
        i--;
        j--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }

    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    String text1 = "abcde";
    String text2 = "ace";

    int result = longestCommonSubsequence(text1, text2);
    System.out.println("LCS length (O(n) space) -> " + result);

    String result1 = getLongestCommonSubsequence(text1, text2);
    System.out.println("LCS -> " + result1);

    String result2 = shortestCommonSupersequence("abac", "cab");
    System.out.println("Shortest common super sequence -> " + result2);

    int result3 = numDistinct("rabbbit", "rabbit");
    System.out.println("LCS -> " + result3);

    System.out.println(
        "longestRepeatingSubsequence -> " + longestRepeatingSubsequence("AAPDRCDBBT"));
    System.out.println(
        "getLongestRepeatingSubsequence -> " + getLongestRepeatingSubsequence("AAPDRCDBBT"));
    System.out.println(
        "longestPalindromeSubsequence -> " + longestPalindromeSubsequence("ABDCBTAP"));
    System.out.println(
        "getLongestPalindromeSubsequence -> " + getLongestPalindromeSubsequence("ABDCBTAP"));
  }

  public static String sol(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    int i = m, j = n;
    while (i > 0 && j > 0) {
      if (text1.charAt(i-1) == text2.charAt(j-1)) {
        sb.append(text1.charAt(i-1));
        i--;
        j--;
      } else if (dp[i-1][j] > dp[i][j-1]) {
        sb.append(text1.charAt(i-1));
        i--;
      } else {
        sb.append(text2.charAt(j-1));
        j--;
      }
    }

    while (i > 0) {
      sb.append(text1.charAt(i-1));
      i--;
    }
    while (j > 0) {
      sb.append(text2.charAt(j-1));
      j--;
    }

    return sb.reverse().toString();
  }
}