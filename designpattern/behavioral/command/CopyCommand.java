package designpattern.behavioral.command;

public class CopyCommand implements Command {

  private final Editor editor;
  private final int start, end;

  public CopyCommand(Editor editor, int start, int end) {
    this.editor = editor;
    this.start = start;
    this.end = end;
  }

  @Override
  public void execute() {
    editor.copy(start, end);
  }
}
