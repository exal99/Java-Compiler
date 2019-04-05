package syntaxtree;

public class Minus extends Expression {
	
	public Expression left, right;
	
	public Minus(int pos, Expression left, Expression right) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
