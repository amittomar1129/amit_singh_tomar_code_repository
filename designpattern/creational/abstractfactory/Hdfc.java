package designpattern.creational.abstractfactory;

public class Hdfc implements Bank {

	private final String bankName;

	public Hdfc() {
		bankName = "HDFC BANK";
	}

	public String getBankName() {
		return bankName;
	}
}