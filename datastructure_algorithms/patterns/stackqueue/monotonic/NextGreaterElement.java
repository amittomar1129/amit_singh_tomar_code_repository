package datastructure_algorithms.patterns.stackqueue.monotonic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//  You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
//  Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
//  The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
//  If it does not exist, output -1 for this number.
//
//  Example 1:
//  Input: [4,1,2]
//  Output: [-1,3,-1]
//  Explanation: For number 4 in the first array, there is no next greater number in the second array. For number 1 in the first array, the next greater number in the second array is 3. For number 2 in the first array, there is no next greater number in the second array.
//
//      Example 2:
//  Input: [2,4]
//  Output: [3,-1]
//  Explanation: For number 2 in the first array, the next greater number in the second array is 3. For number 4 in the first array, there is no next greater number in the second array.
//
//      Example 3:
//  Input: [1,3,5,2,4]
//  Output: [3,-1,-1,3,-1]
//  Explanation: For number 1 in the first array, the next greater number in the second array is 3. For number 3 in the first array, there is no next greater number in the second array. For number 5 in the first array, there is no next greater number in the second array. For number 2 in the first array, the next greater number in the second array is 3. For number 4 in the first array, there is no next greater number in the second array.

//  Solution: I scan nums2 once using a decreasing monotonic stack to compute the next greater element for each value.
//  Every element is pushed and popped once. I store the result in a map and build the answer for nums1 in constant time per element.
//  Time: O(n + m)
//  Space: O(n)

public class NextGreaterElement {

  public static int nextGreaterElement(int nums, int[] nums2) {
    Map<Integer, Integer> nextGreater = new HashMap<>();
    Deque<Integer> stack = new ArrayDeque<>();
    // Step 1: Compute next greater for nums2
    for (int num : nums2) {
      while (!stack.isEmpty() && stack.peek() < num) {
        nextGreater.put(stack.pop(), num);
      }
      stack.push(num);
    }
    // Step 2: Remaining elements have no next greater
    while (!stack.isEmpty()) {
      nextGreater.put(stack.pop(), -1);
    }
    return nextGreater.get(nums);
  }

  public static void main(String[] args) {
    int nums1 = 7;
    int[] nums2 = {1, 3, 4, 7, 5, 9};

    int output = nextGreaterElement(nums1, nums2);

    System.out.println("Next Greater Elements:");
    System.out.println(output);
  }

}
