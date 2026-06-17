package datastructure_algorithms.patterns.arrays.slidingwindow;

//  There are several cards arranged in a row, and each card has a value. You are given an integer
// array cardPoints
//  and an integer k. In one step, you can take one card from the beginning or from the end of the
// row. You have to take
//  exactly k cards. Your score is the sum of the values of the cards you have taken. Return the
// maximum score you can obtain.
//
//  Example 1:
//  Input: [1,2,3,4,5,6,1]
//  Output: 12
//
//  Example 2:
//  Input: [2,2,2]
//  Output: 4
//
//  Example 3:
//  Input: [9,7,7,9,7,7,9]
//  Output: 55

//  Solution: Compute total sum of all cards. Find the minimum sum subarray of length n - k.
//  Answer = totalSum - minWindowSum
//  Flip the problem (Key Trick), If we minimize the sum of the middle block we leave behind,
//  we maximize the sum of cards we take.
//  Time	O(n)
//  Space	O(1)

public class MaximumPoints {

  public static int maxScore(int[] cardPoints, int k) {
    int length = cardPoints.length;
    // If we take all cards
    if (k == length) {
      int sum = 0;
      for (int x : cardPoints) {
        sum += x;
      }
      return sum;
    }

    int totalSum = 0;
    for (int x : cardPoints) {
      totalSum += x;
    }

    int windowSize = length - k;
    int sum = 0;
    int minWindowSum = Integer.MAX_VALUE;

    int left = 0;
    for (int i = 0; i < length; i++) {
      sum += cardPoints[i];
      if (i - left + 1 == windowSize) {
        minWindowSum = Math.min(minWindowSum, sum);
        sum = sum - cardPoints[left++];
      }
    }
    return totalSum - minWindowSum;
  }

  public static void main(String[] args) {
    int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
    int k = 3;
    System.out.println("Maximum score: " + maxScore(cardPoints, k));
  }

  public static int sol(int[] input, int k) {
    int window = input.length - k;
    int sum = 0;

    for (int i = 0; i < window; i++) {
      sum += input[i];
    }
    int minWindowSum = sum;

    for (int i = window; i < input.length; i++) {
      sum += input[i];
      sum -= input[i - window];
      minWindowSum = Math.min(sum, minWindowSum);
    }

    int totalSum = 0;
    for(int i = 0; i < input.length; i++) {
      totalSum += input[i];
    }

    return totalSum - minWindowSum;
  }
}
