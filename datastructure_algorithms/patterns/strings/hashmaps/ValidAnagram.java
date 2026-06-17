package datastructure_algorithms.patterns.strings.hashmaps;

//  Given two strings s and t, return true if t is an anagram of s, and false otherwise.
//
//  Example 1:
//  Input: {"s": "anagram", "t": "nagaram"}
//  Output: true
//  Explanation: Example 1: s = "anagram", t = "nagaram" => t is an anagram of s.
//
//  Example 2:
//  Input: {"s": "rat", "t": "car"}
//  Output: false
//  Explanation: Example 2: s = "rat", t = "car" => t is not an anagram of s.
//
//      Example 3:
//  Input: {"s": "a", "t": "ab"}
//  Output: false
//  Explanation: Example 3: s = "a", t = "ab" => t is not an anagram of s.

//  Solution: I use a 26-length frequency array.
//  I increment counts for the first string and decrement for the second.
//  If any count becomes negative or lengths differ, they’re not anagrams.
//  Time	O(n)
//  Space	O(1)

public class ValidAnagram {

  public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;

    int[] count = new int[26];

    // count characters from s
    for (char c : s.toCharArray()) {
      count[c - 'a']++;
    }

    // subtract characters from t
    for (char c : t.toCharArray()) {
      count[c - 'a']--;
      if (count[c - 'a'] < 0) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(isAnagram("anagram", "nagaram")); // true
    System.out.println(isAnagram("rat", "car"));         // false
  }
}
