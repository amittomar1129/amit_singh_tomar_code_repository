package designpattern.structural.flyweight;

import java.util.HashMap;

//Class used to get a player using HashMap (Returns
//an existing player if a player of given type exists.
//Else creates a new player and returns it.
public class PlayerFactory {

  /*
   * HashMap stores the reference to the object of Terrorist(TS) or
   * CounterTerrorist(CT).
   */
  private static HashMap<String, Player> map = new HashMap<String, Player>();

  // Method to get a player
  public static Player getPlayer(String type) {
    if (map.containsKey(type)) {
      return map.get(type);
    }
    Player player = null;
    /* create an object of TS/CT */

     switch (type) {
      case "Terrorist":
        System.out.println("Terrorist Created");
        player = new Terrorist();
        break;
      case "CounterTerrorist":
        System.out.println("Counter Terrorist Created");
        player = new CounterTerrorist();
        break;
      default:
        System.out.println("Unreachable code!");
    }
    // Once created insert it into the HashMap
    map.put(type, player);
    return player;
  }
}