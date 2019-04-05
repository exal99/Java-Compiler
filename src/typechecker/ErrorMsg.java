package typechecker;

public class ErrorMsg {
	private boolean errors;
	
	public ErrorMsg() {
		errors = false;
	}
	
	public void complain(String msg, String pos, int lineno) {
		errors = true;
		System.out.println(msg + "\n\tat " + pos + ": " + lineno + "\n");
	}
	
	public String formatDuplicate(String type, String name) {

		return "Duplicate definitions of " + type + ": '" + name + "'";
	}

	
	public String formatTypeMissmatch(String got, String expected) {
		return "Type missmatch: cannot convert from " + got + " to " + expected;
	}
	
	public boolean isError() {
		return errors;
	}
}