package syntaxtree;

public class Formal extends AbstractSyntax {
	
	private Type type;
	private Identifier id;
	
	public Formal(int pos, Type type, Identifier id) {
		super(pos);
		this.type = type;
		this.id = id;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
