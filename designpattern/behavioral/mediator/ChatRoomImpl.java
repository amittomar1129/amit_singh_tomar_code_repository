package designpattern.behavioral.mediator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatRoomImpl implements ChatRoom {

  //get current date time
  @Override
  public void sendMsg(String msg, Participant p) {
    System.out.println(p.getName() + "'gets message: " + msg);
  }
}