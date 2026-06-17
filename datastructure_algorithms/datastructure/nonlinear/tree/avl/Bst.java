package datastructure_algorithms.datastructure.nonlinear.tree.avl;

public interface Bst<E extends Comparable> {

  void add(E data);

  // Search the target node from root and delete it.
  Node<E> delete(E data);

  // Gets the node, if present or else returns null.
  Node<E> get(E data);

  // Gets the node inside subtree of provided node, if present or else returns null.
  Node<E> get(
      Node<E> node, E data);

  E min();

  E max();

  // Finds minimum in the subtree of input node.
  Node<E> min(
      Node<E> node);

  // Finds maximum in the subtree of input node.
  Node<E> max(
      Node<E> node);

  // Search the node from the root of the tree.
  boolean contains(E data);

  // Search the node inside the subtree of provided node.
  boolean contains(Node<E> node, E data);

  boolean isEmpty();

  int size();

  int countNodes();

  int getHeight();

  int getHeight(Node<E> node);

  void traverseInOrder();

  void traversePreOrder();

  void traversePostOrder();

  // Traverse in-order(DFS- Depth First Search) in subtree of provided node.
  void traverseInOrder(Node<E> node);

  // Traverse pre-order(DFS- Depth First Search) in subtree of provided node.
  void traversePreOrder(Node<E> node);

  // Traverse post-order(DFS- Depth First Search) in subtree of provided node.
  void traversePostOrder(Node<E> node);

  // Traverse in BFS approach.
  void traverseBreadthFirstSearch();

  // Check if node is root/leaf of internal node.
  NodeType type(E data);

  boolean isBst();

  boolean isFullBst();

  boolean isCompleteBst();

  boolean isPerfectBst();

  boolean isBalancedBst();

  boolean isDegeneratedBst();
}

enum NodeType {
  NOT_FOUND, ROOT, INTERNAL, LEAF;
}
