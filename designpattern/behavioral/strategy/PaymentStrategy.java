package designpattern.behavioral.strategy;

public interface PaymentStrategy<T extends Number> {
	
	void pay(T amount);
}
