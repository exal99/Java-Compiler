package syntaxtree;

public class NewObject extends Expression {
	
	private Identifier objName;
	
	public NewObject(int pos, Identifier objName) {
		super(pos);
		this.objName = objName;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
