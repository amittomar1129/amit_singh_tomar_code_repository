package datastructure_algorithms.datastructure.nonlinear.tree.redblack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static datastructure_algorithms.datastructure.nonlinear.tree.redblack.Color.BLACK;
import static datastructure_algorithms.datastructure.nonlinear.tree.redblack.Color.DOUBLE_BLACK;
import static datastructure_algorithms.datastructure.nonlinear.tree.redblack.Color.RED;

final class RedBlackTree<E extends Comparable> implements Bst<E> {

  private Node<E> root;
  private int size;


  /**
   *
   */
  @Override
  public void add(E data) {
    if (root == null) {
      size++;
      root = new Node<>(data, BLACK);
      return;
    }
    add(this.root, data);
    size++;
  }

  private Node<E> add(Node<E> node, E data) {
    if (node == null) {
      return new Node<>(data, BLACK);
    }

    if (data.compareTo(node.getData()) < 0) {
      Node<E> newNode = add(node.getLeftChild(), data);
      newNode.setParent(node);
      node.setLeftChild(newNode);
    } else {
      Node<E> newNode = add(node.getRightChild(), data);
      newNode.setParent(node);
      node.setRightChild(newNode);
    }
    performAndFixRedBlackPropertiesAfterInsertion(node);
    return node;
  }

  private void performAndFixRedBlackPropertiesAfterInsertion(Node<E> node) {
    // Perform the change color if new node`s parent`s sibling is RED or else bottom level rotation
    // in case of LR or RL.
    if (node.getLeftChild() != null && node.getLeftChild().getColor().equals(RED)) {
      if (node == root) {
        if (node.getRightChild() == null || node.getRightChild().getColor().equals(BLACK)) {
          if (node.getLeftChild().getRightChild() != null && node.getLeftChild().getRightChild()
              .getColor().equals(RED)) {
            Node<E> rotatedNode = rotateLeftWithoutRecolor(node.getLeftChild());
            node.setLeftChild(rotatedNode);
          }
          if (node.getLeftChild().getLeftChild() != null && node.getLeftChild().getLeftChild()
              .getColor().equals(RED)) {
            Node<E> rotatedAndRecoloredNode = rotateRight(node);
            root = rotatedAndRecoloredNode;
            return;
          }
        }
      }
      // For LR situation.
      if (node.getRightChild() != null && node.getRightChild().getColor().equals(RED)) {
        if ((node.getLeftChild().getRightChild() != null && node.getLeftChild().getRightChild()
            .getColor().equals(RED)) || (node.getLeftChild().getLeftChild() != null
            && node.getLeftChild().getLeftChild().getColor().equals(RED))) {
          applyChangeColor(node);
        }
      } else if (node.getLeftChild().getRightChild() != null && node.getLeftChild().getRightChild()
          .getColor().equals(RED)) {
        Node<E> rotatedNode = rotateLeftWithoutRecolor(node.getLeftChild());
        node.setLeftChild(rotatedNode);
      }
    }

    // For RL situation
    if (node.getRightChild() != null && node.getRightChild().getColor().equals(RED)) {
      if (node == root) {
        if (node.getLeftChild() == null || node.getLeftChild().getColor().equals(BLACK)) {
          if (node.getRightChild().getLeftChild() != null && node.getRightChild().getLeftChild()
              .getColor().equals(RED)) {
            Node<E> rotatedNode = rotateRightWithoutRecolor(node.getRightChild());
            node.setRightChild(rotatedNode);
          }
          if (node.getRightChild().getRightChild() != null && node.getRightChild().getRightChild()
              .getColor().equals(RED)) {
            Node<E> rotatedAndRecoloredNode = rotateLeft(node);
            root = rotatedAndRecoloredNode;
            return;
          }
        }
      }
      if (node.getLeftChild() != null && node.getLeftChild().getColor().equals(RED)) {
        if ((node.getRightChild().getLeftChild() != null && node.getRightChild().getLeftChild()
            .getColor().equals(RED)) || (node.getRightChild().getRightChild() != null
            && node.getRightChild().getRightChild().getColor().equals(RED))) {
          applyChangeColor(node);
        }
      } else if (node.getRightChild().getLeftChild() != null && node.getRightChild().getLeftChild()
          .getColor().equals(RED)) {
        Node<E> rotatedNode = rotateRightWithoutRecolor(node.getRightChild());
        node.setRightChild(rotatedNode);
      }
    }
    // Perform the final rotation in case of LL with recolor.
    if (node.getLeftChild() != null && node.getLeftChild().getColor().equals(BLACK)) {
      Node<E> leftChild = node.getLeftChild();
      if (leftChild.getLeftChild() != null && leftChild.getLeftChild().getColor().equals(RED)
          && leftChild.getLeftChild().getLeftChild() != null && leftChild.getLeftChild()
          .getLeftChild().getColor().equals(RED)) {
        Node<E> rotatedAndRecoloredNode = rotateRight(node.getLeftChild());
        node.setLeftChild(rotatedAndRecoloredNode);
      } else if (leftChild.getRightChild() != null && leftChild.getRightChild().getColor()
          .equals(RED) && leftChild.getRightChild().getRightChild() != null
          && leftChild.getRightChild().getRightChild().getColor().equals(RED)) {
        Node<E> rotatedAndRecoloredNode = rotateLeft(node.getLeftChild());
        node.setLeftChild(rotatedAndRecoloredNode);
      }
    }
    // Perform the final rotation in case of RR with recolor.
    if (node.getRightChild() != null && node.getRightChild().getColor().equals(BLACK)) {
      Node<E> rightChild = node.getRightChild();
      if (rightChild.getLeftChild() != null && rightChild.getLeftChild().getColor().equals(RED)
          && rightChild.getLeftChild().getLeftChild() != null && rightChild.getLeftChild()
          .getLeftChild().getColor().equals(RED)) {
        Node<E> rotatedAndRecoloredNode = rotateRight(node.getRightChild());
        node.setRightChild(rotatedAndRecoloredNode);
      } else if (rightChild.getRightChild() != null && rightChild.getRightChild().getColor()
          .equals(RED) && rightChild.getRightChild().getRightChild() != null
          && rightChild.getRightChild().getRightChild().getColor().equals(RED)) {
        Node<E> rotatedAndRecoloredNode = rotateLeft(node.getRightChild());
        node.setRightChild(rotatedAndRecoloredNode);
      }
    }
  }

