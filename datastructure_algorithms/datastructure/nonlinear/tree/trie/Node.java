package datastructure_algorithms.datastructure.nonlinear.tree.trie;

import java.util.HashMap;
import java.util.Map;

public class Node {

  private final Map<Character, Node> children = new HashMap<>();
  private boolean isEnd;

  public Node() {
  }

  public Node(boolean isEnd) {
    this.isEnd = isEnd;
  }

  public Map<Character, Node> getChildren() {
    return children;
  }

  public boolean isEnd() {
    return isEnd;
  }

  public void setEnd(boolean end) {
    isEnd = end;
  }

  @Override
  public String toString() {
//    StringBuilder builder = new StringBuilder();
//    for (Map.Entry<Character, Node> entry : children.entrySet()) {
//      Character key = entry.getKey();
//      Node value = entry.getValue();
//      boolean isEnd1 = entry.getValue().isEnd();
//      builder.append(key).append(" ").append(isEnd1).append(" ").append(value);
//    }
    return children.entrySet().toString() + isEnd;
  }
}
