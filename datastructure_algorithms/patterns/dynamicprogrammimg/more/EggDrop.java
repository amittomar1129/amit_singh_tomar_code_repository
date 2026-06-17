package datastructure_algorithms.patterns.dynamicprogrammimg.more;

//  You are given:
//  E eggs
//  F floors
//
//  You need to find: Minimum number of attempts required in worst case to find the critical floor.
//  Critical floor = highest floor from which egg doesn’t break.


//  Time  = O(E log F)
//  Space = O(E)

public class EggDrop {

  public static int eggDrop(int eggs, int floors) {
    int[] dp = new int[eggs + 1];
    int attempts = 0;

    while (dp[eggs] < floors) {
      attempts++;

      for (int i = eggs; i >= 1; i--) {
        dp[i] = dp[i] + dp[i - 1] + 1;
      }
    }

    return attempts;
  }

  public static void main(String[] args) {
    int eggs = 2;
    int floors = 6;

    System.out.println("Minimum attempts = " + eggDrop(eggs, floors));
  }

  public static int sol(int eggs, int floors) {
    int[] dp = new int[eggs+1];
    int attempts = 0;
    while (dp[eggs] < floors) {
      attempts++;
      for(int i = eggs; i >= 1; i--) {
        dp[i] = dp[i] + dp[i-1] + 1;
      }
    }

    return attempts;
  }























}
