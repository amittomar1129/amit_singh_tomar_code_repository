package datastructure_algorithms.datastructure.nonlinear.tree.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie {

  private Node root;

  public Trie() {
    this.root = new Node();
  }

  public void insert(String word) {
    Node current = root;
    for (char character : word.toCharArray()) {
      current = current.getChildren().computeIfAbsent(character, c -> new Node());
    }
    current.setEnd(true);
  }

  public boolean delete(String word) {
    return delete(this.root, word, 0);
  }

  private boolean delete(Node node, String word, int index) {
    if (index == word.length()) {
      if (node.isEnd() && node.getChildren().isEmpty()) {
        return true;
      }
      return false;
    }

    char character = word.charAt(index);
    Node childNode = node.getChildren().get(character);
    if (childNode != null) {
      boolean shouldDelete = delete(childNode, word, index + 1);
      if (shouldDelete) {
        node.getChildren().remove(character);
        return node.getChildren().isEmpty();
      }
    }
    return false;
  }

  public List<String> collectAllWords() {
    List<String> results = new ArrayList<>();
    collectWords(this.root, new StringBuilder(), results);
    return results;
  }

  // DFS to collect words
  private void collectWords(Node node, StringBuilder prefix, List<String> results) {
    if (node.isEnd()) {
      results.add(prefix.toString());
      return;
    }

    for (Map.Entry<Character, Node> entry : node.getChildren().entrySet()) {
      prefix.append(entry.getKey());
      collectWords(entry.getValue(), prefix, results);
      prefix.deleteCharAt(prefix.length() - 1);
    }
  }

  // Search for a full word
  public boolean search(String word) {
    Node node = getNode(this.root, word, 0);
    return node != null && node.isEnd();
  }

  private Node getNode(Node node, String word, int index) {
    if (index == word.length()) {
      return node;
    }

    char character = word.charAt(index);
    Node childNode = node.getChildren().get(character);
    if (childNode != null) {
      Node found = getNode(childNode, word, index + 1);
      return found;
    }

    return null;
  }

  //   Check if a prefix is present (not necessarily a complete word)
  public boolean isPresent(String prefix) {
    Node node = getNode(this.root, prefix, 0);
    return node != null;
  }

  // Get all words starting with a given prefix
  public List<String> getWordsWithPrefix(String prefix) {
    Node node = getNode(this.root, prefix, 0);
    ArrayList<String> results = new ArrayList<>();
    if (node == null) {
      return results;
    }
    collectWords(node, new StringBuilder(prefix), results);
    return results;
  }

  @Override
  public String toString() {
    return "Trie{" + "root=" + root + '}';
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("amit");
    trie.insert("amrish");
    trie.insert("ram");
    trie.insert("shyam");

    System.out.println(trie);

//    trie.delete("amit");
//    trie.delete("ram");
//    System.out.println(trie);
    System.out.println(trie.collectAllWords());
    System.out.println(trie.search("shyam"));
    System.out.println(trie.isPresent("amit"));
    System.out.println(trie.getWordsWithPrefix("sh"));

  }

}
