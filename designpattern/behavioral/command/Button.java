package designpattern.behavioral.command;

// Invoker: Can be Toolbar Button, Menu Item, Shortcut, etc.
public class Button {

  private final String label;
  private final Command command;

  public Button(String label, Command command) {
    this.label = label;
    this.command = command;
  }

  public void click() {
    System.out.println("Button [" + label + "] clicked");
    command.execute();
  }
}
