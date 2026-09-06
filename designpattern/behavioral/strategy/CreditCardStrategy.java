package designpattern.behavioral.strategy;

public class CreditCardStrategy implements PaymentStrategy{

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;
	
	public CreditCardStrategy() {
		
	}
	
	public CreditCardStrategy(String nm, String ccNum, String cvv, String expiryDate){
		this.name=nm;
		this.cardNumber=ccNum;
		this.cvv=cvv;
		this.dateOfExpiry=expiryDate;
	}
	
	
	@Override
	public void pay(double amount) {
		// Logic to pay
		System.out.println("processing info .... "+name+cardNumber+cvv+dateOfExpiry);
		System.out.println(amount +" paid with credit/debit card");
	}
}
