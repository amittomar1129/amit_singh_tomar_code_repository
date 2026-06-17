package designpattern.behavioral.iterator;

public class ArrayList<T> implements List<T> {

	private T[] t;
	
	public void addAll(T[] t) {
		this.t = t;
	}

	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl<T>(t);
	}

}
