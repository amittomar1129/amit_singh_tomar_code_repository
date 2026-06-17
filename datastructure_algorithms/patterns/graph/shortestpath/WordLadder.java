package datastructure_algorithms.patterns.graph.shortestpath;

//  A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence
//  of words beginWord -> s1 -> s2 -> ... -> sk such that:
//      - Every adjacent pair of words differs by a single letter.
//      - Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//      - sk == endWord
//  Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
//  transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

//  You are given:
//  beginWord (start)
//  endWord (target)
//  wordList (dictionary)
//
//  Rules:
//  You can change only one letter at a time
//  Every intermediate word must exist in wordList
//  You want the shortest sequence
//  Return number of words in the sequence
//  If impossible -> return 0

//  Each word is a node, An edge exists between two words if they differ by exactly one letter.
//  So the problem becomes:
//  Find the shortest path in an unweighted graph

//  Example 1:
//  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//  Output: 5
//  Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
//
//  Example 2:
//  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//  Output: 0
//  Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
//
//  Example 3:
//  Input: beginWord = "a", endWord = "c", wordList = ["a","b","c"]
//  Output: 2
//  Explanation: One shortest transformation is "a" -> "c", return its length 2.

//  Solution: Why BFS (Say This in Interview)
//  Each transformation has equal cost (1 step).
//  BFS guarantees the shortest path.
//  DFS cannot guarantee shortest length.
//  Time	O(N * L * 26)
//      Space	O(N)

//  Input:
//      beginWord = "hit"
//      endWord = "cog"
//      wordList = ["hot","dot","dog","lot","log","cog"]
//  Shortest path:
//      hit -> hot -> dot -> dog -> cog
//  Length:
//      5

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

    Set<String> dict = new HashSet<>(wordList);
    if (!dict.contains(endWord)) {
      return 0;
    }

    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);

    int level = 1; // beginWord counts as level 1

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        String word = queue.poll();
        char[] chars = word.toCharArray();

        for (int j = 0; j < chars.length; j++) {
          char original = chars[j];

          for (char c = 'a'; c <= 'z'; c++) {
            if (c == original) {
              continue;
            }

            chars[j] = c;
            String nextWord = new String(chars);

            if (nextWord.equals(endWord)) {
              return level + 1;
            }

            if (dict.contains(nextWord)) {
              queue.offer(nextWord);
              dict.remove(nextWord); // mark visited
            }
          }
          chars[j] = original;
        }
      }
      level++;
    }

    return 0;
  }

  public static void main(String[] args) {
    String beginWord = "hit";
    String endWord = "cog";
    List<String> wordList =
        Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

    System.out.println("Shortest Transformation Length -> " +
        ladderLength(beginWord, endWord, wordList));
  }
}
