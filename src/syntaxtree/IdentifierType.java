package syntaxtree;

public class IdentifierType extends Type {
	
	public String name;

	public IdentifierType(int pos, String name) {
		super(pos);
		this.name = name;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof IdentifierType && ((IdentifierType) other).name.equals(name);
	}

}
