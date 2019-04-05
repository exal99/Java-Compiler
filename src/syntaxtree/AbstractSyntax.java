package syntaxtree;

public abstract class AbstractSyntax {
	protected int pos;
	
	protected AbstractSyntax(int pos) {
		this.pos = pos;
	}
	
	public abstract <E> E accept(Visitor<E> v);
	
	public int getPos() {
		return pos;
	}
}
