package syntaxtree;

public class Assign extends Statement {
	
	private Identifier name;
	private Expression value;
	
	public Assign(int pos, Identifier name, Expression value) {
		super(pos);
		this.name = name;
		this.value = value;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
