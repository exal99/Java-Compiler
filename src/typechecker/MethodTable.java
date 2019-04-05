package typechecker;

import symbol.Symbol;
import syntaxtree.Type;

public class MethodTable extends TypedTable<Type> {
	
	private Table<FormalList> formals;
	private Table<Type> locals;
	private ClassTable parrent;
	
	private FormalList start, head;
	private int numFormals;
	
	public MethodTable(String name, Type type, ClassTable parrent) {
		super(name, type, parrent);
		this.parrent = parrent;
		formals = new Table<FormalList>(null);
		locals = new Table<Type>(null);
		numFormals = 0;
		start = null;
		head = null;
	}
	
	public boolean addFormal(String name, Type type) {
		return formals.put(Symbol.getSymbol(name), new FormalList(type)) == null;
	}
	
	public boolean addLocal(String name, Type type) {
		return formals.getFromHierchy(Symbol.getSymbol(name)) == null && locals.put(Symbol.getSymbol(name), type) == null;
	}
	
	public boolean contains(String name) {
		return formals.getFromHierchy(Symbol.getSymbol(name)) != null || locals.getFromHierchy(Symbol.getSymbol(name)) != null ||
				parrent.getField(name) != null;
	}
	
	public Type getVariable(String name) {
		Type res = (formals.getFromHierchy(Symbol.getSymbol(name)) != null) ? formals.getFromHierchy(Symbol.getSymbol(name)).value :
				   														      locals.getFromHierchy(Symbol.getSymbol(name));
		if (res == null) {
			res = parrent.getField(name);
		}
		
		return res;
	}
	
	public Type[] getFormalOrder() {
		return start.getOrder();
	}
	
	private class FormalList {
		
		private FormalList next;
		
		public Type value;
		
		public FormalList(Type value) {
			if (head == null) {
				head = this;
				start = this;
			} else {
				head.next = this;
				head = this;
			}
			numFormals++;
			this.value = value;
		}
		
		public Type[] getOrder() {
			Type[] res = new Type[numFormals];
			FormalList current = start;
			int index = 0;
			while (current != null) {
				res[index] = current.value;
				current = current.next;
			}
			
			return res;
		}
	}
}
