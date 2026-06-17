package designpattern.creational.singleton;

public final class OptimizedThreadSafeSingleton {

	private static OptimizedThreadSafeSingleton instance;

	private OptimizedThreadSafeSingleton() {
	}

	/*
	 * To avoid this extra overhead every time, Double-checked locking principle is
	 * used.
	 */
	public static OptimizedThreadSafeSingleton getInstance() {
		System.out.println("Thread name:  " + Thread.currentThread().getName());
		if (instance == null) {
			synchronized (OptimizedThreadSafeSingleton.class) {
				if (instance == null) {
					instance = new OptimizedThreadSafeSingleton();
				}
			}
		}
		return instance;
	}
}
