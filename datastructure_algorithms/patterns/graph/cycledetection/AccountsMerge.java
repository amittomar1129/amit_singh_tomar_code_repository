package datastructure_algorithms.patterns.graph.cycledetection;

//  Given a list of accounts where each element accounts[i] is a list of strings, where the first element
//  accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//  Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there
//  is some common email to both accounts.
//  Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
//  After merging the accounts, return the accounts in the following format:
//    - The first element of each account is the name.
//    - The rest of the elements are emails in sorted order.
//  The accounts themselves can be returned in any order.

//  Each account = [name, email1, email2, ...]
//  Two accounts belong to the same person if they share at least one email
//  Names can be the same for different people, so emails define identity
//  After merging:
//  Output format = [name, sorted_emails...]
//  Order of accounts doesn’t matter

//  Think of emails as nodes in a graph:
//  If two emails appear in the same account, they are connected
//  All connected emails belong to one merged account
//  We need to:
//  Group emails by connectivity
//  Union-Find (DSU) is the cleanest solution

//  Example 1:
//  Input: [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com",
//  "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
//  Output: [["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"], ["John", "johnnybravo@mail.com"],
//  ["Mary", "mary@mail.com"]]
//  Explanation: The first and third John's accounts have common email "johnsmith@mail.com", so they are merged.
//  The second John and Mary accounts remain separate.

//  Solution: “We treat each email as a node and union emails that appear in the same account.
//  After union-find, each connected component forms a merged account.”
//  This belongs to the “Union-Find for grouping by shared attribute” pattern.
//  Time	O(N * ?(N) + N log N)
//  Space	O(N)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsMerge {

  static class UnionFind {
    int[] parent;

    UnionFind(int n) {
      parent = new int[n];
      for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
      if (parent[x] != x)
        parent[x] = find(parent[x]);
      return parent[x];
    }

    void union(int x, int y) {
      int px = find(x);
      int py = find(y);
      if (px != py) parent[py] = px;
    }
  }

  public static List<List<String>> accountsMerge(List<List<String>> accounts) {

    Map<String, Integer> emailToId = new HashMap<>();
    Map<String, String> emailToName = new HashMap<>();
    int id = 0;

    // Assign id to each email
    for (List<String> acc : accounts) {
      String name = acc.get(0);
      for (int i = 1; i < acc.size(); i++) {
        String email = acc.get(i);
        if (!emailToId.containsKey(email)) {
          emailToId.put(email, id++);
          emailToName.put(email, name);
        }
      }
    }

    UnionFind uf = new UnionFind(id);

    // Union emails in the same account
    for (List<String> acc : accounts) {
      int firstEmailId = emailToId.get(acc.get(1));
      for (int i = 2; i < acc.size(); i++) {
        uf.union(firstEmailId, emailToId.get(acc.get(i)));
      }
    }

    // Group emails by root
    Map<Integer, List<String>> groups = new HashMap<>();
    for (String email : emailToId.keySet()) {
      int root = uf.find(emailToId.get(email));
      groups.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
    }

    // Build result
    List<List<String>> result = new ArrayList<>();
    for (List<String> emails : groups.values()) {
      Collections.sort(emails);
      List<String> merged = new ArrayList<>();
      merged.add(emailToName.get(emails.get(0)));
      merged.addAll(emails);
      result.add(merged);
    }

    return result;
  }

  public static void main(String[] args) {
    List<List<String>> accounts = Arrays.asList(
        Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
        Arrays.asList("John", "johnnybravo@mail.com"),
        Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
        Arrays.asList("Mary", "mary@mail.com")
    );

    System.out.println(accountsMerge(accounts));
  }

}
