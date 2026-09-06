package datastructure_algorithms.datastructure.nonlinear.tree.heap;

import java.util.LinkedList;
import java.util.Queue;

public class HeapUsingNode<E extends Comparable> implements Heap<E> {

  private Node<E> root;
  private static int size;

  /** */
  @Override
  public void insert(E element) {
    Node<E> newNode = new Node<>(element);
    if (size == 0) {
      root = newNode;
      size++;
      return;
    }
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      Node<E> current = queue.poll();
      if (current.left == null) {
        current.left = newNode;
        newNode.parent = current;
        break;
      } else {
        queue.offer(current.left);
      }
      if (current.right == null) {
        current.right = newNode;
        newNode.parent = current;
        break;
      } else {
        queue.offer(current.right);
      }
    }
    heapifyUp(newNode);
    size++;
  }

  private void heapifyUp(Node<E> node) {
    while (node.parent != null && node.val.compareTo(node.parent.val) > 0) {
      E temp = node.parent.val;
      node.parent.val = node.val;
      node.val = temp;
      node = node.parent;
    }
  }

  /** */
  @Override
  public E remove() {
    if (size == 0) {
      return null;
    }
    if (size == 1) {
      E data = root.val;
      root = null;
      size--;
      return data;
    }
    E temp = root.val;
    Node<E> lastElement = findLastElement();
    root.val = lastElement.val;
    Node<E> parent = lastElement.parent;
    if (parent.right != null) {
      parent.right = null;
    } else {
      parent.left = null;
    }
    size--;
    heapifyDown(root);
    return temp;
  }

  private void heapifyDown(Node<E> node) {
    while (node.left != null || node.right != null) {
      Node<E> largest = node.left;
      if (node.right != null && largest.val.compareTo(node.right.val) < 0) {
        largest = node.right;
      }
      if (node.val.compareTo(largest.val) >= 0) {
        break;
      }
      E temp = node.val;
      node.val = largest.val;
      largest.val = temp;
      node = largest;
    }
  }

  private Node<E> findLastElement() {
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(root);
    Node<E> lastElement = root;
    while (!queue.isEmpty()) {
      lastElement = queue.poll();
      if (lastElement.left != null) {
        queue.offer(lastElement.left);
      }
      if (lastElement.right != null) {
        queue.offer(lastElement.right);
      }
    }
    return lastElement;
  }

  /** */
  @Override
  public boolean remove(E element) {
    if (size == 0) {
      return false;
    }
    Node<E> foundNode = get(root, element);
    if (foundNode != null) {
      if (size == 1) {
        root = null;
        size--;
        return true;
      }
      Node<E> lastElement = findLastElement();
      foundNode.val = lastElement.val;
      Node<E> parent = lastElement.parent;
      if (parent.right != null) {
        parent.right = null;
      } else {
        parent.left = null;
      }
      size--;
      if (foundNode.parent != null && foundNode.parent.val.compareTo(foundNode.val) < 0) {
        heapifyUp(foundNode);
      } else {
        heapifyDown(foundNode);
      }
      return true;
    }
    return false;
  }

  private Node<E> get(Node<E> node, E element) {
    Node<E> found;
    if (node == null) {
      return null;
    }
    if (node.val.compareTo(element) == 0) {
      return node;
    }

    found = get(node.left, element);
    if (found == null) {
      found = get(node.right, element);
    }
    return found;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Heap: ");
    if (size == 0) {
      return builder.toString();
    }
    Queue<Node<E>> queue = new LinkedList();
    queue.offer(root);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      builder.append(node.val + ", ");
      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }
    }

    return builder.toString();
  }

  public static void main(String[] args) {
    HeapUsingNode<Integer> heap = new HeapUsingNode<Integer>();
    heap.insert(15);
    heap.insert(20);
    heap.insert(2);
    heap.insert(9);
    heap.insert(16);
    heap.insert(35);
    heap.insert(4);
    heap.insert(45);
    System.out.println(heap);

    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap);

    System.out.println(heap.remove(35));
    System.out.println(heap);
    System.out.println(heap.remove(45));
    System.out.println(heap);
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap.remove());
    //    System.out.println(heap);

  }
}
