package datastructure_algorithms.patterns.stackqueue.slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

//  Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
//
//  Example 1:
//  Input: ['MovingAverage', 'next', 'next', 'next', 'next'] [[3], [1], [10], [3], [5]]
//  Output: [null, 1.0, 5.5, 4.66667, 6.0]
//  Explanation: MovingAverage movingAverage = new MovingAverage(3); movingAverage.next(1); // return 1.0 = 1 / 1 movingAverage.next(10); // return 5.5 = (1 + 10) / 2 movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3 movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3

//  You are given: A stream of integers (numbers arrive one by one). A fixed window size k.
//  For each new number, return the average of the last k numbers.
//  If fewer than k numbers exist, average all available numbers.

//  Solution: “I maintain a queue of the last k elements and a running sum so that each new average can be computed in constant time.”
//  Time per next()	O(1)
//      Space	O(k)

public class MovingAverageFromDataStream {

  private Queue<Integer> queue;
  private int size;
  private double sum;

  public MovingAverageFromDataStream(int size) {
    this.size = size;
    this.queue = new LinkedList<>();
    this.sum = 0.0;
  }

  public double next(int val) {
    queue.offer(val);
    sum += val;

    if (queue.size() > size) {
      sum -= queue.poll();
    }

    return sum / queue.size();
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {

    MovingAverageFromDataStream movingAverage = new MovingAverageFromDataStream(3);

    System.out.println(movingAverage.next(1));   // 1.0
    System.out.println(movingAverage.next(10));  // 5.5
    System.out.println(movingAverage.next(3));   // 4.6667
    System.out.println(movingAverage.next(5));   // 6.0
  }
}
