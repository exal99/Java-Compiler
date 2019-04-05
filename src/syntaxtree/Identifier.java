package syntaxtree;

public class Identifier extends AbstractSyntax {
	
	public String id;
	
	public Identifier(int pos, String id) {
		super(pos);
		this.id = id;
	}
	
	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
	
	@Override
	public String toString() {
		return id;
	}
}
