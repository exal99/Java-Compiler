package syntaxtree;

public class Not extends Expression {
	
	private Expression exp;
	
	public Not(int pos, Expression exp) {
		super(pos);
		this.exp = exp;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
