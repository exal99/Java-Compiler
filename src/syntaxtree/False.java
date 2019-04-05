package syntaxtree;

public class False extends Expression {
	
	public False(int pos) {
		super(pos);
	}
	
	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}
