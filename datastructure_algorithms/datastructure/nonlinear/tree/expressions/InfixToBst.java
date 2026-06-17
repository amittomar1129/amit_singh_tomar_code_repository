package datastructure_algorithms.datastructure.nonlinear.tree.expressions;

import datastructure_algorithms.datastructure.nonlinear.tree.bst.BinaryTree;
import datastructure_algorithms.datastructure.nonlinear.tree.bst.Node;

// It is just a bst representation for infix expression without following the rule of precedence and
// associativity. It gives incorrect postfix/prefix expressions by traversing post/preorder.
public class InfixToBst {


  public Node<String> convertToBst(String infix) {
    Node<String> root = new Node<>();
    convertToBst(root, infix);
    return root;
  }

  public Node<String> convertToBst(Node<String> node, String infix) {
    int index = findLowestPrecedenceOperatorIndex(infix);
    if (index == -1) {
      return new Node<>(infix);
    }

    node.val = String.valueOf(infix.charAt(index));
    node.left = convertToBst(new Node<String>(), infix.substring(0, index));
    node.right = convertToBst(new Node<String>(), infix.substring(index + 1));

    return node;
  }


  private int findLowestPrecedenceOperatorIndex(String infix) {
    int result = -1;
    Character current = null;

    char[] charArray = infix.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      if (isOperator(charArray[i])) {
        if ((current == null) || (current != null && precedence(current) >= precedence(
            charArray[i]))) {
          result = i;
          current = charArray[i];
        }
      }
    }
    return result;
  }

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

  public static void main(String[] args) {
    InfixToBst ex = new InfixToBst();
//    ex.convertToBst("++-+KL*MN*//*^OPWUVTQ");
//  ex.convertToBst("+A*-*BC*/D^EFGH");

    BinaryTree<String> tree = new BinaryTree<String>(ex.convertToBst("a*b/c+e/f*g+k-x*y"));
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
    System.out.println("-----------------------------------");
    BinaryTree<String> tree1 = new BinaryTree<>(ex.convertToBst("K+L-M*N+(O^P)*W/U/V*T+Q^J^A"));
    System.out.print("Traverse InOrder: ");
    tree1.traverseInOrder();
    System.out.println();
    System.out.print("Traverse PreOrder: ");
    tree1.traversePreOrder();
    System.out.println();
    System.out.print("Traverse PostOrder: ");
    tree1.traversePostOrder();
    System.out.println();
    System.out.print("Traverse BFS: ");
    tree1.traverseBreadthFirstSearch();
  }
}
