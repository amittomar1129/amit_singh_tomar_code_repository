package designpattern.structural.bridge;

public class AdvancedRemote  extends Remote {

  public AdvancedRemote(TV tv) {
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

  public void mute() {
    System.out.println("TV Muted");
  }
}