package datastructure_algorithms.patterns.arrays.prefixsum;

//  You are given an integer array nums and you have to return a new counts array. The counts array
//  has the property where counts[i] is the number of smaller elements to the right of nums[i].
//
//  Example 1:
//  Input: [5,2,6,1]
//  Output: [2,1,1,0]
//
//  Example 2:
//  Input: []
//  Output: []
//
//  Example 3:
//  Input: [1]
//  Output: [0]

//  Solution: Modified Merge Sort (O(n log n))
//  While merging two sorted halves, if an element from the right half is placed before an element
//  from the left half, then it is smaller and to the right of all remaining left elements.
//  Time	O(n log n)
//  Space	O(n)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfSmallerHARD {

  static class Pair {
    int val;
    int index;

    Pair(int val, int index) {
      this.val = val;
      this.index = index;
    }
  }

  public static int[] countSmaller(int[] input) {
    int length = input.length;
    int[] result = new int[length];

    Pair[] pairs = new Pair[length];
    for (int i = 0; i < length; i++) {
      pairs[i] = new Pair(input[i], i);
    }
    mergeSort(pairs, 0, length - 1, result);
    return result;
  }

  //  {5, 2, 6, 1, 4, 9, 3}
  private static void mergeSort(Pair[] pairs, int left, int right, int[] result) {
    if (left >= right) {
      return;
    }

    int mid = left + (right - left) / 2;
    mergeSort(pairs, left, mid, result);
    mergeSort(pairs, mid + 1, right, result);
    merge(pairs, left, mid, right, result);
  }

  private static void merge(Pair[] pairs, int left, int mid, int right, int[] result) {
    //  {5, 2, 6, 1, 4, 9, 3}
    List<Pair> temp = new ArrayList<>();
    int i = left, j = mid + 1;
    int count = 0;
    while (i <= mid && j <= right) {
      if (pairs[j].val < pairs[i].val) {
        count++;
        temp.add(pairs[j++]);
      } else {
        result[pairs[i].index] += count;
        temp.add(pairs[i++]);
      }
    }
    while (i <= mid) {
      result[pairs[i].index] += count;
      temp.add(pairs[i++]);
    }
    while (j <= right) {
      temp.add(pairs[j++]);
    }
    for (int k = left; k <= right; k++) {
      pairs[k] = temp.get(k - left);
    }
  }

  public static void main(String[] args) {
    int[] nums = {5, 2, 6, 1, 4, 9, 3};
    int[] result = sol(nums);
    System.out.println(Arrays.toString(result));
  }


  public static int[] sol(int[] input) {
    int[] result = new int[input.length];
    Pair[] pairs = new Pair[input.length];

    int left = 0;
    int right = input.length - 1;
    for(int i = 0; i < input.length; i++) {
      pairs[i] = new Pair(input[i], i);
    }
    rec(pairs, left, right, result);

    return result;
  }

  public static void rec(Pair[] pairs, int left, int right, int[] result) {
    if (left >= right) {
      return;
    }

    int mid = left + (right - left) / 2;
    rec(pairs, left, mid, result);
    rec(pairs, mid+1, right, result);

    merges(pairs, left, mid, right, result);
  }

  public static void merges(Pair[] pairs, int left, int mid, int right, int[] result) {
    ArrayList<Pair> temp = new ArrayList<>();
    int i = left;
    int j = mid + 1;
    int count = 0;

    while(i <= mid && j <= right) {
      if (pairs[i].val > pairs[j].val) {
        count++;
        temp.add(pairs[j++]);
      } else {
        result[pairs[i].index] += count;
        temp.add(pairs[i++]);
      }
    }

    while(i <= mid) {
      result[pairs[i].index] += count;
      temp.add(pairs[i++]);
    }

    while(j <= right) {
      temp.add(pairs[j++]);
    }

    for(int k = left; k <= right; k++) {
      pairs[k] = temp.get(k - left);
    }
  }

}
