package datastructure_algorithms.sorting;


//  Tim Sort = Insertion Sort + Merge Sort (done intelligently)
//  Tim Sort is a hybrid stable sorting algorithm that combines insertion sort for small runs and
//  merge sort for large datasets. It takes advantage of existing order in the data, achieving O(n)
//  time for nearly sorted arrays.

//  Steps:
//  Divide array into runs.
//  Sort small runs using Insertion Sort.
//  Push runs onto stack.
//  Merge runs using Merge Sort rules.
//  Continue until one sorted array remains.

public class TimSort {}
