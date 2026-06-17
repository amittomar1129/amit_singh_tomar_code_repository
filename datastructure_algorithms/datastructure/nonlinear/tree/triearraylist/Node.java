package datastructure_algorithms.datastructure.nonlinear.tree.triearraylist;

import java.util.ArrayList;

public class Node {

  private char data;
  private boolean isEnd;
  private ArrayList<Node> children = new ArrayList<>();

  public Node() {
  }

  public Node(char data) {
    this.data = data;
  }

  public Node(char data, boolean isEnd) {
    this.data = data;
    this.isEnd = isEnd;
  }

  public char getData() {
    return data;
  }

  public void setData(char data) {
    this.data = data;
  }

  public boolean isEnd() {
    return isEnd;
  }

  public void setEnd(boolean end) {
    isEnd = end;
  }

  public ArrayList<Node> getChildren() {
    return children;
  }

  public void setChildren(ArrayList<Node> children) {
    this.children = children;
  }

  public Node getChild(char data) {
    return children.stream().filter(child -> child.getData() == data).findFirst().orElse(null);
  }

  @Override
  public String toString() {
    String childString = children.toString();
    return data + " " + isEnd + " " + childString.substring(1, childString.length() - 1);
  }
}
