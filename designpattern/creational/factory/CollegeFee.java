package designpattern.creational.factory;

abstract class CollegeFee {
	
	protected double standardFee = 500000;
	
	protected double standardDeduction = 133000;

	protected double schaolarShip = 0;
	
	public void calculateFee() {
		System.out.println(String.format("Your final first year fee is %s. Your total Scholarship is %s.", standardFee - standardDeduction - schaolarShip, schaolarShip));
	}
}
