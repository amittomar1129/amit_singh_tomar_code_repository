package datastructure_algorithms.datastructure.nonlinear.tree.bst;

public class BinarySearchTreeMain {

  public static void main(String[] args) {

    BinarySearchTree<String> bst = new BinarySearchTree<String>();
    System.out.println("Is Empty: " + bst.isEmpty());
    System.out.println("Size: " + bst.size());
    bst.add("Amit14");
    bst.add("Amit20");
    bst.add("Amit19");
    bst.add("Amit12");
    bst.add("Amit 9");
    bst.add("Amit35");
    bst.add("Amit46");
    bst.add("Amit89");
    bst.add("Amit75");
    bst.add("Amit 3");
    bst.add("Amit 4");
    bst.add("Amit11");
    bst.add("Amit10");
    bst.add("Amit13");
    bst.add("Amit 1");
    System.out.println("Is Empty: " + bst.isEmpty());
    System.out.println("Size: " + bst.size());
    System.out.println("Height: " + bst.getHeight());
    System.out.println("Count Nodes: " + bst.countNodes());

    bst.delete("Amit 3");
    System.out.println("After Delete.....");
    System.out.println("Is Empty: " + bst.isEmpty());
    System.out.println("Size: " + bst.size());
    System.out.println("Height: " + bst.getHeight());
    System.out.println("Count Nodes: " + bst.countNodes());
    System.out.println("Search: " + bst.get("Amit89"));
    Node<String> subNode = bst.get("Amit12");
    System.out.println("Search in Subtree: " + bst.get(subNode, "Amit11"));
    System.out.println("Contains: " + bst.contains("Amit14"));

    System.out.print("Traverse In Order: ");
    bst.traverseInOrder();
    System.out.println("");
    System.out.print("Traverse Pre Order: ");
    bst.traversePreOrder();
    System.out.println("");
    System.out.print("Traverse Post Order: ");
    bst.traversePostOrder();
    System.out.println("");
    System.out.print("Traverse BFS: ");
    bst.traverseBreadthFirstSearch();
    System.out.println("");

    System.out.println("Minimum: " + bst.min());
    System.out.println("Maximum: " + bst.max());
    System.out.println("Node type: " + bst.type("Amit14"));
    System.out.println("Node type: " + bst.type("Amit10"));
    System.out.println("Node type: " + bst.type("Amit46"));
    System.out.println("Node type: " + bst.type("Amit75"));

    System.out.println("Is Bst: " + bst.isBst());
    System.out.println("Is Full BST: " + bst.isFullBst());
    System.out.println("Is Complete BST: " + bst.isCompleteBst());
    System.out.println("Is Perfect BST: " + bst.isPerfectBst());
    System.out.println("Is Balanced BST: " + bst.isBalancedBst());
    System.out.println("Is Degenerated BST: " + bst.isDegeneratedBst());
  }
}
