package designpattern.behavioral.state;

import javax.swing.*;
import java.awt.*;


public class App {

	// It allows an object to change the behavior when its internal state changes.
	// When the behavior of object depends on its state and it must be able to change its behavior at runtime
	// according to the new state.

	// Example can be of Java thread states. A thread can be one of its five states during it’s life cycle. It’s next state
	// can be determined only after getting it`s current state. e.g. we can not start a stopped thread or we cannot a make a thread
	// wait, until it has started running.

	//	Advantage:
	//		It keeps the state-specific behavior.
	//		It makes any state transitions explicit.
	//		The State pattern minimizes conditional complexity, eliminating the need for if and switch statements in objects that have 
	//		different behavior requirements.

	//	Disadvantages:
	//  	A developer needs to write a large amount of code for the state schema.
	//		Thus, for N states with M transition methods, the total number of methods required will be (N+1)*M.
	
//	Problem:
//	ATM/ Payment System
//  States: CardInserted, PinEntered, SelectingTransaction, DispensingCash
//  Behavior changes with state — e.g., you can’t withdraw money until a card and valid PIN are entered.


	public static void main(String[] args) {

		ATMContext atm = new ATMContext(5000);

		atm.enterPin(1234);               // No card inserted
		atm.insertCard();                 // Card inserted
		atm.enterPin(1111);               // Incorrect PIN
		atm.insertCard();                 // Card inserted
		atm.enterPin(1234);               // Correct PIN
		atm.withdrawCash(1500);    // Dispensed
		atm.withdrawCash(4000);    // Insufficient balance
		atm.ejectCard();                  //  Card ejected
	}
}
