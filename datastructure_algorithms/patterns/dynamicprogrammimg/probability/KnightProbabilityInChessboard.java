package datastructure_algorithms.patterns.dynamicprogrammimg.probability;

//  On an N x N chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
//  The rows and columns are 0-indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
//  A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
//  then one square in an orthogonal direction. Each time the knight is to move, it chooses one of eight possible moves
//  uniformly at random (even if the piece would go off the chessboard) and moves there.
//  The knight continues moving until it has made exactly K moves or has moved off the chessboard.
//  Return the probability that the knight remains on the board after it has stopped moving.
//
//  Example 1:
//  Input: N = 3, K = 2, r = 0, c = 0
//  Output: 0.0625
//  Explanation: There are two moves the knight can make from (0,0) that stay on the board.
//  From each of those positions, there are two moves that stay on the board. So the probability is (2/8) * (2/8) = 0.0625.
//
//  Example 2:
//  Input: N = 1, K = 0, r = 0, c = 0
//  Output: 1.0
//  Explanation: Since the knight does not move, it remains on the board with probability 1.
//
//  Example 3:
//  Input: N = 8, K = 30, r = 6, c = 4
//  Output: 0.00019
//  Explanation: After 30 moves starting from position (6,4) on an 8x8 board, the probability that the knight remains on the board is approximately 0.00019.


//  Time Complexity -> O(K * N * N * 8) ? simplifies to O(K * N^2)
//  Space Complexity -> O(N^2)

public class KnightProbabilityInChessboard {

  private static final int[][] DIRS = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2},
      {-1, -2}};

  public static double knightProbability(int N, int K, int r, int c) {
    double[][] prev = new double[N][N];
    prev[r][c] = 1.0;

    for (int step = 1; step <= K; step++) {
      double[][] curr = new double[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (prev[i][j] == 0) {
            continue;
          }

          for (int[] d : DIRS) {
            int ni = i + d[0];
            int nj = j + d[1];

            if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
              curr[ni][nj] += prev[i][j] / 8.0;
            }
          }
        }
      }
      prev = curr;
    }

    double result = 0.0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        result += prev[i][j];
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(knightProbability(3, 2, 0, 0)); // ~0.0625
    System.out.println(knightProbability(8, 30, 6, 4));
  }
}
