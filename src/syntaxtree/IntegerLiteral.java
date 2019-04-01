package syntaxtree;

public class IntegerLiteral extends Expression {
	
	private int value;
	
	public IntegerLiteral(int pos, int value) {
		super(pos);
		this.value= value;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
