package datastructure_algorithms.patterns.arrays.slidingwindow;

//  Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
// B.length >= 3,
//  There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ...
// > B[B.length - 1]
//  (Note that B could be any subarray of A, including the entire array A). Given an array A, return
// the length of the
//  longest mountain. Return 0 if there is no mountain.
//
//  Example 1:
//  Input: [2,1,4,7,3,2,5]
//  Output: 5
//  Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
//
//  Example 2:
//  Input: [2,2,2]
//  Output: 0
//  Explanation: There is no mountain in this array, so the result is 0.
//
//  Example 3:
//  Input: [0,1,2,3,4,5,4,3,2,1,0]
//  Output: 11
//  Explanation: The largest mountain is [0,1,2,3,4,5,4,3,2,1] which has length 11.

//  Solution: Optimal Approach – One Pass (O(n), O(1))
//  A mountain has three parts: Up-slope (strictly increasing), Peak, Down-slope (strictly
// decreasing)
//  Time	O(n)
//  Space	O(1)

//  [0,1,2,3,4,5,4,3,2,1,0]
public class LongestMountain {

  public static int longestMountain(int[] input) {
    int up = 0, down = 0;
    int maxLen = 0;
    for (int i = 1; i < input.length; i++) {
      // Reset if plateau or new increasing after descent
      if (down > 0 && input[i] >= input[i - 1]) {
        up = 0;
        down = 0;
      }
      if (input[i] > input[i - 1]) {
        up++;
      } else if (input[i] < input[i - 1]) {
        if (up > 0) {
          down++;
        }
      }
      if (up > 0 && down > 0) {
        maxLen = Math.max(maxLen, up + down + 1);
      }
    }
    return maxLen;
  }

  public static void main(String[] args) {
    int[] A = {0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0};
    System.out.println("Longest mountain length: " + sol(A));
  }

  public static int sol(int[] input) {
    int maxMountain = 0;
    int up = 0;
    int down = 0;

    for (int i = 1; i < input.length; i++) {

      if (up > 0 && down > 0 && (input[i] > input[i - 1])) {
        up = 0;
        down = 0;
      }

      if (input[i] > input[i - 1]) {
        up++;
      } else {
        if (up > 0) {
          down++;
        }
      }
      if (up > 0 && down > 0) {
        maxMountain = Math.max(maxMountain, down + up + 1);
      }
    }

    return maxMountain;
  }
}
