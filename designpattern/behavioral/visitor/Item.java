package designpattern.behavioral.visitor;

public interface Item {
	
	public int accept(ShoppingCartVisitor visitor);
}
