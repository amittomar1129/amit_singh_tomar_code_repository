package datastructure_algorithms.datastructure.nonlinear.tree.bst;

public class BinaryTreeMain {

  public static void main(String[] args) {

    BinaryTree<String> binaryTree = new BinaryTree<String>();
    System.out.println(binaryTree.isEmpty());
    System.out.println(binaryTree.size());
    binaryTree.add("Amit14");
    binaryTree.add("Amit20");
    binaryTree.add("Amit19");
    binaryTree.add("Amit12");
    binaryTree.add("Amit 9");
    binaryTree.add("Amit35");
    binaryTree.add("Amit46");
    binaryTree.add("Amit89");
    binaryTree.add("Amit75");
    binaryTree.add("Amit 3");
    binaryTree.add("Amit 4");
    binaryTree.add("Amit11");
    binaryTree.add("Amit10");
    binaryTree.add("Amit13");
    binaryTree.add("Amit 1");
    System.out.println(binaryTree.isEmpty());
    System.out.println(binaryTree.size());
    System.out.println("Count Nodes: " + binaryTree.countNodes());
    System.out.println("Height: " + binaryTree.getHeight());
    System.out.print("Traverse In Order: ");
    binaryTree.traverseInOrder();
    System.out.println("");
    System.out.print("Traverse Pre Order: ");
    binaryTree.traversePreOrder();
    System.out.println("");
    System.out.print("Traverse Post Order: ");
    binaryTree.traversePostOrder();
    System.out.println("");
    System.out.print("Traverse BFS: ");
    binaryTree.traverseBreadthFirstSearch();
    System.out.println("");
    System.out.println("Is Full Binary Tree: " + binaryTree.isFullBst());
    System.out.println("Is Complete Binary Tree: " + binaryTree.isCompleteBst());
    System.out.println("Is Perfect Binary Tree: " + binaryTree.isPerfectBst());

    binaryTree.delete("Amit 3");
    System.out.print("Traverse BFS: ");
    binaryTree.traverseBreadthFirstSearch();
    System.out.println("");
    System.out.println("Count Nodes: " + binaryTree.countNodes());

    System.out.println("Get: " + binaryTree.get("Amit12"));
    System.out.println("Contains: " + binaryTree.contains("Amit89"));
    System.out.println("Node type: " + binaryTree.type("Amit14"));
    System.out.println("Node type: " + binaryTree.type("Amit10"));
    System.out.println("Node type: " + binaryTree.type("Amit46"));
    System.out.println("Node type: " + binaryTree.type("Amit75"));

    System.out.println("Minimum: " + binaryTree.min());
    System.out.println("Maximum: " + binaryTree.max());
    System.out.println("Is Binary Search Tree: " + binaryTree.isBst());
    System.out.println("Is Full Binary Tree: " + binaryTree.isFullBst());
    System.out.println("Is Complete Binary Tree: " + binaryTree.isCompleteBst());
    System.out.println("Is Perfect Binary Tree: " + binaryTree.isPerfectBst());
    System.out.println("Is Balanced Binary Tree: " + binaryTree.isBalancedBst());
    System.out.println("Is Degenerated Binary Tree: " + binaryTree.isDegeneratedBst());
    BinaryTree tree = new BinaryTree();
    tree.add("1");
    tree.add("2");
    tree.add("3");
    System.out.println("Is Degenerated Binary Tree: " + tree.isDegeneratedBst());
  }
}
