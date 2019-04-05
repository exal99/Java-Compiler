package syntaxtree;

public class If extends Statement {
	
	public Expression exp;
	public Statement s, elseS;
	
	public If(int pos, Expression exp, Statement s, Statement elseS) {
		super(pos);
		this.exp = exp;
		this.s = s;
		this.elseS = elseS;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
