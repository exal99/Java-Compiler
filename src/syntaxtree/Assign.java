package syntaxtree;

public class Assign extends Statement {
	
	public Identifier name;
	public Expression value;
	
	public Assign(int pos, Identifier name, Expression value) {
		super(pos);
		this.name = name;
		this.value = value;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}
