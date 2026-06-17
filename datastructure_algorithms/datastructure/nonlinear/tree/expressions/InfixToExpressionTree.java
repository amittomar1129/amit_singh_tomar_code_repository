package datastructure_algorithms.datastructure.nonlinear.tree.expressions;

import datastructure_algorithms.datastructure.nonlinear.tree.bst.BinaryTree;
import datastructure_algorithms.datastructure.nonlinear.tree.bst.Node;

import java.util.Stack;

public class InfixToExpressionTree {

  private static final char LEFT_PARENTHESES = '(';
  private static final char RIGHT_PARENTHESES = ')';

  private boolean isOperator(char character) {
    return character == '^' || character == '/' || character == '*' || character == '+'
        || character == '-';
  }

  private int precedence(char character) {
    return switch (character) {
      case '^' -> 3;
      case '/', '*' -> 2;
      case '+', '-' -> 1;
      default -> -1;
    };
  }

  private String associativity(char character) {
    return character == '^' ? "RL" : "LR";
  }

  public Node<String> buildTree(String infix) {
    Stack<Node<String>> nodes = new Stack<>();
    Stack<String> operators = new Stack<>();

    for (char ch : infix.toCharArray()) {
      String input = String.valueOf(ch);
      if (Character.isLetterOrDigit(ch)) {
        nodes.push(new Node<>(input));
      } else if (ch == LEFT_PARENTHESES) {
        operators.push(input);
      } else if (ch == RIGHT_PARENTHESES) {
        while (!operators.isEmpty() && !operators.peek().equals("(")) {
          Node<String> node = new Node<>(operators.pop());
          node.right = nodes.pop();
          node.left = nodes.pop();
          nodes.push(node);
        }
        operators.pop(); // Removes left parenthesis
      } else { // Operator
        while (!operators.isEmpty() && precedence(ch) < precedence(operators.peek().charAt(0))) {
          Node<String> node = new Node<>(operators.pop());
          node.right =nodes.pop();
          node.left = nodes.pop();
          nodes.push(node);
        }
        if (!operators.isEmpty() && precedence(ch) > precedence(operators.peek().charAt(0))) {
          operators.push(input);
        } else if (!operators.isEmpty() && precedence(ch) == precedence(
            operators.peek().charAt(0))) {
          if (associativity(ch).equals("LR")) {
            Node<String> node = new Node<>(operators.pop());
            node.right =nodes.pop();
            node.left = nodes.pop();
            nodes.push(node);
          }
          operators.push(input);
        } else if (operators.isEmpty()) {
          operators.push(input);
        }
      }
    }

    while (!operators.isEmpty()) {
      Node<String> node = new Node<>(operators.pop());
      node.right = nodes.pop();
      node.left = nodes.pop();
      nodes.push(node);
    }
    return nodes.pop();
  }

  public static void main(String[] args) {
    InfixToExpressionTree expTree = new InfixToExpressionTree();
    Node<String> root = expTree.buildTree("K+L-M*N+(O^P)*W/U/V*T+Q^J^A");
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
