package designpattern.creational.singleton;

import java.io.Serializable;

/*
 * Sometimes in distributed systems, we need to implement Serializable interface in the singleton class so that
 * we can store its state in the file system and retrieve it at a later point in time. Here is a small singleton class that
 * implements Serializable interface also:
 */
public final class SerializedSingleton implements Serializable, Cloneable {

  private static final long serialVersionUID = 365287712895576735L;

  private SerializedSingleton() {
  }

  private static class SingletonHelper {
    private static final SerializedSingleton INSTANCE = new SerializedSingleton();
  }

  public static SerializedSingleton getInstance() {
    return SingletonHelper.INSTANCE;
  }

  /*
   * When you serialize and then deserialize this Singleton, you actually get a new object instance!
   * So it destroys the singleton pattern. To overcome this scenario, all we need
   * to do is provide the implementation of readResolve() method.
   * This ensures the same instance is returned after deserialization
   */
  protected Object readResolve() {
    return getInstance();
  }

  /*
   * To prevent cloning from breaking the Singleton pattern, override clone() to throw an exception.
   */
  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Singleton cannot be cloned");
  }
}
