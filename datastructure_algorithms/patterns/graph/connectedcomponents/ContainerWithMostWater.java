package datastructure_algorithms.patterns.graph.connectedcomponents;

//  You are given an integer array height of length n. There are n vertical lines drawn such that the two
//  endpoints of the ith line are (i, 0) and (i, height[i]).
//  Find two lines that together with the x-axis form a container, such that the container contains the most water.
//  Return the maximum amount of water a container can store.
//  Notice that you may not slant the container.

//  You are given heights of vertical lines
//  Pick two lines
//  Area formed = min(height[left], height[right]) * (right - left)
//  Lines cannot be slanted
//  Return maximum possible area

//  Example 1:
//  Input: height = [1,8,6,2,5,4,8,3,7]
//  Output: 49
//  Explanation: The vertical lines are represented by array indices and their heights. The container formed between lines at index 1 and index 8 holds the most water, with an area of 49.
//
//  Example 2:
//  Input: height = [1,1]
//  Output: 1
//  Explanation: The container formed between the two lines at index 0 and 1 holds 1 unit of water.

//  Example 3:
//  Input: height = [4,3,2,1,4]
//  Output: 16
//  Explanation: The container formed between the lines at index 0 and 4 holds the most water, with an area of 16.

//  Solution: Area = min(height[left], height[right]) * (right - left)
//  Time	O(n)
//  Space	O(1)

public class ContainerWithMostWater {

  public static int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxWater = 0;

    while (left < right) {
      int width = right - left;
      int currHeight = Math.min(height[left], height[right]);
      int area = width * currHeight;

      maxWater = Math.max(maxWater, area);

      // Move the shorter pointer
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return maxWater;
  }

  public static void main(String[] args) {
    int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    System.out.println("Maximum Water -> " + maxArea(height));
  }
}
