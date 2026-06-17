package datastructure_algorithms.patterns.stackqueue.twostack;

//  Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means
//  a backspace character. Note that after backspacing an empty text, the text will continue empty.
//
//  Example 1:
//  Input: s = "ab#c", t = "ad#c"
//  Output: true
//  Explanation: Both s and t become "ac".
//
//  Example 2:
//  Input: s = "ab##", t = "c#d#"
//  Output: true
//  Explanation: Both s and t become "".
//
//  Example 3:
//  Input: s = "a#c", t = "b"
//  Output: false
//  Explanation: s becomes "c" while t becomes "b".


//  Solution: “I compare both strings from the end using two pointers, skipping characters that are deleted by
//  backspaces, achieving O(1) extra space.”
//  Time	O(n) Space 	O(1)

public class BackspaceStringCompare {

  public static boolean backspaceCompare(String s, String t) {
    int i = s.length() - 1, j = t.length() - 1;
    int skipS = 0, skipT = 0;

    while (i >= 0 || j >= 0) {

      while (i >= 0) {
        if (s.charAt(i) == '#') {
          skipS++;
          i--;
        } else if (skipS > 0) {
          skipS--;
          i--;
        } else {
          break;
        }
      }

      while (j >= 0) {
        if (t.charAt(j) == '#') {
          skipT++;
          j--;
        } else if (skipT > 0) {
          skipT--;
          j--;
        } else {
          break;
        }
      }

      char chS = i >= 0 ? s.charAt(i) : '\0';
      char chT = j >= 0 ? t.charAt(j) : '\0';

      if (chS != chT) return false;

      i--;
      j--;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(backspaceCompare("ab#c", "ad#c")); // true
    System.out.println(backspaceCompare("ab##", "c#d#")); // true
    System.out.println(backspaceCompare("a#c", "b"));    // false
  }
}
