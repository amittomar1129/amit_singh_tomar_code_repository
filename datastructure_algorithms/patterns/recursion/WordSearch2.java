package datastructure_algorithms.patterns.recursion;

//  Given a 2D board and a list of words from the dictionary, find all words in the board. Each word
// must be constructed
//  from letters of sequentially adjacent cells, where 'adjacent' cells are horizontally or
// vertically neighboring.
//  The same letter cell may not be used more than once in a word.
//
//  Example 1:
//  Input: [['o','a','a','n'],['e','t','a','e'],['i','h','k','r'],['i','f','l','v']],
// ['oath','pea','eat','rain']
//  Output: ['eat','oath']
//  Explanation: Example 1
//
//  Example 2:
//  Input: [['a','b'],['c','d']],
// ['ab','cb','ad','bd','ac','ca','da','bc','db','adcb','dabc','abb','acb']
//  Output: ['ab','ac','bd','ca','db']
//  Explanation: Example 2
//
//  Example 3:
//  Input: [['a']], ['a']
//  Output: ['a']
//  Explanation: Example 3

//  Solution: Word Search II is solved optimally using Trie + DFS to avoid repeated searches and
// prune invalid prefixes early.
//  Time O(board_cells * 4^L)  (pruned heavily by Trie)
//  Space O(total characters in words)  -> Trie
//  O(L) recursion stack

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {

  static class TrieNode {

    TrieNode[] children = new TrieNode[26];
    String word;
  }

  public static List<String> findWords(char[][] board, String[] words) {
    TrieNode root = buildTrie(words);
    Set<String> result = new HashSet<>();

    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        dfs(board, r, c, root, result);
      }
    }
    return new ArrayList<>(result);
  }

  private static void dfs(char[][] board, int r, int c, TrieNode node, Set<String> result) {
    char ch = board[r][c];
    if (ch == '#' || node.children[ch - 'a'] == null) {
      return;
    }
    node = node.children[ch - 'a'];

    if (node.word != null) {
      result.add(node.word);
    }

    board[r][c] = '#'; // mark visited
    if (r > 0) {
      dfs(board, r - 1, c, node, result);
    }
    if (c > 0) {
      dfs(board, r, c - 1, node, result);
    }
    if (r < board.length - 1) {
      dfs(board, r + 1, c, node, result);
    }
    if (c < board[0].length - 1) {
      dfs(board, r, c + 1, node, result);
    }
    board[r][c] = ch; // backtrack
  }

  private static TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String word : words) {
      TrieNode curr = root;
      for (char ch : word.toCharArray()) {
        int index = ch - 'a';
        if (curr.children[index] == null) {
          curr.children[index] = new TrieNode();
        }
        curr = curr.children[index];
      }
      curr.word = word;
    }
    return root;
  }

  public static void main(String[] args) {
    char[][] board = {
      {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}
    };

    String[] words = {"oath", "pea", "eat", "rain"};

    System.out.println(findWords(board, words)); // [eat, oath]
  }
}