  private void applyChangeColor(Node<E> node) {
    if (node != root) {
      applyReverseColor(node);
    }
    if (node.getLeftChild() != null) {
      applyReverseColor(node.getLeftChild());
    }
    if (node.getRightChild() != null) {
      applyReverseColor(node.getRightChild());
    }
  }

  private void applyReverseColor(Node<E> node) {
    if (node.getColor().equals(RED)) {
      node.setColor(BLACK);
    } else {
      node.setColor(RED);
    }
  }

  private Node<E> rotateLeftWithoutRecolor(Node<E> node) {
    Node<E> rightChild = node.getRightChild();
    Node<E> orphanedNode = rightChild.getLeftChild();

    if (orphanedNode != null) {
      orphanedNode.setParent(node);
      node.setRightChild(orphanedNode);
    } else {
      node.setRightChild(null);
    }

    rightChild.setLeftChild(node);
    rightChild.setParent(node.getParent());
    node.setParent(rightChild);
    return rightChild;
  }

  private Node<E> rotateRightWithoutRecolor(Node<E> node) {
    Node<E> leftChild = node.getLeftChild();
    Node<E> orphanedNode = leftChild.getRightChild();

    if (orphanedNode != null) {
      orphanedNode.setParent(node);
      node.setLeftChild(orphanedNode);
    } else {
      node.setLeftChild(null);
    }

    leftChild.setRightChild(node);
    leftChild.setParent(node.getParent());
    node.setParent(leftChild);
    return leftChild;
  }

  private Node<E> rotateLeft(Node<E> node) {
    node.setColor(RED);
    node.getRightChild().setColor(BLACK);
    Node<E> rightChild = node.getRightChild();
    Node<E> orphanedNode = rightChild.getLeftChild();

    if (orphanedNode != null) {
      orphanedNode.setParent(node);
      node.setRightChild(orphanedNode);
    } else {
      node.setRightChild(null);
    }

    rightChild.setLeftChild(node);
    rightChild.setParent(node.getParent());
    node.setParent(rightChild);
    return rightChild;
  }

  private Node<E> rotateRight(Node<E> node) {
    node.setColor(RED);
    node.getLeftChild().setColor(BLACK);
    Node<E> leftChild = node.getLeftChild();
    Node<E> orphanedNode = leftChild.getRightChild();

    if (orphanedNode != null) {
      orphanedNode.setParent(node);
      node.setLeftChild(orphanedNode);
    } else {
      node.setLeftChild(null);
    }

    leftChild.setRightChild(node);
    leftChild.setParent(node.getParent());
    node.setParent(leftChild);
    return leftChild;
  }


