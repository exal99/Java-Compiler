package syntaxtree;

public class While extends Statement {
	
	private Expression exp;
	private Statement s;
	
	public While(int pos, Expression exp, Statement s) {
		super(pos);
		this.exp = exp;
		this.s = s;
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub

	}

}
