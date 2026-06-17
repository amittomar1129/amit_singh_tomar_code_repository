package datastructure_algorithms.datastructure.nonlinear.tree.graph.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindCliques {

  public List<List<Interval>> findMaxCliques(List<Interval> intervals) {
    List<Event> events = new ArrayList<>();

    for (Interval interval : intervals) {
      events.add(new Event(interval.getStart(), true, interval));
      events.add(new Event(interval.getEnd(), false, interval));
    }

    Collections.sort(events);

    List<Interval> active = new ArrayList<>();
    int maxSize = 0;
    List<List<Interval>> result = new ArrayList<>();

    for (Event event : events) {
      if (event.isStart()) {
        active.add(event.getInterval());
        if (active.size() > maxSize) {
          maxSize = active.size();
          result.clear();
          result.add(new ArrayList<>(active));
        } else if (active.size() == maxSize) {
          result.add(new ArrayList<>(active));
        }
      } else {
        active.remove(event.getInterval());
      }
    }
    return result;
  }

  public List<List<Interval>> findAllCliques(List<Interval> intervals) {
    List<Event> events = new ArrayList<>();

    for (Interval interval : intervals) {
      events.add(new Event(interval.getStart(), true, interval));
      events.add(new Event(interval.getEnd(), false, interval));
    }

    Collections.sort(events);

    List<Interval> active = new ArrayList<>();
    List<List<Interval>> result = new ArrayList<>();
    for (Event event : events) {
      if (event.isStart()) {
        active.add(event.getInterval());
        if (active.size() >= 2) {
          result.add(new ArrayList<>(active));
        }
      } else {
        active.remove(event.getInterval());
      }
    }
    return result;
  }

  public List<Interval> maxIndependentSet(List<Interval> intervals) {
    // Sort by end time (ascending)
    intervals.sort(Comparator.comparingInt(interval -> interval.getEnd()));

    List<Interval> result = new ArrayList<>();
    int lastEnd = Integer.MIN_VALUE;

    for (Interval interval : intervals) {
      if (interval.getStart() >= lastEnd) {
        result.add(interval);
        lastEnd = interval.getEnd(); // update time boundary
      }
    }
    return result;
  }

  public static void main(String[] args) {
    FindCliques maximumClique = new FindCliques();
    List<Interval> intervals = new ArrayList<>();
    intervals.add(new Interval(1, 4, "I1"));
    intervals.add(new Interval(2, 5, "I2"));
    intervals.add(new Interval(3, 6, "I3"));
    intervals.add(new Interval(6, 7, "I4"));
    intervals.add(new Interval(6, 8, "I5"));

    List<List<Interval>> maxCliques = maximumClique.findMaxCliques(intervals);
    System.out.println("Maximum Clique Size: " + maxCliques.get(0).size());
    System.out.println("Maximum Cliques:");
    int i = 1;
    for (List<Interval> clique : maxCliques) {
      System.out.println(i++ + ": " + clique);
    }

    List<List<Interval>> cliques = maximumClique.findAllCliques(intervals);
    System.out.println("All Cliques (size >= 2):");
    int i1 = 1;
    for (List<Interval> clique : cliques) {
      System.out.println(i1++ + ": " + clique);
    }

    // Schedule maximum tasks such that no overlaps
    List<Interval> maxSet = maximumClique.maxIndependentSet(intervals);
    System.out.println("Maximum Independent Set Size: " + maxSet.size());
    System.out.println("Intervals: " + maxSet);
  }
}
