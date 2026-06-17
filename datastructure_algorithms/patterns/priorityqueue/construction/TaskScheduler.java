package datastructure_algorithms.patterns.priorityqueue.construction;

//  You are given a char array representing tasks CPU need to do. It contains capital letters A to Z
//  where each letter represents a different task. Tasks could be done without the original order of the array.
//  Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
//  However, there is a non-negative cooling interval n that means between two same tasks,
//  there must be at least n intervals that CPU are doing different tasks or just be idle.
//  You need to return the least number of intervals the CPU will take to finish all the given tasks.

//  tasks -> array of capital letters 'A' to 'Z', each letter = 1 type of task
//  n -> cooling interval
//  Rules: Each task takes 1 interval. Same task must be separated by at least n intervals (can be idle or other tasks)
//  Task order does not matter.
//  You want: Minimum number of intervals to finish all tasks

//  Example 1:
//  Input: tasks = ['A','A','A','B','B','B'], n = 2
//  Output: 8
//  Explanation: A -> B -> idle -> A -> B -> idle -> A -> B
//
//  Example 2:
//  Input: tasks = ['A','A','A','B','B','B'], n = 0
//  Output: 6
//  Explanation: A -> A -> A -> B -> B -> B

//  Solution: I count the frequency of each task.
//  The task with the highest frequency determines the framework of the schedule.
//  Each occurrence of the max frequency task needs n cooling intervals.
//  I use the formula (maxFreq - 1) * (n + 1) + countMax, and compare it with total tasks.
//  This guarantees the minimum intervals.

//  Minimum intervals formula: (intervals without extra tasks) = (maxFreq - 1) * (n + 1) + countMax
//  maxFreq = frequency of the most frequent task
//  countMax = number of tasks with frequency = maxFreq

//  Time	O(n + 26 log 26) ? O(n) ?
//  Space	O(26) = O(1) ?

import java.util.Arrays;

public class TaskScheduler {

  public static int leastInterval(char[] tasks, int n) {
    int[] count = new int[26];
    for (char c : tasks) {
      count[c - 'A']++;
    }
    Arrays.sort(count);

    int maxFreq = count[25];
    int countMax = 0;

    for (int f : count) {
      if (f == maxFreq) {
        countMax++;
      }
    }

    int intervals = (maxFreq - 1) * (n + 1) + countMax;
    return Math.max(intervals, tasks.length);
  }

  public static void main(String[] args) {

    char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
    int n = 2;

    int result = leastInterval(tasks, n);
    System.out.println("Minimum intervals -> " + result);
  }
}
