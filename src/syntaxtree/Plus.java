package syntaxtree;

public class Plus extends Expression {
	
	public Expression left, right;
	
	public Plus(int pos, Expression left, Expression right) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
