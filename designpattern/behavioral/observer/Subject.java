package designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

  private List<Observer> observers = new ArrayList<Observer>();
  private CricketData state;

  public CricketData getState() {
    return state;
  }

  public void setState(int run, int over, int wicket) {
    this.state = new CricketData(run, over, wicket);
    notifyAllObservers();
  }

  public void attach(Observer observer) {
    observers.add(observer);
  }

  public void notifyAllObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }
}
