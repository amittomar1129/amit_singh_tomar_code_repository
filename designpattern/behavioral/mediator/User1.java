package designpattern.behavioral.mediator;

public class User1 extends Participant {

	private String name;
	private ChatRoom chatRoom;

	public User1(ChatRoom chat) {
		this.chatRoom = chat;
	}

	@Override
	public void sendMsg(String msg) {
		chatRoom.sendMsg(msg, this);
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