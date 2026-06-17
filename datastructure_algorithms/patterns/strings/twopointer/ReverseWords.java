package datastructure_algorithms.patterns.strings.twopointer;

//  Given an input string, reverse the string word by word while preserving the order of words.
//  A word is defined as a sequence of non-space characters. The input string does not contain
//  leading or trailing spaces and the words are always separated by a single space.
//
//  Example 1:
//  Input: the sky is blue
//  Output: blue is sky the
//  Explanation: Reverse the entire string, then reverse each word.
//
//      Example 2:
//  Input: hello world
//  Output: world hello
//  Explanation: Trim leading and trailing spaces, then reverse each word.
//
//  Example 3:
//  Input: a good example
//  Output: example good a
//  Explanation: Multiple spaces between words should be condensed to a single space before reversing.

//  Solution: I split by spaces and iterate from the end to rebuild the string.
//  Time: O(n)
//  Space: O(n)

public class ReverseWords {

  public static String reverseStringByWord(String s) {
    StringBuilder result = new StringBuilder();
    String[] words = s.split(" ");

    for (int i = words.length - 1; i >= 0; i--) {
      result.append(words[i]);
      if (i > 0) {
        result.append(" ");
      }
    }
    return result.toString();
  }

//  Time	O(n)
//  Space	O(1)
  public static String reverseStringByWordInPlace(String s) {
    char[] chars = s.toCharArray();
    // Step 1: reverse the entire array
    reverseInPlace(chars, 0, chars.length - 1);
    // Step 2: reverse each word
    int start = 0;
    for (int i = 0; i <= chars.length; i++) {
      if (chars[i] == ' ' || i == chars.length) {
        reverseInPlace(chars, start, i - 1);
        start = i + 1;
      }
    }
    return new String(chars);
  }


  //  Since word order must remain unchanged, we reverse characters inside each word only.
  //  Time: O(n)
//  Space: O(n)
  public static String reverseWords(String s) {
    StringBuilder result = new StringBuilder();
    String[] words = s.split(" ");

    for (int i = 0; i < words.length; i++) {
      result.append(reverse(words[i]));
      if (i < words.length - 1) {
        result.append(" ");
      }
    }
    return result.toString();
  }

  private static String reverse(String word) {
    StringBuilder sb = new StringBuilder(word);
    return sb.reverse().toString();
  }

  //  Time	O(n)
//  Extra Space	O(1)
  public static String reverseWordsInPlace(String s) {
    char[] chars = s.toCharArray();
    int start = 0;
    for (int i = 0; i <= chars.length; i++) {
      // End of word
      if (i == chars.length || chars[i] == ' ') {
        reverseInPlace(chars, start, i - 1);
        start = i + 1;
      }
    }
    return new String(chars);
  }

  private static void reverseInPlace(char[] arr, int left, int right) {
    while (left < right) {
      char temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
      left++;
      right--;
    }
  }

  public static void main(String[] args) {
    String s = "the sky is blue";
    System.out.println(reverseStringByWord(s)); // "blue is sky the"
    System.out.println(reverseWords(s)); // "eht yks si eulb"

//  An algorithm is in-place if it modifies the input data directly and uses O(1) extra space.
    System.out.println(reverseStringByWordInPlace(s)); // "blue is sky the"
    System.out.println(reverseWordsInPlace(s)); // "eht yks si eulb"
  }

//  Problem with Huge or very big Strings:
//  Strings in Java are immutable.
//    Every time you do something like s.split() or s.substring(), Java creates new objects.
//    For a very large string: Lots of temporary objects are created. Huge memory overhead. Performance becomes slow

//   How to Handle Very Large Strings:
// 1.  Use a char[] and work in-place
//    Convert string to char[] once
//    Reverse words / letters directly
//    Avoid creating new strings during processing
//    Only create one final string at the end. This is O(n) time, O(1) extra space.
// 2. Use streaming / buffer approach if string doesn't fit in memory
// 3. Avoid split(), regex, or StringBuilder per word. Each of these creates new objects.

}
