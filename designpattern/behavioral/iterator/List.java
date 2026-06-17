package designpattern.behavioral.iterator;

public interface List<T> {
	
	public void addAll(T[] t);
	
	public Iterator<T> iterator();

}
