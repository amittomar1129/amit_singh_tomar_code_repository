package datastructure_algorithms.datastructure.nonlinear.tree.splay;

import java.util.LinkedList;
import java.util.Queue;

public class SplayTree<E extends Comparable> implements Bst<E> {

  private Node<E> root;
  private int size;

  /**
   *
   */
  @Override
  public void add(E data) {
    if (data == null) {
      return;
    }
    size++;
    Node<E> newNode = new Node<>(data);
    Node<E> node = root;
    while (node != null) {
      if (data.compareTo(node.getData()) < 0) {
        if (node.getLeft() == null) {
          node.setLeft(newNode);
          newNode.setParent(node);
          break;
        } else {
          node = node.getLeft();
        }
      } else {
        if (node.getRight() == null) {
          node.setRight(newNode);
          newNode.setParent(node);
          break;
        } else {
          node = node.getRight();
        }
      }
    }
    splay(newNode);
  }

  private void splay(Node<E> node) {
    Node<E> parent = node.getParent();
    Node<E> grandParent = parent != null ? parent.getParent() : null;

    if (parent == null) {
      root = node;
      return;
    }

    if (grandParent == null || (findRelation(grandParent, parent).equals("left") && findRelation(
        parent, node).equals("right")) ||
        (findRelation(grandParent, parent).equals("right") && findRelation(parent, node).equals(
            "left"))) {
      String relation = findRelation(parent, node);
      if (relation.equals("left")) {
        node = zag(parent);
      } else {
        node = zig(parent);
      }
    } else {
      String relation = findRelation(grandParent, parent);
      if (relation.equals("left")) {
        node = zagZag(grandParent);
      } else {
        node = zigZig(grandParent);
      }
    }
    splay(node);
  }

  private void splay(Node<E> root, Node<E> node) {
    Node<E> parent = node.getParent();
    Node<E> grandParent = parent != null ? parent.getParent() : null;

    if (parent == root) {
      String relation = findRelation(parent, node);
      if (relation.equals("left")) {
        node = zag(parent);
      } else {
        node = zig(parent);
      }
      return;
    }

    if (grandParent == null || (findRelation(grandParent, parent).equals("left") && findRelation(
        parent, node).equals("right")) ||
        (findRelation(grandParent, parent).equals("right") && findRelation(parent, node).equals(
            "left"))) {
      String relation = findRelation(parent, node);
      if (relation.equals("left")) {
        node = zag(parent);
      } else {
        node = zig(parent);
      }
    } else {
      String relation = findRelation(grandParent, parent);
      if (relation.equals("left")) {
        node = zagZag(grandParent);
      } else {
        node = zigZig(grandParent);
      }
    }
    splay(node);
  }


  private String findRelation(Node<E> up, Node<E> bottom) {
    return up.getLeft() != null && up.getLeft().getData().equals(bottom.getData())
        ? "left" : "right";
  }

  // Rotate Left
  private Node<E> zig(Node<E> node) {
    Node<E> right = node.getRight();
    right.setParent(node.getParent());
    if (node.getParent() != null) {
      String relation = findRelation(node.getParent(), node);
      if (relation.equals("left")) {
        node.getParent().setLeft(right);
      } else {
        node.getParent().setRight(right);
      }
    }
    if (right.getLeft() != null) {
      Node<E> orphanedNode = right.getLeft();
      node.setRight(orphanedNode);
      orphanedNode.setParent(node);
    } else {
      node.setRight(null);
    }
    right.setLeft(node);
    node.setParent(right);
    return right;
  }

  // Rotate Left
  private Node<E> zag(Node<E> node) {
    Node<E> left = node.getLeft();
    left.setParent(node.getParent());
    if (node.getParent() != null) {
      String relation = findRelation(node.getParent(), node);
      if (relation.equals("left")) {
        node.getParent().setLeft(left);
      } else {
        node.getParent().setRight(left);
      }
    }
    if (left.getRight() != null) {
      Node<E> orphanedNode = left.getRight();
      node.setLeft(orphanedNode);
      orphanedNode.setParent(node);
    } else {
      node.setLeft(null);
    }
    left.setRight(node);
    node.setParent(left);
    return left;
  }

