package datastructure_algorithms.patterns.priorityqueue.construction;

//  Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other
//  are not the same. If possible, output any possible result. If not possible, return the empty string.
//
//  Example 1:
//  Input: S = 'aab'
//  Output: 'aba'
//  Explanation: In this case, 'aba' is also a valid answer.
//
//  Example 2:
//  Input: S = 'aaab'
//  Output: ''
//  Explanation: No solution exists.

//  Solution: “I always place the most frequent characters first, using a max heap.
//  By taking the top two characters each step, I ensure no two adjacent characters are the same.
//  If the max frequency exceeds (n+1)/2, it is impossible to rearrange.”
//  Time	O(n log 26) ? O(n) ?
//  Space	O(26) = O(1) ?

import java.util.PriorityQueue;

public class ReorganizeString {

  public static String reorganizeString(String S) {

    int n = S.length();
    int[] count = new int[26];
    for (char c : S.toCharArray()) {
      count[c - 'a']++;
    }

    // Step 1: check if possible
    int maxCount = 0;
    for (int f : count) {
      maxCount = Math.max(maxCount, f);
    }
    if (maxCount > (n + 1) / 2) {
      return ""; // impossible
    }

    // Step 2: max heap by frequency
    PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> count[b - 'a'] - count[a - 'a']);

    for (int i = 0; i < 26; i++) {
      if (count[i] > 0) {
        maxHeap.offer((char) (i + 'a'));
      }
    }

    // Step 3: build result
    StringBuilder result = new StringBuilder();

    while (maxHeap.size() >= 2) {
      char first = maxHeap.poll();
      char second = maxHeap.poll();

      result.append(first);
      result.append(second);

      count[first - 'a']--;
      count[second - 'a']--;

      if (count[first - 'a'] > 0) {
        maxHeap.offer(first);
      }
      if (count[second - 'a'] > 0) {
        maxHeap.offer(second);
      }
    }

    // Step 4: append last char if exists
    if (!maxHeap.isEmpty()) {
      result.append(maxHeap.poll());
    }

    return result.toString();
  }

  public static void main(String[] args) {

    String S1 = "aab";
    System.out.println("Reorganized String -> " + reorganizeString(S1));

    String S2 = "aaab";
    System.out.println("Reorganized String -> " + reorganizeString(S2));

    String S3 = "aaabbc";
    System.out.println("Reorganized String -> " + reorganizeString(S3));
  }

}
