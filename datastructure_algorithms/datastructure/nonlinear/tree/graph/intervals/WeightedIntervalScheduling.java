package datastructure_algorithms.datastructure.nonlinear.tree.graph.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WeightedIntervalScheduling {

  private List<Task> maximizeProfit(List<Task> tasks) {
    tasks.sort(Comparator.comparingInt(task -> task.getEnd()));

    // Precompute p(i): last non-overlapping task
    int[] lastNonOverlappingTasks = new int[tasks.size()];
    for (int i = 0; i < tasks.size(); i++) {
      lastNonOverlappingTasks[i] = -1;
      for (int j = i - 1; j >= 0; j--) {
        if (tasks.get(j).getEnd() <= tasks.get(i).getStart()) {
          lastNonOverlappingTasks[i] = j;
          break;
        }
      }
    }

    // DP table
    int[] profit = new int[tasks.size()];
    profit[0] = tasks.get(0).getProfit();

    for (int i = 1; i < tasks.size(); i++) {
      int includeProfit = tasks.get(i).getProfit();
      if (lastNonOverlappingTasks[i] != -1) {
        includeProfit += profit[lastNonOverlappingTasks[i]];
      }
      profit[i] = Math.max(includeProfit, profit[i - 1]);
    }

    // Reconstruct solution
    List<Task> result = new ArrayList<>();
    int i = tasks.size() - 1;
    while (i >= 0) {
      int includeProfit = tasks.get(i).getProfit();
      if (lastNonOverlappingTasks[i] != -1) {
        includeProfit += profit[lastNonOverlappingTasks[i]];
      }

      if (includeProfit >= (i == 0 ? 0 : profit[i - 1])) {
        result.add(tasks.get(i));
        i = lastNonOverlappingTasks[i];
      } else {
        i--;
      }
    }
    Collections.reverse(result);
    return result;
  }

  public static void main(String[] args) {
    WeightedIntervalScheduling scheduling = new WeightedIntervalScheduling();
    List<Task> tasks = new ArrayList<>();
    tasks.add(new Task(1, 4, 5));
    tasks.add(new Task(3, 5, 1));
    tasks.add(new Task(0, 6, 8));
    tasks.add(new Task(4, 7, 4));
    tasks.add(new Task(5, 9, 6));
    tasks.add(new Task(6, 10, 3));

    List<Task> best = scheduling.maximizeProfit(tasks);
    System.out.println("Max Profit: " +
        best.stream().mapToInt(t -> t.getProfit()).sum());
    System.out.println("Selected Tasks: " + best);
  }
}
