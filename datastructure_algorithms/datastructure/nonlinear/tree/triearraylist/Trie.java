package datastructure_algorithms.datastructure.nonlinear.tree.triearraylist;

import java.util.ArrayList;
import java.util.List;

public class Trie {

  private Node root;


  public Trie() {
    this.root = new Node();
  }

  public void insert(String word) {
    Node current = root;
    for (char ch : word.toCharArray()) {
      if (current.getChild(ch) != null) {
        current = current.getChild(ch);
      } else {
        Node node = new Node(ch);
        current.getChildren().add(node);
        current = node;
      }
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
    Node childNode = node.getChild(character);
    if (childNode != null) {
      boolean shouldDelete = delete(childNode, word, index + 1);
      if (shouldDelete) {
        node.getChildren().remove(childNode);
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

    for (Node child : node.getChildren()) {
      prefix.append(child.getData());
      collectWords(child, prefix, results);
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
    Node childNode = node.getChild(character);
    if (childNode != null) {
      return getNode(childNode, word, index + 1);
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
    Node node = root;
    ArrayList<String> results = new ArrayList<>();
    for (char c : prefix.toCharArray()) {
      if (node.getChild(c) != null) {
        node = node.getChild(c);
      } else {
        return results;
      }
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
    System.out.println(trie.isPresent("amr"));
    System.out.println(trie.getWordsWithPrefix("amr"));

  }

}