  // 2 Left Rotations
  private Node<E> zigZig(Node<E> node) {
    Node<E> firstZig = zig(node);
    Node<E> zigZig = zig(firstZig);
    return zigZig;
  }

  // 2 Right Rotations
  private Node<E> zagZag(Node<E> node) {
    Node<E> firstZag = zag(node);
    Node<E> zagZag = zag(firstZag);
    return zagZag;
  }

  /**
   *
   */
  @Override
  public Node<E> delete(E data) {
    if (isEmpty() || size == 1) {
      this.root = null;
      return null;
    }
    Node<E> targetNode = get(data);
    if (targetNode == null) {
      return null;
    }
    splay(targetNode);
    mergeLeftAndRightSubtrees();
    return targetNode;
  }


  private void mergeLeftAndRightSubtrees() {
    if (root.getLeft() == null) {
      root.getRight().setParent(null);
      root = root.getRight();
      return;
    }
    if (root.getRight() == null) {
      root.getLeft().setParent(null);
      root = root.getLeft();
      return;
    }
    if (root.getLeft().getRight() == null) {
      root.getLeft().setRight(root.getRight());
      root.getRight().setParent(root.getLeft());
      root.getLeft().setParent(null);
      root = root.getLeft();
    } else if (root.getRight().getLeft() == null) {
      root.getRight().setLeft(root.getLeft());
      root.getLeft().setParent(root.getRight());
      root.getRight().setParent(null);
      root = root.getRight();
    } else {
      Node<E> max = max(root.getLeft());
      splay(root.getLeft(), max);
      root.getLeft().setRight(root.getRight());
      root.getRight().setParent(root.getLeft());
      root.getLeft().setParent(null);
      root = root.getLeft();
    }
  }

  /**
   *
   */
  @Override
  public Node<E> get(E data) {
    return get(root, data);
  }

  /**
   *
   */
  @Override
  public Node<E> get(Node<E> node, E data) {
    Node<E> left = null;
    Node<E> right = null;
    if (node == null) {
      return null;
    }
    if (data.compareTo(node.getData()) == 0) {
      return node;
    }

    if (data.compareTo(node.getData()) < 0) {
      left = get(node.getLeft(), data);
    } else {
      right = get(node.getRight(), data);
    }
    return left != null ? left : right;

// Below is Through Loop approach.
//    while (node != null) {
//      if (data.compareTo(node.getData()) == 0) {
//        return node;
//      } else if (data.compareTo(node.getData()) < 0) {
//        node = node.getleft();
//      } else {
//        node = node.getright();
//      }
//    }
//    return null;
  }

  /**
   *
   */
  @Override
  public E min() {
    return min(root).getData();
  }

  /**
   *
   */
  @Override
  public E max() {
    return max(root).getData();
  }

  /**
   * This approach works only for BST.
   */
  @Override
  public Node<E> min(Node<E> node) {
    Node<E> min;
    if (node.getLeft() == null) {
      return node;
    }
    min = min(node.getLeft());
    return min;
  }

  /**
   * This approach works only for BST.
   */
  @Override
  public Node<E> max(Node<E> node) {
    Node<E> max;
    if (node.getRight() == null) {
      return node;
    }
    max = max(node.getRight());
    return max;
  }

  /**
   *
   */
  @Override
  public boolean contains(E data) {
    return contains(root, data);
  }

  /**
   *
   */
  @Override
  public boolean contains(Node<E> node, E data) {
    boolean result = false;
    if (node == null) {
      return false;
    }
    if (data.compareTo(node.getData()) == 0) {
      return true;
    }

    if (data.compareTo(node.getData()) < 0) {
      result = contains(node.getLeft(), data);
    } else if (!result) {
      result = contains(node.getRight(), data);
    }
    return result;
  }

  /**
   *
   */
  @Override
  public boolean isEmpty() {
    return root == null;
  }

  /**
   *
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   *
   */
  @Override
  public int countNodes() {
    return countNodes(root);
  }

