package syntaxtree;

public class Formal extends AbstractSyntax {
	
	public Type type;
	public Identifier id;
	
	public Formal(int pos, Type type, Identifier id) {
		super(pos);
		this.type = type;
		this.id = id;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}
