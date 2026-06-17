package datastructure_algorithms.patterns.graph.dag;

//  There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1. You are
//  given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course
//  bi first if you want to take course ai.
//  For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//  Return the ordering of courses you should take to finish all courses. If there are many valid answers,
//  return any of them. If it is impossible to finish all courses, return an empty array.
//
//  Example 1:
//  Input: numCourses = 2, prerequisites = [[1,0]]
//  Output: [0,1]
//  Explanation: To take course 1 you should have finished course 0. So the correct course order is [0,1].
//
//  Example 2:
//  Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//  Output: [0,2,1,3]
//  Explanation: There are a few correct orders, for example [0,1,2,3] and [0,2,1,3].

//  Solution: “This is a topological sort problem. I used Kahn’s algorithm with indegree counting to
//  detect cycles and produce a valid course order.”
//  Time: O(V + E)
//  Space: O(V + E)


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule2 {


  public static int[] findOrder(int numCourses, int[][] prerequisites) {

    // Step 1: Build graph and indegree array
    List<List<Integer>> graph = new ArrayList<>();
    int[] indegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] p : prerequisites) {
      int course = p[0];
      int prereq = p[1];
      graph.get(prereq).add(course);
      indegree[course]++;
    }

    // Step 2: Add courses with indegree 0 to queue
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    // Step 3: BFS
    int[] order = new int[numCourses];
    int index = 0;

    while (!queue.isEmpty()) {
      int curr = queue.poll();
      order[index++] = curr;

      for (int next : graph.get(curr)) {
        indegree[next]--;
        if (indegree[next] == 0) {
          queue.offer(next);
        }
      }
    }

    // Step 4: Check if all courses are taken
    if (index != numCourses) {
      return new int[0]; // cycle detected
    }

    return order;
  }

  public static void main(String[] args) {
    int numCourses = 4;
    int[][] prerequisites = {
        {1, 0},
        {2, 0},
        {3, 1},
        {3, 2}
    };

    int[] result = findOrder(numCourses, prerequisites);

    if (result.length == 0) {
      System.out.println("No valid ordering possible");
    } else {
      System.out.println("Course Order:");
      for (int c : result) {
        System.out.print(c + " ");
      }
    }
  }
}
