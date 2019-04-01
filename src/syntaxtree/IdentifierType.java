package syntaxtree;

public class IdentifierType extends Type {
	
	private String name;

	public IdentifierType(int pos, String name) {
		super(pos);
		this.name = name;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
