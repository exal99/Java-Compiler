package syntaxtree;

public class IntArrayType extends Type {

	public IntArrayType(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
	
	@Override
	public String toString() {
		return "int[]";
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof IntArrayType;
	}
}
