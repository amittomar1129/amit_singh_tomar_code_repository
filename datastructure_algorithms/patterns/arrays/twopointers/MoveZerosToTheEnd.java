package datastructure_algorithms.patterns.arrays.twopointers;

//  Move to zeros to the end of the given array without changing the actual order of input array.
//  In-place
//  O(n) time
//  O(1) extra space
//  Order of non-zero elements preserved

//  Time	O(n)
//  Space	O(1)

import java.util.Arrays;

public class MoveZerosToTheEnd {

  public static void moveAllZerosToTheEnd(int[] input) {
    int left = 0;
    for (int i = 0; i < input.length; i++) {
      if(input[i] != 0) {
        input[left++] = input[i];
      }
    }
    while (left < input.length) {
      input[left++] = 0;
    }
  }

  public static void main(String[] args) {
    int[] input = {1, 2, 0, 0, 3, 4, 0, 5, 0, 0, 0, 6, 7};
    sol(input);
    System.out.println(Arrays.toString(input));
  }


  public static void sol(int[] input) {
    int left = 0;
    int write = 0;

    while (left < input.length) {
      if (input[left] != 0) {
        input[write++] = input[left++];
      } else {
        left++;
      }
    }

    for(int i = write; i < input.length; i++) {
      input[i] = 0;
    }
  }























}
