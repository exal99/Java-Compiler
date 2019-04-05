package syntaxtree;

public class True extends Expression {
	
	public True(int pos) {
		super(pos);
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
