package designpattern.behavioral.mediator;

public class User2 extends Participant {

  private String name;
  private ChatRoom chat;

  public User2(ChatRoom chat) {
    this.chat = chat;
  }

  @Override
  public void sendMsg(String msg) {
    this.chat.sendMsg(msg, this);
  }

  @Override
  public void setname(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}