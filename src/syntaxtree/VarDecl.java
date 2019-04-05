package syntaxtree;

public class VarDecl extends AbstractSyntax{
	
	public Type type;
	public Identifier name;
	
	public VarDecl(int pos, Type type, Identifier name) {
		super(pos);
		this.type = type;
		this.name = name;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}
