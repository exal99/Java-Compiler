package syntaxtree;

public class NewArray extends Expression {
	
	public Expression size;
	
	public NewArray(int pos, Expression size) {
		super(pos);
		this.size = size;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}
