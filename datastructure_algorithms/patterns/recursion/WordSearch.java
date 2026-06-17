package datastructure_algorithms.patterns.recursion;

//  Given an m x n board and a word, find if the word exists in the grid. The word can be
// constructed from letters
//  of sequentially adjacent cells, where 'adjacent' cells are horizontally or vertically
// neighboring.
//  The same letter cell may not be used more than once.
//
//  Example 1:
//  Input: [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], 'ABCCED'
//  Output: true
//  Explanation: Example 1: Return true because the word 'ABCCED' exists in the grid.
//
//  Example 2:
//  Input: [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], 'SEE'
//  Output: true
//  Explanation: Example 2: Return true because the word 'SEE' exists in the grid.
//
//  Example 3:
//  Input: [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], 'ABCB'
//  Output: false
//  Explanation: Example 3: Return false because the word 'ABCB' does not exist in the grid.

//  Solution: I try to start DFS from every cell. If characters match, I explore all directions
// while
//  marking cells visited and backtracking when needed.
//  Time -> O(m × n × 4^L), L = word length
//  Space -> O(L) recursion stack

public class WordSearch {

  public static boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;

    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (dfs(board, word, r, c, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean dfs(char[][] board, String word, int r, int c, int index) {
    // all characters matched
    if (index == word.length()) {
      return true;
    }
    // boundary or mismatch
    if (r < 0
        || c < 0
        || r >= board.length
        || c >= board[0].length
        || board[r][c] != word.charAt(index)) {
      return false;
    }

    // mark visited
    char temp = board[r][c];
    board[r][c] = '#';

    // explore all 4 directions
    boolean found =
        dfs(board, word, r + 1, c, index + 1)
            || dfs(board, word, r - 1, c, index + 1)
            || dfs(board, word, r, c + 1, index + 1)
            || dfs(board, word, r, c - 1, index + 1);

    // backtrack
    board[r][c] = temp;

    return found;
  }

  public static void main(String[] args) {
    char[][] board = {
      {'A', 'B', 'C', 'E'},
      {'S', 'F', 'C', 'S'},
      {'A', 'D', 'E', 'E'}
    };

    System.out.println(exist(board, "ABCCED")); // true
    System.out.println(exist(board, "SEE")); // true
    System.out.println(exist(board, "ABCB")); // false
  }
}
