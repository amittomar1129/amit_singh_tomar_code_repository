package datastructure_algorithms.datastructure.nonlinear.tree.bst;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public final class BinaryTree<E extends Comparable> implements Bst<E> {

  private Node<E> root;
  private int size;

  public BinaryTree() {}

  public BinaryTree(Node<E> root) {
    this.root = root;
  }

  /** Adds through BFS or Level order traversal. */
  @Override
  public void add(E data) {
    size++;
    Node<E> newNode = new Node<>(data);
    if (isEmpty()) {
      root = newNode;
      return;
    }
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      if (node.left == null) {
        node.left = newNode;
        return;
      } else {
        queue.offer(node.left);
      }
      if (node.right == null) {
        node.right = newNode;
        return;
      } else {
        queue.offer(node.right);
      }
    }
  }

  /**
   * Adds the target new node in the subtree of input root node through BFS or Level order
   * traversal.
   */
  private void add(Node<E> rootNode, Node<E> newNode) {
    if (rootNode == null) {
      return;
    }
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(rootNode);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      if (node.left == null) {
        node.left = newNode;
        return;
      } else {
        queue.offer(node.left);
      }
      if (node.right == null) {
        node.right = newNode;
        return;
      } else {
        queue.offer(node.right);
      }
    }
  }

  /** */
  @Override
  public Node<E> delete(E data) {
    return delete(root, data);
  }

  private Node<E> delete(Node<E> node, E data) {
    if (node == null) {
      return null;
    }
    if (isEqual(node.val, data)) { // target node is found in the tree.
      if (size == 1) { // If only root present then delete it.
        size--;
        root = null;
        return null;
      } // Leaf node can be simply deleted.
      else if (node.left == null && node.right == null) {
        size--;
        return null;
      } // If target node is internal and having only right child, Replace it with that.
      else if (node.left == null) {
        size--;
        return node.right;
      } // If target node is internal and having only left child, Replace it with that.
      else if (node.right == null) {
        size--;
        return node.left;
      } else { // If target node is internal that has both children.
        size--;
        if (node == root) {
          // Always replacing with right child node.
          if (node.right.left == null) {
            node.right.left = node.left;
            root = node.right;
            return root;
          } else {
            Node<E> orphanedNode = node.right.left;
            add(node.left, orphanedNode);
            node.right.left = node.left;
            root = node.right;
            return root;
          }
        } else {
          // Always replacing with right child node.
          if (node.right.left == null) {
            node.right.left = node.left;
            return node.right;
          } else {
            Node<E> orphanedNode = node.right.left;
            add(node.left, orphanedNode);
            node.right.left = node.left;
            return node.right;
          }
        }
      }
    }

    node.left = delete(node.left, data);
    node.right = delete(node.right, data);
    return node;
  }

  /** */
  @Override
  public Node<E> get(E data) {
    return get(root, data);
  }

  /** */
  @Override
  public Node<E> get(Node<E> node, E data) {
    Node<E> result;
    if (node == null) {
      return null;
    }
    if (isEqual(data, node.val)) {
      return node;
    }
    result = get(node.left, data);
    result = result == null ? get(node.right, data) : result;
    return result;
  }

  private boolean isEqual(E data1, E data2) {
    return data1.compareTo(data2) == 0;
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

  /** */
  @Override
  public Node<E> min(Node<E> node) {
    if (node == null) {
      return null;
    }
    Node<E> min = getMinimumNode(node, node.left, node.right);

    Node<E> left = min(node.left);
    Node<E> right = min(node.right);

    return getMinimumNode(left, min, right);
  }

  /** */
  @Override
  public Node<E> max(Node<E> node) {
    if (node == null) {
      return null;
    }
    Node<E> max = getMaximumNode(node, node.left, node.right);

    Node<E> left = max(node.left);
    Node<E> right = max(node.right);

    return getMaximumNode(left, right, max);
  }

  private Node<E> getMinimumNode(Node<E>... nodes) {
    return Arrays.stream(nodes)
        .filter(node -> node != null)
        .reduce((node1, node2) -> node1.val.compareTo(node2.val) < 0 ? node1 : node2)
        .get();
  }

  private Node<E> getMaximumNode(Node<E>... nodes) {
    return Arrays.stream(nodes)
        .filter(node -> node != null)
        .reduce((node1, node2) -> node1.val.compareTo(node2.val) > 0 ? node1 : node2)
        .get();
  }

  /** */
  @Override
  public boolean contains(E data) {
    return contains(root, data);
  }

  /** */
  @Override
  public boolean contains(Node<E> node, E data) {
    boolean result;
    if (node == null) {
      return false;
    }
    if (isEqual(data, node.val)) {
      return true;
    }
    result = contains(node.left, data);
    result = !result ? contains(node.right, data) : result;
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
    return size;
  }

  /** */
  @Override
  public int countNodes() {
    return countNodes(root);
  }

  private int countNodes(Node<E> node) {
    int left, right;
    if (node == null) {
      return 0;
    }
    left = countNodes(node.left);
    right = countNodes(node.right);
    return 1 + left + right;
  }

  /** */
  @Override
  public int getHeight() {
    return getHeight(root) - 1;
  }

  /** */
  @Override
  public int getHeight(Node<E> node) {
    int left, right;
    if (node == null) {
      return 0;
    }
    left = getHeight(node.left);
    right = getHeight(node.right);
    return Math.max(left, right) + 1;
  }

  /** */
  @Override
  public void traverseInOrder() {
    traverseInOrder(root);
  }

  /** */
  @Override
  public void traversePreOrder() {
    traversePreOrder(root);
  }

  /** */
  @Override
  public void traversePostOrder() {
    traversePostOrder(root);
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
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
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

  /** */
  @Override
  public NodeType type(E data) {
    Node<E> node = get(data);
    if (node == null) {
      return NodeType.NOT_FOUND;
    } else if (node == root) {
      return NodeType.ROOT;
    } else if (node.left == null && node.right == null) {
      return NodeType.LEAF;
    }
    return NodeType.INTERNAL;
  }

  /** */
  @Override
  public boolean isBst() {
    return isBst(root);
  }

  private boolean isBst(Node<E> node) {
    boolean isBst;
    if (node == null) {
      return true;
    }
    if (node.left != null && node.left.val.compareTo(node.val) >= 0
        || node.right != null && node.val.compareTo(node.right.val) > 0) {
      return false;
    }
    isBst = isBst(node.left);
    isBst = isBst ? isBst(node.right) : isBst;
    return isBst;
  }

  /** */
  @Override
  public boolean isFullBst() {
    return isFullBst(root);
  }

  private boolean isFullBst(Node<E> node) {
    boolean isFullBst;
    if (node == null) {
      return true;
    }
    // Search for any node that has only one child then tree is not a full bst.
    if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
      return false;
    }
    isFullBst = isFullBst(node.left);
    isFullBst = isFullBst ? isFullBst(node.right) : isFullBst;
    return isFullBst;
  }

  /** */
  @Override
  public boolean isCompleteBst() {
    //    return isCompleteBstThroughDfs(root, 0);
    return isCompleteBstThroughBfs(root);
  }

  /** */
  private boolean isCompleteBstThroughDfs(Node<E> node, int index) {
    boolean left, right;
    if (node == null) {
      return true;
    }
    // If any middle space is empty then definitely the index of last node will be greater than
    // equal to the total nodes.
    if (index >= size) {
      return false;
    }
    left = isCompleteBstThroughDfs(node.left, 2 * index + 1);
    right = isCompleteBstThroughDfs(node.right, 2 * index + 2);
    return left && right;
  }

  // If any node is missing and later on we found any node then it`s not a complete binary tree.
  private boolean isCompleteBstThroughBfs(Node<E> rootNode) {
    boolean end = false;
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(rootNode);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      if (node.left != null) {
        if (end) {
          return false;
        }
        queue.add(node.left); //
      } else {
        end = true;
      }
      if (node.right != null) {
        if (end) {
          return false;
        }
        queue.add(node.right);
      } else {
        end = true;
      }
    }
    return true;
  }

  /** */
  @Override
  public boolean isPerfectBst() {
    //    return isPerfectBstThroughBfs(root);
    return isPerfectBstThroughDfs(root);
  }

  private boolean isPerfectBstThroughDfs(Node<E> node) {
    boolean isPerfect;
    if (node == null) {
      return true;
    }
    if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
      return false;
    }
    Node<E> leftChild = node.left;
    Node<E> rightChild = node.right;
    if (leftChild != null && rightChild != null) {
      if ((leftChild.left == null
              && leftChild.right == null
              && rightChild.left != null
              && rightChild.right != null)
          || (leftChild.left != null
              && leftChild.right != null
              && rightChild.left == null
              && rightChild.right == null)) {
        return false;
      }
    }
    isPerfect = isPerfectBstThroughDfs(node.left);
    isPerfect = isPerfect ? isPerfectBstThroughDfs(node.right) : isPerfect;
    return isPerfect;
  }

  private boolean isPerfectBstThroughBfs(Node<E> rootNode) {
    // Condition for single root node.
    if (size == 1) {
      return true;
    }
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(rootNode);
    boolean leafNode = false;
    int levelIndex = 1;
    for (int i = 1; !queue.isEmpty(); i++) {
      Node<E> node = queue.poll();
      // Any node that has only one child then tree would not be a perfect.
      if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
        return false;
      }
      if (i == levelIndex * 2) { // Starting point of every level.
        if (node.left == null && node.right == null) {
          leafNode = true;
        } else {
          queue.offer(node.left);
          queue.offer(node.right);
        }
        levelIndex = levelIndex * 2;
      } else { // Next nodes in each level
        if (leafNode) {
          // All Leaf nodes are on same level or not allow to have any child.
          if (node.left != null && node.right != null) {
            return false;
          }
        } else if (node.left == null && node.right == null) {
          // If this is not last level then any node must have 2 children.
          return false;
        } else {
          // Enqueue both children in th queue for next iteration.
          queue.offer(node.left);
          queue.offer(node.right);
        }
      }
    }
    return true;
  }

  /** */
  @Override
  public boolean isBalancedBst() {
    return isBalancedBstThroughDfs(root);
  }

  private boolean isBalancedBstThroughDfs(Node<E> node) {
    return dfsHeight(node) != -1;
  }

  private int dfsHeight(Node<E> node) {
    int left, right;
    if (node == null) {
      return 0;
    }

    left = dfsHeight(node.left);
    if (left == -1) {
      return -1;
    }
    right = dfsHeight(node.right);
    if (right == -1) {
      return -1;
    }
    if (right - left <= -2 || right - left >= 2) {
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
    boolean isDegenerated;
    if (node == null) {
      return true;
    }
    if (node.left != null && node.right != null) {
      return false;
    }
    isDegenerated = isDegeneratedBst(node.left);
    isDegenerated = isDegenerated ? isDegeneratedBst(node.right) : isDegenerated;
    return isDegenerated;
  }
}
