package syntaxtree;

public class ArrayLength extends Expression {
	
	private Expression array;
	
	public ArrayLength(int pos, Expression array) {
		super(pos);
		this.array = array;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
