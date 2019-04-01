package syntaxtree;

public class Identifier extends AbstractSyntax {
	
	private String id;
	
	public Identifier(int pos, String id) {
		super(pos);
		this.id = id;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
