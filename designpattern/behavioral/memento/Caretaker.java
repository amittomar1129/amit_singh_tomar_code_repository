package designpattern.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

  private Life life;

  private List<Caretaker.Memento> savedLives = new ArrayList<Caretaker.Memento>();

  public void set(Life life) {
    this.life = life;
  }

  public void saveToMemento() {
    savedLives.add(new Memento(life));
  }

  public void getMemento() {
    Life restoredLife = savedLives.get(savedLives.size() - 1).getSavedLife();
    System.out.println("Time restored from Memento: " + restoredLife);
  }

  public void restoreFromMemento(int index) {
    Life restoredLife = savedLives.get(index).getSavedLife();
    System.out.println("Time restored from Memento: " + restoredLife);
  }

  private static class Memento {

    private final Life life;

    public Memento(Life life) {
      this.life = life;
    }

    public Life getSavedLife() {
      return life;
    }
  }
}