  private Node<E> getParentsSibling(Node<E> node) {
    Node<E> parent = node.getParent().getParent();
    return parent.getLeftChild() == node.getParent() ? parent.getRightChild()
        : parent.getLeftChild();
  }

  private String figureOutTheRotations(Node<E> childNode) {
    String result =
        childNode.getParent().getParent().getLeftChild() == childNode.getParent() ? "L" : "R";
    result += childNode.getParent().getLeftChild() == childNode ? "L" : "R";
    return result;
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

    }

    if (data.compareTo(node.getData()) < 0) {
      Node<E> newNode = delete(node.getLeftChild(), data);
      node.setLeftChild(newNode);
      if (newNode != null) {
        newNode.setParent(node);
      }
    } else {
      Node<E> newNode = delete(node.getRightChild(), data);
      node.setRightChild(newNode);
      if (newNode != null) {
        newNode.setParent(node);
      }
    }

    return node;
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
    Node<E> result = null;
    if (node == null) {
      return null;
    }

    if (data.compareTo(node.getData()) == 0) {
      return node;
    }

    if (data.compareTo(node.getLeftChild().getData()) < 0) {
      result = get(node.getLeftChild(), data);
    } else if (result == null) {
      result = get(node.getRightChild(), data);
    }

    return result;
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
   *
   */
  @Override
  public Node<E> min(Node<E> node) {
    Node<E> left, right, min;
    if (node == null) {
      return null;
    }
    min = getMinimumNode(node.getLeftChild(), node.getRightChild(), node);

    left = min(node.getLeftChild());
    right = min(node.getRightChild());

    return getMinimumNode(left, right, min);
  }

  private Node<E> getMinimumNode(Node<E>... nodes) {
    return Arrays.stream(nodes).filter(node -> node != null)
        .reduce((node1, node2) -> node1.getData().compareTo(node2.getData()) < 0 ? node1 : node2)
        .get();
  }

  private Node<E> getMaximumNode(Node<E>... nodes) {
    return Arrays.stream(nodes).filter(node -> node != null)
        .reduce((node1, node2) -> node1.getData().compareTo(node2.getData()) < 0 ? node2 : node1)
        .get();
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
    max = getMaximumNode(node.getLeftChild(), node.getRightChild(), node);

    left = min(node.getLeftChild());
    right = min(node.getRightChild());

    return getMaximumNode(left, right, max);
  }

  /**
   *
   */
  @Override
  public boolean contains(E data) {
    return get(data) != null;
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
    return size == 0;
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

    left = countNodes(node.getLeftChild());
    right = countNodes(node.getRightChild());

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

    left = countNodes(node.getLeftChild());
    right = countNodes(node.getRightChild());

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
    System.out.print(
        String.format("%s:%s, ", node.getData(), node.getColor().toString().charAt(0)));
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
    System.out.print(
        String.format("%s:%s, ", node.getData(), node.getColor().toString().charAt(0)));
    traversePreOrder(node.getLeftChild());
    traversePreOrder(node.getRightChild());
  }

  /**
   *
   */
  @Override
  public void traversePostOrder(Node<E> node) {
    if (node == null) {
      return;
    }
    traversePostOrder(node.getLeftChild());
    traversePostOrder(node.getRightChild());
    System.out.print(
        String.format("%s:%s, ", node.getData(), node.getColor().toString().charAt(0)));
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
        return;
      }
      System.out.print(
          String.format("%s:%s, ", node.getData(), node.getColor().toString().charAt(0)));
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
    return false;
  }

  /**
   *
   */
  @Override
  public boolean isDegeneratedBst() {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean isRedBlackBst() {
    if (isEmpty()) {
      return true;
    } else if (root.getColor().equals(RED)) {
      return false;
    }
    return isRedBlackBst(root) != -1;
  }

  private int isRedBlackBst(Node<E> node) {
    int left, right;
    if (node == null) {
      return 0;
    }

    if (node.getColor().equals(RED)) {
      if ((node.getLeftChild() != null && node.getLeftChild().getColor().equals(RED)) || (
          node.getRightChild() != null && node.getRightChild().getColor().equals(RED))) {
        return -1;
      }
    }

    left = isRedBlackBst(node.getLeftChild());
    if (left == -1) {
      return -1;
    }
    right = isRedBlackBst(node.getRightChild());
    if (right == -1) {
      return -1;
    }

    if (node.getColor().equals(BLACK)) {
      left++;
      right++;
    }
    if (left != right) {
      return -1;
    }
    return left;
  }
}
