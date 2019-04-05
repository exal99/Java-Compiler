package typechecker;

import symbol.Symbol;
import syntaxtree.Type;

public class ClassTable extends NamedTable<Type> {
	
	private Table<Type> fields;
	private Table<MethodTable> methods;
	
	private ClassTable parrent;

	
	public ClassTable(String name, ClassTable base) {
		super(name, base);
		fields = new Table<Type>(base.fields);
		methods = new Table<MethodTable>(base.methods);
	}
	
	public boolean addField(String name, Type type) {
		return put(Symbol.getSymbol(name), type) == null && fields.put(Symbol.getSymbol(name), type) == null;
	}
	
	public MethodTable addMethod(String name, Type type) {
		if (methods.containsKey(Symbol.getSymbol(name))) return null;
		MethodTable method = new MethodTable(name, type, this);
		methods.put(Symbol.getSymbol(name), method);
		return method;
	}
	
	public boolean instanceOf(String name) {
		if (name.equals(this.name)) {
			return true;
		} else {
			if (parrent != null) {
				return parrent.instanceOf(name);
			} else {
				return false;
			}
		}
	}
	
	public boolean contains(String name) {
		return fields.getFromHierchy(Symbol.getSymbol(name)) != null || methods.getFromHierchy(Symbol.getSymbol(name)) != null;
	}
	
	public Type getField(String name) {
		return fields.getFromHierchy(Symbol.getSymbol(name));
	}
	
	public MethodTable getMethod(String name) {
		return methods.getFromHierchy(Symbol.getSymbol(name));
	}
	
	
}
