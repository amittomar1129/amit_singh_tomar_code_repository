package designpattern.creational.singleton;

public final class EagerByStaticBlockSingleton {

	private static final EagerByStaticBlockSingleton INSTANCE;

	private EagerByStaticBlockSingleton() {}

	// static block initialization for exception handling
	static {
		try {
			INSTANCE = new EagerByStaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred in creating singleton instance");
		}
	}

	public static EagerByStaticBlockSingleton getInstance() {
		return INSTANCE;
	}
}
