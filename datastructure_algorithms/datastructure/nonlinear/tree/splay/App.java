package datastructure_algorithms.datastructure.nonlinear.tree.splay;

public class App {

  public static void main(String[] args) {
    SplayTree<Integer> tree = new SplayTree<>();
    tree.add(23);
    tree.add(54);
    tree.add(1);
    tree.add(75);
    tree.add(9);
    tree.add(22);
    tree.add(32);
    tree.add(90);
//    tree.add(2);
    System.out.print("Traverse In Order: ");
    tree.traverseInOrder();
    System.out.println("");
    System.out.print("Traverse BFS: ");
    tree.traverseBreadthFirstSearch();
//    System.out.println("");
//    tree.delete(15);
//    System.out.print("Traverse BFS: ");
//    tree.traverseBreadthFirstSearch();
//    System.out.println("");
//    tree.delete(7);
//    System.out.print("Traverse BFS: ");
//    tree.traverseBreadthFirstSearch();
//    System.out.println("");
//    tree.delete(16);
//    System.out.print("Traverse BFS: ");
//    tree.traverseBreadthFirstSearch();
//    System.out.println("");
//    tree.delete(13);
//    System.out.print("Traverse BFS: ");
//    tree.traverseBreadthFirstSearch();
  }

}
