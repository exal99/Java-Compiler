package syntaxtree;

public class Plus extends Expression {
	
	private Expression left, right;
	
	public Plus(int pos, Expression left, Expression right) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
