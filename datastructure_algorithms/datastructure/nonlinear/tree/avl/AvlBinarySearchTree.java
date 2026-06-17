package datastructure_algorithms.datastructure.nonlinear.tree.avl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

final class AvlBinarySearchTree<E extends Comparable> implements Bst<E> {

  private Node<E> root;
  private int size;

  /**
   * Adds new node as simple bst approach. Then calculate the height of it`s ancestors and update it
   * and checks the balance factor of each ancestor node and apply rotations if required.
   */
  @Override
  public void add(E data) {
    size++;
    if (root == null) {
      root = new Node<E>(data, 0);
      return;
    }
    // Adds a new node in bst.
    add(root, data);
  }

  private Node<E> add(Node<E> node, E data) {
    if (node == null) {
      return new Node<>(data, 0);
    }

    if (data.compareTo(node.getData()) < 0) {
      node.setLeftChild(add(node.getLeftChild(), data));
    } else {
      node.setRightChild(add(node.getRightChild(), data));
    }

    Node<E> balancedNode = updateTheHeightAndCheckForRotation(node);
    if (node == root) {
      root = balancedNode;
    }
    return balancedNode;
  }

  private Node<E> updateTheHeightAndCheckForRotation(Node<E> node) {
    // Update the height of all ancestors of deleted node.
    if (node.getLeftChild() == null && node.getRightChild() == null) {
      node.setHeight(0);
    } else {
      node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }
    // Re-balance all the ancestors of new node if required.
    // If balance factor <= -2 and balance factor >= 2 then node is unbalance and there would be
    // following four types of rotations would be applied on it.
    int rightHeight = node.getRightChild() == null ? 0 : height(node.getRightChild()) + 1;
    int leftHeight = node.getLeftChild() == null ? 0 : height(node.getLeftChild()) + 1;
    int balanceFactor = rightHeight - leftHeight;

    // Check and apply the appropriate rotation if required to balance the tree.
    if (balanceFactor <= -2 && node.getLeftChild().getRightChild() != null) {
      // That would be the L-R case and two rotations are required.
      node.setLeftChild(rotateLeft(node.getLeftChild()));
      return rotateRight(node);
    }
    if (balanceFactor <= -2 && node.getLeftChild().getLeftChild() != null) {
      // That would be the L-L case and R-R rotation is required.
      return rotateRight(node);
    }
    if (balanceFactor >= 2 && node.getRightChild().getLeftChild() != null) {
      // That would be the R-L case and two rotations are required.
      node.setRightChild(rotateRight(node.getRightChild()));
      return rotateLeft(node);
    }
    if (balanceFactor >= 2 && node.getRightChild().getRightChild() != null) {
      // That would be the R-R case and L-L rotation is required.
      return rotateLeft(node);
    }
    return node;
  }

  private int height(Node<E> node) {
    if (node == null) {
      return 0;
    }
    return node.getHeight();
  }

  private Node<E> rotateRight(Node<E> node) {
    Node<E> leftChild = node.getLeftChild();
    Node<E> orphanedNode = leftChild.getRightChild();

    node.setLeftChild(orphanedNode);
    if (node.getLeftChild() == null && node.getRightChild() == null) {
      node.setHeight(0);
    } else {
      node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }
    leftChild.setRightChild(node);
    leftChild = updateTheHeightAndCheckForRotation(leftChild);
    if (leftChild.getLeftChild() == null && leftChild.getRightChild() == null) {
      leftChild.setHeight(0);
    } else {
      leftChild.setHeight(
          Math.max(height(leftChild.getLeftChild()), height(leftChild.getRightChild())) + 1);
    }
    return leftChild;
  }

  private Node<E> rotateLeft(Node<E> node) {
    Node<E> rightChild = node.getRightChild();
    Node<E> orphanedNode = rightChild.getLeftChild();

    node.setRightChild(orphanedNode);
    if (node.getLeftChild() == null && node.getRightChild() == null) {
      node.setHeight(0);
    } else {
      node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }
    rightChild.setLeftChild(node);
    rightChild = updateTheHeightAndCheckForRotation(rightChild);
    if (rightChild.getLeftChild() == null && rightChild.getRightChild() == null) {
      rightChild.setHeight(0);
    } else {
      rightChild.setHeight(
          Math.max(height(rightChild.getLeftChild()), height(rightChild.getRightChild())) + 1);
    }
    return rightChild;
  }

  /**
   *
   */
  @Override
  public Node<E> delete(E data) {
    return delete(root, data);
  }

