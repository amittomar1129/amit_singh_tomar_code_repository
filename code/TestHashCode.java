package code;

import java.util.Objects;

public class TestHashCode {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestHashCode that = (TestHashCode) o;
    return id == that.id && Objects.equals(name, that.name);
  }

  public static void main(String[] args) {

    TestHashCode t1 = new TestHashCode();
    TestHashCode t2 = new TestHashCode();

    System.out.println(t1);
    System.out.println(t2);
    System.out.println(t1.equals(t2));
    System.out.println(t1 == t2);
  }
}
