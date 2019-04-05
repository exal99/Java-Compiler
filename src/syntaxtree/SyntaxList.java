package syntaxtree;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class SyntaxList<T extends AbstractSyntax> extends AbstractSyntax implements SyntaxListInterface<T>, Iterable<T> {
	
	protected ArrayList<T> list;
	
	protected SyntaxList(int pos) {
		super(pos);
		list = new ArrayList<T>();
	}
	
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
	
	@Override
	public void addElement(T element) {
		list.add(element);
	}
	
	@Override
	public T elementAt(int i) {
		return list.get(i);
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Deprecated
	@Override
	public <E> E accept(Visitor<E> v) {
		return null;
	}
	
	@Deprecated
	@Override
	public int getPos() {
		return -1;
	}

}
