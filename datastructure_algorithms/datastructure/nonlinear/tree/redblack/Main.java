package datastructure_algorithms.datastructure.nonlinear.tree.redblack;

public class Main {

  public static void main(String[] args) {

//    RedBlackTree<Integer> tree = new RedBlackTree();
//    System.out.println("IsEmpty: "+ tree.isEmpty());
//    System.out.println("Size: "+ tree.size());
//    tree.add(10);
//    tree.add(18);
//    tree.add(7);
//    tree.add(15);
//    tree.add(16);
//    tree.add(30);
//    tree.add(25);
//    tree.add(40);
//    tree.add(60);
//    tree.add(2);
//    tree.add(1);
//    tree.add(70);
//
//    System.out.println("IsEmpty: "+ tree.isEmpty());
//    System.out.println("Size: "+ tree.size());
//
//    System.out.print("Traverse In-Order: ");
//    tree.traverseInOrder();
//    System.out.print("Traverse Pre-Order: ");
//    tree.traversePreOrder();
//    System.out.print("Traverse Post-Order: ");
//    tree.traversePostOrder();
//    System.out.print("Traverse BFS: ");
//    tree.traverseBreadthFirstSearch();
//    System.out.println("Is RedBlack Tree: " + tree.isRedBlackBst());

    RedBlackTree<Integer> tree = new RedBlackTree();
    System.out.println("IsEmpty: "+ tree.isEmpty());
    System.out.println("Size: "+ tree.size());
    tree.add(40);
    tree.add(20);
    tree.add(60);
    tree.add(10);
    tree.add(30);
    tree.add(50);
    tree.add(80);
    tree.add(70);
    tree.add(90);
    tree.add(100);

    System.out.println("IsEmpty: "+ tree.isEmpty());
    System.out.println("Size: "+ tree.size());

    System.out.print("Traverse In-Order: ");
    tree.traverseInOrder();
    System.out.print("Traverse Pre-Order: ");
    tree.traversePreOrder();
    System.out.print("Traverse Post-Order: ");
    tree.traversePostOrder();
    System.out.print("Traverse BFS: ");
    tree.traverseBreadthFirstSearch();
    System.out.println("Is RedBlack Tree: " + tree.isRedBlackBst());
  }
}
