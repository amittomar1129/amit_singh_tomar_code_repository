package designpattern.behavioral.state;

public class HasCardState implements ATMState {

  @Override
  public void insertCard(ATMContext context) {
    System.out.println("? Card already inserted.");
  }

  @Override
  public void enterPin(ATMContext context, int pin) {
    if (pin == 1234) {
      System.out.println("? Correct PIN entered.");
      context.setState(new AuthenticatedState());
    } else {
      System.out.println("? Incorrect PIN. Card ejected.");
      context.setState(new NoCardState());
    }
  }

  @Override
  public void withdrawCash(ATMContext context, int amount) {
    System.out.println("? Enter PIN first.");
  }

  @Override
  public void ejectCard(ATMContext context) {
    System.out.println("? Card ejected.");
    context.setState(new NoCardState());
  }
}
