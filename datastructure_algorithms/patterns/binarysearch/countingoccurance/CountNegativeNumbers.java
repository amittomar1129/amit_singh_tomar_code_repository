package datastructure_algorithms.patterns.binarysearch.countingoccurance;

//  Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise,
//  return the number of negative numbers in grid.
//
//  Example 1:
//  Input: [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
//  Output: 8
//  Explanation: There are 8 negatives numbers in the matrix.
//
//      Example 2:
//  Input: [[3,2],[1,0]]
//  Output: 0
//  Explanation: There are no negative numbers in the matrix.
//
//  Example 3:
//  Input: [[1,-1],[-1,-1]]
//  Output: 3
//  Explanation: There are 3 negative numbers in the matrix.

//  Solution: Because rows and columns are sorted, I start from the top-right and eliminate one row or column
//  at each step, achieving O(m + n) time.
//  Time	O(m + n)
//  Space	O(1)


public class CountNegativeNumbers {

  public static int countNegatives(int[][] input) {
    int m = input.length;

    int row = 0;
    int col = input[0].length - 1;
    int count = 0;

    while (row < m && col >= 0) {
      if (input[row][col] < 0) {
        count = count + (m - row);
        col--; // move left
      } else {
        row++; // move down
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int[][] grid = {
        {4, 3, 2, -1},
        {3, 2, 1, -1},
        {1, 1, -1, -2},
        {-1, -1, -2, -3}
    };

    System.out.println(sol(grid)); // Output -> 8
  }

  public static int sol(int[][] input) {
    int m = input.length;
    int n = input[0].length;

    int row = 0;
    int col = n - 1;
    int count = 0;

    while (row < m && col >= 0) {
      if (input[row][col] < 0) {
        count += m - row;
        col--;
      } else {
        row++;
      }
    }
    return count;
  }




















}
