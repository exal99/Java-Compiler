package syntaxtree;

public class While extends Statement {
	
	public Expression exp;
	public Statement s;
	
	public While(int pos, Expression exp, Statement s) {
		super(pos);
		this.exp = exp;
		this.s = s;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
