package datastructure_algorithms.patterns.priorityqueue.frequentelements;

//  Given a string, sort it in decreasing order based on the frequency of characters.
//
//  Example 1:
//  Input: tree
//  Output: eert
//  Explanation: e appears twice while r and t both appear once. So in decreasing order, the result is 'eert'.

//  Solution: Bucket Sort
//  Time	O(n)
//  Space	O(n)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCharactersByFrequency {

  public static String frequencySort(String s) {

    Map<Character, Integer> freqMap = new HashMap<>();
    for (char c : s.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }

    List<Character>[] buckets = new List[s.length() + 1];

    for (char c : freqMap.keySet()) {
      int freq = freqMap.get(c);
      if (buckets[freq] == null) {
        buckets[freq] = new ArrayList<>();
      }
      buckets[freq].add(c);
    }

    StringBuilder sb = new StringBuilder();

    for (int i = buckets.length - 1; i >= 0; i--) {
      if (buckets[i] != null) {
        for (char c : buckets[i]) {
          for (int j = 0; j < i; j++) {
            sb.append(c);
          }
        }
      }
    }

    return sb.toString();
  }

  // main method
  public static void main(String[] args) {

    String s = "tree";
    System.out.println("Sorted by frequency -> " + frequencySort(s));
  }
}
