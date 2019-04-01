package syntaxtree;

public class IdentifierExpression extends Expression {
	
	private String id;
	
	public IdentifierExpression(int pos, String id) {
		super(pos);
		this.id = id;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
