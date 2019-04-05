package syntaxtree;

import java.util.ArrayList;

public abstract class SyntaxList<T extends AbstractSyntax> extends AbstractSyntax implements SyntaxListInterface<T> {
	
	protected ArrayList<T> list;
	
	protected SyntaxList(int pos) {
		super(pos);
		list = new ArrayList<T>();
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
