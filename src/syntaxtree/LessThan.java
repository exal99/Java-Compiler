package syntaxtree;

public class LessThan extends Expression {
	
	public Expression left, right;
	
	public LessThan(int pos, Expression left, Expression right) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
