package datastructure_algorithms.sorting;

//  Bucket Sort is a distribution-based sorting algorithm that achieves linear time on uniformly
// distributed data
//  by dividing elements into buckets, sorting them individually, and merging them
//  It’s not a comparison sort (like Quick/Merge). It works best when input values are uniformly
// distributed.

//  Create k empty buckets
//  Put each element into its bucket
//  Sort each bucket (usually Insertion Sort)
//  Concatenate all buckets

//  Bucket Index Formula (Very Important):
//  For numbers in range [min, max]: bucketIndex = (value - min) * numberOfBuckets / (max - min + 1)
//  For floats in [0,1]: bucketIndex = value * numberOfBuckets

//  Time Complexity
//  Best	O(n), Average	O(n + k), Worst	O(n²) (all elements in one bucket)
//  Space: O(n + k)

//  When Bucket Sort is Used:
//  Floating-point numbers in range [0, 1)
//  Numbers in a known range
//  Uniform distribution

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

  public static void bucketSort(double[] input) {
    // 1. Create buckets
    List<Double>[] buckets = new ArrayList[input.length];
    for (int i = 0; i < input.length; i++) {
      buckets[i] = new ArrayList<>();
    }

    // 2. Distribute elements
    for (double num : input) {
      int bucketIndex = (int) (num * input.length);
      buckets[bucketIndex].add(num);
    }

    // 3. Sort individual buckets
    for (List<Double> bucket : buckets) {
      Collections.<Double>sort(bucket); // Insertion sort internally for small lists
    }

    // 4. Concatenate buckets
    int index = 0;
    for (List<Double> bucket : buckets) {
      for (double num : bucket) {
        input[index++] = num;
      }
    }
  }

  public static void main(String[] args) {
    double[] arr = {0.42, 0.32, 0.23, 0.52, 0.25, 0.47};

    bucketSort(arr);

    for (double num : arr) {
      System.out.print(num + " ");
    }
  }
}
