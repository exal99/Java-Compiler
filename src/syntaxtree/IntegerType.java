package syntaxtree;

public class IntegerType extends Type {

	public IntegerType(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
