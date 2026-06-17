package designpattern.creational.abstractfactory;

public class BankFactory extends AbstractFactory {
	
	public Bank getBank(String bank) {
		if (bank == null) {
			return null;
		}
		if (bank.equalsIgnoreCase("HDFC")) {
			return new Hdfc();
		} else if (bank.equalsIgnoreCase("ICICI")) {
			return new Icici();
		}
		return null;
	}

	public Loan getLoan(String loan) {
		return null;
	}
}