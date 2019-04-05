package syntaxtree;

public class ArrayAssign extends Statement {
	
	public Identifier name;
	public Expression index, value;
	
	public ArrayAssign(int pos, Identifier name, Expression index, Expression value) {
		super(pos);
		this.name = name;
		this.index = index;
		this.value = value;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
