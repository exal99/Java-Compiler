package syntaxtree;

public class ArrayLookup extends Expression {
	
	private Expression index, value;
	
	public ArrayLookup(int pos, Expression index, Expression value) {
		super(pos);
		this.index = index;
		this.value = value;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
