package syntaxtree;

public abstract class Type extends AbstractSyntax {

	protected Type(int pos) {
		super(pos);
	}
	
	@Override
	abstract public String toString();
	
	@Override
	abstract public boolean equals(Object other);
}
