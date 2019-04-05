package syntaxtree;

public class ArrayLookup extends Expression {
	
	public Expression index, value;
	
	public ArrayLookup(int pos, Expression value, Expression index) {
		super(pos);
		this.index = index;
		this.value = value;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
