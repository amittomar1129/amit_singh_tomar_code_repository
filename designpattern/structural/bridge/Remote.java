package designpattern.structural.bridge;

public abstract class Remote {

  protected TV tv;

  public Remote(TV tv) {
    this.tv = tv;
  }

  abstract void power();

  abstract void setChannel(int channel);
}