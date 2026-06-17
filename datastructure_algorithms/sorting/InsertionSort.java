package datastructure_algorithms.sorting;

//  Insertion Sort builds the sorted array one element at a time by taking the next element and
// inserting it
//  into its correct position in the already sorted part.
//  Just like sorting playing cards in hand

//  How It Works (Mental Model)
//  Left part -> sorted
//  Right part -> unsorted
//  Pick one element from unsorted
//  Shift elements in sorted part
//  Insert element at correct position

public class InsertionSort {

  public static void insertionSort(int[] input) {
    for (int i = 1; i < input.length; i++) {
      int current = input[i]; // element to insert
      int j = i - 1;

      // Shift elements greater than key
      while (j >= 0 && input[j] > current) {
        input[j + 1] = input[j];
        j--;
      }

      // Insert key at correct position
      input[j + 1] = current;
    }
  }

  public static void main(String[] args) {
    int[] arr = {12, 11, 13, 5, 6};

    insertionSort(arr);

    for (int num : arr) {
      System.out.print(num + " ");
    }
  }
}
