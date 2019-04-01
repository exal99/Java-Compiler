package syntaxtree;

public class True extends Expression {
	
	public True(int pos) {
		super(pos);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
