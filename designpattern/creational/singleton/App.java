package designpattern.creational.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {

//	Singleton pattern restricts the instantiation of a class and ensures that only one instance of
//	the class exists in the Java Virtual Machine.
//	Singleton design pattern is also used in other design patterns like Abstract Factory, Builder, Prototype, Facade, etc.

//	Usage:
// 		Used for logging, caching, and thread pool, configuration settings etc.
// 		for example, java.lang.Runtime, java.awt.Desktop, ActionServlet, Calendar.
//		Singleton pattern is mostly used in multi-threaded and database applications.

//	Advantage:
//		Saves memory because object is not created at each request. Only single instance is reused again and again.
//		It allows re-usability of existing functionality.

//	Disadvantages:
//		Singletons act like global variables. Any class can access it from anywhere — breaking encapsulation.

//	  Difficult to Unit Test: Because the instance is globally shared, Hard to isolate tests — one test might affect another.
//	  Fix: “Singletons violate test isolation, so I prefer dependency injection or using frameworks like Spring for managed singletons.”

//	  Race conditions when multiple threads create instances simultaneously.
//	  Fix: Use thread-safe initialization (e.g., synchronized, volatile, or enum-based Singleton).

//	  Difficult in Multithreaded: Singleton assumes one instance per JVM.
//    But in distributed systems (microservices, multiple JVMs), you might end up with multiple singletons, defeating its purpose.
//	  Managing global state across services becomes tricky.

//	  Violates SOLID Principles:
//	  Single Responsibility Principle: Singleton handles both logic + instance control.
//    Open/Closed Principle: Difficult to extend or subclass.
//   	Dependency Inversion: High-level modules depend on low-level concrete instances.

//		Tight Coupling: Classes depending on a Singleton are tightly coupled.

//		Serialization and Cloning Issues:
//		If Singleton is serialized or cloned, new instances can be created — breaking Singleton guarantee
//		Fix: Override readResolve() or prevent clone().


//	Initialization Types of Singleton:
//		Early initialization : In this method, class is initialized whether it is to be used or not.
//		The main advantage of this method is its simplicity. You initiate the class at the time of class loading.
//		Its drawback is that class is always initialized whether it is being used or not.
//		Lazy initialization : In this method, class in initialized only when it is required.
//		It can save you from instantiating the class when you don’t need it. Generally,
//		lazy initialization is used when we create a singleton class.
	
//	Problem:
//	The Singleton pattern solves two problems at the same time, violating the Single Responsibility Principle:
//	1. Ensures that a class has just a single instance. Why would any one want to control how many instances a class has?
//	The most common reason for this is to control access to some shared resource—for example, a database or a file.
//	Note that this behavior is impossible to implement with a regular constructor since a constructor call must always return 
//	a new object by design.	
//	2. Provide a global access point to instance. While they’re very handy, they’re also very unsafe
//	since any code can potentially overwrite the contents of those variables and crash the app.

	public static void main(String[] args) throws Exception {
		System.out.println("EagerInitializedSingleton " + EagerInitializedSingleton.getInstance());
		System.out.println("EagerInitializedSingleton " + EagerInitializedSingleton.getInstance());

		System.out.println("EagerByStaticBlockSingleton " + EagerByStaticBlockSingleton.getInstance());
		System.out.println("EagerByStaticBlockSingleton " + EagerByStaticBlockSingleton.getInstance());

		System.out.println("LazyInitializedSingleton " + LazyInitializedSingleton.getInstance());
		System.out.println("LazyInitializedSingleton " + LazyInitializedSingleton.getInstance());

		System.out.println("ThreadSafeSingleton " + ThreadSafeSingleton.getInstance());
		Thread childThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("ThreadSafeSingleton " + ThreadSafeSingleton.getInstance());
			}
		});
		childThread.start();

		System.out.println("OptimizedThreadSafeSingleton " + OptimizedThreadSafeSingleton.getInstance());
		Thread childThread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("OptimizedThreadSafeSingleton " + OptimizedThreadSafeSingleton.getInstance());
			}
		});
		childThread1.start();

		System.out.println("BillPughSingleton " + BillPughSingleton.getInstance());
		System.out.println("BillPughSingleton " + BillPughSingleton.getInstance());

		// Using Reflection to destroy or violate Singleton Pattern.
		reflectionSingletonTest();

		System.out.println(EnumSingleton.INSTANCE.hashCode());
		EnumSingleton.INSTANCE.doSomething();
		EnumSingleton.INSTANCE.doBusinessLogics();

		System.out.println("SerializedSingleton " + SerializedSingleton.getInstance());
		System.out.println("SerializedSingleton " + SerializedSingleton.getInstance());

		singletonSerializedTest();
		// By providing readResolve() method in SerializedSingleton class we can prevent violating Singleton pattern.
 	}

	// Reflection can be used to destroy all the previous singleton implementation
	// approaches. Here is an example:
	static void reflectionSingletonTest() {

		EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
		EagerInitializedSingleton instanceTwo = null;
		Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
		try {
			for (Constructor constructor : constructors) {
				// This code will destroy the singleton pattern
				constructor.setAccessible(true);
				instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
				break;
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(instanceOne);
		System.out.println(instanceTwo);
	}

	static void singletonSerializedTest() throws Exception {

		SerializedSingleton instanceOne = SerializedSingleton.getInstance();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
		out.writeObject(instanceOne);
		out.close();

		// deserialize from file to object
		ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
		SerializedSingleton instanceTwo = (SerializedSingleton) in.readObject();
		in.close();

		System.out.println("instanceOne " + instanceOne);
		System.out.println("instanceTwo " + instanceTwo);
	}
}
