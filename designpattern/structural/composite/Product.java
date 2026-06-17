package designpattern.structural.composite;

public class Product implements Item {
  private final String name;
  private final double price;

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return name + " ($" + price + ")";
  }
}