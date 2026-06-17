package datastructure_algorithms.patterns.strings.hashmaps;

//  Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//  An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically
//  using all the original letters exactly once.
//
//  Example 1:
//  Input: strs = ["ab", "ba", "cd"]
//  Output: [["ab", "ba"], ["cd"]]
//  Explanation: There is no string in strs that can be rearranged to form "bat". The strings "nat" and "tan" are anagrams as they can be rearranged to form each other. The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
//
//      Example 2:
//  Input: strs = [""]
//  Output: [[""]]
//  Explanation: There is no anagrams excist
//
//  Example 3:
//  Input: strs = ["a"]
//  Output: [["a"]]
//  Explanation: The groups of anagrams is [["a"]].

//  Solution: Instead of sorting strings, I use a 26-length frequency array as the key.
//  This reduces time complexity to O(n * k) and is optimal for lowercase English letters.

//  Time O(n * k)
//  Space	O(n)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

  public static List<List<String>> groupAnagrams(String[] input) {
    Map<String, List<String>> map = new HashMap<>();

    for (String s : input) {
      int[] count = new int[26];
      // count characters
      for (char c : s.toCharArray()) {
        count[c - 'a']++;
      }
      // build unique key
      StringBuilder keyBuilder = new StringBuilder();
      for (int num : count) {
        keyBuilder.append('#').append(num);
      }
      String key = keyBuilder.toString();
      map.putIfAbsent(key, new ArrayList<>());
      map.get(key).add(s);
    }

    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    System.out.println(groupAnagrams(strs));
  }
}
