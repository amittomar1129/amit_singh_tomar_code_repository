package datastructure_algorithms.patterns.strings.hashmaps;

//  Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
//
//  Example 1:
//  Input: s = "leetcode"
//  Output: 0
//  Explanation: The first non-repeating character is 'l' and its index is 0.
//
//  Example 2:
//  Input: s = "loveleetcode"
//  Output: 2
//  Explanation: The first non-repeating character is 'v' and its index is 2.
//
//  Example 3:
//  Input: s = "aabb"
//  Output: -1
//  Explanation: There is no non-repeating character, so return -1.

//  Solution: I count character frequencies in one pass and then scan the string again to find
//  the first character with frequency one.
//  Time	O(n)
//  Space	O(1)

public class FirstUniqueCharacter {

  public static int firstUniqChar(String s) {
    int[] count = new int[26];

    // 1st pass: count characters
    for (char c : s.toCharArray()) {
      count[c - 'a']++;
    }

    // 2nd pass: find first unique character
    for (int i = 0; i < s.length(); i++) {
      if (count[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(firstUniqChar("leetcode"));       // 0
    System.out.println(firstUniqChar("loveleetcode"));   // 2
    System.out.println(firstUniqChar("aabb"));           // -1
  }
}
