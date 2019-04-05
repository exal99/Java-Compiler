package typechecker;

public class ErrorMsg {
	private boolean errors;
	
	public ErrorMsg() {
		errors = false;
	}
	
	public void complain(String msg) {
		errors = true;
		System.out.println(msg);
	}
	
	public String formatDuplicate(String type, String name) {
		return formatDuplicate(type, name, "", 0);
	}
	
	public String formatDuplicate(String type, String name, String pos, int lineno) {
		if (pos.length() > 0) {
			return "Duplicate definitions of " + type + ": '" + name + "'\nat " + pos + "(" + lineno + ")";
		} else {
			return "Duplicate definitions of " + type + ": '" + name;
		}
	}
	
	public String formatTypeMissmatch(String got, String expected) {
		return "Type missmatch: cannot convert from " + got + " to " + expected;
	}
	
	public boolean isError() {
		return errors;
	}
}