package designpattern.behavioral.state;

public class AuthenticatedState implements ATMState {

  @Override
  public void insertCard(ATMContext context) {
    System.out.println("? Card already inserted and authenticated.");
  }

  @Override
  public void enterPin(ATMContext context, int pin) {
    System.out.println("? PIN already verified.");
  }

  @Override
  public void withdrawCash(ATMContext context, int amount) {
    if (context.getBalance() >= amount) {
      context.deductBalance(amount);
      System.out.println("? Dispensed: " + amount + " | Remaining Balance: " + context.getBalance());
    } else {
      System.out.println("? Insufficient balance.");
    }
  }

  @Override
  public void ejectCard(ATMContext context) {
    System.out.println("? Card ejected. Thank you!");
    context.setState(new NoCardState());
  }
}
