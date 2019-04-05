package typechecker;

import java.util.HashMap;

import symbol.Symbol;

public class Table<E> extends HashMap<Symbol, E>{

	private static final long serialVersionUID = -4823785920866018579L;
	protected Table<E> parrent;
	
	public Table(Table parrent) {
		this.parrent = parrent;
	}
	
	@Override
	public E put(Symbol key, E value) {
		E res = super.put(key, value);
		return res;
	}
	
	public Symbol[] getKeys() {
		Symbol[] res = new Symbol[this.size()];
		this.keySet().toArray(res);
		return res;
	}
	
	public E getFromHierchy(Symbol value) {
		if (get(value) != null) {
			return get(value);
		} else {
			return (parrent != null) ? parrent.getFromHierchy(value) : null;
		}
	}


}
