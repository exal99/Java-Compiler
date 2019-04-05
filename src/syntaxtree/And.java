package syntaxtree;

public class And extends Expression {
	
	public Expression left, right;
	
	public And(int pos, Expression left, Expression right) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
