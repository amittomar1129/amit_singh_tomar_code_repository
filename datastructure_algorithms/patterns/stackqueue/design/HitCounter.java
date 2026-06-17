package datastructure_algorithms.patterns.stackqueue.design;

//  Design a hit counter which counts the number of hits received in the past 5 minutes.
//
//  Example 1:
//  Input: hit(1)
//  Output: null
//  Explanation: hit(1) - The counter is incremented at timestamp = 1.
//
//  Example 2:
//  Input: hit(2)
//  Output: null
//  Explanation: hit(2) - The counter is incremented at timestamp = 2.
//
//  Example 3:
//  Input: getHits(301)
//  Output: 2
//  Explanation: getHits(301) — Only hits at timestamps 2 and 301 are within the last 5 minutes; the hit at timestamp 1 is expired.

//  Solution: “I’ll use a fixed-size sliding window with a circular buffer to achieve O(1) time and constant space.”
//  “This avoids memory blow-up under heavy traffic.”
//  Time O(1)
//  Space O(300)

public class HitCounter {

  private static class Bucket {
    int timestamp;
    int count;
  }

  private final int WINDOW = 300;
  private Bucket[] buckets;

  public HitCounter() {
    buckets = new Bucket[WINDOW];
    for (int i = 0; i < WINDOW; i++) {
      buckets[i] = new Bucket();
    }
  }

  // Record a hit
  public void hit(int timestamp) {
    int index = timestamp % WINDOW;
    Bucket bucket = buckets[index];

    if (bucket.timestamp != timestamp) {
      bucket.timestamp = timestamp;
      bucket.count = 1;
    } else {
      bucket.count++;
    }
  }

  // Get hits in last 5 minutes
  public int getHits(int timestamp) {
    int totalHits = 0;
    for (Bucket bucket : buckets) {
      if (timestamp - bucket.timestamp < WINDOW) {
        totalHits += bucket.count;
      }
    }
    return totalHits;
  }

  // Demo
  public static void main(String[] args) {
    HitCounter counter = new HitCounter();
    counter.hit(1);
    counter.hit(2);
    counter.hit(3);
    System.out.println(counter.getHits(4));   // 3

    counter.hit(300);
    System.out.println(counter.getHits(300)); // 4
    System.out.println(counter.getHits(301)); // 3
  }

}
