package syntaxtree;

public class Call extends Expression {
	
	public Expression obj;
	public Identifier method;
	public ExpressionList args;
	
	public Call(int pos, Expression obj, Identifier method, ExpressionList args) {
		super(pos);
		this.obj = obj;
		this.method = method;
		this.args = args;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
