package designpattern.behavioral.state;

public class NoCardState implements ATMState {

  @Override
  public void insertCard(ATMContext context) {
    System.out.println("? Card inserted.");
    context.setState(new HasCardState());
  }

  @Override
  public void enterPin(ATMContext context, int pin) {
    System.out.println("? No card inserted yet.");
  }

  @Override
  public void withdrawCash(ATMContext context, int amount) {
    System.out.println("? No card inserted.");
  }

  @Override
  public void ejectCard(ATMContext context) {
    System.out.println("? No card to eject.");
  }
}
