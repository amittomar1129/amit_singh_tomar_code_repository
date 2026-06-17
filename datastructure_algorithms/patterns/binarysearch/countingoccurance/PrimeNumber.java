package datastructure_algorithms.patterns.binarysearch.countingoccurance;

//  Solution: Instead of checking: 2, 3, 4, 5, 6, 7, 8...
//  We check:
//      5, 7
//      11, 13
//      17, 19
//      23, 25 (25 fails automatically)
//      29, 31
//      ...
//  Because:
//  Multiples of 2 already removed.
//  Multiples of 3 already removed.
//  Remaining possible primes are 6k ± 1.
//  This reduces ~66% of iterations compared to naive n^1/2 solution.

//  Time Complexity: O(n^1/2)
//  Space Complexity: O(1)

public class PrimeNumber {

  public static boolean isPrime(long n) {
    if (n <= 1) {
      return false;
    }
    if (n <= 3) {
      return true; // 2 and 3 are prime
    }
    if (n % 2 == 0 || n % 3 == 0) {
      return false;
    }
    // Check from 5 to n^1/2, skipping multiples of 2 and 3
    for (long i = 5; i * i <= n; i += 6) {
      if (n % i == 0 || n % (i + 2) == 0) {
        return false;
      }
    }

    return true;
  }

  // Check if number can be expressed as sum of two primes.
  //    Solution: “For odd numbers, only 2 can be one of the primes. For even numbers, we check
  //    prime pairs up to n/2 using an optimized primality test.”
  //  Time Complexity: O(n.n^1/2) worst, fast in practice
  public static boolean isSumOfTwoPrimes(long n) {
    if (n < 4) return false;

    // If n is odd, only possible case is 2 + (n-2)
    if ((n & 1) == 1) {
      return isPrime(n - 2);
    }

    // If n is even, try all primes up to n/2
    for (long i = 3; i <= n / 2; i += 2) {
      if (isPrime(i) && isPrime(n - i)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(isPrimeNo(29)); // true
    System.out.println(isPrimeNo(100)); // false

    System.out.println(sol(10)); // true (3 + 7)
    System.out.println(sol(11)); // false
    System.out.println(sol(28)); // true (11 + 17)
  }

  public static boolean isPrimeNo(int number) {
    if (number == 1) {
      return false;
    }
    if (number == 2 || number == 3) {
      return true;
    }

    if (number % 2 == 0 || number % 3 == 0) {
      return false;
    }

    for(int i = 5; i * i <= number; i = i + 6) {
      if (number % i == 0 || number % (i + 2) == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean sol(int number) {
    if (number <= 4) {
      return false;
    }

    if ((number & 1) == 1) { // odd number
      return isPrime(number - 2);
    }

    for(int i = 3; i <= number / 2; i+=2) {
      if (isPrimeNo(i) && isPrimeNo(number - i)) {
        return true;
      }
    }
    return false;
  }





}
