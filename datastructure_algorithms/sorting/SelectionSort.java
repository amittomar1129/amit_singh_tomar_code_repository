package datastructure_algorithms.sorting;

//  Selection Sort works by repeatedly selecting the smallest element from the unsorted part of the
// array
//  and placing it at the beginning.

//  On every pass:
//  Find minimum element in the unsorted part.
//  Swap it with the first unsorted position.

public class SelectionSort {

  public static void selectionSort(int[] input) {
    for (int i = 0; i < input.length - 1; i++) {
      int minIndex = i;

      // Find minimum in unsorted part
      for (int j = i + 1; j < input.length; j++) {
        if (input[j] < input[minIndex]) {
          minIndex = j;
        }
      }

      // Swap only if needed
      if (minIndex != i) {
        int temp = input[i];
        input[i] = input[minIndex];
        input[minIndex] = temp;
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = {64, 25, 12, 22, 11};

    selectionSort(arr);

    for (int num : arr) {
      System.out.print(num + " ");
    }
  }
}
