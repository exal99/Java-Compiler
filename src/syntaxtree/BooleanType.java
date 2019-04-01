package syntaxtree;

public class BooleanType extends Type {

	public BooleanType(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
