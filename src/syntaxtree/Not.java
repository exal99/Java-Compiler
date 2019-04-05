package syntaxtree;

public class Not extends Expression {
	
	public Expression exp;
	
	public Not(int pos, Expression exp) {
		super(pos);
		this.exp = exp;
	}
	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
