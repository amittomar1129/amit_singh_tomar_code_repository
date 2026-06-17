package datastructure_algorithms.patterns.dynamicprogrammimg.probability;

//  A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint
//  to the generator where it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
//  Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be
//  obtained with exact n rolls. Two sequences are considered different if at least one element differs from each other.
//  Since the answer may be too large, return it modulo 10^9 + 7.

//  You are given:
//  n ? total number of dice rolls
//  A standard die ? values 1..6
//  rollMax[i] ? maximum number of consecutive rolls of face i+1
//  Goal:
//  Count distinct sequences of length n
//  Constraint: face i cannot appear more than rollMax[i] times consecutively
//  Return result modulo 10^9 + 7

//  Example 1:
//  Input: n = 2, rollMax = [1,1,2,2,2,3]
//  Output: 34
//  Explanation: There are 36 possible sequences of length 2 from a 6-sided die. The sequences with consecutive rolls
//  exceeding the limits are removed, leaving 34 valid sequences.
//
//  Example 2:
//  Input: n = 2, rollMax = [1,1,1,1,1,1]
//  Output: 30
//  Explanation: Each number can only appear once consecutively, so sequences with two identical numbers are invalid.
//  There are 6 sequences with identical numbers, so 36 - 6 = 30 valid sequences.


//  Time Complexity -> O(n * 6 * maxRoll * 6)
//  maxRoll <= 15
//  Effectively O(n)
//  Space Complexity -> O(6 * maxRoll)
//  Constant space

public class DiceRollSimulation {

  private static final int MOD = 1_000_000_007;

  public static int dieSimulator(int n, int[] rollMax) {
    // dp[j][k] -> number of sequences ending with face i repeated k times
    long[][] dp = new long[6][16];

    // Base case: first roll
    for (int i = 0; i < 6; i++) {
      dp[i][1] = 1;
    }

    for (int roll = 2; roll <= n; roll++) {
      long[][] next = new long[6][16];

      for (int j = 0; j < 6; j++) {
        for (int k = 1; k <= rollMax[j]; k++) {
          long curr = dp[j][k];
          if (curr == 0) {
            continue;
          }

          // Case 1: same face
          if (k < rollMax[j]) {
            next[j][k + 1] = (next[j][k + 1] + curr) % MOD;
          }

          // Case 2: different face
          for (int x = 0; x < 6; x++) {
            if (x != j) {
              next[x][1] = (next[x][1] + curr) % MOD;
            }
          }
        }
      }
      dp = next;
    }

    long result = 0;
    for (int j = 0; j < 6; j++) {
      for (int k = 1; k <= rollMax[j]; k++) {
        result = (result + dp[j][k]) % MOD;
      }
    }
    return (int) result;
  }

  public static void main(String[] args) {
    int[] rollMax1 = {1, 1, 2, 2, 2, 3};
    System.out.println(dieSimulator(2, rollMax1)); // 34

    int[] rollMax2 = {1, 1, 1, 1, 1, 1};
    System.out.println(dieSimulator(3, rollMax2)); // 150
  }
}
