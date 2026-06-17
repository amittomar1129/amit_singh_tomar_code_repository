package datastructure_algorithms.patterns.binarysearch.countingoccurance;

//  Write an efficient algorithm that searches for a target value in an m x n integer matrix.
//  The matrix has the following properties: Integers in each row are sorted in ascending order from left to right.
//  Integers in each column are sorted in ascending order from top to bottom.
//
//  Example 1:
//  Input: matrix = [[1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17, 24], [18, 21, 23, 26, 30]], target = 5
//  Output: true
//  Explanation: Return true because 5 is present in the matrix.
//
//      Example 2:
//  Input: matrix = [[1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17, 24], [18, 21, 23, 26, 30]], target = 20
//  Output: false
//  Explanation: Return false because 20 is not in the matrix.
//
//      Example 3:
//  Input: matrix = [[-1, 3]], target = 3
//  Output: true
//  Explanation: Return true because 3 is present in the matrix.

//  Solution: From certain corners of the matrix, you can eliminate a full row or column at every step.
//  The best starting point is: Top-right corner (or bottom-left)
//  Traversal:
//  15 -> left
//  11 -> left
//  7  -> left
//  4  -> down
//  5  -> found
//  Time	O(m + n)
//  Space	O(1)

public class SearchA2DMatrix {

  public static boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;

    int row = 0;
    int col = matrix[0].length - 1;

    while (row < m && col >= 0) {
      if (matrix[row][col] == target) {
        return true;
      } else if (matrix[row][col] > target) {
        col--;      // move left
      } else {
        row++;      // move down
      }
    }

    return false;
  }

  public static void main(String[] args) {
    int[][] matrix = {
        {1, 4, 7, 11, 15},
        {2, 5, 8, 12, 19},
        {3, 6, 9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}
    };

    System.out.println(searchMatrix(matrix, 5));  // true
    System.out.println(searchMatrix(matrix, 20)); // false
  }


}
