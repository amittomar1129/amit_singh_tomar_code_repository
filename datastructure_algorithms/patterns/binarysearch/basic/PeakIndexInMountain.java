package datastructure_algorithms.patterns.binarysearch.basic;

//  Let's call an array arr a mountain if the following properties hold: arr.length >= 3,
//  there exists some i with 0 < i < arr.length - 1 such that: arr[0] < arr[1] < ... arr[i-1] < arr[i] > arr[i+1] > ... >
//  arr[arr.length - 1]. Given an integer array arr that is guaranteed to be a mountain,
//  return any i such that arr[i] is the peak element.
//
//  Example 1:
//  Input: [0,1,0]
//  Output: 1
//  Explanation: In this case, 1 is the peak element because arr[1] > arr[0] and arr[1] > arr[2].
//
//  Example 2:
//  Input: [0,2,1,0]
//  Output: 1
//  Explanation: In this case, 2 is the peak element because arr[2] > arr[1] and arr[2] > arr[3].
//
//  Example 3:
//  Input: [0,10,5,2]
//  Output: 1
//  Explanation: In this case, 10 is the peak element because arr[1] > arr[0] and arr[1] > arr[2].

//  Solution: At any position mid:
//  If arr[mid] < arr[mid + 1] -> you are on the increasing slope
//  If arr[mid] > arr[mid + 1] -> you are on the decreasing slope or at peak
//  This lets us use binary search.
//  Time: O(log n)
//  Space: O(1)

public class PeakIndexInMountain {

  public static int peakIndexInMountainArray(int[] input) {
    int left = 0;
    int right = input.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (input[mid] < input[mid + 1]) {
        // Increasing slope ? peak is on the right
        left = mid + 1;
      } else {
        // Decreasing slope or peak ? peak is on the left or mid
        right = mid;
      }
    }

    // left == right ? peak index
    return left;
  }

  public static void main(String[] args) {
    int[] arr = {1, 3, 5, 7, 6, 4, 2};
    System.out.println(sol(arr)); // 3
  }

//  {1, 3, 5, 7, 6, 4, 2}
//  {1, 9, 7, 6, 4, 2, 1}
//  {1, 3, 5, 7, 8, 9, 2}

  public static int sol(int[] input) {
    int left = 0;
    int right = input.length -1 ;
    int answer = 0;

    while (left < right) {
      int mid = left + (right - left) /2;

      if (input[mid] > input[mid - 1]) {
        left = mid + 1;
        answer = mid;
      } else {
        right = mid;
      }
    }

    return answer;
  }






















}
