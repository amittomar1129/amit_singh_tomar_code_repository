package designpattern.creational.abstractfactory;

public class Icici implements Bank {

	private final String bankName;

	public Icici() {
		bankName = "ICICI BANK";
	}

	public String getBankName() {
		return bankName;
	}
}