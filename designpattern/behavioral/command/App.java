package designpattern.behavioral.command;

public class App {

//	A Command Pattern also known as Action or Transaction.
//	says that "encapsulate a request under an object as a command and pass it to invoker object. 
//	Invoker object looks for the appropriate object which can handle this command and pass the command 
//	to the corresponding object and that object executes the command".
//	When you need to create and execute requests at different times.
// 	When you need to support rollback, logging or transaction functionality.

//	Advantage:
//		It separates the object that invokes the operation from the object that actually performs the operation.
//  	It makes easy to add new commands, because existing classes remain unchanged.

//	Disadvantages:
//  	There are a high number of classes and objects working together to achieve a goal. 
//  	Application developers need to be careful developing these classes correctly.

//	Problem:
//	Imagine that you’re working on a new text-editor app. Your current task is to create a toolbar with a bunch of buttons for various operations.
//	You created a very neat Button class that can be used for buttons on the toolbar, as well as for generic buttons in various dialogs.
//	Where would you put the code for the various click handlers of these buttons? The simplest solution is to create tons of subclasses
//	for each place where the button is used. These subclasses would contain the code that would have to be executed on a button click.
//	Initially, having the code for copying text inside the CopyButton subclass was fine. But then, when you implement context menus,
//	shortcuts, and other stuff, you have to either duplicate the operation’s code in many classes or make menus dependent on buttons,
//	which is an even worse option.

// Solution:
//	You should extract all the request details, such as the object being called, the name of the method and the list of arguments
//	into a separate command class with a single method that triggers this request.
//	From now on, the GUI object does not need to know what business logic object will receive the request and how it’ll be processed.
//	The GUI object just triggers the command, which handles all the details.

    public static void main(String[] args) {

        Editor editor = new Editor();
        editor.type("Hello World!");

        Button copyButton = new Button("Copy", new CopyCommand(editor, 0, 5));
        Button pasteButton = new Button("Paste", new PasteCommand(editor, 12));
        copyButton.click();
        pasteButton.click();
        editor.show();
    }
}
