package datastructure_algorithms.patterns.strings.twopointer;

//  Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//
//  Example 1:
//  Input: "A man, a plan, a canal: Panama"
//  Output: true
//  Explanation: Explanation: "amanaplanacanalpanama" is a palindrome.
//
//      Example 2:
//  Input: "race a car"
//  Output: false
//  Explanation: Explanation: "raceacar" is not a palindrome.
//
//  Example 3:
//  Input: ""
//  Output: true
//  Explanation: Explanation: An empty string is a palindrome.

//  Solution: One pointer from the start. One pointer from the end.
//  Time: O(n)
//  Space: O(1)

public class ValidPalindrome {

  public static boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;

    while (left < right) {
      // Skip non-alphanumeric from left
      while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
        left++;
      }
      // Skip non-alphanumeric from right
      while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
        right--;
      }
      // Compare ignoring case
      if (Character.toLowerCase(s.charAt(left)) !=
          Character.toLowerCase(s.charAt(right))) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  public static void main(String[] args) {
    String s = "A man, a plan, a canal: Panama";
    System.out.println(isPalindrome(s)); // true
  }
}
