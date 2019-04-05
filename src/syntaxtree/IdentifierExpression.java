package syntaxtree;

public class IdentifierExpression extends Expression {
	
	public String id;
	
	public IdentifierExpression(int pos, String id) {
		super(pos);
		this.id = id;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
