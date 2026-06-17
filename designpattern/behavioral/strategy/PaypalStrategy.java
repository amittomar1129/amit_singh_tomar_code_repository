package designpattern.behavioral.strategy;

public class PaypalStrategy implements PaymentStrategy<Double>{
	
	private String emailId;
	private String password;

	public PaypalStrategy() {
	}
	
	public PaypalStrategy(String email, String pwd){
		this.emailId=email;
		this.password=pwd;
	}
	
	@Override
	public void pay(Double amount) {
		// Logic to pay
		System.out.println("processing info .... "+emailId+password);
		System.out.println(amount +" paid with paypal");
	}
}
