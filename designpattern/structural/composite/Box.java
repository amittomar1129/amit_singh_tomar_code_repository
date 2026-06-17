package designpattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Box implements Item {

  private final String name;
  private final double packagingCost;
  private final List<Item> items = new ArrayList<>();

  public Box(String name, double packagingCost) {
    this.name = name;
    this.packagingCost = packagingCost;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  @Override
  public double getPrice() {
    double total = packagingCost;
    for (Item item : items) {
      total += item.getPrice(); // recursive call if item is Box
    }
    return total;
  }

  @Override
  public String toString() {
    return name;
  }

  public void clear() {
    items.clear();
  }
}
