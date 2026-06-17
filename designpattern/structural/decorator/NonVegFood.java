package designpattern.structural.decorator;

class NonVegFood implements Food {

	public String prepareFood() {
		return "Non Veg Food";
	}

	public double foodPrice() {
		return 150.0;
	}
}
