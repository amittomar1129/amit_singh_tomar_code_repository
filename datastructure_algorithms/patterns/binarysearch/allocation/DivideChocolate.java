package datastructure_algorithms.patterns.binarysearch.allocation;

//  You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given
// by the array sweetness.
//  You want to share the chocolate with your K friends so you start cutting the chocolate bar into
// K+1 pieces using K cuts,
//  each piece consists of some consecutive chunks. Being generous, you will eat the piece with the
// minimum total sweetness and
//  give the other pieces to your friends. Find the maximum total sweetness of the piece you can get
// by cutting the chocolate bar
//  optimally.
//
//  Example 1:
//  Input: [1,2,3,4,5,6,7,8,9]
//  Output: 6
//  Explanation: You can divide the chocolate to [1,2,3], [4,5,6], [7], [8], [9]. The piece with the
// minimum total sweetness is [4,5,6] and the total sweetness is 15.
//
//  Example 2:
//  Input: [5,6,7,8,9,1,2,3,4]
//  Output: 8
//  Explanation: You can divide the chocolate to [5,6,7], [8], [9], [1,2,3,4]. The piece with the
// minimum total sweetness is [8] and the total sweetness is 8.
//
//  Example 3:
//  Input: [1,2,2,1,2,2,1,2,2]
//  Output: 5
//  Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]. The piece with the
// minimum total sweetness is [1,2,2] and the total sweetness is 5.

//  Solution: This is a maximize-the-minimum problem. I binary search on the minimum sweetness and
//  greedily check if I can form at least K+1 pieces.
//  Time	O(n log S)
//  Space	O(1)

public class DivideChocolate {

  public static int maximizeSweetness(int[] sweetness, int K) {
    int left = Integer.MAX_VALUE;
    int right = 0;

    for (int s : sweetness) {
      left = Math.min(left, s);
      right += s;
    }

    right = right / (K + 1); // max possible minimum sweetness
    int answer = left;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (canSplit(sweetness, K + 1, mid)) {
        answer = mid;
        left = mid + 1; // try bigger minimum sweetness
      } else {
        right = mid - 1;
      }
    }

    return answer;
  }

  private static boolean canSplit(int[] sweetness, int piecesNeeded, int minSweetness) {
    int currentSum = 0;
    int pieces = 0;

    for (int s : sweetness) {
      currentSum += s;
      if (currentSum >= minSweetness) {
        pieces++;
        currentSum = 0;
      }
    }

    return pieces >= piecesNeeded;
  }

  public static void main(String[] args) {
    int[] sweetness = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int K = 5;
    System.out.println(sol(sweetness, K)); // Output -> 6
  }

  public static int sol(int[] input, int k) {
    int total = 0;
    int left = Integer.MAX_VALUE;

    for (int sweet : input) {
      total += sweet;
      left = Math.min(left, sweet);
    }

    int right = total / (k + 1);
    int answer = -1;

    while (left < right ) {
      int mid = left + (right - left) / 2;
      if (canSplitAll(input, k +1, mid)){
        left = mid + 1;
        answer = mid;
      } else {
        right = mid - 1;
      }
    }

    return answer;
  }

  // {1, 2, 3, 4, 5, 6, 7, 8, 9}
  public static boolean canSplitAll(int[] input, int piecesAllowed, int mid) {
    int total = 0;
    int pieces = 0;

    for (int sweet : input ) {
      total += sweet;
      if (total > mid) {
        pieces++;
        total = sweet;
      }
    }

    return pieces >= piecesAllowed;
  }
}
