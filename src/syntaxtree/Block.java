package syntaxtree;

public class Block extends Statement {
	
	public StatementList statements;
	
	public Block(int pos, StatementList statements) {
		super(pos);
		this.statements = statements;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}
