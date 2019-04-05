package syntaxtree;

public class Times extends Expression {
	
	public Expression left, right;
	
	public Times(int pos, Expression left, Expression right) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
