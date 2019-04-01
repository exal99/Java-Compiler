package syntaxtree;

public class False extends Expression {
	
	public False(int pos) {
		super(pos);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
