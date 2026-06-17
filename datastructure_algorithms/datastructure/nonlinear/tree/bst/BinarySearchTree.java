package datastructure_algorithms.datastructure.nonlinear.tree.bst;

import java.util.LinkedList;
import java.util.Queue;

final class BinarySearchTree<E extends Comparable> implements Bst<E> {

  private Node<E> root;
  private int size;

  /** */
  @Override
  public void add(E val) {
    size++;
    Node<E> newNode = new Node(val);
    if (isEmpty()) {
      root = newNode;
      return;
    }
    // Adds recursively
    add(root, val);
    //    addByLoop(val);
  }

  private Node<E> add(Node<E> node, E val) {
    if (node == null) {
      return new Node<E>(val);
    }

    if (val.compareTo(node.val) < 0) {
      node.left = add(node.left, val);
    } else {
      node.right = add(node.right, val);
    }
    return node;
  }

  private void addByLoop(E val) {
    Node<E> newNode = new Node(val);
    Node<E> node = root;
    // Traverse into the tree to find out suitable place for new node.
    while (node != null) {
      // If new node is less, traverse to left subtree.
      if (val.compareTo(node.val) < 0) {
        if (node.left == null) {
          node.left = newNode;
          return;
        } else {
          node = node.left;
        }
      }
      // If new node is greater or equal to, then traverse to right subtree.
      else {
        if (node.right == null) {
          node.right = newNode;
          return;
        } else {
          node = node.right;
        }
      }
    }
  }

  /** */
  @Override
  public Node<E> delete(E val) {
    return delete(root, val);
  }

  private Node<E> delete(Node<E> node, E val) {
    if (node == null) {
      return null;
    }
    if (val.compareTo(node.val) == 0) {
      if (node == root && size == 1) { // has only root
        size--;
        root = null;
        return null;
      } else if (node.left == null && node.right == null) { // leaf node
        size--;
        return null;
      } else if (node.left == null) { // internal node with one child
        size--;
        if (node == root) {
          root = node.right;
        }
        return node.right;
      } else if (node.right == null) { // internal node with one child
        size--;
        if (node == root) {
          root = node.left;
        }
        return node.left;
      } else { // node with both children
        size--;
        if (node.right.left == null) {
          node.right.left = node.left;
          if (node == root) {
            root = node.right;
          }
          return node.right;
        }
        // Find and remove the pre-order successor from right subtree.
        Node<E> preOrderSuccessor = min(node.right);
        Node<E> newNode = new Node(preOrderSuccessor.val);
        Node<E> newSubTree = delete(node.right, preOrderSuccessor.val);
        newNode.left = node.left;
        newNode.right = newSubTree;
        if (node == root) {
          root = newNode;
        }
        return newNode;
      }
    }

    if (val.compareTo(node.val) < 0) {
      node.left = delete(node.left, val);
    } else {
      node.right = delete(node.right, val);
    }
    return node;
  }

  /** */
  @Override
  public Node<E> get(E val) {
    return get(root, val);
  }

  /** */
  @Override
  public Node<E> get(Node<E> node, E val) {
    Node<E> left = null;
    Node<E> right = null;
    if (node == null) {
      return null;
    }
    if (val.compareTo(node.val) == 0) {
      return node;
    }

    if (val.compareTo(node.val) < 0) {
      left = get(node.left, val);
    } else {
      right = get(node.right, val);
    }
    return left != null ? left : right;

    // Below is Through Loop approach.
    //    while (node != null) {
    //      if (val.compareTo(node.getval()) == 0) {
    //        return node;
    //      } else if (val.compareTo(node.getval()) < 0) {
    //        node = node.getLeftChild();
    //      } else {
    //        node = node.getRightChild();
    //      }
    //    }
    //    return null;
  }

  /** */
  @Override
  public E min() {
    return min(root).val;
  }

  /** */
  @Override
  public E max() {
    return max(root).val;
  }

  /** This approach works only for BST. */
  @Override
  public Node<E> min(Node<E> node) {
    if (node.left == null) {
      return node;
    }
    Node<E> min = min(node.left);
    return min;
  }

  /** This approach works only for BST. */
  @Override
  public Node<E> max(Node<E> node) {
    if (node.right == null) {
      return node;
    }
    Node<E> max = max(node.right);
    return max;
  }

  /** */
  @Override
  public boolean contains(E val) {
    return contains(root, val);
  }

  /** */
  @Override
  public boolean contains(Node<E> node, E val) {
    boolean result = false;
    if (node == null) {
      return false;
    }
    if (val.compareTo(node.val) == 0) {
      return true;
    }

    if (val.compareTo(node.val) < 0) {
      result = contains(node.left, val);
    } else if (!result) {
      result = contains(node.right, val);
    }
    return result;
  }

  /** */
  @Override
  public boolean isEmpty() {
    return root == null;
  }

  /** */
  @Override
  public int size() {
    return this.size;
  }

  /** */
  @Override
  public int countNodes() {
    return countNodes(root);
  }

  private int countNodes(Node<E> node) {
    if (node == null) {
      return 0;
    }
    int left = countNodes(node.left);
    int right = countNodes(node.right);
    return left + right + 1;
  }

  /** */
  @Override
  public int getHeight() {
    return getHeight(root) - 1;
  }

  /** */
  @Override
  public int getHeight(Node<E> node) {
    if (node == null) {
      return 0;
    }
    int left = getHeight(node.left);
    int right = getHeight(node.right);
    return Math.max(left, right) + 1;
  }

  /** */
  @Override
  public void traverseInOrder() {
    if (isEmpty()) {
      System.out.println("BST is empty!");
    } else {
      traverseInOrder(root);
    }
  }

