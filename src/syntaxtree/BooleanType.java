package syntaxtree;

public class BooleanType extends Type {

	public BooleanType(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
	
	@Override
	public String toString() {
		return "boolean";
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof BooleanType;
	}
}
