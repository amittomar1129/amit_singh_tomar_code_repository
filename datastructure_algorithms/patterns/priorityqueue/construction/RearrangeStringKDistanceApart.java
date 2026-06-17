package datastructure_algorithms.patterns.priorityqueue.construction;

//  Given a non-empty string s and an integer k, rearrange the string such that the same characters are
//  at least distance k from each other.
//
//  Example 1:
//  Input: s = 'aabbcc', k = 3
//  Output: 'abcabc'
//  Explanation: The same letters are at least distance 3 from each other.
//
//  Example 2:
//  Input: s = 'aaabc', k = 3
//  Output: ''
//  Explanation: It is not possible to rearrange the string.

//  Solution: I always place the most frequent character first using a max heap.
//  After placing, I put it into a cooldown queue for k intervals.
//  When the character finishes cooldown, I push it back to the heap.
//  If at any point we cannot place a character but the string is not finished, it is impossible.
//  Time complexity is O(n) practically because only 26 characters are involved.
//  Time	O(n log 26) ? O(n) practically, because max 26 letters
//  Space	O(26 + n) ? heap + queue + result


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeStringKDistanceApart {

  public static String rearrangeString(String s, int k) {
    if (k == 0) {
      return s; // no restriction
    }
    Map<Character, Integer> count = new HashMap<>();
    for (char c : s.toCharArray()) {
      count.put(c, count.getOrDefault(c, 0) + 1);
    }

    PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> count.get(b) - count.get(a));

    maxHeap.addAll(count.keySet());

    Queue<Character> queue = new LinkedList<>();
    StringBuilder result = new StringBuilder();

    while (!maxHeap.isEmpty()) {
      char curr = maxHeap.poll();
      result.append(curr);
      count.put(curr, count.get(curr) - 1);

      queue.offer(curr);

      if (queue.size() >= k) {
        char front = queue.poll();
        if (count.get(front) > 0) {
          maxHeap.offer(front);
        }
      }
    }
    return result.length() == s.length() ? result.toString() : "";
  }

  public static void main(String[] args) {
    String s = "aabbcc";
    int k = 3;

    System.out.println("Rearranged String -> " + rearrangeString(s, k));
  }
}
