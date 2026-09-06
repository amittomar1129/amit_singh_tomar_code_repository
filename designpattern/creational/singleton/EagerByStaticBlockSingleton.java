package designpattern.creational.singleton;

public final class EagerByStaticBlockSingleton {

	private static final EagerByStaticBlockSingleton INSTANCE;

	// static block initialization for exception handling
	static {
		try {
			INSTANCE = new EagerByStaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred in creating singleton instance");
		}
	}

	private EagerByStaticBlockSingleton() {}

	public static EagerByStaticBlockSingleton getInstance() {
		return INSTANCE;
	}
}
