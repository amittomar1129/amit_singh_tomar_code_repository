package datastructure_algorithms.datastructure.nonlinear.tree.avl;

public class Main {

  public static void main(String[] args) {

    AvlBinarySearchTree<Integer> avl = new AvlBinarySearchTree();
    System.out.println("Is Empty: " + avl.isEmpty());
    System.out.println("Size: " + avl.size());
    avl.add(14);
    avl.add(17);
    avl.add(11);
    avl.add(7);
    avl.add(53);
    avl.add(4);
    avl.add(13);
    avl.add(12);
    avl.add(8);
    avl.add(60);
    avl.add(19);
    avl.add(16);
    avl.add(20);

    System.out.println("Is Empty: " + avl.isEmpty());
    System.out.println("Size: " + avl.size());
    System.out.println("Is Balanced: " + avl.isBalancedBst());

    System.out.print("Traverse In-Order: ");
    avl.traverseInOrder();
    System.out.print("Traverse Pre-Order: ");
    avl.traversePreOrder();
    System.out.print("Traverse Post-Order: ");
    avl.traversePostOrder();
    System.out.print("Traverse BFS: ");
    avl.traverseBreadthFirstSearch();

    System.out.println("After Deletion >>>>");
    avl.delete(8);
    avl.delete(7);
    avl.delete(11);
    avl.delete(14);
    avl.delete(17);

    System.out.print("Traverse In-Order: ");
    avl.traverseInOrder();
    System.out.print("Traverse Pre-Order: ");
    avl.traversePreOrder();
    System.out.print("Traverse Post-Order: ");
    avl.traversePostOrder();
    System.out.print("Traverse BFS: ");
    avl.traverseBreadthFirstSearch();
    System.out.println("Is Balanced: " + avl.isBalancedBst());

    System.out.println("");
    System.out.println("");
    System.out.println("");
    System.out.println("");
    System.out.println("");
    System.out.println("Second Complex Tree Testing.....");
    AvlBinarySearchTree tree = new AvlBinarySearchTree();
    tree.add(50);
    tree.add(40);
    tree.add(60);
    tree.add(30);
    tree.add(45);
    tree.add(55);
    tree.add(70);
    tree.add(20);
    tree.add(37);
    tree.add(44);
    tree.add(47);
    tree.add(53);
    tree.add(58);
    tree.add(67);
    tree.add(80);
    tree.add(10);
    tree.add(22);
    tree.add(35);
    tree.add(39);
    tree.add(49);
    tree.add(90);
    tree.add(1);
    tree.add(11);
    tree.add(32);

    System.out.println("Is Empty: " + tree.isEmpty());
    System.out.println("Size: " + tree.size());
    System.out.println("Is Balanced: " + tree.isBalancedBst());

    System.out.print("Traverse In-Order: ");
    tree.traverseInOrder();
    System.out.print("Traverse Pre-Order: ");
    tree.traversePreOrder();
    System.out.print("Traverse Post-Order: ");
    tree.traversePostOrder();
    System.out.print("Traverse BFS: ");
    tree.traverseBreadthFirstSearch();

    System.out.println("After Deletion >>>>");
    tree.delete(40);

    System.out.print("Traverse In-Order: ");
    tree.traverseInOrder();
    System.out.print("Traverse Pre-Order: ");
    tree.traversePreOrder();
    System.out.print("Traverse Post-Order: ");
    tree.traversePostOrder();
    System.out.print("Traverse BFS: ");
    tree.traverseBreadthFirstSearch();
    System.out.println("Is Balanced: " + tree.isBalancedBst());
  }
}
