package datastructure_algorithms.datastructure.nonlinear.tree.heap;

public class Data<E extends Comparable> {

  private E value;

  public Data(E value) {
    this.value = value;
  }

  public E getValue() {
    return value;
  }

  public void setValue(E value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
