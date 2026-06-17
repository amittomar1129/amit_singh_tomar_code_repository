package datastructure_algorithms.patterns.recursion;

//  Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
//  find all unique combinations in candidates where the candidate numbers sums to target.
//  The same repeated number may be chosen from candidates unlimited number of times.
//
//  Example 1:
//  Input: candidates = [2,3,6,7], target = 7
//  Output: [[7],[2,2,3]]
//  Explanation: 2 and 3 are candidates, and 2+2+3 = 7. Note that 7 is also a candidate.
//
//  Example 2:
//  Input: candidates = [2,3,5], target = 8
//  Output: [[2,2,2,2],[2,3,3],[3,5]]
//  Explanation: 2, 3, and 5 are candidates, and 2+2+2+2 = 8, 2+3+3 = 8, and 3+5 = 8.
//
//  Example 3:
//  Input: candidates = [2], target = 1
//  Output: []
//  Explanation: No combination is possible to sum up to the target.

//  Solution: We use backtracking, keeping track of remaining target and current combination.
//  We reuse elements by not moving the index forward, and we prune paths when the target becomes negative.
//  Time -> Exponential (backtracking)
//  Space -> O(target) recursion depth

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

  public static List<List<Integer>> combinationSum(int[] input, int target) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(input, target, 0, new ArrayList<>(), result);
    return result;
  }

  private static void backtrack(int[] input, int target, int index, ArrayList<Integer> current,
      List<List<Integer>> result) {
    // Base case: target achieved
    if (target == 0) {
      result.add(new ArrayList<>(current));
      return;
    }
    // If target becomes negative, stop
    if (target < 0) {
      return;
    }
    // Try all candidates starting from current index
    for (int i = index; i < input.length; i++) {
      current.add(input[i]);
      backtrack(input, target - input[i], i, current, result);
      current.remove(current.size() - 1); // backtrack
    }
  }

  public static void main(String[] args) {
    int[] candidates = {2, 3, 6, 7};
    int target = 7;

    List<List<Integer>> result = combinationSum(candidates, target);
    System.out.println(result);
  }
}
