package datastructure_algorithms.patterns.stackqueue.slidingwindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//  You have a queue of integers, you need to retrieve the first unique integer in the queue. Implement the FirstUnique
//  class with the following methods: FirstUnique(nums) Initializes the object with the numbers in the queue.
//  int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
//  void add(int value) insert value to the queue.
//
//  Example 1:
//  Input: ['FirstUnique', 'showFirstUnique', 'add', 'showFirstUnique', 'add', 'showFirstUnique', 'add', 'showFirstUnique'] [[[2,3,5]], [], [5], [], [2], [], [3], []]
//  Output: [null, 2, null, 2, null, 3, null, -1]
//  Explanation: Example 1: FirstUnique firstUnique = new FirstUnique([2,3,5]); // the queue is [2,3,5] firstUnique.showFirstUnique(); // return 2 firstUnique.add(5); // the queue is [2,3,5,5] firstUnique.showFirstUnique(); // return 2 firstUnique.add(2); // the queue is [2,3,5,5,2] firstUnique.showFirstUnique(); // return 3 firstUnique.add(3); // the queue is [2,3,5,5,2,3] firstUnique.showFirstUnique(); // return -1

//  You have a queue of integers. You need to:
//  Always return the first number that appears exactly once
//  Support: Initialization with a list, Adding new numbers dynamically.

//  Solution: “I track frequencies with a hashmap and maintain insertion order using a queue, removing
//  non-unique elements lazily when querying.”
//  Time Amortized O(1)
//  Total Space		O(n)

public class FirstUniqueNumber {

  private Queue<Integer> queue;
  private Map<Integer, Integer> freq;

  public FirstUniqueNumber(int[] nums) {
    queue = new LinkedList<>();
    freq = new HashMap<>();
    for (int num : nums) {
      add(num);
    }
  }

  public int showFirstUnique() {
    while (!queue.isEmpty() && freq.get(queue.peek()) > 1) {
      queue.poll();
    }
    return queue.isEmpty() ? -1 : queue.peek();
  }

  public void add(int value) {
    freq.put(value, freq.getOrDefault(value, 0) + 1);
    queue.offer(value);
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {
    int[] nums = {2, 3, 5};
    FirstUniqueNumber firstUnique = new FirstUniqueNumber(nums);

    System.out.println(firstUnique.showFirstUnique()); // 2
    firstUnique.add(5);
    System.out.println(firstUnique.showFirstUnique()); // 2
    firstUnique.add(2);
    System.out.println(firstUnique.showFirstUnique()); // 3
    firstUnique.add(3);
    System.out.println(firstUnique.showFirstUnique()); // -1
  }
}
