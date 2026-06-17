package datastructure_algorithms.patterns.binarysearch.countingoccurance;

//  Count the number of prime numbers less than a non-negative number, n.
//
//  Example 1:
//  Input: n = 10
//  Output: 4
//  Explanation: There are 4 prime numbers less than 10, which are 2, 3, 5, 7.
//
//  Example 2:
//  Input: n = 0
//  Output: 0
//  Explanation: There are no prime numbers less than 0.
//
//  Example 3:
//  Input: n = 1
//  Output: 0
//  Explanation: There are no prime numbers less than 1.

//  Solution: Checking each number individually is too slow.
//  Assume all numbers are prime. Systematically mark multiples of primes as non-prime. Count what
// remains.
//  Time	O(n log log n)
//  Space	O(n)

public class CountPrimes {

  public static int countPrimes(int n) {
    if (n < 2) {
      return 0;
    }

    boolean[] isPrime = new boolean[n];
    for (int i = 2; i < n; i++) {
      isPrime[i] = true;
    }

    for (int i = 2; i * i < n; i++) {
      if (isPrime[i]) {
        for (int j = i * i; j < n; j = j + i) {
          isPrime[j] = false;
        }
      }
    }

    int count = 0;
    for (int i = 2; i < n; i++) {
      if (isPrime[i]) {
        count++;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    System.out.println(sol(10)); // Output -> 4 (2,3,5,7)
    System.out.println(sol(50)); // Output -> 15
    System.out.println(sol(100)); // Output -> 25
  }

  public static int sol(int number) {
    int count = 0;
    if (number == 1) {
      return 0;
    }
    boolean[] isPrime = new boolean[number];
    for (int i = 2; i < isPrime.length; i++) {
      isPrime[i] = true;
    }

    for (int i = 2; i * i < number; i++) {
      if (!isPrime[i]) {
        continue;
      }
      for (int j = i * i; j < number; j = j + i) {
        isPrime[j] = false;
      }
    }

    for (int i = 2; i < isPrime.length; i++) {
      if (isPrime[i]) {
        count++;
      }
    }
    return count;
  }
}
