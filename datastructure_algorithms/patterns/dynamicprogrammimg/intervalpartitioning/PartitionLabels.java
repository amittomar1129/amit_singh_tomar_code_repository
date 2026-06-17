package datastructure_algorithms.patterns.dynamicprogrammimg.intervalpartitioning;

//  A string S of lowercase English letters is given. We want to partition this string into as many parts
//  as possible so that each letter appears in at most one part, and then return a list of integers representing
//  the size of these parts.

//  You need to:
//  Split the string into as many parts as possible.
//  Each character must appear in at most one part.
//  Return the size of each part.

//  Example 1:
//  Input: ababcbacadefegdehijhklij
//  Output: [9,7,8]
//  Explanation: The partition is 'ababcbaca', 'defegde', 'hijhklij'. This is a partition so that each letter appears in
//  at most one part.
//
//  Example 2:
//  Input: eccbbbbdec
//  Output: [10]
//  Explanation: The partition is 'eccbbbbdec'.

//  Solution: I record the last occurrence of each character, then greedily expand the current partition until
//  all characters inside it finish. When the current index reaches the farthest last index, I close the partition.
//  Time	O(n)
//  Space	O(1) (26 letters)

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

  public static List<Integer> partitionLabels(String s) {
    int[] lastIndex = new int[26];

    // Step 1 -> record last occurrence of each character
    for (int i = 0; i < s.length(); i++) {
      lastIndex[s.charAt(i) - 'a'] = i;
    }

    List<Integer> result = new ArrayList<>();
    int start = 0;
    int end = 0;

    // Step 2 -> greedy partitioning
    for (int i = 0; i < s.length(); i++) {
      end = Math.max(end, lastIndex[s.charAt(i) - 'a']);

      if (i == end) {
        result.add(end - start + 1);
        start = i + 1;
      }
    }


    return result;
  }

  public static void main(String[] args) {
    String s = "ababcbacadefegdehijhklij";

    System.out.println("Partition sizes -> " + sol(s));
  }

  public static List<Integer> sol(String input) {
    List<Integer> result = new ArrayList<>();
    int start = 0;
    int end = 0;
    int[] array = new int[26];

    for(int i = 0; i < input.length(); i++) {
      array[input.charAt(i) - 'a'] = i;
    }


    for(int i = 0; i < input.length(); i++) {
      end = Math.max(end, array[input.charAt(i) - 'a']);
      if (i == end) {
        result.add(end - start + 1);
        start = i + 1;
      }
    }

    return result;
  }























}
