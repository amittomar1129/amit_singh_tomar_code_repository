package datastructure_algorithms.datastructure.nonlinear.tree.graph.intervals;

public class Event implements Comparable<Event> {

  private int time;
  private boolean isStart;
  private Interval interval;

  public Event(int time, boolean isStart, Interval interval) {
    this.time = time;
    this.isStart = isStart;
    this.interval = interval;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public boolean isStart() {
    return isStart;
  }

  public void setStart(boolean start) {
    isStart = start;
  }

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(
      Interval interval) {
    this.interval = interval;
  }

  @Override
  public int compareTo(Event event) {
    if (this.getTime() != event.getTime()) {
      return this.getTime() - event.getTime();
    }
    // End events should come before start events at the same time
    return Boolean.compare(this.isStart(), event.isStart());
  }
}
