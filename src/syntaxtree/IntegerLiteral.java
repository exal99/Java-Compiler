package syntaxtree;

public class IntegerLiteral extends Expression {
	
	public int value;
	
	public IntegerLiteral(int pos, int value) {
		super(pos);
		this.value= value;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
