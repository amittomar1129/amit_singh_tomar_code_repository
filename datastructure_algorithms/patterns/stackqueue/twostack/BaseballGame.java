package datastructure_algorithms.patterns.stackqueue.twostack;

//  You are keeping score for a baseball game with strange rules. The game consists of several rounds, where the scores of
//  past rounds may affect future rounds' scores.
//  At the beginning of the game, you start with an empty record. You are given a list of strings ops, where ops[i] is
//  the ith operation you must apply to the record and is one of the following:
//  - An integer x - Record a new score of x.
//  - "+" - Record a new score that is the sum of the previous two scores.
//  - "D" - Record a new score that is double the previous score.
//  - "C" - Invalidate the previous score, removing it from the record.
//  Return the sum of all the scores on the record after applying all the operations.

//  Example 1:
//  Input: ["5","2","C","D","+"]
//  Output: 30
//  Explanation: Operation 1: Record 5, record is now [5] Operation 2: Record 2, record is now [5, 2] Operation 3: Invalidate previous score (2), record is now [5] Operation 4: Record double previous score (5 * 2 = 10), record is now [5, 10] Operation 5: Record sum of previous two scores (5 + 10 = 15), record is now [5, 10, 15] Sum of all scores is 5 + 10 + 15 = 30
//
//  Example 2:
//  Input: ["5","-2","4","C","D","9","+","+"]
//  Output: 27
//  Explanation: Operation 1: Record 5, record is now [5] Operation 2: Record -2, record is now [5, -2] Operation 3: Record 4, record is now [5, -2, 4] Operation 4: Invalidate previous score (4), record is now [5, -2] Operation 5: Record double previous score (-2 * 2 = -4), record is now [5, -2, -4] Operation 6: Record 9, record is now [5, -2, -4, 9] Operation 7: Record sum of previous two scores (9 + -4 = 5), record is now [5, -2, -4, 9, 5] Operation 8: Record sum of previous two scores (5 + 9 = 14), record is now [5, -2, -4, 9, 5, 14] Sum of all scores is 5 + -2 + -4 + 9 + 5 + 14 = 27
//
//  Example 3:
//  Input: ["1"]
//  Output: 1
//  Explanation: Operation 1: Record 1, record is now [1] Sum of all scores is 1

//  You are keeping a record of scores (like a stack).
//  Each operation modifies the record:
//  Operation                 	Meaning
//  "x"	                      Push integer x
//  "+"	                      Push sum of last two scores
//  "D"	                      Push double of last score
//  "C"	                      Remove last score
//  At the end, return the sum of all remaining scores.

//  Solution: “I simulate the score record using a stack since every operation depends on the most recent scores.”
//  Time: O(n)
//  Space: O(n)

import java.util.ArrayDeque;
import java.util.Deque;

public class BaseballGame {

  public static int calPoints(String[] ops) {
    Deque<Integer> stack = new ArrayDeque<>();

    for (String op : ops) {

      if (op.equals("+")) {
        int top = stack.pop();
        int sum = top + stack.peek();
        stack.push(top);
        stack.push(sum);
      } else if (op.equals("D")) {
        stack.push(2 * stack.peek());
      } else if (op.equals("C")) {
        stack.pop();
      } else {
        stack.push(Integer.parseInt(op));
      }
    }

    int total = 0;
    for (int score : stack) {
      total += score;
    }
    return total;
  }

  public static void main(String[] args) {

    String[] ops1 = {"5", "2", "C", "D", "+"};
    String[] ops2 = {"5", "-2", "4", "C", "D", "9", "+", "+"};

    System.out.println(calPoints(ops1)); // 30
    System.out.println(calPoints(ops2)); // 27
  }

}
