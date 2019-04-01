package syntaxtree;

public class ArrayAssign extends Statement {
	
	private Identifier name;
	private Expression index, value;
	
	public ArrayAssign(int pos, Identifier name, Expression index, Expression value) {
		super(pos);
		this.name = name;
		this.index = index;
		this.value = value;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
