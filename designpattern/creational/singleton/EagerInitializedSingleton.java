package designpattern.creational.singleton;

public final class EagerInitializedSingleton {

	private static final EagerInitializedSingleton INSTANCE = new EagerInitializedSingleton();

	// private constructor to avoid client applications using the constructor
	private EagerInitializedSingleton() {}

	public static EagerInitializedSingleton getInstance() {
		return INSTANCE;
	}
}
