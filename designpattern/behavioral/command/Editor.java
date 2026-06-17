package designpattern.behavioral.command;

// Receiver: The actual editor logic
public class Editor {

  private StringBuilder text = new StringBuilder();
  private String clipboard = "";

  public void type(String words) {
    text.append(words);
  }

  public void copy(int start, int end) {
    clipboard = text.substring(start, end);
    System.out.println("Copied: " + clipboard);
  }

  public void paste(int position) {
    text.insert(position, clipboard);
  }

  public void show() {
    System.out.println("Editor Text: " + text);
  }
}
