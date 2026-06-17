package designpattern.structural.flyweight;

import java.util.Random;
import java.util.stream.IntStream;

public class App {

//	Flyweight design pattern is used when we need to create a lot of Objects of a class. Since every object consumes memory space
//	that can be crucial for low memory devices, such as mobile devices or embedded systems, 
//	flyweight design pattern can be applied to reduce the load on memory by sharing objects. of the subsystem from the client".
//	To reuse already existing similar kind of objects by storing them and create new object when no matching object is found.
//	One important feature of flyweight objects is that they are immutable.

//  We need to consider following factors:
//	The object properties can be divided into intrinsic and extrinsic properties,
//  Intrinsic properties make the Object unique whereas extrinsic properties are set by client code and
//  used to perform different operations.

// Advantage:
// 		It reduces the number of objects.
//		It reduces the amount of memory and storage devices required if the objects are persisted.

// Disadvantages:
// 		Increased complexity due to separation of intrinsic and extrinsic state.
//		Reduced security due to shared state.	

//	Problem:
//	You decided to create a simple video game: players would be moving around a map and shooting each other. 
//	You chose to implement a realistic particle system and make it a distinctive feature of the game. Vast quantities of 
//	bullets, missiles, and shrapnel from explosions should fly all over the map and deliver a thrilling experience to the player.
//	The game was running flawlessly on your machine, your friend wasn’t able to play for long. On his computer, 
//	the game kept crashing after a few minutes of gameplay. After spending several hours digging through debug logs, 
//	you discovered that the game crashed because of an insufficient amount of RAM. It turned out that your friend’s rig was much 
//	less powerful than your own computer, and that’s why the problem emerged so quickly on his machine.

//	Solution:
//	The Flyweight pattern suggests that you stop storing the extrinsic state inside the object. Instead, you should pass this state to 
//	specific methods which rely on it. 
//	Only the intrinsic state stays within the object, letting you reuse it in different contexts. As a result, you’d need fewer of these objects 
//	since they only differ in the intrinsic state, which has much fewer variations than the extrinsic.

//	Minimize memory usage or computational expenses by sharing as much as possible with related objects. 
//	It is beneficial when many similar objects need to be created, and the overhead of creating and maintaining each object individually is too high.

  // All player types and weapon (used by getRandPlayerType() and getRandWeapon()
  private static String[] playerType = {"Terrorist", "CounterTerrorist"};
  private static String[] weapons = {"AK-47", "Maverick", "Gut Knife", "Desert Eagle"};

  public static void main(String[] args) {
    Player p1 = PlayerFactory.getPlayer("Terrorist");
    p1.assignWeapon("AK-47");
    p1.mission();

    Player p2 = PlayerFactory.getPlayer("Terrorist");
    p2.assignWeapon("Gut Knife");
    p2.mission();

    Player p3 = PlayerFactory.getPlayer("Terrorist");
    p3.assignWeapon("Maverick");
    p3.mission();

    Player p4 = PlayerFactory.getPlayer("Terrorist");
    p4.assignWeapon("AK-47");
    p4.mission();

    Player p5 = PlayerFactory.getPlayer("Terrorist");
    p5.assignWeapon("Gut Knife");
    p5.mission();

    Player p6 = PlayerFactory.getPlayer("CounterTerrorist");
    p6.assignWeapon("Desert Eagle");
    p6.mission();

    Player p7 = PlayerFactory.getPlayer("CounterTerrorist");
    p7.assignWeapon("Maverick");
    p7.mission();

    Player p8 = PlayerFactory.getPlayer("CounterTerrorist");
    p8.assignWeapon("Maverick");
    p8.mission();

    Player p9 = PlayerFactory.getPlayer("CounterTerrorist");
    p9.assignWeapon("AK-47");
    p9.mission();

    Player p10 = PlayerFactory.getPlayer("CounterTerrorist");
    p10.assignWeapon("AK-47");
    p10.mission();
  }
}
