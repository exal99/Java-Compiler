package syntaxtree;

public class Call extends Expression {
	
	private Expression obj;
	private Identifier method;
	private ExpressionList args;
	
	public Call(int pos, Expression obj, Identifier method, ExpressionList args) {
		super(pos);
		this.obj = obj;
		this.method = method;
		this.args = args;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
