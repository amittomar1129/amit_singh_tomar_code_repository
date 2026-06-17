package datastructure_algorithms.datastructure.nonlinear.tree.graph.intervals;

public class Task {

  private int start;
  private int end;
  private int profit;

  public Task(int start, int end, int profit) {
    this.start = start;
    this.end = end;
    this.profit = profit;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public int getProfit() {
    return profit;
  }

  public void setProfit(int profit) {
    this.profit = profit;
  }

  @Override
  public String toString() {
    return "Task{" +
        "start=" + start +
        ", end=" + end +
        ", profit=" + profit +
        '}';
  }
}

