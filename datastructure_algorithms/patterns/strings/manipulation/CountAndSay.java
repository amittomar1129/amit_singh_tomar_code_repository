package datastructure_algorithms.patterns.strings.manipulation;

//  The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
// countAndSay(1) = '1',
//  countAndSay(n) is the way you would 'say' the digit string from countAndSay(n-1), which is then
// converted
//  into a different digit string.
//
//  Example 1:
//  Input: 1
//  Output: 1
//  Explanation: The digit 1 is '1' which is one 1.
//
//  Example 2:
//  Input: 4
//  Output: 1211
//  Explanation: The digit 1 is '1' which is one 1. The digit 2 is '11' which is two 1s.
//
//      Example 3:
//  Input: 5
//  Output: 111221
//  Explanation: The digit 1 is '1' which is one 1. The digit 2 is '11' which is two 1s. The digit 1
// is '21' which is one 2 and one 1.

//  n = 1
//      "1"
//
//  n = 2
//  Read "1"
//      ? “one 1”
//      ? "11"
//
//  n = 3
//  Read "11"
//      ? “two 1s”

//      ? "21"
//
//  n = 4
//  Read "21"
//      ? “one 2, one 1”
//      ? "1211"
//
//  n = 5
//  Read "1211"
//      ? “one 1, one 2, two 1s”
//      ? "111221"

//  Solution: countAndSay(1) = "1",  countAndSay(n) = say(countAndSay(n-1))
//  What does “say” mean? Read the string group by group:
//  Count consecutive identical digits
//  Say -> count followed by digit

//  Time -> O(total characters generated)
//  Space -> O(current string size)

//  1 ->                        = "1"
//  2 -> one 1                  = "11"
//  3 -> two 1s                 = "21"
//  4 -> one 2 one 1            = "1211"
//  5 -> one 1 one 2 two 1s     = "111221"
//  6 -> three 1s two 2s one 1  = "312211"

public class CountAndSay {

  public static String countAndSay(int n) {
    String result = "1";

    for (int i = 2; i <= n; i++) {
      StringBuilder sb = new StringBuilder();
      int count = 1;

      for (int j = 1; j < result.length(); j++) {
        if (result.charAt(j) == result.charAt(j - 1)) {
          count++;
        } else {
          sb.append(count);
          sb.append(result.charAt(j - 1));
          count = 1;
        }
      }
      // append last group
      sb.append(count);
      sb.append(result.charAt(result.length() - 1));
      result = sb.toString();
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(countAndSay(1)); // 1
    System.out.println(countAndSay(4)); // 1211
    System.out.println(countAndSay(5)); // 111221
  }
}
