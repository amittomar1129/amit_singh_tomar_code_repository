package datastructure_algorithms.patterns.priorityqueue.find;

//  Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
//  not the kth distinct element.
//
//  Example 1:
//  Input: [3,2,1,5,6,4] 2
//  Output: 5
//  Explanation: Return the second largest element which is 5.
//
//  Example 2:
//  Input: [3,2,3,1,2,4,5,5,6] 4
//  Output: 4
//  Explanation: Return the fourth largest element which is 4.

//  Solution: Quickselect, Average O(n) time, In-place, Same idea as Quicksort but only explores one side.
//  kth largest -> (n - k)th smallest
//  Average Time -> O(n)
//  Worst Time -> O(n²) (rare, acceptable to mention)
//  Space -> O(1)


public class KthLargestElement {

  public static int findKthLargest(int[] nums, int k) {
    int targetIndex = nums.length - k;
    return quickSelect(nums, 0, nums.length - 1, targetIndex);
  }

//  {3, 2, 1, 5, 6, 4}
  private static int quickSelect(int[] input, int left, int right, int k) {
    if (left == right) {
      return input[left];
    }
    int pivotIndex = partition(input, left, right);
    if (k == pivotIndex) {
      return input[k];
    } else if (k < pivotIndex) {
      return quickSelect(input, left, pivotIndex - 1, k);
    } else {
      return quickSelect(input, pivotIndex + 1, right, k);
    }
  }

  private static int partition(int[] input, int left, int right) {
    int pivot = input[right];
    int i = left;
    for (int j = left; j < right; j++) {
      if (input[j] <= pivot) {
        swap(input, i, j);
        i++;
      }
    }
    swap(input, i, right);
    return i;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  // main method
  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 5, 6, 4};
    int k = 3;

    System.out.println("Kth largest element -> " + findKthLargest(nums, k));
  }
}
