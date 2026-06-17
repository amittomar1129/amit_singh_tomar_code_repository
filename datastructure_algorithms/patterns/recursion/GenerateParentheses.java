package datastructure_algorithms.patterns.recursion;

import java.util.ArrayList;
import java.util.List;

//  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//  Example 1:
//  Input: n = 3
//  Output: ["((()))","(()())","(())()","()(())","()()()"]
//  Explanation: Example 1: The solution set contains all valid combinations of 3 pairs of parentheses.
//
//      Example 2:
//  Input: n = 1
//  Output: ["()"]
//  Explanation: Example 2: The solution set contains all valid combinations of 1 pair of parentheses.
//
//      Example 3:
//  Input: n = 2
//  Output: ["(())","()()"]
//  Explanation: Example 3: The solution set contains all valid combinations of 2 pairs of parentheses.

//  Solution: We use backtracking and only build valid prefixes by ensuring at any time closing
//  parentheses never exceed opening ones.
//  Time -> Catalan Number ? O(4^n / sqrt(n))
//  Space -> O(n) recursion depth

public class GenerateParentheses {

  public static List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrack(result, "", 0, 0, n);
    return result;
  }

  private static void backtrack(List<String> result, String current, int open, int close, int n) {
    // If the current string is complete
    if (current.length() == 2 * n) {
      result.add(current);
      return;
    }
    // Try adding '('
    if (open < n) {
      backtrack(result, current + "(", open + 1, close, n);
    }
    // Try adding ')'
    if (close < open) {
      backtrack(result, current + ")", open, close + 1, n);
    }
  }

  public static void main(String[] args) {
    int n = 3;
    List<String> result = generateParenthesis(n);

    System.out.println("Well-formed parentheses: "+ result);
  }
}
