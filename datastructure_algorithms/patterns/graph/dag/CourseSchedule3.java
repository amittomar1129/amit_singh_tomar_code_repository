package datastructure_algorithms.patterns.graph.dag;

//  There are a total of n courses you have to take, labeled from 0 to n - 1.
//  Some courses may have direct prerequisites, for example, to take course 0 you have to first take
//  course 1, which is expressed as a pair: [1,0]
//  Given the total number of courses n, a list of direct prerequisite pairs prerequisites, and a list
//  of queries pairs queries.
//  You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.
//  Return a boolean array answer, where answer[i] is the answer to the ith query.
//
//  Example 1:
//  Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
//  Output: [false,true]
//  Explanation: To take course 0 you have to first take course 1, so course 1 is a prerequisite of course 0. For the queries: Is 0 a prerequisite of 1? No. Is 1 a prerequisite of 0? Yes.
//
//  Example 2:
//  Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
//  Output: [false,false]
//  Explanation: There are no prerequisites, so no course is a prerequisite of another.
//
//  Example 3:
//  Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
//  Output: [true,true]
//  Explanation: Course 1 is a prerequisite of course 0 and course 2, so both queries return true.

//  Solution: We precompute all prerequisite relationships once, then answer each query in O(1).
//  Topological Sort + DP (Most Scalable), Avoids O(n³) Floyd–Warshall
//  “I precompute all prerequisite relationships using topological order and DP. For each course,
//  I propagate its prerequisites to dependent courses. This allows answering each query in O(1).”
//  Time	O(n³) worst case, but optimized via topo
//  Space	O(n²)


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule3 {

  public static boolean[] checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {

    List<Integer>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }

    int[] indegree = new int[n];
    for (int[] p : prerequisites) {
      graph[p[0]].add(p[1]);
      indegree[p[1]]++;
    }

    // pre[i][j] -> i is prerequisite of j
    boolean[][] pre = new boolean[n][n];

    // Topological sort
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    while (!queue.isEmpty()) {
      int u = queue.poll();

      for (int v : graph[u]) {
        pre[u][v] = true;

        // inherit all prerequisites
        for (int i = 0; i < n; i++) {
          if (pre[i][u]) {
            pre[i][v] = true;
          }
        }

        indegree[v]--;
        if (indegree[v] == 0) {
          queue.offer(v);
        }
      }
    }

    boolean[] result = new boolean[queries.length];
    for (int i = 0; i < queries.length; i++) {
      result[i] = pre[queries[i][0]][queries[i][1]];
    }

    return result;
  }

  public static void main(String[] args) {
    int n = 4;
    int[][] prerequisites = {
        {0, 1},
        {1, 2},
        {2, 3}
    };

    int[][] queries = {
        {0, 3},
        {1, 3},
        {3, 0}
    };

    boolean[] ans = checkIfPrerequisite(n, prerequisites, queries);

    System.out.println(Arrays.toString(ans));
  }
}
