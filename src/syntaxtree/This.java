package syntaxtree;

public class This extends Expression {
	
	public This(int pos) {
		super(pos);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
