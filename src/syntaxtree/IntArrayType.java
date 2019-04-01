package syntaxtree;

public class IntArrayType extends Type {

	public IntArrayType(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
