package datastructure_algorithms.patterns.priorityqueue.merge;

//  Given three integer arrays sorted in non-decreasing order, find the smallest number that is common
//  in all three arrays. If there is no common number, return -1.

//  Solution: Since arrays are sorted, I use three pointers.
//  At each step, I advance the pointer with the smallest value because it cannot match larger ones later.
//  Time	O(n1 + n2 + n3)
//  Space	O(1)

public class FindSmallestCommonNumber {

  public static int findCommon(int[] a, int[] b, int[] c) {

    int i = 0, j = 0, k = 0;

    while (i < a.length && j < b.length && k < c.length) {

      if (a[i] == b[j] && b[j] == c[k]) {
        return a[i];
      }

      int min = Math.min(a[i], Math.min(b[j], c[k]));

      if (a[i] == min) i++;
      if (b[j] == min) j++;
      if (c[k] == min) k++;
    }

    return -1;
  }

  public static void main(String[] args) {

    int[] a = {1, 5, 10, 20, 40, 80};
    int[] b = {6, 7, 20, 80, 100};
    int[] c = {3, 4, 15, 20, 30, 70, 80, 120};

    int result = findCommon(a, b, c);

    System.out.println("Smallest common element -> " + result);
  }
}
