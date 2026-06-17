package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Given two words word1 and word2, find the minimum number of operations required to convert word1
// to word2.
//  You have the following 3 operations permitted on a word: Insert a character, Delete a character,
// Replace a character.

//  You are given two strings:
//  word1
//  word2
//  You can perform three operations:
//  Insert a character
//  Delete a character
//  Replace a character
//  Each operation costs 1 step.
//  Goal: Convert word1 into word2 using the minimum number of operations.
//
//  Example 1:
//  Input: word1 = "horse", word2 = "ros"
//  Output: 3
//  Explanation: Operation 1: replace 'h' with 'r'. Operation 2: remove 'o'. Operation 3: remove
// 'e'.
//
//  Example 2:
//  Input: word1 = "intention", word2 = "execution"
//  Output: 5
//  Explanation: Operation 1: replace 'i' with 'e'. Operation 2: replace 'n' with 'x'. Operation 3:
// remove 't'. Operation 4: remove 'e'. Operation 5: insert 'c'.

//  Solution: At every point, we compare prefixes of the two words.
//  What is the minimum cost to convert the first i characters of word1 into the first j characters
// of word2?
//  dp[i][j] = minimum operations to convert word1[0..i-1] -> word2[0..j-1]
//  If word1 is empty
//  dp[0][j] = j   (insert j characters)
//  If word2 is empty
//  dp[i][0] = i   (delete i characters)

//  DP Formula:
//  If characters are equal:
//  word1[i-1] == word2[j-1]
//  dp[i][j] = dp[i-1][j-1]
//
//  If characters are different:
//  dp[i][j] = 1 + min(
//      dp[i-1][j],     // delete
//      dp[i][j-1],     // insert
//      dp[i-1][j-1]    // replace
//  )

//  Time -> O(m × n)
//  Space -> O(m × n)

public class EditDistance {

  public static int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    int[][] dp = new int[m + 1][n + 1];

    // Base cases
    for (int i = 0; i <= m; i++) {
      dp[i][0] = i;
    }

    for (int j = 0; j <= n; j++) {
      dp[0][j] = j;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] =
              1
                  + Math.min(
                      dp[i - 1][j - 1], // replace
                      Math.min(
                          dp[i - 1][j], // delete
                          dp[i][j - 1] // insert
                          ));
        }
      }
    }

    return dp[m][n];
  }

  public static void main(String[] args) {
    String word1 = "horse";
    String word2 = "ros";

    int result = sol(word1, word2);
    System.out.println("Minimum Edit Distance (O(n) space) -> " + result);
  }

  public static int sol(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    if (n > m) {
      return sol(word2, word1);
    }

    int[][] dp = new int[m + 1][n + 1];
    for(int i = 0; i <= m; i++) {
      dp[i][0] = i;
    }
    for(int i = 0; i <= n; i++) {
      dp[0][i] = i;
    }


    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
        }
      }
    }

    return dp[m][n];
  }
}
