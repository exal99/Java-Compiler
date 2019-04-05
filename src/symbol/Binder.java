package symbol;

public class Binder<E> {
	private E object;
	private Symbol top;
	private Binder<E> tail;
	
	public Binder(E object, Symbol top, Binder<E> tail) {
		this.object = object;
		this.top = top;
		this.tail = tail;
	}
	
	public E get() {
		return object;
	}
	
	public Symbol getTop() {
		return top;
	}
	
	public Binder<E> getTail(){
		return tail;
	}
}
