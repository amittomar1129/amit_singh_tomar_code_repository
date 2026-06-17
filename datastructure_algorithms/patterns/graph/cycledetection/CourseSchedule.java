package datastructure_algorithms.patterns.graph.cycledetection;

//  There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
//  You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take
//  course bi first if you want to take course ai.
//  For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//  Return true if you can finish all courses. Otherwise, return false.

//  You have numCourses courses: 0 -> numCourses-1
//  prerequisites[i] = [a, b] means:
//  b -> a   (b must be done before a)
//  You must check if it’s possible to complete all courses

//  Example 1:
//  Input: numCourses = 2, prerequisites = [[1,0]]
//  Output: true
//  Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
//
//  Example 2:
//  Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//  Output: false
//  Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should have finished course 1. So it is impossible.

//  Solution: This is a directed graph. The question becomes:
//  Does the directed graph contain a cycle?
//  If there is a cycle -> impossible
//  If no cycle -> possible
//  BFS (Topological Sort) Interview-Preferred: This is a cycle detection problem in a directed graph.
//  I use topological sorting with indegree counting. If all nodes are processed, no cycle exists.
//  Time	O(V + E)
//  Space	O(V + E)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

  public static boolean canFinish(int numCourses, int[][] prerequisites) {

    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    int[] indegree = new int[numCourses];

    // Build graph
    for (int[] pre : prerequisites) {
      int course = pre[0];
      int prereq = pre[1];
      graph.get(prereq).add(course);
      indegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();

    // Courses with no prerequisites
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    int completed = 0;

    while (!queue.isEmpty()) {
      int curr = queue.poll();
      completed++;

      for (int next : graph.get(curr)) {
        indegree[next]--;
        if (indegree[next] == 0) {
          queue.offer(next);
        }
      }
    }

    return completed == numCourses;
  }

  public static void main(String[] args) {
    int numCourses = 2;
    int[][] prerequisites = {{1, 0}};

    System.out.println("Can finish all courses -> " + canFinish(numCourses, prerequisites));
  }
}
