package designpattern.behavioral.command;

public class PasteCommand implements Command{

  private final Editor editor;
  private final int position;

  public PasteCommand(Editor editor, int position) {
    this.editor = editor;
    this.position = position;
  }

  @Override
  public void execute() {
    editor.paste(position);
  }
}
