package datastructure_algorithms.datastructure.nonlinear.tree.graph.intervals;

import java.util.Objects;

public class Interval {

  private int start;
  private int end;
  private int index;
  private String name;

  public Interval(int start, int end, int index) {
    this.start = start;
    this.end = end;
    this.index = index;
  }

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public Interval(int start, int end, String name) {
    this.start = start;
    this.end = end;
    this.name = name;
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

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Interval interval = (Interval) o;
    return start == interval.start && end == interval.end;
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  @Override
  public String toString() {
    return start+"->"+end+" "+name+" "+ index;
  }
}
