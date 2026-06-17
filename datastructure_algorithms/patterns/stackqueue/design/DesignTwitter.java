package datastructure_algorithms.patterns.stackqueue.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//  Design a simplified version of Twitter where users can post tweets, follow/unfollow other users,
//  and see the 10 most recent tweets in the user's news feed. Implement the Twitter class:
//
//  Example 1:
//  Input: null
//  Output: null
//  Explanation: Twitter twitter = new Twitter(); Initialize the Twitter object
//
//  Example 2:
//  Input: twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5) twitter.getNewsFeed(1);
//  Output: null, [5]
//  Explanation: User 1 posts a tweet with ID 5, User 1's news feed returns [5] — only their own tweet.
//
//  Example 3:
//  Input: twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6) twitter.follow(1, 2); // User 1 follows User 2 twitter.getNewsFeed(1);
//  Output: null, null, [6, 5]
//  Explanation: User 2 posts a tweet with ID 6, User 1 follows User 2, User 1's news feed now includes tweets from both User 1 and User 2, ordered from most recent to oldest: [6, 5].

//  Solution: We need to support:
//  postTweet(userId, tweetId)
//  follow(followerId, followeeId)
//  unfollow(followerId, followeeId)
//  getNewsFeed(userId) -> last 10 most recent tweets from:
//  user himself
//  users he follows

//  Key Observations:
//  Tweets are ordered by time
//  Each user has their own tweet list
//  News feed = merge tweets from multiple users
//  Only top 10 needed -> use max heap

//  postTweet
//      Time -> O(1)
//      Space -> O(total tweets)
//  follow / unfollow
//      Time -> O(1)
//  getNewsFeed
//      Worst case -> O(N log N)
//      N = total tweets from followees


public class DesignTwitter {

  private static int timeStamp = 0;

  private Map<Integer, Set<Integer>> followMap;
  private Map<Integer, List<Tweet>> tweetMap;

  // Tweet inner class
  private static class Tweet {

    int id;
    int time;

    Tweet(int id, int time) {
      this.id = id;
      this.time = time;
    }
  }

  public DesignTwitter() {
    followMap = new HashMap<>();
    tweetMap = new HashMap<>();
  }

  public void postTweet(int userId, int tweetId) {
    tweetMap.putIfAbsent(userId, new ArrayList<>());
    tweetMap.get(userId).add(new Tweet(tweetId, timeStamp++));
  }

  public List<Integer> getNewsFeed(int userId) {
    PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);
    // User follows himself
    followMap.putIfAbsent(userId, new HashSet<>());
    followMap.get(userId).add(userId);
    for (int followee : followMap.get(userId)) {
      List<Tweet> tweets = tweetMap.get(followee);
      if (tweets != null) {
        for (Tweet t : tweets) {
          maxHeap.offer(t);
        }
      }
    }
    List<Integer> result = new ArrayList<>();
    int count = 0;
    while (!maxHeap.isEmpty() && count < 10) {
      result.add(maxHeap.poll().id);
      count++;
    }
    return result;
  }

  public void follow(int followerId, int followeeId) {
    followMap.putIfAbsent(followerId, new HashSet<>());
    followMap.get(followerId).add(followeeId);
  }

  public void unfollow(int followerId, int followeeId) {
    if (followMap.containsKey(followerId) && followeeId != followerId) {
      followMap.get(followerId).remove(followeeId);
    }
  }

  // Test
  public static void main(String[] args) {
    DesignTwitter twitter = new DesignTwitter();

    twitter.postTweet(1, 5);
    System.out.println(twitter.getNewsFeed(1)); // [5]

    twitter.follow(1, 2);
    twitter.postTweet(2, 6);
    System.out.println(twitter.getNewsFeed(1)); // [6,5]

    twitter.unfollow(1, 2);
    System.out.println(twitter.getNewsFeed(1)); // [5]
  }

}
