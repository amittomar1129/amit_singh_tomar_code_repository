package datastructure_algorithms.datastructure.nonlinear.tree.expressions;

import datastructure_algorithms.datastructure.nonlinear.tree.bst.BinaryTree;
import datastructure_algorithms.datastructure.nonlinear.tree.bst.Node;

import java.util.Stack;

public class PreFixToExpressionTree {

  private boolean isOperator(char character) {
    return character == '^' || character == '/' || character == '*' || character == '+'
        || character == '-';
  }

  public Node<String> buildTree(String prefix) {
    StringBuilder reverse = new StringBuilder(prefix).reverse();
    Stack<Node<String>> nodes = new Stack<>();

    for (char ch : reverse.toString().toCharArray()) {
      String input = String.valueOf(ch);
      if (Character.isLetterOrDigit(ch)) {
        nodes.push(new Node<>(input));
      } else if (isOperator(ch)) {
        Node<String> node = new Node<>(input);
        node.left = nodes.pop();
        node.right = nodes.pop();
        nodes.push(node);
      }
    }
    return nodes.pop();
  }

  public static void main(String[] args) {
    PreFixToExpressionTree expTree = new PreFixToExpressionTree();
    Node<String> root = expTree.buildTree("++-+KL*MN*//*^OPWUVT^Q^JA");
    BinaryTree tree = new BinaryTree(root);
    System.out.print("Traverse InOrder: ");
    tree.traverseInOrder();
    System.out.println();
    System.out.print("Traverse PreOrder: ");
    tree.traversePreOrder();
    System.out.println();
    System.out.print("Traverse PostOrder: ");
    tree.traversePostOrder();
    System.out.println();
    System.out.print("Traverse BFS: ");
    tree.traverseBreadthFirstSearch();
    System.out.println();
  }
}
