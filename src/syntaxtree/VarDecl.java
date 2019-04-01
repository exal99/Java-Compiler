package syntaxtree;

public class VarDecl extends AbstractSyntax{
	
	private Type type;
	private Identifier name;
	
	public VarDecl(int pos, Type type, Identifier name) {
		super(pos);
		this.type = type;
		this.name = name;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
