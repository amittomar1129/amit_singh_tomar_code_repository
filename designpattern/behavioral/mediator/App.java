package designpattern.behavioral.mediator;

public class App {

//	It promotes loose coupling between objects by centralizing their communication through a mediator object.
//	Instead of objects directly communicating with each other, they communicate through the mediator.
// 	The mediator works as a router between objects, and it can have its own logic to provide a way of communication.
//	If the objects interact with each other directly, the system components are tightly-coupled with each other which
//	makes maintainability cost higher and not flexible to extend easily. 
//	It is commonly used in message-based systems likewise chat applications.

//	Advantage:
//		It decouples the number of classes.
//		It simplifies object protocols and centralizes the control.
//		The individual components become simpler and much easier to deal with because they don't need to pass messages to one another.

//	Disadvantages:
//  	The Mediator pattern can introduce complexity into the system, as it requires a new object to be created and managed.
//		This can make the system more difficult to understand and maintain. 	
//  	Single point of failure: The Mediator object is a single point of failure in the system.
	
//	Problem:
//	Say you have a dialog for creating and editing customer profiles. It consists of various form
//	controls such as text fields, checkboxes, buttons, etc. Some of the form elements may interact
//	with others. For instance, selecting the “I have a dog” checkbox may reveal a hidden text field for entering the dog’s name.
//	Another example is the submit button that has to validate values of all fields before saving the data.	
//	By having this logic implemented directly inside the code of the form elements you make these elements’ classes 
//	much harder to reuse in other forms of the app.
//	For example, you won’t be able to use that checkbox class inside another form, because it’s coupled to the dog’s text field. 

	public static void main(String[] args) {

		ChatRoom chat = new ChatRoomImpl();

		Participant u1 = new User1(chat);
		u1.setname("Ashwani Rajput");
		u1.sendMsg("Hi Ashwani! how are you?");

		Participant u2 = new User2(chat);
		u2.setname("Soono Jaiswal");
		u2.sendMsg("I am Fine ! You tell?");
	}
}
