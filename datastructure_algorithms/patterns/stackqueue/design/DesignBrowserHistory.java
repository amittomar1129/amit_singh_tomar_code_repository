package datastructure_algorithms.patterns.stackqueue.design;

//  You have a browser of one tab where you start on the homepage and you can visit another url, get back in the
//  history number of steps or move forward in the history number of steps. Implement the BrowserHistory class:
//
//  Example 1:
//  Input: ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
//  Output: [null,null,null,null,"leetcode.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]

//  Dry Run (Doubly Linked List)
//  Start: leetcode.com
//  Visit google.com -> current = google.com
//  Visit facebook.com -> current = facebook.com
//  Visit youtube.com -> current = youtube.com
//  back(1) -> move to facebook.com
//  back(1) -> move to google.com
//  forward(1) -> move to facebook.com
//  Visit linkedin.com -> clears forward (youtube.com gone)
//  forward(2) -> can't move, stays at linkedin.com
//  back(2) -> move to google.com
//  back(7) -> move to leetcode.com

//  Advantages Over Two Stacks
//  Feature	                      Two Stacks	                  Doubly Linked List
//  visit	                          O(1)	                          O(1)
//  back	                          O(steps)	                      O(steps)
//  forward	                        O(steps)	                      O(steps)
//  Memory	                        extra stacks	                  pure nodes
//  Large history	                  stack overhead	                memory-friendly

public class DesignBrowserHistory {

  // Doubly Linked List Node
  private class Node {

    String url;
    Node prev;
    Node next;

    Node(String url) {
      this.url = url;
    }
  }

  private Node current;

  // Constructor
  public DesignBrowserHistory(String homepage) {
    current = new Node(homepage);
  }

  // Visit a new URL
  public void visit(String url) {
    Node newNode = new Node(url);
    current.next = null; // clear forward history
    newNode.prev = current;
    current.next = newNode;
    current = newNode;
  }

  // Move back up to steps
  public String back(int steps) {
    while (steps > 0 && current.prev != null) {
      current = current.prev;
      steps--;
    }
    return current.url;
  }

  // Move forward up to steps
  public String forward(int steps) {
    while (steps > 0 && current.next != null) {
      current = current.next;
      steps--;
    }
    return current.url;
  }

  // Main method to test
  public static void main(String[] args) {
    DesignBrowserHistory browser = new DesignBrowserHistory("leetcode.com");
    browser.visit("google.com");
    browser.visit("facebook.com");
    browser.visit("youtube.com");

    System.out.println(browser.back(1));    // facebook.com
    System.out.println(browser.back(1));    // google.com
    System.out.println(browser.forward(1)); // facebook.com
    browser.visit("linkedin.com");
    System.out.println(browser.forward(2)); // linkedin.com
    System.out.println(browser.back(2));    // google.com
    System.out.println(browser.back(7));    // leetcode.com
  }

}
