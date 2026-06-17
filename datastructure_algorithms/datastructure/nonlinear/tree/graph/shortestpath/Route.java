package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import java.util.List;

final class Route implements Comparable<Route> {

  private List<Integer> vertices;
  private int cost;

  public Route() {
  }

  public Route(List<Integer> vertices, int cost) {
    this.vertices = vertices;
    this.cost = cost;
  }

  public List<Integer> getVertices() {
    return vertices;
  }

  public void setVertices(List<Integer> vertices) {
    this.vertices = vertices;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  @Override
  public String toString() {
    return cost +": " + vertices ;
  }

  @Override
  public int compareTo(Route o) {
    return Integer.compare(this.getCost(), o.getCost());
  }
}