  /** */
  @Override
  public void traversePreOrder() {
    if (isEmpty()) {
      System.out.println("BST is empty!");
    } else {
      traversePreOrder(root);
    }
  }

  /** */
  @Override
  public void traversePostOrder() {
    if (isEmpty()) {
      System.out.println("BST is empty!");
    } else {
      traversePostOrder(root);
    }
  }

  /** */
  @Override
  public void traverseInOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    traverseInOrder(node.left);
    System.out.print(node.val + ", ");
    traverseInOrder(node.right);
  }

  /** */
  @Override
  public void traversePreOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    System.out.print(node.val + ", ");
    traversePreOrder(node.left);
    traversePreOrder(node.right);
  }

  /** */
  @Override
  public void traversePostOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    traversePostOrder(node.left);
    traversePostOrder(node.right);
    System.out.print(node.val + ", ");
  }

  /** */
  @Override
  public void traverseBreadthFirstSearch() {
    Queue<Node<E>> queue = new LinkedList();
    queue.offer(root);

    while (!queue.isEmpty()) {
      for (int i = 0; i < queue.size(); i++) {
        Node<E> node = queue.poll();
        System.out.print(node.val + ", ");
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }
  }

  /** */
  @Override
  public NodeType type(E val) {
    Node<E> node = get(val);
    if (node == null) {
      return NodeType.NOT_FOUND;
    }
    if (node == root) {
      return NodeType.ROOT;
    }
    if (node.left == null && node.right == null) {
      return NodeType.LEAF;
    }
    return NodeType.INTERNAL;
  }

  /** This is implemented through BFS concept. */
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
      Node<E> leftChild = node.left;
      Node<E> rightChild = node.right;
      if (leftChild != null) {
        if (leftChild.val.compareTo(node.val) > 0) {
          return false;
        }
        queue.offer(leftChild);
      }
      if (rightChild != null) {
        if (node.val.compareTo(rightChild.val) > 0) {
          return false;
        }
        queue.offer(rightChild);
      }
    }
    return true;
  }

  private boolean isBstThroughDfs(Node<E> node) {
    if (node == null) {
      return true;
    }
    if ((node.left != null && node.left.val.compareTo(node.val) >= 0)
        || (node.right != null && node.val.compareTo(node.right.val) > 0)) {
      return false;
    }

    return isBstThroughDfs(node.left) && isBstThroughDfs(node.right);
  }

  /** */
  @Override
  public boolean isFullBst() {
    return isFullBst(root);
  }

  private boolean isFullBst(Node<E> node) {
    if (node == null) {
      return true;
    }
    if ((node.left == null) != (node.right == null)) {
      return false;
    }

    return isFullBst(node.left) && isFullBst(node.right);
  }

  /** */
  @Override
  public boolean isCompleteBst() {
    //    return isCompleteBst(root, 0);
    return isCompleteBstThroughBfs(root);
  }

  private boolean isCompleteBst(Node<E> node, int index) {
    if (node == null) {
      return true;
    }
    if (index >= size) {
      return false;
    }
    return isCompleteBst(node.left, 2 * index + 1) && isCompleteBst(node.right, 2 * index + 2);
  }

  private boolean isCompleteBstThroughBfs(Node<E> rootNode) {
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(rootNode);
    boolean end = false;

    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      if (node == null) {
        end = true;
      } else if (end) {
        return false;
      } else {
        queue.offer(node.left);
        queue.offer(node.right);
      }
    }
    return true;
  }

  /** */
  @Override
  public boolean isPerfectBst() {
    int depth = depth(root);
    return isPerfectBst(root, 0, depth);
    //    return isPerfectBstThroughBfs(root);
  }

  private int depth(Node<E> node) {
    int depth = 0;
    while (node != null) {
      depth++;
      node = node.left;
    }
    return depth;
  }

  private boolean isPerfectBst(Node<E> node, int level, int expectedDepth) {
    if (node == null) {
      return true;
    }
    if (node.left == null && node.right == null) {
      return level + 1 == expectedDepth;
    }
    if (node.left == null || node.right == null) {
      return false;
    }
    return isPerfectBst(node.left, level + 1, expectedDepth)
        && isPerfectBst(node.right, level + 1, expectedDepth);
  }

  private boolean isPerfectBstThroughBfs(Node<E> rootNode) {
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(rootNode);
    int expectedNodes  = 1;

    while (!queue.isEmpty()) {
      if (queue.size() != expectedNodes) {
        return false;
      }
      for (int i = 0; i < queue.size(); i++) {
        Node<E> node = queue.poll();

        // Must have either 0 or 2 children
        if ((node.left == null) != (node.right == null)) {
          return false;
        }

        if (node.left != null) {
          queue.offer(node.left);
          queue.offer(node.right);
        }
      }
      expectedNodes *= 2;
    }

    return true;
  }

  /** */
  @Override
  public boolean isBalancedBst() {
    return isBalancedBst(root) != -1;
  }

  private int isBalancedBst(Node<E> node) {
    if (node == null) {
      return 0;
    }

    int left = isBalancedBst(node.left);
    if (left == -1) {
      return -1;
    }
    int right = isBalancedBst(node.right);
    if (right == -1) {
      return -1;
    }
    if (Math.abs(left - right) > 1) {
      return -1;
    }

    return Math.max(left, right) + 1;
  }

  /** */
  @Override
  public boolean isDegeneratedBst() {
    return isDegeneratedBst(root);
  }

  private boolean isDegeneratedBst(Node<E> node) {
    if (node == null) {
      return true;
    }
    if (node.left != null && node.right != null) {
      return false;
    }

    return isDegeneratedBst(node.left) && isDegeneratedBst(node.right);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