  private Node<E> delete(Node<E> node, E data) {
    if (node == null) {
      return null;
    }
    if (data.compareTo(node.getData()) == 0) {
      if (node == root && size == 1) {
        size--;
        root = null;
        return null;
      } else if (node.getLeftChild() == null && node.getRightChild() == null) {
        size--;
        return null;
      } else if (node.getLeftChild() != null && node.getRightChild() == null) {
        if(node == root) {
          root = node.getLeftChild();
        }
        size--;
        return node.getLeftChild();
      } else if (node.getLeftChild() == null && node.getRightChild() != null) {
        if(node == root) {
          root = node.getRightChild();
        }
        size--;
        return node.getRightChild();
      } else {
        size--;
        if (node.getLeftChild().getRightChild() == null) {
          node.getLeftChild().setRightChild(node.getRightChild());
          // This new node may un-balanced after adding left/right subtree so update it`s height
          // and apply suitable rotation on it to make it balance.
          Node<E> leftSubTree = updateTheHeightAndCheckForRotation(node.getLeftChild());
          if (node == root) {
            root = leftSubTree;
          }
          return leftSubTree;
        } else {
          // Replacing with pre-order predecessor or maximum element from left subtree.
          Node<E> max = max(node.getLeftChild());
          Node<E> newNode = new Node<>(max.getData());
          // Max node always be a leaf node so delete method enough to update all it`s
          // ancestor`s height and balance the subtree.
          Node<E> newLeftSubtree = delete(node.getLeftChild(), max.getData());
          newNode.setRightChild(node.getRightChild());
          newNode.setLeftChild(newLeftSubtree);
          // This new node may un-balanced after adding left/right subtree so update it`s height
          // and apply suitable rotation on it to make it balance.
          newNode = updateTheHeightAndCheckForRotation(newNode);
          if (node == root) {
            root = newNode;
          }
          return newNode;
        }
      }
    }

    if (data.compareTo(node.getData()) < 0) {
      node.setLeftChild(delete(node.getLeftChild(), data));
    } else {
      node.setRightChild(delete(node.getRightChild(), data));
    }

    return updateTheHeightAndCheckForRotation(node);
  }

  /**
   *
   */
  @Override
  public Node<E> get(E data) {
    return null;
  }

  /**
   *
   */
  @Override
  public Node<E> get(Node<E> node, E data) {
    return null;
  }

  /**
   *
   */
  @Override
  public E min() {
    return min(root) != null ? min(root).getData() : null;
  }

  /**
   *
   */
  @Override
  public E max() {
    return max(root).getData();
  }

  /**
   *
   */
  @Override
  public Node<E> min(Node<E> node) {
    Node<E> min;
    if (node.getLeftChild() == null) {
      return node;
    }
    min = min(node.getLeftChild());
    return min;
  }

  /**
   *
   */
  @Override
  public Node<E> max(Node<E> node) {
    Node<E> left, right, max;
    if (node == null) {
      return null;
    }

    max = getMaximumNode(node, node.getLeftChild(), node.getRightChild());

    left = max(node.getLeftChild());
    right = max(node.getRightChild());

    return getMaximumNode(left, right, max);
  }

  private Node<E> getMaximumNode(Node<E>... nodes) {
    return Arrays.stream(nodes).filter(node -> node != null)
        .reduce((node1, node2) -> node1.getData().compareTo(node2.getData()) > 0 ? node1 : node2)
        .get();
  }

  /**
   *
   */
  @Override
  public boolean contains(E data) {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean contains(Node<E> node, E data) {
    return false;
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
    return 0;
  }

  /**
   *
   */
  @Override
  public int getHeight() {
    return getHeight(root);
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

    left = getHeight(node.getLeftChild());
    right = getHeight(node.getRightChild());

    return Math.max(left, right) + 1;
  }

  /**
   *
   */
  @Override
  public void traverseInOrder() {
    traverseInOrder(root);
    System.out.println("");
  }

  /**
   *
   */
  @Override
  public void traversePreOrder() {
    traversePreOrder(root);
    System.out.println("");
  }

  /**
   *
   */
  @Override
  public void traversePostOrder() {
    traversePostOrder(root);
    System.out.println("");
  }

  /**
   *
   */
  @Override
  public void traverseInOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    traverseInOrder(node.getLeftChild());
    System.out.print(node.getData() + ":H=" + node.getHeight() + ",");
    traverseInOrder(node.getRightChild());
  }

  /**
   *
   */
  @Override
  public void traversePreOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    System.out.print(node.getData() + ":H=" + node.getHeight() + ",");
    traverseInOrder(node.getLeftChild());
    traverseInOrder(node.getRightChild());
  }

  /**
   *
   */
  @Override
  public void traversePostOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    traverseInOrder(node.getLeftChild());
    traverseInOrder(node.getRightChild());
    System.out.print(node.getData() + ":H=" + node.getHeight() + ",");
  }

  /**
   *
   */
  @Override
  public void traverseBreadthFirstSearch() {
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      if (node == null) {
        continue;
      }
      System.out.print(node.getData() + ":H=" + node.getHeight() + ",");
      if (node.getLeftChild() != null) {
        queue.offer(node.getLeftChild());
      }
      if (node.getRightChild() != null) {
        queue.offer(node.getRightChild());
      }
    }
    System.out.println("");
  }

  /**
   *
   */
  @Override
  public NodeType type(E data) {
    return null;
  }

  /**
   *
   */
  @Override
  public boolean isBst() {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean isFullBst() {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean isCompleteBst() {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean isPerfectBst() {
    return false;
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

    left = isBalancedBst(node.getLeftChild());
    if (left == -1) {
      return -1;
    }
    right = isBalancedBst(node.getRightChild());
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
    return false;
  }
}
