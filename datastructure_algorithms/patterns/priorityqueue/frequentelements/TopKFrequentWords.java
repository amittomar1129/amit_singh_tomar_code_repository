package datastructure_algorithms.patterns.priorityqueue.frequentelements;

//  Given a non-empty list of words, return the k most frequent elements. Your answer should be sorted by frequency
//  from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
//
//  Example 1:
//  Input: ['i', 'love', 'leetcode', 'i', 'love', 'coding'], 2
//  Output: ['i', 'love']
//  Explanation: Return the top 2 frequent words: 'i' and 'love'. 'i' comes before 'love' due to a lower alphabetical order.
//
//  Example 2:
//  Input: ['the', 'day', 'is', 'sunny', 'the', 'the', 'the', 'sunny', 'is', 'is'], 4
//  Output: ['the', 'is', 'sunny', 'day']
//  Explanation: Return the top 4 frequent words: 'the', 'is', 'sunny', and 'day'. The words 'is' and 'sunny' have the same frequency,
//  but 'is' comes before 'sunny' due to a lower alphabetical order.

//  Time	O(n log k)
//  Space	O(n)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {

  public static List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> freqMap = new HashMap<>();
    for (String word : words) {
      freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
    }

    PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
      if (!freqMap.get(a).equals(freqMap.get(b))) {
        return freqMap.get(a) - freqMap.get(b); // freq asc
      }
      return b.compareTo(a); // lex desc
    });

    for (String word : freqMap.keySet()) {
      minHeap.offer(word);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }

    List<String> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll());
    }

    Collections.reverse(result);
    return result;
  }

  // main method
  public static void main(String[] args) {
    String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
    int k = 2;

    List<String> ans = topKFrequent(words, k);
    System.out.println(ans);
  }
}
