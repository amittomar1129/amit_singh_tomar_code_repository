package datastructure_algorithms.datastructure.nonlinear.tree.graph;

import java.util.Comparator;
import java.util.Objects;

public class Edge implements Comparable<Edge>  {

  private int from;
  private int to;
  private int weight;
  private Edge reverseEdge;

  public Edge() {
  }

  public Edge(int to) {
    this.to = to;
  }

  public Edge(int to, int weight) {
    this.to = to;
    this.weight = weight;
  }

  public Edge(int from, int to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getTo() {
    return to;
  }

  public void setTo(int to) {
    this.to = to;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public Edge getReverseEdge() {
    return reverseEdge;
  }

  public void setReverseEdge(Edge reverseEdge) {
    this.reverseEdge = reverseEdge;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Edge edge = (Edge) o;
    return to == edge.to && weight == edge.weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(to, weight);
  }

  @Override
  public String toString() {
    return to + "";
  }

  @Override
  public int compareTo(Edge other) {
    return this.weight - other.weight;
  }
}
