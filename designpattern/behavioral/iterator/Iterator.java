package designpattern.behavioral.iterator;

public interface Iterator<T> {
	
	public boolean hasNext();
	
	public T next();
	
	public T currentItem();
	
	public boolean hasPrevious();
	
	public T previous();
	
	public void reset();
}
