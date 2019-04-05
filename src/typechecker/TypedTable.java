package typechecker;

import syntaxtree.Type;

public abstract class TypedTable<E> extends NamedTable<E> {
	
	protected Type type;
	
	public TypedTable(String name, Type type, Table parrent) {
		super(name, parrent);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

}
