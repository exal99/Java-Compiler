package syntaxtree;

public class If extends Statement {
	
	private Expression exp;
	private Statement s, elseS;
	
	public If(int pos, Expression exp, Statement s, Statement elseS) {
		super(pos);
		this.exp = exp;
		this.s = s;
		this.elseS = elseS;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
