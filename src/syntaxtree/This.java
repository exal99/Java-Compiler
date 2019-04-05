package syntaxtree;

public class This extends Expression {
	
	public This(int pos) {
		super(pos);
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
