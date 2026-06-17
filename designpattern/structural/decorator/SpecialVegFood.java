package designpattern.structural.decorator;

public class SpecialVegFood extends FoodDecorator {
	
	public SpecialVegFood(Food newFood) {
		super(newFood);
	}

	public String prepareFood() {
		return super.prepareFood() + " With Special Sweet and 2 Paneer curry  ";
	}

	public double foodPrice() {
		return super.foodPrice() + 175.0;
	}
}