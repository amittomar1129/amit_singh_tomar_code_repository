package datastructure_algorithms.patterns.priorityqueue.frequentelements;

//  Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//
//  Example 1:
//  Input: nums = [1,1,1,2,2,3], k = 2
//  Output: [1,2]
//  Explanation: Return the two most frequent elements, 1 and 2.
//
//  Example 2:
//  Input: nums = [1], k = 1
//  Output: [1]
//  Explanation: Return the only element in the array.

//  Solution: Bucket Sort
//  Time	O(n)
//  Space	O(n)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

  public static int[] topKFrequent(int[] nums, int k) {

    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    List<Integer>[] buckets = new List[nums.length + 1];

    for (int num : freqMap.keySet()) {
      int freq = freqMap.get(num);
      if (buckets[freq] == null) {
        buckets[freq] = new ArrayList<>();
      }
      buckets[freq].add(num);
    }

    int[] result = new int[k];
    int index = 0;

    for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
      if (buckets[i] != null) {
        for (int num : buckets[i]) {
          result[index++] = num;
          if (index == k) {
            break;
          }
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {

    int[] nums = {1, 1, 1, 2, 2, 3};
    int k = 2;

    int[] ans = topKFrequent(nums, k);

    System.out.println("Top K Frequent Elements:");
    for (int x : ans) {
      System.out.print(x + " ");
    }
  }
}
