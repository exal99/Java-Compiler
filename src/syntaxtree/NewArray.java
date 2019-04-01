package syntaxtree;

public class NewArray extends Expression {
	
	private Expression size;
	
	public NewArray(int pos, Expression size) {
		super(pos);
		this.size = size;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
