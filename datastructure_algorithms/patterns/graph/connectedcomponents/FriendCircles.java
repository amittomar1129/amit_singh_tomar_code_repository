package datastructure_algorithms.patterns.graph.connectedcomponents;

//  There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature.
//  For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
//  And we defined a friend circle is a group of students who are direct or indirect friends.
//  Given a N*N matrix M representing the friend relationship between students in the class.
//  If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
//  You have to output the total number of friend circles among all the students.

//  We have N students
//  M[i][j] = 1 means student i and j are direct friends
//  Friendship is transitive
//  A friend circle = one connected component in the friendship graph
//  Goal -> count connected components

//  Example 1:
//  Input: M = [[1,1,0],[1,1,0],[0,0,1]]
//  Output: 2
//  Explanation: The 0th and 1st students are direct friends, so they are in a friend circle. The 2nd student is in another
//  friend circle.
//
//  Example 2:
//  Input: M = [[1,1,0],[1,1,1],[0,1,1]]
//  Output: 1
//  Explanation: All students are connected directly or indirectly, so there is only one friend circle.

//  Solution: This is a connected components problem in an undirected graph, where the adjacency matrix is given.
//  Time	O(N^2)
//  Space	O(N)

public class FriendCircles {

  public static int findCircleNum(int[][] graph) {
    int n = graph.length;
    boolean[] visited = new boolean[n];
    int components = 0;

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        dfs(graph, visited, i);
        components++;
      }
    }
    return components;
  }

  private static void dfs(int[][] graph, boolean[] visited, int i) {
    visited[i] = true;

    for (int j = 0; j < graph.length; j++) {
      if (graph[i][j] == 1 && !visited[j]) {
        dfs(graph, visited, j);
      }
    }
  }

  public static void main(String[] args) {
    int[][] M = {{1, 1, 0},
                 {1, 1, 0},
                 {0, 0, 1}};

    System.out.println("Friend Circles -> " + findCircleNum(M));
  }
}
