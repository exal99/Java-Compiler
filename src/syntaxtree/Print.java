package syntaxtree;

public class Print extends Statement {
	
	private Expression exp;
	
	public Print(int pos, Expression exp) {
		super(pos);
		this.exp = exp;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
