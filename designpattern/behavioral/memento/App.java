package designpattern.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

public class App {

//	Memento pattern is also known as Token.	
//	The memento design pattern is used when we want to save the state of an object so that we can restore it later on. 
//	Saved state data of the object is not accessible outside the Object, this protects the integrity of saved state data.
//	It is used in Undo and Redo operations in most software.
//	It is also used in database transactions.

//	Memento pattern is implemented with two Objects – Originator and Caretaker.
//	The originator is the Object whose state needs to be saved and restored,
//	The private inner class is called “Memento”, It uses an inner class to save the state of Object.
// 	Caretaker responsible for keeping the memento. The memento is transparent to the caretaker,
// 	and the caretaker must not operate on it.

//	Advantage:
//		It preserves encapsulation boundaries.
//		It simplifies the originator.

//	Disadvantages:
//  	Increased Memory Usage: The Memento pattern can increase memory usage, particularly
//  	when objects need to store a large number of mementos.
//		Caretakers should track the originator's lifecycle to be able to destroy obsolete mementos.

//	Problem:
//		The Memento Design Pattern offers a solution to implement undoable actions. We can do this by saving the state of an object
//		at a given instant and restoring it if the actions performed since need to be undone. Practically, the object whose state 
//		needs to be saved is called an Originator.

	public static void main(String[] args) {

		Caretaker caretaker = new Caretaker();

		Life life1 = new Life("born", "1991");
		Life life2 = new Life("enjoy", "2001");
		Life life3 = new Life("photo", "2015");
		
		// time travel and record the eras
		caretaker.set(life1);
		caretaker.saveToMemento();
		caretaker.set(life2);
		caretaker.saveToMemento();
		caretaker.set(life3);
		caretaker.saveToMemento();

		caretaker.getMemento();
		caretaker.restoreFromMemento(1);
	}
}
