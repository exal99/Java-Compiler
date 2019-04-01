package syntaxtree;

public abstract class AbstractSyntax {
	protected int pos;
	
	protected AbstractSyntax(int pos) {
		this.pos = pos;
	}
	
	public abstract void accept(Visitor v);
	
	public int getPos() {
		return pos;
	}
}
