package designpattern.behavioral.strategy;

public class UPIStrategy implements PaymentStrategy<Double> {

  private String upiId;
  private String pin;

  public UPIStrategy() {
  }

  public UPIStrategy(String upiId, String pin) {
    this.upiId = upiId;
    this.pin = pin;
  }

  @Override
  public void pay(Double amount) {
    // Logic to pay
    System.out.println("processing info .... " + upiId + pin);
    System.out.println(amount + " paid with upi");

  }
}
