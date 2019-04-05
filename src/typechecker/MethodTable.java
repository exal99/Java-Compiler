package typechecker;

import symbol.Symbol;
import syntaxtree.Type;

public class MethodTable extends TypedTable<Type> {
	
	private Table<Type> formals;
	private Table<Type> locals;
	private ClassTable parrent;
	
	public MethodTable(String name, Type type, ClassTable parrent) {
		super(name, type, parrent);
		formals = new Table<Type>(null);
		locals = new Table<Type>(null);
	}
	
	public boolean addFormal(String name, Type type) {
		return formals.put(Symbol.getSymbol(name), type) == null;
	}
	
	public boolean addLocal(String name, Type type) {
		return formals.getFromHierchy(Symbol.getSymbol(name)) == null && locals.put(Symbol.getSymbol(name), type) == null;
	}
	
	public boolean contains(String name) {
		return formals.getFromHierchy(Symbol.getSymbol(name)) != null || locals.getFromHierchy(Symbol.getSymbol(name)) != null ||
				parrent.getField(name) != null;
	}
	
	public Type getVariable(String name) {
		Type res = (formals.getFromHierchy(Symbol.getSymbol(name)) != null) ? formals.getFromHierchy(Symbol.getSymbol(name)) :
				   														      locals.getFromHierchy(Symbol.getSymbol(name));
		if (res == null) {
			res = parrent.getField(name);
		}
		
		return res;
	}
}
