package designprinciple.solid.interface_segregation;

public class App {

//	Definition:
//	“Clients should not be forced to depend on interfaces/methods that they do not use.”
//	It means that interfaces should be designed with a client’s needs in mind. When you follow the ISP, interfaces belong to
//	clients rather than libraries or hierarchies, enabling you to avoid situations where clients are “forced to depend
//	on methods that they do not use.” it results in code that is less rigid and fragile.
//  We should break large interfaces into small, focused ones so that implementing classes only depend on what
//  they actually need. This improves cohesion, reduces unnecessary coupling, and makes the system easier to
//  maintain and extend.

//	Instead of creating one big fat interface with many unrelated methods,
//	You should split it into smaller, specific interfaces so each class implements only what it actually needs.

//	Advantage of applying ISP:
//	Reduce Coupling: Coupling negatively impacts reusability. When an interface is long and detailed, it is difficult
//	for you to use it elsewhere.
//	Simplifies the Application: the ISP makes an application easier to maintain, update, and deploy.
//	Increase Testability: The ISP and the other SOLID principles make it easier to mock and test small and cohesive interfaces.


// 	A large interface forces classes to implement methods they don’t need: (Violating ISP)
//	interface Player {
//		void playVideo();
//		void playAudio();
//		void showSubtitles();
//		void record();
//	}


//	Now suppose we have a YouTubePlayer and MusicPlayer:
//	class MusicPlayer implements Player {
//		@Override
//		public void playVideo() { }      // ? Not needed
//		@Override
//		public void showSubtitles() { }  // ? Not needed
//		@Override
//		public void playAudio() { }
//		@Override
//		public void record() { }         // ? Not needed
//	}

//	Break big interface into small, purpose-specific ones: (Valid ISP Example)
//
//	interface AudioPlayable {
//		void playAudio();
//	}
//
//	interface VideoPlayable {
//		void playVideo();
//		void showSubtitles();
//	}
//
//	interface Recordable {
//		void record();
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
