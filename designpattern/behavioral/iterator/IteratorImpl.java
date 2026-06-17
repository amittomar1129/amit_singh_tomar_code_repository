package designpattern.behavioral.iterator;

public class IteratorImpl<T> implements Iterator<T> {

	private T[] t;
	private int position;
	
	public IteratorImpl(T[] t) {
		this.t = t;
		this.position = 0;
	}

	@Override
	public boolean hasNext() {
		if(position >= t.length)
			return false;
		return true;
	}

	@Override
	public T next() {
		if(position < t.length)
			return t[position++];
		return null;
	}

	@Override
	public T currentItem() {
		return t[position];
	}

	@Override
	public boolean hasPrevious() {
		if(position == 0)
			return false;
		return true;
	}

	@Override
	public T previous() {
		if(position > 0)
			return t[--position];
		return null;
	}

	@Override
	public void reset() {
		this.position = 0;
	}
}
