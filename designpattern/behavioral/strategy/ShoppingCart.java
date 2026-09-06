package designpattern.behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart<T extends Number> {

  List<Product> products;
  PaymentStrategy paymentStrategy;

  public ShoppingCart() {
    products = new ArrayList<Product>();
  }

  public void addItem(Product item) {
    products.add(item);
  }

  public void removeItem(Product item) {
    products.remove(item);
  }

  public void selectStrategy(PaymentStrategy strategy) {
    this.paymentStrategy = strategy;
  }

  public void changeStrategy(PaymentStrategy strategy) {
    this.paymentStrategy = strategy;
  }

  private double calculateAmount() {
    Double amount = this.products.stream().mapToDouble(l -> Double.parseDouble(l.getPrice())).sum();
    return amount;
  }

  public void processPayment() {
    this.paymentStrategy.pay(calculateAmount());
  }
}
