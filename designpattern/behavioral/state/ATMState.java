package designpattern.behavioral.state;

// State interface defining ATM operations
public interface ATMState {
  void insertCard(ATMContext context);
  void enterPin(ATMContext context, int pin);
  void withdrawCash(ATMContext context, int amount);
  void ejectCard(ATMContext context);
}
