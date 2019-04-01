package syntaxtree;

public class And extends Expression {
	
	private Expression left, right;
	
	public And(int pos, Expression left, Expression right) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
