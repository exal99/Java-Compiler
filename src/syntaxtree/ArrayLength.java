package syntaxtree;

public class ArrayLength extends Expression {
	
	public Expression array;
	
	public ArrayLength(int pos, Expression array) {
		super(pos);
		this.array = array;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}
