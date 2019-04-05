package typechecker;

import symbol.Symbol;

public class GlobalTable extends NamedTable<ClassTable> {
	
	public static final String NAME = "GLOBALS";
	
	public GlobalTable() {
		super(NAME, null);
	}
	
	public ClassTable addClass(String name) {
		if (contains(name)) return null;
		
		ClassTable newTable = new ClassTable(name, null);
		put(Symbol.getSymbol(name), newTable);
		return newTable;
	}
	
	public ClassTable addClass(String name, String base) {
		if (contains(name)) return null;
		if (!contains(base)) return null;
		
		ClassTable newClass = new ClassTable(name, get(Symbol.getSymbol(base)));
		put(Symbol.getSymbol(name), newClass);
		return newClass;
	}
	
	public boolean contains(String name) {
		return super.containsKey(Symbol.getSymbol(name));
	}
	
	public ClassTable getClassTable(String name) {
		return get(Symbol.getSymbol(name));
	}
	
	public ClassTable[] getClassTables() {
		ClassTable[] res = new ClassTable[getKeys().length];
		return values().toArray(res);
	}
	
	public String dump() {
		return "";
	}
	
}
