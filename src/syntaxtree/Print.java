package syntaxtree;

public class Print extends Statement {
	
	public Expression exp;
	
	public Print(int pos, Expression exp) {
		super(pos);
		this.exp = exp;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
