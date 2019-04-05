package symbol;

import java.util.Hashtable;

public class Symbol {
	
	private String name;
	private static Hashtable<String, Symbol> map = new Hashtable<String, Symbol>();
	
	private Symbol(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public static Symbol getSymbol(String string) {
		String real = string.intern();
		Symbol s = map.get(real);
		if (s == null) {
			s = new Symbol(string);
			map.put(real, s);
		}
		return s;
	}
	
}
