package datastructure_algorithms.sorting;

//  Heap Sort is a comparison-based, in-place sorting algorithm that uses a binary heap data
// structure.
//  Convert the array into a Max Heap. Repeatedly remove the maximum element.
//  Place it at the end of the array. Reduce heap size and heapify again.

//  Space: O(1)

import java.util.Arrays;

public class HeapSort {

  public static void heapSort(int[] input) {
    // Step 1: Build Max Heap
    for (int i = input.length / 2 - 1; i >= 0; i--) {
      heapify(input, input.length, i);
    }

    // Step 2: Extract elements from heap one by one
    for (int i = input.length - 1; i > 0; i--) {
      swap(input, 0, i); // Move max to end
      heapify(input, i, 0); // Heapify reduced heap
    }
  }

  private static void heapify(int[] input, int heapSize, int root) {
    int largest = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;

    if (left < heapSize && input[left] > input[largest]) {
      largest = left;
    }

    if (right < heapSize && input[right] > input[largest]) {
      largest = right;
    }

    if (largest != root) {
      swap(input, root, largest);
      heapify(input, heapSize, largest);
    }
  }

  private static void swap(int[] input, int i, int j) {
    int temp = input[i];
    input[i] = input[j];
    input[j] = temp;
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {
    int[] arr = {4, 10, 3, 5, 1};

    System.out.println("Before Sorting:");
    System.out.println(Arrays.toString(arr));

    heapSort(arr);

    System.out.println("After Sorting:");
    System.out.println(Arrays.toString(arr));
  }
}
