package designpattern.behavioral.state;

public class ATMContext {

  private ATMState state;
  private int balance;

  public ATMContext(int initialBalance) {
    this.balance = initialBalance;
    this.state = new NoCardState();
  }

  public ATMState getState() {
    return state;
  }

  public void setState(ATMState state) {
    this.state = state;
  }

  public int getBalance() {
    return balance;
  }

  public void deductBalance(int amount) {
    this.balance -= amount;
  }

  // Delegate methods
  public void insertCard() {
    state.insertCard(this);
  }

  public void enterPin(int pin) {
    state.enterPin(this, pin);
  }

  public void withdrawCash(int amount) {
    state.withdrawCash(this, amount);
  }

  public void ejectCard() {
    state.ejectCard(this);
  }
}
