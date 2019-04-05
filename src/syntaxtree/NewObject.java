package syntaxtree;

public class NewObject extends Expression {
	
	public Identifier objName;
	
	public NewObject(int pos, Identifier objName) {
		super(pos);
		this.objName = objName;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
