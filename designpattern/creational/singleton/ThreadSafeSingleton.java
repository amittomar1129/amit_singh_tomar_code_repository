package designpattern.creational.singleton;

public final class ThreadSafeSingleton {

	private static volatile ThreadSafeSingleton instance;

	private ThreadSafeSingleton() {
	}

	/*
	 * works fine and provides thread-safety, but it reduces the performance because
	 * of the cost associated with the synchronized method, although we need it only
	 * for the first few threads that might create separate instances.
	 */
	public static synchronized ThreadSafeSingleton getInstance() {
		System.out.println("Thread name:  " + Thread.currentThread().getName());
		if (instance == null) {
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}

}