  private int countNodes(Node<E> node) {
    int left, right;
    if (node == null) {
      return 0;
    }
    left = countNodes(node.getLeft());
    right = countNodes(node.getRight());
    return left + right + 1;
  }

  /**
   *
   */
  @Override
  public int getHeight() {
    return getHeight(root) - 1;
  }

  /**
   *
   */
  @Override
  public int getHeight(Node<E> node) {
    int left, right;
    if (node == null) {
      return 0;
    }
    left = getHeight(node.getLeft());
    right = getHeight(node.getRight());
    return Math.max(left, right) + 1;
  }

  /**
   *
   */
  @Override

  public void traverseInOrder() {
    if (isEmpty()) {
      System.out.println("BST is empty!");
    } else {
      traverseInOrder(root);
    }
  }

  /**
   *
   */
  @Override
  public void traversePreOrder() {
    if (isEmpty()) {
      System.out.println("BST is empty!");
    } else {
      traversePreOrder(root);
    }
  }

  /**
   *
   */
  @Override
  public void traversePostOrder() {
    if (isEmpty()) {
      System.out.println("BST is empty!");
    } else {
      traversePostOrder(root);
    }
  }

  /**
   *
   */
  @Override
  public void traverseInOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    traverseInOrder(node.getLeft());
    System.out.print(node.getData() + ", ");
    traverseInOrder(node.getRight());
  }


  /**
   *
   */
  @Override
  public void traversePreOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    System.out.print(node.getData() + ", ");
    traversePreOrder(node.getLeft());
    traversePreOrder(node.getRight());
  }

  /**
   *
   */
  @Override
  public void traversePostOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    traversePostOrder(node.getLeft());
    traversePostOrder(node.getRight());
    System.out.print(node.getData() + ", ");
  }

  /**
   *
   */
  @Override
  public void traverseBreadthFirstSearch() {
    Queue<Node<E>> queue = new LinkedList();
    queue.offer(root);

    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      System.out.print(node.getData() + ", ");
      if (node.getLeft() != null) {
        queue.offer(node.getLeft());
      }
      if (node.getRight() != null) {
        queue.offer(node.getRight());
      }
    }
  }

  /**
   *
   */
  @Override
  public NodeType type(E data) {
    Node<E> node = get(data);
    if (node == null) {
      return NodeType.NOT_FOUND;
    }
    if (node == root) {
      return NodeType.ROOT;
    }
    if (node.getLeft() == null && node.getRight() == null) {
      return NodeType.LEAF;
    }
    return NodeType.INTERNAL;
  }

  /**
   * This is implemented through BFS concept.
   */
  @Override
  public boolean isBst() {
//    return isBstThroughBfs();
    return isBstThroughDfs(root);
  }

  private boolean isBstThroughBfs() {
    Queue<Node<E>> queue = new LinkedList();
    queue.offer(root);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      Node<E> left = node.getLeft();
      Node<E> right = node.getRight();
      if (left != null) {
        if (left.getData().compareTo(node.getData()) > 0) {
          return false;
        }
        queue.offer(left);
      }
      if (right != null) {
        if (node.getData().compareTo(right.getData()) > 0) {
          return false;
        }
        queue.offer(right);
      }
    }
    return true;
  }

  private boolean isBstThroughDfs(Node<E> node) {
    boolean isBst;
    if (node == null) {
      return true;
    }
    if ((node.getLeft() != null
        && node.getLeft().getData().compareTo(node.getData()) >= 0) || (
        node.getRight() != null
            && node.getData().compareTo(node.getRight().getData()) > 0)) {
      return false;
    }

    isBst = isBstThroughDfs(node.getLeft());
    isBst = isBst ? isBstThroughDfs(node.getRight()) : isBst;
    return isBst;
  }

  /**
   *
   */
  @Override
  public boolean isFullBst() {
    return isFullBst(root);
  }

  private boolean isFullBst(Node<E> node) {
    boolean isFull;
    if (node == null) {
      return true;
    }
    if ((node.getLeft() != null && node.getRight() == null) || (
        node.getLeft() == null && node.getRight() != null)) {
      return false;
    }

    isFull = isFullBst(node.getLeft());
    isFull = isFull ? isFullBst(node.getRight()) : isFull;
    return isFull;
  }

  /**
   *
   */
  @Override
  public boolean isCompleteBst() {
//    return isCompleteBst(root, 0);
    return isCompleteBstThroughBfs(root);

  }


  private boolean isCompleteBst(Node<E> node, int index) {
    boolean isComplete;
    if (node == null) {
      return true;
    }
    if (index >= size) {
      return false;
    }
    isComplete = isCompleteBst(node.getLeft(), 2 * index + 1);
    isComplete = isComplete ? isCompleteBst(node.getRight(), 2 * index + 2) : isComplete;
    return isComplete;
  }

  private boolean isCompleteBstThroughBfs(Node<E> rootNode) {
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(rootNode);
    boolean end = false;

    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      if (node.getLeft() != null) {
        if (end) {
          return false;
        }
        queue.add(node.getLeft());
      } else {
        end = true;
      }
      if (node.getRight() != null) {
        if (end) {
          return false;
        }
        queue.add(node.getRight());
      } else {
        end = true;
      }
    }
    return true;
  }

  /**
   *
   */
  @Override
  public boolean isPerfectBst() {
//    return isPerfectBst(root);
    return isPerfectBstThroughBfs(root);
  }

  private boolean isPerfectBstThroughBfs(Node<E> rootNode) {
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(rootNode);
    int index = 1;
    boolean leafFound = false;
    for (int i = 1; !queue.isEmpty(); i++) {
      Node<E> node = queue.poll();

      if ((node.getLeft() != null && node.getRight() == null) || (
          node.getLeft() == null && node.getRight() != null)) {
        return false;
      } else if (leafFound) {
        if (node.getLeft() != null) {
          return false;
        }
      }

      if (i == 2 * index) {
        if (node.getLeft() == null) {
          leafFound = true;
        }
        index = 2 * index;
      }
      if (node.getLeft() != null) {
        queue.offer(node.getLeft());
      }
      if (node.getRight() != null) {
        queue.offer(node.getRight());
      }
    }
    return true;
  }

  private boolean isPerfectBst(Node<E> node) {
    boolean isPerfect;
    if (node == null) {
      return true;
    }

    if ((node.getLeft() == null && node.getRight() != null) || (
        node.getLeft() != null && node.getRight() == null)) {
      return false;
    }
    Node<E> left = node.getLeft();
    Node<E> right = node.getRight();
    if (left != null && right != null) {
      if ((left.getLeft() == null && left.getRight() == null
          && right.getLeft() != null && right.getRight() != null) || (
          left.getLeft() != null && left.getRight() != null
              && right.getLeft() == null && right.getRight() == null)) {
        return false;
      }
    }

    isPerfect = isPerfectBst(node.getLeft());
    isPerfect = isPerfect ? isPerfectBst(node.getRight()) : isPerfect;
    return isPerfect;
  }


  /**
   *
   */
  @Override
  public boolean isBalancedBst() {
    return isBalancedBst(root) != -1;
  }

  private int isBalancedBst(Node<E> node) {
    int left, right;
    if (node == null) {
      return 0;
    }

    left = isBalancedBst(node.getLeft());
    if (left == -1) {
      return -1;
    }
    right = isBalancedBst(node.getRight());
    if (right == -1) {
      return -1;
    }
    if (right - left <= -2 || right - left >= 2) {
      return -1;
    }

    return Math.max(left, right) + 1;
  }

  /**
   *
   */
  @Override
  public boolean isDegeneratedBst() {
    return isDegeneratedBst(root);
  }

  private boolean isDegeneratedBst(Node<E> node) {
    boolean isDegenerated;
    if (node == null) {
      return true;
    }
    if (node.getLeft() != null && node.getRight() != null) {
      return false;
    }
    isDegenerated = isDegeneratedBst(node.getLeft());
    isDegenerated = isDegenerated ? isDegeneratedBst(node.getRight()) : isDegenerated;
    return isDegenerated;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
