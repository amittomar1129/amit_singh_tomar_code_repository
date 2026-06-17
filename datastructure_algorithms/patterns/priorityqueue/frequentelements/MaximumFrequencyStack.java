package datastructure_algorithms.patterns.priorityqueue.frequentelements;

//  Implement FreqStack, a class which simulates the operation of a stack that has the following
//  functions: push(int x), pop() -> int where pop() returns the most frequent element in the stack.
//  If there is a tie, return the element closest to the top of the stack.

//  You need a stack-like structure, but:
//  pop() returns the most frequent element
//  If frequencies tie ? return the most recently pushed one

//  push	O(1)
//  pop	O(1)
//  Space	O(n)

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MaximumFrequencyStack {

  private Map<Integer, Integer> freqMap;
  private Map<Integer, Deque<Integer>> groupMap;
  private int maxFreq;

  public MaximumFrequencyStack() {
    freqMap = new HashMap<>();
    groupMap = new HashMap<>();
    maxFreq = 0;
  }

  public void push(int num) {
    int freq = freqMap.getOrDefault(num, 0) + 1;
    freqMap.put(num, freq);

    if (!groupMap.containsKey(freq)) {
      groupMap.put(freq, new ArrayDeque<>());
    }

    groupMap.get(freq).push(num);
    maxFreq = Math.max(maxFreq, freq);
  }

  public int pop() {
    int val = groupMap.get(maxFreq).pop();
    freqMap.put(val, freqMap.get(val) - 1);

    if (groupMap.get(maxFreq).isEmpty()) {
      maxFreq--;
    }

    return val;
  }

  // main method with output
  public static void main(String[] args) {
    MaximumFrequencyStack stack = new MaximumFrequencyStack();

    stack.push(5);
    stack.push(7);
    stack.push(5);
    stack.push(7);
    stack.push(4);
    stack.push(5);

    System.out.println(stack.pop()); // -> 5
    System.out.println(stack.pop()); // -> 7
    System.out.println(stack.pop()); // -> 5
    System.out.println(stack.pop()); // -> 4
    System.out.println(stack.pop()); // -> 7
    System.out.println(stack.pop()); // -> 5
  }
}
