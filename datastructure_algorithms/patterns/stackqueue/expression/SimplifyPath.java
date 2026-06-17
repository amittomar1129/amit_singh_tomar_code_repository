package datastructure_algorithms.patterns.stackqueue.expression;

import java.util.ArrayDeque;
import java.util.Deque;

//  Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
//  In a UNIX-style file system, a period '.' refers to the current directory. Furthermore, a double period '..'
//     moves up a directory. For more information, see: Absolute path vs relative path in Linux/Unix
//
//  Example 1:
//  Input: /home/
//  Output: /home
//  Explanation: Note that there is no trailing slash after the last directory name.
//
//  Example 2:
//  Input: /../
//  Output: /
//  Explanation: Going one level up from the root directory is a no-op, as the root directory is the highest level you can go.
//
//      Example 3:
//  Input: /home//foo/
//  Output: /home/foo
//  Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

//  Time: O(n)
//  Space: O(n) Where n is the length of the path.

public class SimplifyPath {

  public static String simplifyPath(String path) {
    Deque<String> stack = new ArrayDeque<>();
    String[] parts = path.split("/");

    for (String part : parts) {
      if (part.equals("") || part.equals(".")) {
        continue;
      } else if (part.equals("..")) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(part);
      }
    }

    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
      result.insert(0, "/" + stack.pop());
    }

    return result.length() == 0 ? "/" : result.toString();
  }

  public static void main(String[] args) {
    System.out.println(simplifyPath("/home/"));           // /home
    System.out.println(simplifyPath("/a/./b/../../c/"));  // /c
    System.out.println(simplifyPath("/../"));             // /
    System.out.println(simplifyPath("/home//foo/"));      // /home/foo
  }
}
