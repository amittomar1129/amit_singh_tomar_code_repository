package designpattern.structural.bridge;

public class BasicRemote extends Remote {

  public BasicRemote(TV tv) {
    super(tv);
  }

  @Override
  public void power() {
    tv.on();
  }

  @Override
  public void setChannel(int channel) {
    tv.tuneChannel(channel);
  }
}