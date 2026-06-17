package designpattern.structural.adapter;

public class App {

//	The Adapter Pattern is also known as Wrapper.	
// 	The Adapter Pattern acts as a bridge between two incompatible interfaces (or unrelated entities), making them work together
// 	This pattern involves a single class, known as the adapter, which is responsible for joining functionalities
// 	of independent or incompatible interfaces.

//	Advantage:
//		It allows two or more incompatible objects to interact.
//		It allows re-usability of existing functionality. When want to create a reusable class that cooperates
//		with classes which don't have compatible interfaces.

//	Disadvantages:
//  	Using adapters excessively can lead to an overly complex and convoluted architecture.
//		Clutter the codebase and make it harder to understand.

//	Problem:
//	Imagine that you’re creating a stock market monitoring app. The app downloads the stock data from multiple sources in XML format 
//	then displays nice-looking charts and diagrams for the user.
//	At some point, you decide to improve the app by integrating a smart 3rd-party analytics library. 
//	But there’s a catch: the analytics library only works with data in JSON format.
//	You could change the library to work with XML. However, this might break some existing code that relies on the library. 
//	And worse, you might not have access to the library’s source code in the first place, making this approach impossible.
	
	public static void main(String[] args) {

		AudioPlayer audioPlayer = new AudioPlayer();

		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}
}
