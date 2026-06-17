package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Solution: Since it’s substring, we must reset DP to zero on mismatch. That’s the key difference
// from subsequence.”
//  Time	O(n × m)
//  Space	O(min(n, m))

public class LongestCommonSubstring {

  public static String longestCommonSubstring(String s1, String s2) {
    int m = s1.length();
    int n = s2.length();

    // Always use smaller string for DP array
    if (n > m) {
      return longestCommonSubstring(s2, s1);
    }

    int[] dp = new int[n + 1];
    int maxLength = 0;
    int end = 0;

    for (int i = 1; i <= m; i++) {
      // iterate backwards to avoid overwriting dp[j-1]
      for (int j = n; j > 0; j--) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[j] = dp[j - 1] + 1;
          if (dp[j] > maxLength) {
            maxLength = dp[j];
            end = i;
          }
        } else {
          dp[j] = 0;
        }
      }
    }

    return s1.substring(end - maxLength, end);
  }

  public static void main(String[] args) {
    String s1 = "mmkkaasasnjakozzp";
    String s2 = "amitaoasasnjakolpqi";

    System.out.println(sol(s1, s2));
  }

  public static String sol(String s1, String s2) {
    int m = s1.length();
    int n = s2.length();

    if (n > m) {
      return sol(s2, s1);
    }

    int[] dp = new int[n+1];
    int maxLength = 0;
    int end = 0;

    for(int i = 1; i < m; i++) {
      for (int j = n; j > 0; j--) {
        if (s1.charAt(i-1) == s2.charAt(j-1)) {
          dp[j] = dp[j-1] + 1;
          if(dp[j] > maxLength) {
            maxLength = dp[j];
            end = i;
          }

        } else {
            dp[j] = 0;
        }
      }
    }

    return s1.substring(end-maxLength, end);
  }






















}
