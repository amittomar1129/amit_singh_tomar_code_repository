package datastructure_algorithms.patterns.priorityqueue.construction;

//  Given a sentence text (A sentence is a string of space-separated words) in the following format:
//      - First letter is in upper case.
//      - Each word in text are separated by a single space.
//  Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
//  If two words have the same length, arrange them in their original order.
//  Return the new text following the format shown above.
//
//  Example 1:
//  Input: "Leetcode is cool"
//  Output: "Is cool Leetcode"
//  Explanation: The words are rearranged by length: "is" (2), "cool" (4), "Leetcode" (8). The first letter of the sentence is capitalized.

//  Time	O(n log n), n = number of words (due to sorting)
//  Space	O(n), for array of words

import java.util.Arrays;
import java.util.Comparator;

public class RearrangeWords {

  public static String arrangeWords(String text) {

    // Step 1: lowercase the whole sentence
    text = text.toLowerCase();

    // Step 2: split into words
    String[] words = text.split(" ");

    // Step 3: stable sort by length
    Arrays.sort(words, Comparator.comparingInt(String::length));

    // Step 4: capitalize first word
    words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1);

    // Step 5: join words
    return String.join(" ", words);
  }

  public static void main(String[] args) {
    String text1 = "Leetcode is cool";
    System.out.println(arrangeWords(text1)); // Output: "Is cool leetcode"

    String text2 = "Keep calm and code on";
    System.out.println(arrangeWords(text2)); // Output: "On and keep calm code"
  }
}
